package tab2xml.parser;

import org.junit.jupiter.api.Test;

import tab2xml.model.NoteType;
import tab2xml.model.guitar.Tune;

public class TestArea {

	@Test
	final void testGuitarOctaves_0() {
		// test guitar Octaves 
		for (int i = 1; i <= 6; i++) {
			Tune tune = new Tune(i);
			System.out.print(tune.getTune() + NoteType.getOctave(tune, 0) + "| ");
			for (int j = 1; j <= 24; j++) {
				System.out.print(NoteType.getOctave(tune, j) + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	@Test
	final void testBassOctaves_0() {
		// test bass Octaves
		for (int i = 1; i <= 4; i++) {
			Tune tune = new Tune(i);
			tune.setBass(true);
			System.out.print(tune.getTune() + NoteType.getOctave(tune, 0) + "| ");
			for (int j = 1; j <= 24; j++) {
				System.out.print(NoteType.getOctave(tune, j) + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
