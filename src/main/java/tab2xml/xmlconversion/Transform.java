package tab2xml.xmlconversion;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;

import tab2xml.parser.Instrument;
import tab2xml.model.Note;
import tab2xml.model.Score;
import tab2xml.model.Staff;
import tab2xml.model.StringItem;

/**
 * The transformer which generates the XML output as a string.
 * 
 * @author amir
 */
public class Transform {
	private Score sheet;
	MusicSheet musicSheet;
	private Document doc;
	private DocumentBuilder dBuilder;
	private DocumentBuilderFactory dbFactory;

	/**
	 * Construct a transformer that accepts a specified score and an instrument.
	 * 
	 * @param sheet      the score retrieved by the parser
	 * @param instrument the type of instrument corresponding to this score
	 */
	public Transform(Score sheet, Instrument instrument) {
		this.sheet = sheet;
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
		case BASS:
			generateBass();
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

		int measureCount = sheet.numberOfMeasures();
		ArrayList<XMLElement> measures = new ArrayList<>();
		measures.ensureCapacity(measureCount);

		for (int i = 0; i < measureCount; i++) {
			XMLElement measure = new XMLElement("measure", musicSheet);
			measure.setAttribute("number", Integer.toString(i + 1));
			measures.add(measure);
		}

		Staff staff = sheet.getStaffs().get(0);
		staff.setUpperBeat("4");
		staff.setLowerBeat("4");
		setStaffDefaults(staff, measures.get(0));

		for (int i = 0; i < sheet.size(); i++) {
			Iterator<StringItem> itr = sheet.staffIterator(i);

			while (itr.hasNext()) {
				Note note = (Note) itr.next();
				if (note == null)
					continue;
				addNoteToMeasure(note, note.getMeasure(), measures);
			}
		}

		for (XMLElement measure : measures)
			part1.append(measure);
	}

	/**
	 * Append a note element to its correct measure.
	 * 
	 * @param currNote    the note to be added to specified measure
	 * @param currMeasure the index of the measure to append to
	 * @param measures    the list of all the measures
	 */
	private void addNoteToMeasure(Note currNote, int currMeasure, ArrayList<XMLElement> measures) {
		System.out.println(currMeasure);
		if (measures.get(currMeasure) != null) {
			XMLElement note = new XMLElement("note", musicSheet);

			if (currNote.isChord()) {
				XMLElement chord = new XMLElement("chord", musicSheet);
				note.append(chord);
			}

			XMLElement pitch = new XMLElement("pitch", musicSheet);
			XMLElement step = new XMLElement("step", musicSheet);
			step.setText(currNote.getStep());
			pitch.append(step);

			if (currNote.getStep().contains("#")) {
				step.setText(currNote.getStep().substring(0, 1));
				XMLElement alter = new XMLElement("alter", musicSheet);
				alter.setText("1");
				pitch.append(alter);
			}

			XMLElement octave = new XMLElement("octave", musicSheet);
			octave.setText(currNote.getOctave());
			pitch.append(step, octave);

			XMLElement duration = new XMLElement("duration", musicSheet);
			duration.setText(currNote.getDuration());
			XMLElement voice = new XMLElement("voice", musicSheet);
			voice.setText(currNote.getVoice());
			XMLElement type = new XMLElement("type", musicSheet);
			type.setText(currNote.getType());

			XMLElement notations = new XMLElement("notations", musicSheet);
			XMLElement technical = new XMLElement("technical", musicSheet);

			if (currNote.isStartHammer()) {
				XMLElement hammeron = new XMLElement("hammer-on", musicSheet);
				setNotationAttr(hammeron, currNote, "start", "H");
				technical.append(hammeron);

			} else if (currNote.isStopHammer()) {
				XMLElement hammeron = new XMLElement("hammer-on", musicSheet);
				setNotationAttr(hammeron, currNote, "stop", "");
				technical.append(hammeron);

			} else if (currNote.isStartPull()) {
				System.out.println("created pull off");
				XMLElement pulloff = new XMLElement("pull-off", musicSheet);
				setNotationAttr(pulloff, currNote, "start", "P");
				technical.append(pulloff);

			} else if (currNote.isStopPull()) {
				XMLElement pulloff = new XMLElement("pull-off", musicSheet);
				setNotationAttr(pulloff, currNote, "stop", "");
				technical.append(pulloff);

			} else if (currNote.isStartChain()) {
				notations.append(slur(currNote, "start", "above"));

			} else if (currNote.isStopChain()) {
				notations.append(slur(currNote, "stop", "above"));

			} else if (currNote.isStartSlide()) {
				XMLElement slide = new XMLElement("slide", musicSheet);
				slide.setAttribute("line-type", "solid");
				setNotationAttr(slide, currNote, "start", "S");
				notations.append(slide);

			} else if (currNote.isStopSlide()) {
				XMLElement slide = new XMLElement("slide", musicSheet);
				slide.setAttribute("line-type", "solid");
				setNotationAttr(slide, currNote, "stop", "");
				notations.append(slide);
			}

			XMLElement string = new XMLElement("string", musicSheet);
			string.setText(currNote.getString());
			XMLElement fret = new XMLElement("fret", musicSheet);
			fret.setText(currNote.getFret().getValue());
			technical.append(string, fret);
			notations.append(technical);
			note.append(pitch, duration, voice, type, notations);

			measures.get(currMeasure).append(note);
		}
	}

