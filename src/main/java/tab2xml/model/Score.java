package tab2xml.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.nio.charset.StandardCharsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import tab2xml.antlr.GuitarTabLexer;
import tab2xml.antlr.GuitarTabParser;
import tab2xml.parser.SerializeScore;
import tab2xml.model.Score;

/**
 * A representation of a score sheet.
 * 
 * @author amir
 *
 */
public class Score {
	private List<Staff> staffs;

	/**
	 * Construct an empty score.
	 * 
	 */
	public Score() {
		this.staffs = new ArrayList<>();
	}

	/**
	 * Add a specified staff, <b>s></b> to this score.
	 * 
	 * @param s the staff to add to this score
	 */
	public void addStaff(Staff s) {
		this.staffs.add(s);
	}

	/**
	 * Return the total number of staffs in this score.
	 * 
	 * @return the number of staffs in this score
	 */
	public int size() {
		return staffs.size();
	}

	/**
	 * Return a list of staffs.
	 * 
	 * @return the list of staffs in this score
	 */
	public List<Staff> getStaffs() {
		return staffs;
	}

	/**
	 * Return the number of measures in this score.
	 * 
	 * @return the number of measures in this score
	 */
	public int numberOfMeasures() {
		int count = 0;
		for (int i = 0; i < staffs.size(); i++) {
			Staff staff = staffs.get(i);
			count += staff.numberOfMeasures();
		}
		return count;
	}

	/**
	 * Reset the number of measures on undo.
	 * 
	 */
	public static void resetMeasureCount() {
		StaffIterator.currMeasure = 0;
	}

	/**
	 * Extract notes from a staff at a specified index.
	 * 
	 * @param index the index to extract notes from
	 * @return a list of notes representing a staff at a specified index
	 */
	public List<LinkedList<StringItem>> staffToNoteList(int index) {
		List<LinkedList<StringItem>> res = new ArrayList<>();

		Staff staff = staffs.get(index);

		for (GuitarString s : staff.getStrings()) {
			List<StringItem> items = s.getItems();
			LinkedList<StringItem> notes = new LinkedList<>();

			for (StringItem item : items) {
				if (item.getClass() == Slide.class) {
					Slide sl = (Slide) item;
					notes.addAll(sl.getNotes());

				} else if (item.getClass() == PullOff.class) {
					PullOff po = (PullOff) item;
					notes.addAll(po.getNotes());

				} else if (item.getClass() == HammerOn.class) {
					HammerOn ho = (HammerOn) item;
					notes.addAll(ho.getNotes());

				} else if (item.getClass() == HammerPull.class) {
					HammerPull hp = (HammerPull) item;
					notes.addAll(hp.getNotes());

				} else if (item.getClass() == Harmonic.class) {
					Harmonic h = (Harmonic) item;
					notes.addAll(h.getNotes());

				} else if (item.getClass() == Note.class) {
					Note note = (Note) item;
					notes.add((StringItem) StringItem.deepClone(note));

				} else if (item.getClass() == Bar.class) {
					Bar bar = (Bar) item;
					notes.add((StringItem) StringItem.deepClone(bar));
				}
			}
			res.add(notes);
		}
		return res;
	}

