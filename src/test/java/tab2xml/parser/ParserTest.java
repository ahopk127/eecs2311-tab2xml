package tab2xml.parser;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import tab2xml.exceptions.InvalidInputException;
import tab2xml.exceptions.InvalidTokenException;
import tab2xml.model.GToken;

class ParserTest {
	
	@Test
	void testTokenizeGuitar() throws InvalidTokenException {
		String sample = "|-----------0-----|-0--------rrrrrrrrrrrrrr-------|\n" + "|---------0---0---|-0---------------|\n"
				+ "|-------1-------1-|-1---------------|\n" + "|-----2-----------|-2---------------|\n"
				+ "|---2-------rrrrrrrrrrrrrrr------|-2---------------|\n" + "|-0--------rrrrrrrrrrrrrrrrrrr-------|-0---------------|\n";
		
		String expected = "|-----------0-----|-0---------------|\n" + "|---------0---0---|-0---------------|\n"
				+ "|-------1-------1-|-1---------------|\n" + "|-----2-----------|-2---------------|\n"
				+ "|---2-------------|-2---------------|\n" + "|-0---------------|-0---------------|\n";
		
		Lexer lx2 = new Lexer(sample, Instrument.GUITAR);
		ArrayList<ArrayList<GToken>> tokens = lx2.tokenize();

		StringBuilder sb = new StringBuilder();
		
		for (ArrayList<GToken> line: tokens) {
			for (GToken t: line) {
				sb.append(t.getData());
			}
			sb.append("\n");
		}
		assertEquals(expected, sb.toString());	
	}

	@Test
	void testTokenizeDrum() throws InvalidTokenException {
		Lexer lx = new Lexer("", Instrument.DRUM);
		ArrayList<ArrayList<GToken>> tokens = lx.tokenize();
		assertTrue(tokens.size() == 0);
	}
	
	@Test
	void testInvalidTuneException() throws InvalidTokenException {
		String sample = "-----------0-----|-0---------------|\n" + "|---------0---0---|-0---------------|\n"
				+ "|-------1-------1-|-1---------------|\n" + "|-----2-----------|-2---------------|\n"
				+ "|---2-------------|-2---------------|\n" + "|-0---------------|-0---------------|";
		
		Parser parser = new Parser(sample, Instrument.GUITAR);
		InvalidInputException thrown = assertThrows(InvalidInputException.class, () -> parser.parse(),
				"String 1 does not have a proper tune.");
		assertTrue(thrown.getMessage().contains("String 1 does not have a proper tune."));
	}
	

}
