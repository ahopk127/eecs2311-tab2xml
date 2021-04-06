package tab2xml.parser;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

import tab2xml.model.Instrument;
import tab2xml.model.Score;
import tab2xml.model.LineItem;
import tab2xml.model.Note;
import tab2xml.model.guitar.GuitarString;
import tab2xml.xmlconversion.ValidateXML;
import tab2xml.xmlconversion.XMLMetadata;
import tab2xml.model.guitar.GuitarNote;
import tab2xml.model.guitar.GuitarStaff;

class ParserTest {
	static final Path TEST_FILES = Path.of("src", "test", "resources");
	static final File MUSICXML_XSD = TEST_FILES.resolve("musicxml.xsd").toFile();
	static final ValidateXML validator = new ValidateXML(MUSICXML_XSD);

	@Test
	void stringItemCompareTo() {
		final int NUM_NOTES = 12;
		final Random RAND = new Random();

		LineItem[] stringItems = new LineItem[NUM_NOTES];
		GuitarString[] strings = new GuitarString[6];

		for (int i = 1; i <= strings.length; i++) {
			strings[i - 1] = new GuitarString(i);
		}

		int index;

		// same string: insertion at random orders
		for (int i = 1; i <= NUM_NOTES; i++) {
			GuitarNote note = new GuitarNote(strings[0], String.valueOf(i));
			note.setColumn(i);

			do {
				index = RAND.nextInt(NUM_NOTES);
			} while (stringItems[index] != null);
			stringItems[index] = note;
		}

		Arrays.sort(stringItems);
		String[] expected1 = { "F", "F#", "G", "G#", "A", "A#", "B", "C", "C#", "D", "D#", "E" };
		assertEquals(Arrays.toString(expected1), Arrays.toString(stringItems));
		Arrays.fill(stringItems, null);

		// different strings: insertion at random orders
		for (int i = 1; i <= NUM_NOTES; i++) {
			GuitarNote note = new GuitarNote(strings[(i - 1) % strings.length], String.valueOf(i));
			note.setColumn(i);

			do {
				index = RAND.nextInt(NUM_NOTES);
			} while (stringItems[index] != null);
			stringItems[index] = note;
		}

		Arrays.sort(stringItems);
		String[] expected2 = { "F", "C#", "A#", "F#", "D", "A#", "B", "G", "E", "C", "G#", "E" };
		assertEquals(Arrays.toString(expected2), Arrays.toString(stringItems));
		Arrays.fill(stringItems, null);
	}

	@Test
	void testConversion_0() {
		final String input;
		final Instrument instrument = Instrument.GUITAR;

		// noteCount, numStrings, numMeasures, ...
		final int[][] exStaffData = { { 14, 6, 2 } };

		// fret, step, ...
		final String[][] exNoteData = { { "0", "E" }, { "2", "B" }, { "2", "E" }, { "1", "G#" }, { "0", "B" },
				{ "0", "E" }, { "0", "B" }, { "1", "G#" }, { "0", "E" }, { "2", "B" }, { "2", "E" }, { "1", "G#" },
				{ "0", "B" }, { "0", "E" } };

		// octaves
		final String[] exOctaves = { "2", "2", "3", "3", "3", "4", "3", "3", "2", "2", "3", "3", "3", "4" };

		final int exTotalNotes = sumColumn(0, exStaffData);

		try {
			final Path TEST_INPUT_FILE = TEST_FILES.resolve("test0.txt");
			input = Files.readString(TEST_INPUT_FILE);
			final Processor processor = new Processor(input, instrument, null);

			// The processor's instrument is set to GUITAR.
			// Because of that, process() will always return Score<GuitarStaff>
			@SuppressWarnings("unchecked")
			final Score<GuitarStaff> score = (Score<GuitarStaff>) processor.process();

			assertEquals(1, score.size());
			assertEquals(2, score.numberOfMeasures());
			assertEquals(exTotalNotes, score.getNoteCount());

			final List<GuitarStaff> staffs = score.getStaffs();

			for (int i = 0; i < staffs.size(); i++) {
				GuitarStaff staff = staffs.get(i);

				assertEquals(exStaffData[i][0], staff.getNoteCount());
				assertEquals(exStaffData[i][1], staff.size());

				int j = 0;
				Iterator<Note> noteItr = staff.noteIterator();
				while (noteItr.hasNext()) {
					LineItem item = noteItr.next();
					if (item == null)
						continue;
					final GuitarNote note = (GuitarNote) item;
//					System.out.println("note.getOctave(): " + note.getOctave() + "--------------------");
//					System.out.println("exOctaves[j]: " + exOctaves[j] + "--------------------");
					assertEquals(exOctaves[j], note.getOctave());
					assertEquals(exNoteData[j][0], note.getFret());
					assertEquals(exNoteData[j][1], note.getStep());
					j++;
				}
			}

			final Parser parser = new Parser(input, instrument, XMLMetadata.fromDefaultTitle());
			final var output = parser.parse();
			@SuppressWarnings("unused")
			final String xml = output.getFirst();
			// assertTrue(validator.validate(xml));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Error: failed to parse tab correctly");
			return;
		}
	}

