package tab2xml.parser;

import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import tab2xml.antlr.GuitarTabBaseVisitor;
import tab2xml.antlr.GuitarTabParser.SheetContext;
import tab2xml.model.Score;
import tab2xml.model.guitar.Staff;

/**
 * Serialize a score into a list of staffs.
 * 
 * @author amir
 *
 */
public class SerializeGuitarScore extends GuitarTabBaseVisitor<Score> {
	@Override
	public Score visitSheet(SheetContext ctx) {
		Score score = new Score();
		ExtractGuitarStaffs visitor = new ExtractGuitarStaffs();
		ctx.children.stream().filter(c -> c.getClass() != TerminalNodeImpl.class)
				.forEach(c -> score.addStaff((Staff) visitor.visit(c)));
		return score;
	}
}
