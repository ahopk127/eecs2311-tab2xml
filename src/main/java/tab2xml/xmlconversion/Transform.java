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

public class Transform {
	private ArrayList<ArrayList<Object>> data;
	MusicSheet musicSheet;
	private Document doc;
	private DocumentBuilder dBuilder;
	private DocumentBuilderFactory dbFactory;

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

	private void generateGuitar() {
		XMLElement root = new XMLElement("score-partwise", musicSheet);
		XMLElement part1 = setDefaults(root);
		root.append(part1);
		printData();

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

		while (measuresNotSeen != 0) {
			for (int y = data.size() - 1; y >= 0; y--) {
				if (Arrays.stream(lengths).sum() == 0)
					Arrays.fill(lengths, -1);

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
						if (++count % numOfStrings == 0) {
							setNumNotesInMeasure(lengths);
							measuresNotSeen--;
							currMeasure++;
							count = 0;
						}
						data.get(y).remove(obj);
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
			data.removeIf(l -> l.isEmpty());
		}
		for (XMLElement measure : measures)
			part1.append(measure);
	}

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

	private boolean isNote(Object obj) {
		return obj.getClass().equals(Note.class);
	}

	private boolean isToken(Object obj) {
		return obj.getClass().equals(Token.class);
	}

	private void generateDrum() {
		generateSamplePlaceHolder();
	}

	private XMLElement setDefaults(XMLElement root) {
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

		XMLElement part1 = new XMLElement("part", musicSheet);
		part1.setAttribute("id", "P1");

		return part1;
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
				default:
					break;
				}
			}
		}
		return count;
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

	public String toXML() {
		return musicSheet.toXML();
	}

}
