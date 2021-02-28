package tab2xml.parser;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.Test;

import tab2xml.antlr.GuitarTabLexer;
import tab2xml.antlr.GuitarTabParser;
import tab2xml.antlr.GuitarTabParser.StringContext;
import tab2xml.antlr.GuitarTabParser.StringItemsContext;
import tab2xml.model.Fret;
import tab2xml.model.GuitarString;
import tab2xml.model.*;

class ParserTest {
	// TODO: create detailed parser test for model.
	@Test
	void testScore() {
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
			
			String[] expected = { "G", "B", "F#", "B", "C", "F#", "G", "G", "F#", "G", "F#", "G", "C", "D", "E", "D",
					"F#", "F#", "E", "E", "C", "C", "G", "E", "C", "G", "B", "F#", "C", "F#", "B", "F#", "B", "B", "E",
					"F#", "A", "F#", "B", "E", "D", "G", "F#", "D", "C", "E", "D", "A", "E", "A", "E", "F#", "G", "G",
					"E", "F#", "B", "G", "C", "E", "G", "B", "E", "D", "F#", "F#", "A", "E", "D", "F#", "A", "D", "A",
					"A", "B", "G", "B", "B", "A", "D", "B", "A", "A", "G", "D", "F#", "B", "D", "C", "E", "A", "G", "A",
					"E", "A", "E", "B", "E", "E", "B", "B", "C", "B", "D", "F#", "G", "B", "B", "A", "D", "B", "E", "B",
					"F#", "C", "F#", "B", "C", "F#", "G", "C", "E", "F#", "B", "A", "D", "E", "E", "E", "E", "E", "A",
					"G", "F#", "B", "F#", "G" };
			
			assertTrue(4 == sheet.size());
		}
		
		
		
	}

	@Test
	void testStaff() {
		String input = "|-----------0-----|-0---------------|\r\n" + "|---------0---0---|-0---------------|\r\n"
				+ "|-------1-------1-|-1---------------|\r\n" + "|-----2-----------|-2---------------|\r\n"
				+ "|---2-------------|-2---------------|\r\n" + "|-0---------------|-0---------------|";
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

			ParseTree root = parser.staff();
			// expected strings, measures, tuning

			//this takes a list of strings (empty at start)
			List<ArrayList<Staff>> stringList = new ArrayList<>();
			ExtractStaffs estaff = new ExtractStaffs(stringList);

			String[][] expectedTuning = { { "E", "4" }, { "B", "3" }, { "G", "3" }, { "D", "3" }, { "A", "2" },
					{ "E", "2" } };

			Staff staff = (Staff) (estaff.visit(root));
			assertEquals(6, staff.size());
			assertEquals(2, staff.numberOfMeasures());
			for (int i = 0; i < stringList.size(); i++) {
				GuitarString gs = (GuitarString) (estaff.visit(root.getChild(i)));
				String tune = gs.getTune();
				assertEquals(expectedTuning[i], tune);

			}

		}

	}

	@Test
	void testStrings() {
		String input = "|-----------0-----|-0---------------|\r\n" + "|---------0---0---|-0---------------|\r\n"
				+ "|-------1-------1-|-1---------------|\r\n" + "|-----2-----------|-2---------------|\r\n"
				+ "|---2-------------|-2---------------|\r\n" + "|-0---------------|-0---------------|";
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

			ParseTree root = parser.staff();
			// expected strings, measures, tuning

			//this takes a list of strings (empty at start)
			List<ArrayList<Staff>> stringList = new ArrayList<>();
			ExtractStaffs estaff = new ExtractStaffs(stringList);
			String[][] expectedTuning = { { "E", "4" }, { "B", "3" }, { "G", "3" }, { "D", "3" }, { "A", "2" },
					{ "E", "2" } };

			for (int i = 0; i < stringList.size(); i++) {
				GuitarString gs = (GuitarString) (estaff.visit(root.getChild(i)));
				String tune = gs.getTune();
				assertEquals(expectedTuning[i], tune);
			}
		}
	}

	@Test
	void testStringItems() {

		String input = "|-----------0-----|-0---------------|\r\n" + "|---------0---0---|-0---------------|\r\n"
				+ "|-------1-------1-|-1---------------|\r\n" + "|-----2-----------|-2---------------|\r\n"
				+ "|---2-------------|-2---------------|\r\n" + "|-0---------------|-0---------------|";
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

			ParseTree root = parser.staff();
			// expected strings, measures, tuning

			//this takes a list of strings (empty at start)
			List<ArrayList<Staff>> stringList = new ArrayList<>();
			ExtractStaffs estaff = new ExtractStaffs(stringList);
			String[][] expectedTuning = { { "E", "4" }, { "B", "3" }, { "G", "3" }, { "D", "3" }, { "A", "2" },
					{ "E", "2" } };

			for (int i = 0; i < stringList.size(); i++) {
				GuitarString gs = (GuitarString) (estaff.visit(root.getChild(i)));
				String tune = gs.getTune();
				assertEquals(expectedTuning[i], tune);
			}
		}
	}

	@Test
	void stringItemCompareTo() {
		List<StringItem> stringItems = new ArrayList<>();
		Fret fret1 = new Fret("0", 6);
		GuitarString guitarString1 = new GuitarString(1);
		guitarString1.setTune("E");
		Note note1 = new Note(fret1, guitarString1);
		Fret fret2 = new Fret("0", 5);
		GuitarString guitarString2 = new GuitarString(2);
		guitarString2.setTune("B");
		Note note2 = new Note(fret2, guitarString2);
		Fret fret3 = new Fret("0", 4);
		GuitarString guitarString3 = new GuitarString(3);
		guitarString3.setTune("G");
		Note note3 = new Note(fret3, guitarString3);
		Fret fret4 = new Fret("0", 3);
		GuitarString guitarString4 = new GuitarString(3);
		guitarString4.setTune("D");
		Note note4 = new Note(fret4, guitarString4);
		Fret fret5 = new Fret("0", 2);
		GuitarString guitarString5 = new GuitarString(4);
		guitarString5.setTune("A");
		Note note5 = new Note(fret5, guitarString5);
		Fret fret6 = new Fret("0", 1);
		GuitarString guitarString6 = new GuitarString(5);
		guitarString6.setTune("E");
		Note note6 = new Note(fret6, guitarString6);
		stringItems.add(note1);
		stringItems.add(note2);
		stringItems.add(note3);
		stringItems.add(note4);
		stringItems.add(note5);
		stringItems.add(note6);
		Collections.sort(stringItems);
		System.out.println(stringItems);
		String[] expected = { "E", "A", "D", "G", "B", "E" };
		assertEquals(Arrays.toString(expected), stringItems.toString());
	}

	@Test
	void testHammerPull() {
		String input = "E|--10h12p10---0-------0--------------------|-----------------------------------|\r\n"
				+ "		B|-------------0-------0-----0-2-3s10-----8-|-8h10p8--7---------------5---------|\r\n"
				+ "		G|---------0--------------------------------|---------------------------6-------|\r\n"
				+ "		D|----------------------------------------9-|-9---------7---------------5-------|\r\n"
				+ "		A|------------------------------------------|-0-----------------0---------------|\r\n"
				+ "		D|-----------------5-------2----------------|-----------------------------------|";
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

			ParseTree root = parser.hampullchain();
			ParseTree root2 = parser.staff();
			root.getChild(0);
			root.getChild(root.getChildCount() - 1);
			List<ArrayList<Staff>> stringList = new ArrayList<>();
			ExtractStaffs estaff = new ExtractStaffs(stringList);
			ParseTree staff = (ParseTree) (estaff.visit(root2));
			staff.getChild(1);
			
			
			for (int i = 0; i < staff.getChildCount(); i++) {
					GuitarString gs = (GuitarString)(estaff.visit(root.getChild(i)));
					ExtractStringItems eItems = new ExtractStringItems(gs, (StringContext) staff.getChild(i));
					staff.getChild(i);
					//StringItemsContext sic = eItems.visit(staff.getChild(i).getChild(1));
					
				
			}
			
		}
 
	}

	@Test
	void testHammerOn() {

	}

	@Test
	void testPullOff() {

	}

	@Test
	void testHarmonic() {
	}

	@Test
	void testFret() {

	}

	@Test
	void testDeepClone() {

	}
}
