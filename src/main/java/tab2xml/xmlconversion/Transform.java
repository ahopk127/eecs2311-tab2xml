package tab2xml.xmlconversion;

import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;

import tab2xml.parser.Instrument;
import tab2xml.parser.Note;
import tab2xml.parser.Token;

/**
 * The transformer which generates the XML output as a string.
 * 
 * @author amir
 */
public class Transform {
	private ArrayList<ArrayList<Object>> data;
	MusicSheet musicSheet;
	private Document doc;
	private DocumentBuilder dBuilder;
	private DocumentBuilderFactory dbFactory;

	/**
	 * Construct a transformer that accepts specified data and an instrument.
	 * 
	 * @param data       the data retrieved by the parser
	 * @param instrument the type of instrument corresponding to this data
	 */
	public Transform(ArrayList<ArrayList<Object>> data, Instrument instrument) {
		this.data = data;
		this.dbFactory = DocumentBuilderFactory.newInstance();

		try {
			this.dBuilder = dbFactory.newDocumentBuilder();
			this.doc = dBuilder.newDocument();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		this.musicSheet = new MusicSheet(doc, dBuilder, dbFactory);

		switch (instrument) {
		case GUITAR:
			generateGuitar();
			break;
		case DRUM:
			generateDrum();
			break;
		default:
			throw new IllegalArgumentException("Unsupported instrument: " + instrument);
		}
	}

	/**
	 * A string of the music sheet in musicXML format.
	 * 
	 * @return a string format of the converted XML
	 */
	public String toXML() {
		return musicSheet.toXML();
	}

	/**
	 * Generate XML from data for selected instrument, Guitar.
	 */
	private void generateGuitar() {
		XMLElement root = new XMLElement("score-partwise", musicSheet);
		setDefaults(root);
		XMLElement part1 = new XMLElement("part", musicSheet);
		part1.setAttribute("id", "P1");
		root.append(part1);

		printData();

		// parse a single staff with x measures
		// TODO: modularize so we can have measures on multiple staffs
		// and potentially each staff will contain n strings.
		int measureCount = countMeaures();
		int currMeasure = 0;
		int numOfStrings = 6;

		ArrayList<XMLElement> measures = new ArrayList<>();
		measures.ensureCapacity(measureCount);

		for (int i = 0; i < measureCount; i++) {
			XMLElement measure = new XMLElement("measure", musicSheet);
			measure.setAttribute("number", Integer.toString(i + 1));
			measures.add(measure);
		}

		int lengths[] = new int[numOfStrings];
		Arrays.fill(lengths, -1);
		int count = 0;
		int measuresNotSeen = measureCount + 1;
		int factor = -1;
		int y = data.size() - 1;
		boolean skipRight = false;

		while (measuresNotSeen != 0) {
			for (; (factor > 0 ? y < data.size() : y >= 0); y += factor) {
				if (lengths[y] == 0)
					continue;

				Object obj = data.get(y).get(0);
				if (isToken(obj)) {
					Token token = (Token) obj;
					switch (token.type()) {
					case NOTE:
						data.get(y).remove(obj);
						break;
					case BAR:
						data.get(y).remove(obj);
						if (++count % numOfStrings == 0) {
							setNumNotesInMeasure(lengths);
							measuresNotSeen--;
							currMeasure++;
							count = 0;
							skipRight = true;
						}
						break;
					default:
						break;
					}
				} else {
					Note note = (Note) obj;
					data.get(y).remove(obj);
					lengths[y]--;
					addNoteToMeasure(note, currMeasure - 1, measures);
				}
			}
			if (skipRight == true) {
				skipRight = false;
				y = data.size() - 1;
			}
			if (y == -1) {
				factor = 1;
				y = 0;
			}

			if (y == data.size()) {
				factor = -1;
				y = data.size() - 1;
			}
			if (Arrays.stream(lengths).sum() == 0)
				Arrays.fill(lengths, -1);

			data.removeIf(l -> l.isEmpty());
		}

		for (XMLElement measure : measures)
			part1.append(measure);
	}

	/**
	 * Generate XML from data for selected instrument, Drum.
	 */
	private void generateDrum() {
		generateSamplePlaceHolder();
	}

	/**
	 * Set the lengths array to the number of notes in each string per measure.
	 * 
	 * @param lengths the array containing the number of notes left in each string
	 */
	private void setNumNotesInMeasure(int[] lengths) {
		int len = 0;
		int currMeasure = 0;
		for (int i = data.size() - 1; i >= 0; i--) {
			ArrayList<Object> line = data.get(i);
			for (int j = 0; j < line.size(); j++) {
				Object obj = line.get(j);
				if (isToken(obj)) {
					Token token = (Token) obj;
					switch (token.type()) {
					case BAR:
						currMeasure++;
						break;
					default:
						break;
					}
				} else if (currMeasure != 1 && isNote(obj))
					len++;
			}
			lengths[i] = len;
			currMeasure = 0;
			len = 0;
		}
	}

	/**
	 * Append a note element to its correct measure.
	 * 
	 * @param currNote    the note to be added to specified measure
	 * @param currMeasure the index of the measure to append to
	 * @param measures    the list of all the measures
	 */
	private void addNoteToMeasure(Note currNote, int currMeasure, ArrayList<XMLElement> measures) {
		if (measures.get(currMeasure) != null) {
			XMLElement note = new XMLElement("note", musicSheet);

			XMLElement pitch = new XMLElement("pitch", musicSheet);
			XMLElement step = new XMLElement("step", musicSheet);
			step.setText(currNote.getData());
			pitch.append(step);

			if (currNote.getData().contains("#")) {
				step.setText(currNote.getData().substring(0, 1));
				XMLElement alter = new XMLElement("alter", musicSheet);
				alter.setText("1");
				pitch.append(alter);
			}

			XMLElement octave = new XMLElement("octave", musicSheet);
			octave.setText(Integer.toString(currNote.getOctave()));
			pitch.append(step, octave);

			XMLElement duration = new XMLElement("duration", musicSheet);
			duration.setText(Integer.toString(currNote.getDuration()));
			XMLElement voice = new XMLElement("voice", musicSheet);
			voice.setText(Integer.toString(currNote.getVoice()));
			XMLElement type = new XMLElement("type", musicSheet);
			type.setText(Integer.toHexString(currNote.getType()));

			XMLElement notations = new XMLElement("notations", musicSheet);
			XMLElement technical = new XMLElement("technical", musicSheet);
			XMLElement string = new XMLElement("string", musicSheet);
			string.setText(Integer.toString(currNote.getString()));
			XMLElement fret = new XMLElement("fret", musicSheet);
			fret.setText(Integer.toString(currNote.getFret()));
			technical.append(string, fret);
			notations.append(technical);
			note.append(pitch, duration, voice, type, notations);

			measures.get(currMeasure).append(note);
		}
	}

	/**
	 * Return whether an object is a note.
	 * 
	 * @param obj the object to test if its a note
	 * @return true if <b>obj</b> is a note
	 */
	private boolean isNote(Object obj) {
		return obj.getClass().equals(Note.class);
	}

	/**
	 * Return whether an object is a token.
	 * 
	 * @param obj the object to test if its a token
	 * @return true if <b>obj</b> is a token
	 */
	private boolean isToken(Object obj) {
		return obj.getClass().equals(Token.class);
	}

	/**
	 * Set the default values that every music sheet has.
	 * 
	 * @param root the root element of the music sheet
	 */
	private void setDefaults(XMLElement root) {
		root.setAttribute("version", "3.1");
		musicSheet.append(root);

		XMLElement partList = new XMLElement("part-list", musicSheet);
		XMLElement scorePart = new XMLElement("score-part", musicSheet);
		scorePart.setAttribute("id", "P1");
		XMLElement partName = new XMLElement("part-name", musicSheet);
		partName.setText("Clasical Guitar");

		scorePart.append(partName);
		partList.append(scorePart);

		root.append(partList);
	}

	/**
	 * Return the number of measures in the music sheet.
	 * 
	 * @return the number of measures in the data
	 */
	private int countMeaures() {
		int count = 0;
		for (ArrayList<Object> line : data) {
			for (int i = 0; i < line.size(); i++) {
				Object obj = line.get(i);
				Token token = (Token) obj;
				switch (token.type()) {
				case BAR:
					if (i != line.size() - 1)
						count++;
					else
						return count;
					break;
				default:
					break;
				}
			}
		}
		return count;
	}

	private void generateSamplePlaceHolder() {
		XMLElement root = new XMLElement("score-partwise", musicSheet);
		musicSheet.append(root);
		int measureCount = 10;
		for (int i = 0; i < measureCount; i++) {
			XMLElement measure = new XMLElement("measure", musicSheet);
			root.append(measure);
			XMLElement note = new XMLElement("note", musicSheet);
			note.setText("sample attributes of note " + i);
			measure.append(note);
		}
	}

	private void printData() {
		StringBuilder sb = new StringBuilder();
		for (ArrayList<Object> line : data) {
			for (Object obj : line) {
				sb.append(obj.toString());
				sb.append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