	/**
	 * Return an array of staffs with the notes representing the score as a string.
	 */
	@Override
	public String toString() {
		int acc = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < staffs.size(); i++) {
			Staff staff = staffs.get(i);
			List<GuitarString> strings = staff.getStrings();
			for (int j = 0; j < strings.size(); j++) {
				for (StringItem item : strings.get(j).getItems()) {
					int count = item.getPosition() - acc;
					acc += count + (item.toString().length() / 2);
					while (--count > 0)
						sb.append("-");

					sb.append(item.toString());
				}
				sb.append("\n");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	/**
	 * Return a custom iterator for traversing a staff at a specified index.
	 * 
	 * @param index the index of the staff to traverse
	 * @return an iterator for a staff at specified <b>index</b>
	 */
	public Iterator<StringItem> staffIterator(int index) {
		return new StaffIterator(this.staffToNoteList(index), staffs.get(index), this.numberOfMeasures());
	}

	// TODO: make it public and a stand alone iterator(to reuse for drums/bass)
	/**
	 * A custom iterator for traversing a staff and its notes.
	 * 
	 * @author amir
	 *
	 */
	private static class StaffIterator implements Iterator<StringItem> {
		private List<LinkedList<StringItem>> notes;
		private static PriorityQueue<StringItem> pq;
		private int x = 0;
		private int y;
		@SuppressWarnings("unused")
		private int barsNotSeen;
		@SuppressWarnings("unused")
		private int totalMeasures;
		private int numStrings;
		private int lengths[];
		private int totalNotesInCurrMeasure;
		private static int currMeasure = 0;
		private boolean collecting;
		private boolean hasMore = true;
		Note previousNote = null;

		/**
		 * Construct a staff iterator.
		 * 
		 * @param notes         the notes representing <b>staff</b>
		 * @param staff         the staff to iterate
		 * @param totalMeasures the total number of measures <b>staff</b>
		 */
		public StaffIterator(List<LinkedList<StringItem>> notes, Staff staff, int totalMeasures) {
			this.notes = notes;
			this.totalMeasures = totalMeasures;
			y = staff.size() - 1;
			barsNotSeen = staff.numberOfMeasures();
			numStrings = staff.size();
			lengths = new int[numStrings];
			Arrays.fill(lengths, -1);
			collecting = true;
			totalNotesInCurrMeasure = setNotesInCurrMeasure(lengths);
			pq = new PriorityQueue<>();
		}

		/**
		 * @return true the specified staff has notes remaining.
		 */
		@Override
		public boolean hasNext() {
			notes.stream().forEach(l -> hasMore = !l.isEmpty());
			return !pq.isEmpty() || hasMore;
		}

		/**
		 * Return the next note within a specified staff.
		 * 
		 */
		@Override
		public StringItem next() {
			// TODO: figure out how to calculate duration and type??
			while ((pq.isEmpty() || collecting) && totalNotesInCurrMeasure != 0) {
				collecting = true;

				if (y > notes.size() - 1)
					y = 0;

				if (lengths[y] == 0) {
					y++;
					continue;
				}

				StringItem item = (StringItem) notes.get(y).get(x);

				if (item.getClass() == Note.class) {
					Note note = (Note) item;
					note.setMeasure(currMeasure);
					pq.add(item);
					notes.get(y).remove(item);
					lengths[y]--;
					totalNotesInCurrMeasure--;
				}
				y++;
			}
			collecting = false;
			y = 0;

			// at this point we have collected the notes in the measure:

			if (totalNotesInCurrMeasure == 0) {
				notes.stream().filter(l -> l.size() > 0).forEach(l -> l.remove(0));
				totalNotesInCurrMeasure = setNotesInCurrMeasure(lengths);
				barsNotSeen--;
				if (barsNotSeen == 0 || totalNotesInCurrMeasure != 0)
					currMeasure++;
			}

			Note note = (Note) pq.poll();

			if (note == null)
				return null;

			if (previousNote != null && note.getPosition() == previousNote.getPosition()) {
				note.setChord(true);
				note.setType(1);
				note.setDuration("1");
			} else {
				note.setChord(false);
				note.setType(1);
				note.setDuration("1");
			}
			previousNote = (Note) StringItem.deepClone(note);

			return note;
		}

		private int setNotesInCurrMeasure(int[] lengths) {
			int len = 0;
			for (int i = notes.size() - 1; i >= 0; i--) {
				LinkedList<StringItem> line = notes.get(i);
				for (int j = 0; j < line.size(); j++) {
					Object obj = line.get(j);
					if (obj.getClass() == Bar.class) {
						lengths[i] = len;
						len = 0;
						break;
					} else if (obj.getClass() == Note.class)
						len++;
				}
			}
			return Arrays.stream(lengths).sum();
		}

		@SuppressWarnings("unused")
		private void printNotes() {
			for (LinkedList<StringItem> line : notes) {
				for (StringItem item : line)
					System.out.print(item.toString() + " ");
				System.out.println();
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		String input = "e|--------2-----|-----3--------|-----------2--|-----------2--|--0-----0-----|\r\n"
				+ "B|--------0-----|--------------|--------------|--1--3--------|-----1-----0--|\r\n"
				+ "G|-----0--------|--------------|--------------|--------------|--------------|\r\n"
				+ "D|--------------|--4-----------|--------------|--0--------2--|-----------4--|\r\n"
				+ "A|-----2--3-----|--------------|--------------|--------------|--3-----3--3--|\r\n"
				+ "E|--------------|--3-----2-----|--3--------3--|--0-----2-----|-----3--3--2--|\r\n" + "\r\n"
				+ "e|-----2--------|--2-----------|-----0--------|-----0-----3--|--0-----------|\r\n"
				+ "B|--0--0--------|-----0--------|-----3--------|--------------|-----------1--|\r\n"
				+ "G|--------4-----|-----------0--|--------------|-----------0--|-----4--------|\r\n"
				+ "D|--------2--4--|--------0-----|--0-----------|-----4--------|--4-----------|\r\n"
				+ "A|-----------0--|--------------|--3--0-----0--|--------------|--------------|\r\n"
				+ "E|--------------|-----0-----2--|-----0--------|--------------|--------3-----|\r\n" + "\r\n"
				+ "e|--0-----------|-----------3--|--------------|--------------|--------0-----|\r\n"
				+ "B|--------------|--3--3-----0--|--------3-----|--3--0--------|--------0--1--|\r\n"
				+ "G|-----0--------|-----2--2-----|-----2--4--2--|--------------|-----2--4-----|\r\n"
				+ "D|--------0--4--|--4-----------|--------------|-----0--------|-----2--------|\r\n"
				+ "A|-----2-----0--|--0-----2--2--|-----------0--|-----3--0--0--|-----2--------|\r\n"
				+ "E|-----0--2--0--|--------------|-----------3--|--2--0--3--0--|-----0--------|\r\n" + "\r\n"
				+ "e|--------------|--0--------2--|-----------2--|--------------|--0-----3--2--|\r\n"
				+ "B|--------------|--------1-----|--1-----------|--0-----3-----|--------------|\r\n"
				+ "G|--4--0--4--2--|--4-----------|--------------|--------------|-----2--------|\r\n"
				+ "D|--0--------0--|-----4--------|--4-----------|-----------2--|--2-----4-----|\r\n"
				+ "A|--------2--2--|-----------2--|-----3--------|--0-----------|--------2-----|\r\n"
				+ "E|--2-----------|--------------|--3--0--------|--------0--0--|-----------3--|\r\n" + "";

		input += "\r\n";
		InputStream stream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
		GuitarTabLexer lexer = null;
		try {
			lexer = new GuitarTabLexer(CharStreams.fromStream(stream, StandardCharsets.UTF_8));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (lexer != null) {

			CommonTokenStream tokens = new CommonTokenStream(lexer);
			GuitarTabParser parser = new GuitarTabParser(tokens);

			ParseTree root = parser.sheet();

			SerializeScore ss = new SerializeScore();
			Score sheet = ss.visit(root);

			System.out.println(sheet.toString());

			for (int i = 0; i < sheet.size(); i++) {
				Iterator<StringItem> itr = sheet.staffIterator(i);

				while (itr.hasNext()) {
					Note note = (Note) itr.next();
					if (note == null)
						continue;
					System.out.print(note.toString() + "(" + +note.getMeasure() + "," + note.getStringNum() + ") ");
				}
				System.out.println();
			}
		}
	}
}
