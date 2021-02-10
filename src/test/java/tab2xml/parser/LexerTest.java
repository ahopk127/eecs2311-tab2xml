package tab2xml.parser;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.junit.jupiter.api.Test;

class LexerTest {
	/**
	 * @author Edward and Sayed
	 * this tests the constructor method in the Lexer class
	 */
	@Test
	void testLexer() {
		Field[] fields = Lexer.class.getDeclaredFields();
        for (Field f: fields){
            assertTrue("SparseList contains a public field", 
                    !Modifier.isPublic(f.getModifiers()));
        }

        assertTrue ("Number of constructors != 1", 
                Lexer.class.getDeclaredConstructors().length == 1);
	}

	@Test
	void testTokenize() {
		fail("Not yet implemented");
	}

	@Test
	void testTokenizeGuitar() {
		fail("Not yet implemented");
	}

	@Test
	void testTokenizeDrum() {
		fail("Not yet implemented");
	}

}