	@Test
	void testConversion_1() {
		final String input;
		final Instrument instrument = Instrument.GUITAR;

		// noteCount, numStrings, numMeasures, ...
		final int[][] exStaffData = { { 9, 6, 2 }, { 26, 6, 2 } };

		// fret, step, ...
		final String[][][] exNoteData = {
				{ { "0", "E" }, { "5", "G" }, { "3", "A#" }, { "3", "D" }, { "5", "E" }, { "0", "A" }, { "2", "E" },
						{ "2", "A" }, { "2", "C#" } },
				{ { "0", "D" }, { "3", "F" }, { "2", "A" }, { "3", "D" }, { "3", "F" }, { "2", "A" }, { "3", "D" },
						{ "2", "E" }, { "2", "A" }, { "2", "C#" }, { "0", "A" }, { "5", "G" }, { "2", "A" },
						{ "2", "C#" }, { "0", "D" }, { "3", "F" }, { "2", "A" }, { "3", "D" }, { "3", "F" },
						{ "2", "A" }, { "3", "D" }, { "2", "E" }, { "2", "A" }, { "2", "C#" }, { "0", "A" },
						{ "2", "A" } } };

		// octaves
		//4, 3, 3, 4, 4, 2, 3, 3, 4,    2, 3, 3, 4, 3, 3, 4, 3, 3, 4, 2, 3, 3, 4,  2, 3, 3, 4,  3, 3, 4, 3, 3, 4, 2, 3
		final String[][] exOctaves = { {"4", "3", "3", "4", "4", "2", "3", "3", "4"}, {"2", "3", "3", "4", "3", "3", "4",
				"3", "3", "4", "2", "3", "3", "4", "2", "3", "3", "4", "3", "3", "4", "3", "3", "4", "2", "3"} };

		final int exTotalNotes = sumColumn(0, exStaffData);

		try {
			final Path TEST_INPUT_FILE = TEST_FILES.resolve("test1.txt");
			input = Files.readString(TEST_INPUT_FILE);
			final Processor processor = new Processor(input, instrument, null);

			// The processor's instrument is set to GUITAR.
			// Because of that, process() will always return Score<GuitarStaff>
			@SuppressWarnings("unchecked")
			final Score<GuitarStaff> score = (Score<GuitarStaff>) processor.process();

			assertEquals(2, score.size());
			assertEquals(4, score.numberOfMeasures());
			assertEquals(exTotalNotes, score.getNoteCount());

			final List<GuitarStaff> staffs = score.getStaffs();

			for (int i = 0; i < staffs.size(); i++) {
				GuitarStaff staff = staffs.get(i);

				assertEquals(exStaffData[i][0], staff.getNoteCount());
				assertEquals(exStaffData[i][1], staff.size());

				int j = 0;
				Iterator<Note> noteItr = staff.noteIterator();
				while (noteItr.hasNext()) {
					LineItem item = noteItr.next();
					if (item == null)
						continue;
					final GuitarNote note = (GuitarNote) item;
//					System.out.print('"' + note.getOctave() + '"' + "," + " ");
					assertEquals(exOctaves[i][j], note.getOctave());
//					System.out.println("note.getOctave(): " + note.getOctave() + "--------------------");
//					System.out.println("exOctaves[j]: " + exOctaves[i][j] + "--------------------");
					assertEquals(exNoteData[i][j][0], note.getFret());
					assertEquals(exNoteData[i][j][1], note.getStep());
					j++;		
					
				}
				
			}

			final Parser parser = new Parser(input, instrument, XMLMetadata.fromDefaultTitle());
			final var output = parser.parse();
			@SuppressWarnings("unused")
			final String xml = output.getFirst();
			// assertTrue(validator.validate(xml));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Error: failed to parse tab correctly");
			return;
		}
	}

