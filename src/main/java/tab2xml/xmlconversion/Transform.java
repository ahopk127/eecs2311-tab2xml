package tab2xml.xmlconversion;

import org.w3c.dom.Document;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;

import tab2xml.Main;
import tab2xml.model.Instrument;
import tab2xml.model.Score;
import tab2xml.model.LineItem;
import tab2xml.model.guitar.GuitarNote;
import tab2xml.model.guitar.GuitarStaff;

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
	private Instrument instrument;
	
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
		this.instrument = instrument;
		switch (instrument) {
		case GUITAR:
		case BASS:
			generateGuitarBass();
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
	 * Generate XML from data for selected instrument, Guitar or Bass.
	 * 
	 */
	private void generateGuitarBass() {
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

		GuitarStaff staff = sheet.getStaffs().get(0);
		
		// if not selected by user set to default
		staff.setBeats("4");
		staff.setBeatType("4");
		setStaffDefaultsGuitarBass(staff, measures.get(0));

		for (GuitarStaff st : sheet.getStaffs()) {
			for (LineItem item : st) {
				GuitarNote note = (GuitarNote) item;
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
	private void addNoteToMeasure(GuitarNote currNote, int currMeasure, ArrayList<XMLElement> measures) {
		if (measures.get(currMeasure) != null) {
			XMLElement note = new XMLElement("note", musicSheet);

			if (currNote.isChord()) {
				XMLElement chord = new XMLElement("chord", musicSheet);
				note.append(chord);
			}

			if (currNote.isGrace()) {
				XMLElement grace = new XMLElement("grace", musicSheet);
				note.append(grace);
			}

			XMLElement pitch = new XMLElement("pitch", musicSheet);
			XMLElement step = new XMLElement("step", musicSheet);
			XMLElement alter = null;

			if (currNote.getStep().contains("#")) {
				step.setText(currNote.getStep().substring(0, 1));
				alter = new XMLElement("alter", musicSheet);
				alter.setText("1");
			} else
				step.setText(currNote.getStep());

			XMLElement octave = new XMLElement("octave", musicSheet);
			octave.setText(currNote.getOctave());
			pitch.append(step, alter, octave);

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
			} else if (currNote.isHarmonic()) {
				XMLElement harmonic = new XMLElement("harmonic", musicSheet);
				XMLElement artificial = new XMLElement("artificial", musicSheet);
				XMLElement basePitch = new XMLElement("basePitch", musicSheet);
				harmonic.append(artificial, basePitch);
				technical.append(harmonic);
			}

			XMLElement string = new XMLElement("string", musicSheet);
			string.setText(currNote.getString());
			XMLElement fret = new XMLElement("fret", musicSheet);
			fret.setText(currNote.getFret());
			technical.append(string, fret);
			notations.append(technical);
			note.append(pitch, duration, voice, type, notations);

			if (currNote.isRepeatedStart()) {
				XMLElement barline = new XMLElement("barline", musicSheet);
				barline.setAttribute("location", "left");
				XMLElement barStyle = new XMLElement("bar-style", musicSheet);
				barStyle.setText("heavy-light");
				XMLElement repeat = new XMLElement("repeat", musicSheet);
				repeat.setAttribute("direction", "forward");
				barline.append(barStyle, repeat);

				XMLElement direction = new XMLElement("direction", musicSheet);
				direction.setAttribute("placement", "above");
				XMLElement directionType = new XMLElement("direction-type", musicSheet);
				XMLElement words = new XMLElement("words", musicSheet);
				words.setAttribute("relative-x", "256.17");
				words.setAttribute("relative-y", "16.01");
				words.setText(String.format("Repeat %d times", currNote.getRepeatCount()));
				directionType.append(words);
				direction.append(directionType);

				measures.get(currMeasure).append(barline, direction);

			}

			measures.get(currMeasure).append(note);

			if (currNote.isRepeatedStop()) {
				XMLElement barline = new XMLElement("barline", musicSheet);
				barline.setAttribute("location", "right");
				XMLElement barStyle = new XMLElement("bar-style", musicSheet);
				barStyle.setText("light-heavy");
				XMLElement repeat = new XMLElement("repeat", musicSheet);
				repeat.setAttribute("direction", "backward");
				barline.append(barStyle, repeat);
				measures.get(currMeasure).append(barline);
			}

			if (currNote.isDoubleBar()) {
				XMLElement barline = new XMLElement("barline", musicSheet);
				barline.setAttribute("location", "right");
				XMLElement barStyle = new XMLElement("bar-style", musicSheet);
				barStyle.setText("light-heavy");
				barline.append(barStyle);
				measures.get(currMeasure).append(barline);
			}
		}
	}

	/**
	 * @param note      the note to add a slur to
	 * @param type      the type of slur
	 * @param placement the placement of the slur
	 * @return the slur XML element
	 */
	private XMLElement slur(GuitarNote note, String type, String placement) {
		XMLElement slur = new XMLElement("slur", musicSheet);
		slur.setAttribute("number", Integer.toString(note.getLineNum()));
		slur.setAttribute("type", type);
		slur.setAttribute("placement", placement);
		return slur;
	}

	/**
	 * @param item the item to set attributes for
	 * @param note the note to add the item to
	 * @param type the type of item
	 * @param text the text content
	 */
	private void setNotationAttr(XMLElement item, GuitarNote note, String type, String text) {
		item.setAttribute("number", Integer.toString(note.getLineNum()));
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
		XMLElement work = new XMLElement("work", musicSheet);
		XMLElement workTitle = new XMLElement("work-title", musicSheet);
		work.append(workTitle);
		workTitle.setText("TAB2XML " + Main.PROGRAM_VERSION + " - Group 2");
		XMLElement partList = new XMLElement("part-list", musicSheet);
		XMLElement scorePart = new XMLElement("score-part", musicSheet);
		scorePart.setAttribute("id", "P1");
		XMLElement partName = new XMLElement("part-name", musicSheet);
		partName.setText(instrument.getName());
		
		switch (instrument) {
		case GUITAR:
		case BASS:
			scorePart.append(partName);
			break;
		case DRUM:
			int p = 0;
			for (int i = 36; i <= 65; i++) {
				if (i == 40)
					continue;
				XMLElement scoreInstrument = new XMLElement("score-instrument-name", musicSheet);
				scoreInstrument.setAttribute("id", String.format("P1-I%d", i));
				XMLElement instrumentName = new XMLElement("instrument-name", musicSheet);
				instrumentName.setText(Instrument.drumSet[p++][0]);
				scoreInstrument.append(instrumentName);
				partList.append(scoreInstrument);
			}
			break;
		default:
			break;
		}
		partList.append(scorePart);
		root.append(work, partList);
	}

	/**
	 * Set the default staff attributes if the Guitar is in standard tuning.
	 * 
	 * @param staff   the first staff of the score
	 * @param measure first measure to append the staff attributes to
	 */
	private void setStaffDefaultsGuitarBass(GuitarStaff staff, XMLElement measure) {
		XMLElement attributes = new XMLElement("attributes", musicSheet);
		XMLElement divisions = new XMLElement("divisions", musicSheet);
		divisions.setText("2");

		XMLElement key = new XMLElement("key", musicSheet);
		XMLElement fifths = new XMLElement("fifths", musicSheet);
		fifths.setText("0");
		key.append(fifths);

		XMLElement time = new XMLElement("time", musicSheet);
		XMLElement beats = new XMLElement("beats", musicSheet);
		beats.setText(staff.getBeats());
		XMLElement beatType = new XMLElement("beat-type", musicSheet);
		beatType.setText(staff.getBeatType());
		time.append(beats, beatType);

		XMLElement clef = new XMLElement("clef", musicSheet);
		XMLElement sign = new XMLElement("sign", musicSheet);
		sign.setText("TAB");
		XMLElement line = new XMLElement("line", musicSheet);
		line.setText("5");
		clef.append(sign, line);

		measure.append(attributes);
	}

	/**
	 * Set the default staff attributes if the Guitar is in standard tuning.
	 * 
	 * @param staff   the first staff of the score
	 * @param measure first measure to append the staff attributes to
	 */
	private void setDrumStaffAttributes(GuitarStaff staff, XMLElement measure) {
		XMLElement attributes = new XMLElement("attributes", musicSheet);
		XMLElement divisions = new XMLElement("divisions", musicSheet);
		divisions.setText("4");

		XMLElement key = new XMLElement("key", musicSheet);
		XMLElement fifths = new XMLElement("fifths", musicSheet);
		fifths.setText("0");
		key.append(fifths);

		XMLElement time = new XMLElement("time", musicSheet);
		XMLElement beats = new XMLElement("beats", musicSheet);
		beats.setText(staff.getBeats());
		XMLElement beatType = new XMLElement("beat-type", musicSheet);
		beatType.setText(staff.getBeatType());
		time.append(beats, beatType);

		XMLElement clef = new XMLElement("clef", musicSheet);
		XMLElement sign = new XMLElement("sign", musicSheet);
		sign.setText("percussion");
		XMLElement line = new XMLElement("line", musicSheet);
		line.setText("2");
		clef.append(sign, line);

		measure.append(attributes);
	}
	/**
	 * Generate XML from score for selected instrument, Drum.
	 * 
	 */
	private void generateDrum() {
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
		
		GuitarStaff staff = sheet.getStaffs().get(0);
		// if not selected by user set to default
		staff.setBeats("4");
		staff.setBeatType("4");
		setStaffDefaultsGuitarBass(staff, measures.get(0));
		
	}
}
