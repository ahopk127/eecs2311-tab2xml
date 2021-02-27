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
import tab2xml.model.Score;
import tab2xml.parser.SerializeScore;

public class Score {
	private List<Staff> staffs;

	public Score() {
		this.staffs = new ArrayList<>();
	}

	public void addStaff(Staff s) {
		this.staffs.add(s);
	}

	public int size() {
		return staffs.size();
	}

	public List<Staff> getStaffs() {
		return staffs;
	}

	public int numberOfMeasures() {
		int count = 0;
		for (int i = 0; i < staffs.size(); i++) {
			Staff staff = staffs.get(i);
			count += staff.numberOfMeasures();
		}
		return count;
	}

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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < staffs.size(); i++) {
			Staff staff = staffs.get(i);
			List<GuitarString> strings = staff.getStrings();
			for (int j = 0; j < strings.size(); j++) {
				List<StringItem> stringItems = strings.get(j).getItems();
				stringItems.stream().forEach(item -> sb.append(item.toString() + " "));
				sb.append("\n");
			}
			sb.append("\n\n");
		}
		return sb.toString();
	}

	public Iterator<StringItem> staffIterator(int index) {
		return new StaffIterator(this.staffToNoteList(index), staffs.get(index), this.numberOfMeasures());
	}

	private static class StaffIterator implements Iterator<StringItem> {
		List<LinkedList<StringItem>> notes;

		int x = 0;
		int y;
		int barsNotSeen;
		int totalMeasures;
		int numStrings;
		int lengths[];
		int count = 0;
		@SuppressWarnings("unused")
		int totalNotesInCurrMeasure;
		private static int currMeasure = 0;
		StringItem previousItem = null;

		public StaffIterator(List<LinkedList<StringItem>> notes, Staff staff, int totalMeasures) {
			this.notes = notes;
			y = staff.size() - 1;
			barsNotSeen = staff.numberOfMeasures();
			this.totalMeasures = totalMeasures;
			numStrings = staff.size();
			lengths = new int[numStrings];
			Arrays.fill(lengths, -1);
			totalNotesInCurrMeasure = setNotesPerString(lengths);
		}

		@Override
		public boolean hasNext() {
			if (barsNotSeen == 0 && currMeasure == totalMeasures)
				currMeasure = 0;
			return barsNotSeen != 0;
		}

		@Override
		public StringItem next() {
			PriorityQueue<StringItem> pq = new PriorityQueue<>((a, b) -> a.getPosition() - b.getPosition());
			// TODO: figure out how to calculate duration and type??
			while (y >= 0) {
				if (Arrays.stream(lengths).sum() == 0)
					Arrays.fill(lengths, -1);

				if (lengths[y] == 0) {
					y--;
					continue;
				}

				StringItem item = (StringItem) notes.get(y).get(x);

				if (item.getClass() == Bar.class) {
					if (++count % numStrings == 0) {
						notes.forEach(l -> l.remove(0));
						totalNotesInCurrMeasure = setNotesPerString(lengths);
						barsNotSeen--;
						currMeasure++;
						count = 0;
					}
				} else if (item.getClass() == Note.class) {
					Note note = (Note) item;
					note.setMeasure(currMeasure);

					if (previousItem != null && note.getPosition() == previousItem.getPosition()) {
						note.setChord(true);
						note.setType(1);
						note.setDuration("1");
					} else {
						note.setChord(false);
						note.setType(1);
						note.setDuration("1");
					}

					pq.add(item);
				}
				notes.removeIf(l -> l.isEmpty());
				y--;
			}
			y = notes.size() - 1;

			StringItem item = pq.poll();
			previousItem = (StringItem) StringItem.deepClone(item);

			if (item == null)
				return null;

			int string = item.getStringNum() - 1;
			notes.get(string).remove(item);
			lengths[item.getStringNum() - 1]--;
			pq.clear();
			return item;
		}

		private int setNotesPerString(int[] lengths) {
			int len = 0;
			int currMeasure = 0;
			for (int i = notes.size() - 1; i >= 0; i--) {
				LinkedList<StringItem> line = notes.get(i);
				for (int j = 0; j < line.size(); j++) {
					Object obj = line.get(j);
					if (obj.getClass() == Bar.class) {
						currMeasure++;
					} else if (currMeasure != 1 && obj.getClass() == Note.class)
						len++;
				}
				lengths[i] = len;
				currMeasure = 0;
				len = 0;
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
		String input = "E|--8p5-------5----------------7-9-10-12-|-13---13p12-10----------9h12p9-----------|\r\n"
				+ "B|--------6-------6-------9-10-----------|------10---------10------------11-8------|\r\n"
				+ "G|---------------------------------------|------10---------10-----------------9----|\r\n"
				+ "D|--------7-------7----------------------|--------------------------------------11-|\r\n"
				+ "A|------------8-------7--------0---------|-------------0--------7---------0--------|\r\n"
				+ "D|----0----------------------------------|--0--------------------------------------|\r\n" + "\r\n"
				+ "";

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

			System.out.println("-----------------------");
			System.out.println(sheet.toString());
			System.out.println("-----------------------\n\n");

			for (int i = 0; i < sheet.size(); i++) {
				Iterator<StringItem> itr = sheet.staffIterator(i);

				while (itr.hasNext()) {
					Note note = (Note) itr.next();
					if (note == null)
						continue;
					System.out.println(note.getMeasure());
				}
			}

			SerializeScore ss2 = new SerializeScore();
			Score sheet2 = ss2.visit(root);

			System.out.println("-----------------------");
			System.out.println(sheet2.toString());
			System.out.println("-----------------------\n\n");

			for (int i = 0; i < sheet2.size(); i++) {
				Iterator<StringItem> itr = sheet2.staffIterator(i);

				while (itr.hasNext()) {
					Note note = (Note) itr.next();
					if (note == null)
						continue;
					System.out.println(note.getMeasure());
				}
			}
		}
	}
}