	@Test
	void testConversion_2() {
		final String input;
		final Instrument instrument = Instrument.GUITAR;

		// noteCount, numStrings, numMeasures, ...
		//TODO isnt notecount of the first staff 32?
		final int[][] exStaffData = { { 31, 6, 2 }, { 37, 6, 2 }, { 34, 6, 2 } };

		// fret, step, ...
		
		final String[][][] exNoteData = { { { "0", "D" }, { "8", "A#" }, { "0", "D" }, { "7", "A" }, { "8", "F" },
				{ "10", "A" }, { "7", "A" }, { "7", "D" }, { "7", "E" }, { "8", "G" }, { "6", "F" }, { "0", "A" },
				{ "5", "E" }, { "6", "F" }, { "8", "G" }, { "5", "E" }, { "3", "D" }, { "0", "D" }, { "6", "F" },
				{ "7", "A" }, { "7", "D" }, { "7", "F#" }, { "7", "A" }, { "5", "C" }, { "5", "D" }, { "7", "A" },
				{ "5", "C" }, { "7", "F#" }, { "5", "A" }, { "8", "C" }, { "11", "D#" }, { "10", "D" }, },
				{ { "8", "C" }, { "10", "D" }, { "0", "G" }, { "8", "C" }, { "8", "G" }, { "6", "A#" }, { "3", "A#" },
						{ "5", "A" }, { "5", "E" }, { "3", "G" }, { "0", "A" }, { "6", "A#" }, { "5", "A" },
						{ "10", "D" }, { "9", "C#" }, { "12", "E" }, { "10", "D" }, { "13", "F" }, { "12", "E" },
						{ "0", "A" }, { "15", "G" }, { "12", "E" }, { "10", "D" }, { "9", "C#" }, { "12", "E" },
						{ "10", "D" }, { "6", "A#" }, { "5", "A" }, { "8", "C" }, { "6", "A#" }, { "8", "G" },
						{ "0", "E" }, { "3", "D" }, { "3", "A#" }, { "0", "A" }, { "2", "C#" }, { "2", "A" }, },
				{ { "6", "A#" }, { "8", "C" }, { "6", "A#" }, { "0", "A" }, { "5", "A" }, { "6", "C#" }, { "5", "E" },
						{ "7", "A" }, { "6", "C#" }, { "5", "E" }, { "0", "A" }, { "9", "C#" }, { "6", "A#" },
						{ "8", "G" }, { "3", "D" }, { "0", "E" }, { "3", "A#" }, { "0", "G" }, { "0", "A" },
						{ "2", "E" }, { "2", "A" }, { "2", "C#" }, { "10", "A" }, { "0", "E" }, { "9", "C#" },
						{ "12", "E" }, { "0", "A" }, { "6", "A#" }, { "3", "G" }, { "5", "E" }, { "3", "D" },
						{ "3", "A#" }, { "0", "G" }, { "2", "E" }, }, };

		// octaves
		//2,2,2, 3, 3, 4 , 3 ,4, 3 , 4, 4, 2, 4, 4, 4, 4, 4, 2, 4, 3, 4, 4, 3, 4, 3, 3, 4, 4, 4, 5, 5, 5,
		// 5, 5, 3, 5, 4, 4, 3, 4, 4, 4, 2, 4, 4, 5, 5, 5, 5, 5, 5,2, 5, 5, 5, 5, 5, 5,4, 4, 5, 4, 4, 4, 4, 3, 2, 4,3
		// 4, 5, 4, 2, 4, 4, 4, 3, 4, 4, 2, 5, 4, 4, 4, 4, 3, 3, 2, 3, 3, 4, 4, 4, 5, 5, 2, 4, 4, 4, 4, 3, 3, 3
		final String[][] exOctaves = { {"2", "2", "2", "3", "3", "4", "3", "4", "3", "4", "4", "2", "4", "4", "4", "4",
				"4", "2", "4", "3", "4", "4", "3", "4", "3", "3", "4", "4", "4", "5", "5", "5"},
				{ "5", "5", "3", "5", "4", "4", "3", "4", "4", "4", "2", "4", "4", "5", "5", "5", "5", "5", "5", "2", "5", "5", 
					"5", "5", "5", "5", "4", "4", "5", "4", "4", "4", "4", "3", "2", "4", "3"},
				{"4", "5", "4", "2", "4", "4", "4", "3", "4", "4", "2", "5", "4", "4", "4", "4", "3", "3", "2", 
						"3", "3", "4", "4", "4", "5", "5", "2", "4", "4", "4", "4", "3", "3", "3"} };

		final int exTotalNotes = sumColumn(0, exStaffData);

		try {
			final Path TEST_INPUT_FILE = TEST_FILES.resolve("test2.txt");
			input = Files.readString(TEST_INPUT_FILE);
			final Processor processor = new Processor(input, instrument, null);

			// The processor's instrument is set to GUITAR.
			// Because of that, process() will always return Score<GuitarStaff>
			@SuppressWarnings("unchecked")
			final Score<GuitarStaff> score = (Score<GuitarStaff>) processor.process();

			assertEquals(3, score.size());
			assertEquals(6, score.numberOfMeasures());
			assertEquals(exTotalNotes, score.getNoteCount());

			final List<GuitarStaff> staffs = score.getStaffs();

			for (int i = 0; i < staffs.size(); i++) {
				GuitarStaff staff = staffs.get(i);

				assertEquals(exStaffData[i][0], staff.getNoteCount());
				assertEquals(exStaffData[i][1], staff.size());

				int j = 0;
				Iterator<Note> noteItr = staff.noteIterator();
				while (noteItr.hasNext()) {
					LineItem item = noteItr.next();
					if (item == null)
						continue;
					final GuitarNote note = (GuitarNote) item;
//					System.out.print('"' + note.getOctave() + '"' + "," + " ");
					assertEquals(exOctaves[i][j], note.getOctave());
					assertEquals(exNoteData[i][j][0], note.getFret());
					assertEquals(exNoteData[i][j][1], note.getStep());
					j++;
				}
			}

			final Parser parser = new Parser(input, instrument, XMLMetadata.fromDefaultTitle());
			final var output = parser.parse();
			@SuppressWarnings("unused")
			final String xml = output.getFirst();
			// assertTrue(validator.validate(xml));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Error: failed to parse tab correctly");
			return;
		}
	}

