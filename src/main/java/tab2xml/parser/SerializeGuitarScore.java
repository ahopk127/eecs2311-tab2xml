package tab2xml.parser;

import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import tab2xml.antlr.GuitarTabBaseVisitor;
import tab2xml.antlr.GuitarTabParser.SheetContext;
import tab2xml.model.Score;
import tab2xml.model.guitar.GuitarStaff;

/**
 * Serialize a score into a list of staffs.
 * 
 * @author amir
 *
 */
public class SerializeGuitarScore extends GuitarTabBaseVisitor<Score<GuitarStaff>> {
	@Override
	public Score<GuitarStaff> visitSheet(SheetContext ctx) {
		Score<GuitarStaff> score = new Score<>();
		ExtractGuitarStaffs visitor = new ExtractGuitarStaffs();
		ctx.children.stream().filter(c -> c.getClass() != TerminalNodeImpl.class)
				.forEach(c -> score.addStaff((GuitarStaff) visitor.visit(c)));
		return score;
	}
}