	private XMLElement slur(Note note, String type, String placement) {
		XMLElement slur = new XMLElement("slur", musicSheet);
		slur.setAttribute("number", Integer.toString(note.getStringNum()));
		slur.setAttribute("type", type);
		slur.setAttribute("placement", placement);
		return slur;
	}

	private void setNotationAttr(XMLElement item, Note note, String type, String text) {
		item.setAttribute("number", Integer.toString(note.getStringNum()));
		item.setAttribute("type", type);
		item.setAttribute("default-y", "-11");
		item.setText(text);
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
		partName.setText("Classical Guitar");

		scorePart.append(partName);
		partList.append(scorePart);

		root.append(partList);
	}

	/**
	 * Set the default staff attributes if the Guitar is in standard tuning.
	 * 
	 * @param staff   the first staff of the score
	 * @param measure first measure to append the staff attributes to
	 */
	private void setStaffDefaults(Staff staff, XMLElement measure) {
		XMLElement attributes = new XMLElement("attributes", musicSheet);

		XMLElement divisions = new XMLElement("divisions", musicSheet);
		divisions.setText("2");

		XMLElement key = new XMLElement("key", musicSheet);
		XMLElement fifths = new XMLElement("fifths", musicSheet);
		fifths.setText("0");
		key.append(fifths);

		XMLElement time = new XMLElement("time", musicSheet);
		XMLElement beats = new XMLElement("beats", musicSheet);
		beats.setText(staff.getUpperBeat());
		XMLElement beatType = new XMLElement("beat-type", musicSheet);
		beatType.setText(staff.getLowerBeat());
		time.append(beats, beatType);

		XMLElement clef = new XMLElement("clef", musicSheet);
		XMLElement sign = new XMLElement("sign", musicSheet);
		sign.setText("TAB");
		XMLElement line = new XMLElement("line", musicSheet);
		line.setText("5");
		clef.append(sign, line);

		XMLElement staffDetails = new XMLElement("staff-details", musicSheet);
		XMLElement staffLines = new XMLElement("staff-lines", musicSheet);
		staffLines.setText(staff.stringCount());
		staffDetails.append(staffLines);
		int numStrings = staff.size();

		for (int i = 0; i < numStrings; i++) {
			XMLElement staffTuning = new XMLElement("staff-tuning", musicSheet);
			staffTuning.setAttribute("line", Integer.toString(i + 1));
			XMLElement tuningStep = new XMLElement("tuning-step", musicSheet);
			tuningStep.setText(Instrument.standardTuningGuitar[i]);
			XMLElement tuningOctave = new XMLElement("tuning-octave", musicSheet);
			tuningOctave.setText(Instrument.tuningOctaveGuitar[i]);
			staffTuning.append(tuningStep, tuningOctave);
			staffDetails.append(staffTuning);
		}
		attributes.append(divisions, key, time, clef, staffDetails);
		measure.append(attributes);
	}

	/**
	 * Generate XML from score for selected instrument, Drum.
	 */
	private void generateDrum() {
		generateSamplePlaceHolder();
	}

	/**
	 * Generate XML from score for selected instrument, Bass.
	 */
	private void generateBass() {
		generateSamplePlaceHolder();
	}

	/* template xml placeholder */
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
}