	@Test
	void testConversion_3() {
		final String input;
		final Instrument instrument = Instrument.GUITAR;

		// noteCount, numStrings, numMeasures, ...
		final int[][] exStaffData = { { 15, 6, 2 } };

		// fret, step, ...
		final String[][][] exNoteData = { { { "0", "E" }, { "2", "B" }, { "2", "E" }, { "0", "G" }, { "1", "G#" },
				{ "0", "B" }, { "0", "E" }, { "0", "B" }, { "1", "G#" }, { "0", "E" }, { "2", "B" }, { "2", "E" },
				{ "1", "G#" }, { "0", "B" }, { "0", "E" } } };

		final int exTotalNotes = sumColumn(0, exStaffData);

		try {
			final Path TEST_INPUT_FILE = TEST_FILES.resolve("test3.txt");
			input = Files.readString(TEST_INPUT_FILE);
			final Processor processor = new Processor(input, instrument, null);

			// The processor's instrument is set to GUITAR.
			// Because of that, process() will always return Score<GuitarStaff>
			@SuppressWarnings("unchecked")
			final Score<GuitarStaff> score = (Score<GuitarStaff>) processor.process();

			assertEquals(1, score.size());
			assertEquals(2, score.numberOfMeasures());
			assertEquals(exTotalNotes, score.getNoteCount());

			final List<GuitarStaff> staffs = score.getStaffs();

			for (int i = 0; i < staffs.size(); i++) {
				GuitarStaff staff = staffs.get(i);

				assertEquals(exStaffData[i][0], staff.getNoteCount());
				assertEquals(exStaffData[i][1], staff.size());

				int j = 0;
				Iterator<Note> noteItr = staff.noteIterator();
				while (noteItr.hasNext()) {
					LineItem item = noteItr.next();
					if (item == null)
						continue;
					final GuitarNote note = (GuitarNote) item;
					assertEquals(exNoteData[i][j][0], note.getFret());
					assertEquals(exNoteData[i][j][1], note.getStep());
					j++;
				}
			}

			final Parser parser = new Parser(input, instrument, XMLMetadata.fromDefaultTitle());
			final var output = parser.parse();
			@SuppressWarnings("unused")
			final String xml = output.getFirst();
			// assertTrue(validator.validate(xml));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Error: failed to parse tab correctly");
			return;
		}
	}

