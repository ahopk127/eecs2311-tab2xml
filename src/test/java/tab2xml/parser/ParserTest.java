package tab2xml.parser;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class ParserTest {
	
	@Test
	void testTokenizeGuitar() {
		String sample = "|-----------0-----|-0--------rrrrrrrrrrrrrr-------|\n" + "|---------0---0---|-0---------------|\n"
				+ "|-------1-------1-|-1---------------|\n" + "|-----2-----------|-2---------------|\n"
				+ "|---2-------rrrrrrrrrrrrrrr------|-2---------------|\n" + "|-0--------rrrrrrrrrrrrrrrrrrr-------|-0---------------|\n";
		
		String expected = "|-----------0-----|-0---------------|\n" + "|---------0---0---|-0---------------|\n"
				+ "|-------1-------1-|-1---------------|\n" + "|-----2-----------|-2---------------|\n"
				+ "|---2-------------|-2---------------|\n" + "|-0---------------|-0---------------|\n";
		
		Lexer lx2 = new Lexer(sample, Instrument.GUITAR);
		ArrayList<ArrayList<Token>> tokens = lx2.tokenize();

		StringBuilder sb = new StringBuilder();
		
		for (ArrayList<Token> line: tokens) {
			for (Token t: line) {
				sb.append(t.getData());
			}
			sb.append("\n");
		}
		assertEquals(expected, sb.toString());	
	}

	@Test
	void testTokenizeDrum() {
		Lexer lx = new Lexer("", Instrument.DRUM);
		ArrayList<ArrayList<Token>> tokens = lx.tokenize();
		assertTrue(tokens.size() == 0);
	}
	
	@Test
	void testInvalidTuneException() {
		String sample = "-----------0-----|-0---------------|\n" + "|---------0---0---|-0---------------|\n"
				+ "|-------1-------1-|-1---------------|\n" + "|-----2-----------|-2---------------|\n"
				+ "|---2-------------|-2---------------|\n" + "|-0---------------|-0---------------|";
		
		Parser parser = new Parser(sample, Instrument.GUITAR);
		InvalidInputException thrown = assertThrows(InvalidInputException.class, () -> parser.parse(),
				"String 1 does not have a proper tune.");
		assertTrue(thrown.getMessage().contains("String 1 does not have a proper tune."));
	}
	

}