	@Test
	void testConversion_4() {
		final String input;
		final Instrument instrument = Instrument.GUITAR;

		// noteCount, numStrings, numMeasures, ...
		final int[][] exStaffData = { { 16, 6, 2 } };

		// fret, step, ...
		final String[][][] exNoteData = { { { "0", "E" }, { "2", "B" }, { "2", "E" }, { "1", "G#" }, { "0", "B" },
				{ "0", "E" }, { "0", "B" }, { "1", "G#" }, { "0", "E" }, { "2", "B" }, { "0", "E" }, { "2", "B" },
				{ "2", "E" }, { "1", "G#" }, { "0", "B" }, { "0", "E" } } };

		final int exTotalNotes = sumColumn(0, exStaffData);

		try {
			final Path TEST_INPUT_FILE = TEST_FILES.resolve("test4.txt");
			input = Files.readString(TEST_INPUT_FILE);
			final Processor processor = new Processor(input, instrument, null);

			// The processor's instrument is set to GUITAR.
			// Because of that, process() will always return Score<GuitarStaff>
			@SuppressWarnings("unchecked")
			final Score<GuitarStaff> score = (Score<GuitarStaff>) processor.process();

			assertEquals(1, score.size());
			assertEquals(2, score.numberOfMeasures());
			assertEquals(exTotalNotes, score.getNoteCount());

			final List<GuitarStaff> staffs = score.getStaffs();

			for (int i = 0; i < staffs.size(); i++) {
				GuitarStaff staff = staffs.get(i);

				assertEquals(exStaffData[i][0], staff.getNoteCount());
				assertEquals(exStaffData[i][1], staff.size());

				int j = 0;
				Iterator<Note> noteItr = staff.noteIterator();
				while (noteItr.hasNext()) {
					LineItem item = noteItr.next();
					if (item == null)
						continue;
					final GuitarNote note = (GuitarNote) item;
//					System.out.println("note.getOctave(): " + note.getOctave() + "--------------------");
					assertEquals(exNoteData[i][j][0], note.getFret());
					assertEquals(exNoteData[i][j][1], note.getStep());
					j++;
				}
			}

			final Parser parser = new Parser(input, instrument, XMLMetadata.fromDefaultTitle());
			final var output = parser.parse();
			@SuppressWarnings("unused")
			final String xml = output.getFirst();
			// assertTrue(validator.validate(xml));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Error: failed to parse tab correctly");
			return;
		}
	}

	private static int sumColumn(int column, int[][] arr) {
		int sum = 0;
		for (int i = 0; i < arr.length; i++)
			sum += arr[i][column];
		return sum;
	}

	/**
	 * @author sayed
	 */
	@Test
	void testConversion_0_Bass() {
		final String input;
		final Instrument instrument = Instrument.BASS;

		// noteCount, numStrings, numMeasures, ...
		final int[][] exStaffData = { { 20, 4, 1 } };

		// fret, step, ...
		final String[][][] exNoteData = { { { "6", "D#" }, { "6", "D#" }, { "6", "D#" }, { "6", "D#" }, { "6", "D#" },
				{ "6", "C#" }, { "6", "C#" }, { "6", "C#" }, { "6", "C#" }, { "6", "C#" }, { "6", "G#" }, { "6", "G#" },
				{ "6", "G#" }, { "6", "G#" }, { "6", "G#" }, { "8", "A#" }, { "8", "A#" }, { "8", "A#" }, { "8", "A#" },
				{ "8", "A#" } } };

		final int exTotalNotes = sumColumn(0, exStaffData);

		try {
			final Path TEST_INPUT_FILE = TEST_FILES.resolve("test0-Bass.txt");
			input = Files.readString(TEST_INPUT_FILE);
			final Processor processor = new Processor(input, instrument, null);

			// The processor's instrument is set to GUITAR.
			// Because of that, process() will always return Score<GuitarStaff>
			@SuppressWarnings("unchecked")
			final Score<GuitarStaff> score = (Score<GuitarStaff>) processor.process();
			// System.out.println("score.size(): " + score.size() + "--------------------");
			// System.out.println("score.numberOfMeasures(): " + score.numberOfMeasures() +
			// "--------------------");
			// System.out.println("score.getNoteCount(): " + score.getNoteCount() +
			// "--------------------");
			assertEquals(1, score.size());
			assertEquals(1, score.numberOfMeasures());
			assertEquals(exTotalNotes, score.getNoteCount());

			final List<GuitarStaff> staffs = score.getStaffs();

			for (int i = 0; i < staffs.size(); i++) {
				GuitarStaff staff = staffs.get(i);

				assertEquals(exStaffData[i][0], staff.getNoteCount());
				assertEquals(exStaffData[i][1], staff.size());

				int j = 0;
				Iterator<Note> noteItr = staff.noteIterator();
				while (noteItr.hasNext()) {
					LineItem item = noteItr.next();
					if (item == null)
						continue;
					final GuitarNote note = (GuitarNote) item;
					assertEquals(exNoteData[i][j][0], note.getFret());
					assertEquals(exNoteData[i][j][1], note.getStep());
					j++;
				}
			}

			final Parser parser = new Parser(input, instrument, XMLMetadata.fromDefaultTitle());
			final var output = parser.parse();
			@SuppressWarnings("unused")
			final String xml = output.getFirst();
			// assertTrue(validator.validate(xml));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Error: failed to parse tab correctly");
			return;
		}
	}

	/**
	 * @author sayed
	 */
	@Test
	void testConversion_1_Bass() {
		final String input;
		final Instrument instrument = Instrument.BASS;

		// noteCount, numStrings, numMeasures, ...
		final int[][] exStaffData = { { 32, 4, 4 } };

		// fret, step, ...
		final String[][][] exNoteData = { { { "3", "C" }, { "3", "C" }, { "3", "C" }, { "3", "C" }, { "3", "C" },
				{ "3", "C" }, { "1", "A#" }, { "3", "G" }, { "0", "A" }, { "0", "A" }, { "0", "A" }, { "0", "A" },
				{ "0", "A" }, { "0", "A" }, { "0", "A" }, { "2", "B" }, { "3", "C" }, { "3", "C" }, { "3", "C" },
				{ "3", "C" }, { "3", "C" }, { "3", "C" }, { "1", "A#" }, { "3", "G" }, { "0", "A" }, { "0", "A" },
				{ "0", "A" }, { "0", "A" }, { "0", "A" }, { "0", "A" }, { "0", "A" }, { "2", "B" } } };

		final int exTotalNotes = sumColumn(0, exStaffData);

		try {
			final Path TEST_INPUT_FILE = TEST_FILES.resolve("test1-Bass.txt");
			input = Files.readString(TEST_INPUT_FILE);
			final Processor processor = new Processor(input, instrument, null);

			// The processor's instrument is set to GUITAR.
			// Because of that, process() will always return Score<GuitarStaff>
			@SuppressWarnings("unchecked")
			final Score<GuitarStaff> score = (Score<GuitarStaff>) processor.process();
			// System.out.println("score.size(): " + score.size() + "--------------------");
			// System.out.println("score.numberOfMeasures(): " + score.numberOfMeasures() +
			// "--------------------");
			// System.out.println("score.getNoteCount(): " + score.getNoteCount() +
			// "--------------------");
			assertEquals(1, score.size());
			assertEquals(4, score.numberOfMeasures());
			assertEquals(exTotalNotes, score.getNoteCount());

			final List<GuitarStaff> staffs = score.getStaffs();

			for (int i = 0; i < staffs.size(); i++) {
				GuitarStaff staff = staffs.get(i);

				assertEquals(exStaffData[i][0], staff.getNoteCount());
				assertEquals(exStaffData[i][1], staff.size());

				int j = 0;
				Iterator<Note> noteItr = staff.noteIterator();
				while (noteItr.hasNext()) {
					LineItem item = noteItr.next();
					if (item == null)
						continue;
					final GuitarNote note = (GuitarNote) item;
					assertEquals(exNoteData[i][j][0], note.getFret());
					assertEquals(exNoteData[i][j][1], note.getStep());
					j++;
				}
			}

			final Parser parser = new Parser(input, instrument, XMLMetadata.fromDefaultTitle());
			final var output = parser.parse();
			@SuppressWarnings("unused")
			final String xml = output.getFirst();
			// assertTrue(validator.validate(xml));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Error: failed to parse tab correctly");
			return;
		}
	}

	/**
	 * @author sayed
	 */
	@Test
	void testConversion_2_Bass() {
		final String input;
		final Instrument instrument = Instrument.BASS;

		// noteCount, numStrings, numMeasures, ...
		final int[][] exStaffData = { { 15, 4, 1 }, { 15, 4, 1 }, { 16, 4, 1 } };

		// fret, step, ...
		final String[][][] exNoteData = {
				{ { "2", "B" }, { "0", "D" }, { "2", "B" }, { "2", "F#" }, { "0", "A" }, { "2", "B" }, { "0", "D" },
						{ "2", "B" }, { "4", "B" }, { "4", "F#" }, { "2", "B" }, { "2", "F#" }, { "0", "A" },
						{ "2", "B" }, { "0", "D" } },
				{ { "4", "F#" }, { "4", "F#" }, { "4", "C#" }, { "2", "F#" }, { "0", "A" }, { "4", "C#" },
						{ "4", "F#" }, { "3", "F" }, { "2", "E" }, { "2", "B" }, { "0", "E" }, { "3", "G" },
						{ "0", "A" }, { "2", "B" }, { "0", "D" } },
				{ { "2", "B" }, { "4", "B" }, { "4", "F#" }, { "2", "B" }, { "2", "F#" }, { "0", "A" }, { "2", "B" },
						{ "0", "D" }, { "2", "B" }, { "4", "B" }, { "4", "F#" }, { "2", "B" }, { "2", "F#" },
						{ "0", "A" }, { "2", "B" }, { "0", "D" } } };

		final int exTotalNotes = sumColumn(0, exStaffData);

		try {
			final Path TEST_INPUT_FILE = TEST_FILES.resolve("test2-Bass.txt");
			input = Files.readString(TEST_INPUT_FILE);
			final Processor processor = new Processor(input, instrument, null);

			// The processor's instrument is set to GUITAR.
			// Because of that, process() will always return Score<GuitarStaff>
			@SuppressWarnings("unchecked")
			final Score<GuitarStaff> score = (Score<GuitarStaff>) processor.process();
			// System.out.println("score.size(): " + score.size() + "--------------------");
			// System.out.println("score.numberOfMeasures(): " + score.numberOfMeasures() +
			// "--------------------");
			// System.out.println("score.getNoteCount(): " + score.getNoteCount() +
			// "--------------------");
			assertEquals(3, score.size());
			assertEquals(3, score.numberOfMeasures());
			assertEquals(exTotalNotes, score.getNoteCount());

			final List<GuitarStaff> staffs = score.getStaffs();

			for (int i = 0; i < staffs.size(); i++) {
				GuitarStaff staff = staffs.get(i);

				assertEquals(exStaffData[i][0], staff.getNoteCount());
				assertEquals(exStaffData[i][1], staff.size());

				int j = 0;
				Iterator<Note> noteItr = staff.noteIterator();
				while (noteItr.hasNext()) {
					LineItem item = noteItr.next();
					if (item == null)
						continue;
					final GuitarNote note = (GuitarNote) item;
					assertEquals(exNoteData[i][j][0], note.getFret());
					assertEquals(exNoteData[i][j][1], note.getStep());
					j++;
				}
			}

			final Parser parser = new Parser(input, instrument, XMLMetadata.fromDefaultTitle());
			final var output = parser.parse();
			@SuppressWarnings("unused")
			final String xml = output.getFirst();
			// assertTrue(validator.validate(xml));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Error: failed to parse tab correctly");
			return;
		}
	}

	/**
	 * @author sayed
	 */
	@Test
	void testConversion_3_Bass() {
		final String input;
		final Instrument instrument = Instrument.BASS;

		// noteCount, numStrings, numMeasures, ...
		final int[][] exStaffData = { { 8, 4, 1 }, { 16, 4, 1 } };

		// fret, step, ...
		final String[][][] exNoteData = {
				{ { "2", "B" }, { "4", "B" }, { "4", "F#" }, { "2", "B" }, { "2", "F#" }, { "0", "A" }, { "2", "B" },
						{ "0", "D" } },
				{ { "4", "F#" }, { "4", "F#" }, { "4", "F#" }, { "4", "C#" }, { "2", "F#" }, { "0", "A" },
						{ "4", "C#" }, { "4", "F#" }, { "3", "F" }, { "2", "E" }, { "2", "B" }, { "0", "E" },
						{ "3", "G" }, { "0", "A" }, { "2", "B" }, { "0", "D" } } };

		final int exTotalNotes = sumColumn(0, exStaffData);

		try {
			final Path TEST_INPUT_FILE = TEST_FILES.resolve("test3-Bass.txt");
			input = Files.readString(TEST_INPUT_FILE);
			final Processor processor = new Processor(input, instrument, null);

			// The processor's instrument is set to GUITAR.
			// Because of that, process() will always return Score<GuitarStaff>
			@SuppressWarnings("unchecked")
			final Score<GuitarStaff> score = (Score<GuitarStaff>) processor.process();
			// System.out.println("score.size(): " + score.size() + "--------------------");
			// System.out.println("score.numberOfMeasures(): " + score.numberOfMeasures() +
			// "--------------------");
			// System.out.println("score.getNoteCount(): " + score.getNoteCount() +
			// "--------------------");
			assertEquals(2, score.size());
			assertEquals(2, score.numberOfMeasures());
			assertEquals(exTotalNotes, score.getNoteCount());

			final List<GuitarStaff> staffs = score.getStaffs();

			for (int i = 0; i < staffs.size(); i++) {
				GuitarStaff staff = staffs.get(i);

				assertEquals(exStaffData[i][0], staff.getNoteCount());
				assertEquals(exStaffData[i][1], staff.size());

				int j = 0;
				Iterator<Note> noteItr = staff.noteIterator();
				while (noteItr.hasNext()) {
					LineItem item = noteItr.next();
					if (item == null)
						continue;
					final GuitarNote note = (GuitarNote) item;
//					System.out.println("note.getOctave(): " + note.getOctave() + "--------------------");
					assertEquals(exNoteData[i][j][0], note.getFret());
					assertEquals(exNoteData[i][j][1], note.getStep());
					j++;
				}
			}

			final Parser parser = new Parser(input, instrument, XMLMetadata.fromDefaultTitle());
			final var output = parser.parse();
			@SuppressWarnings("unused")
			final String xml = output.getFirst();
			// assertTrue(validator.validate(xml));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Error: failed to parse tab correctly");
			return;
		}
	}

	/**
	 * @author sayed
	 */
	@Test
	void testConversion_4_Bass() {
		final String input;
		final Instrument instrument = Instrument.BASS;

		// noteCount, numStrings, numMeasures, ...
		final int[][] exStaffData = { { 16, 4, 1 } };

		// fret, step, ...
		final String[][][] exNoteData = { { { "6", "D#" }, { "6", "D#" }, { "8", "F" }, { "9", "F#" }, { "9", "B" },
				{ "8", "A#" }, { "6", "G#" }, { "1", "F" }, { "6", "G#" }, { "3", "G" }, { "9", "B" }, { "8", "A#" },
				{ "9", "F#" }, { "6", "D#" }, { "6", "C#" }, { "8", "D#" } } };

		final int exTotalNotes = sumColumn(0, exStaffData);

		try {
			final Path TEST_INPUT_FILE = TEST_FILES.resolve("test4-Bass.txt");
			input = Files.readString(TEST_INPUT_FILE);
			final Processor processor = new Processor(input, instrument, null);

			// The processor's instrument is set to GUITAR.
			// Because of that, process() will always return Score<GuitarStaff>
			@SuppressWarnings("unchecked")
			final Score<GuitarStaff> score = (Score<GuitarStaff>) processor.process();
			// System.out.println("score.size(): " + score.size() + "--------------------");
			// System.out.println("score.numberOfMeasures(): " + score.numberOfMeasures() +
			// "--------------------");
			// System.out.println("score.getNoteCount(): " + score.getNoteCount() +
			// "--------------------");
			assertEquals(1, score.size());
			assertEquals(1, score.numberOfMeasures());
			assertEquals(exTotalNotes, score.getNoteCount());

			final List<GuitarStaff> staffs = score.getStaffs();

			for (int i = 0; i < staffs.size(); i++) {
				GuitarStaff staff = staffs.get(i);

				assertEquals(exStaffData[i][0], staff.getNoteCount());
				assertEquals(exStaffData[i][1], staff.size());

				int j = 0;
				Iterator<Note> noteItr = staff.noteIterator();
				while (noteItr.hasNext()) {
					LineItem item = noteItr.next();
					if (item == null)
						continue;
					final GuitarNote note = (GuitarNote) item;
					assertEquals(exNoteData[i][j][0], note.getFret());
					assertEquals(exNoteData[i][j][1], note.getStep());
					j++;
				}
			}

			final Parser parser = new Parser(input, instrument, XMLMetadata.fromDefaultTitle());
			final var output = parser.parse();
			@SuppressWarnings("unused")
			final String xml = output.getFirst();
			// assertTrue(validator.validate(xml));

		} catch (final Exception e) {
			e.printStackTrace();
			fail("Error: failed to parse tab correctly");
			return;
		}
	}
}
