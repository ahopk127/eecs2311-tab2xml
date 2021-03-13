package tab2xml.parser;

import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import tab2xml.antlr.GuitarTabBaseVisitor;
import tab2xml.antlr.GuitarTabParser.SheetContext;
import tab2xml.model.guitar.Score;
import tab2xml.model.guitar.Staff;

/**
 * Serialize a score into a list of staffs.
 * 
 * @author amir
 *
 */
public class SerializeScore extends GuitarTabBaseVisitor<Score> {

	@Override
	public Score visitSheet(SheetContext ctx) {
		Score score = new Score();
		ExtractStaffs visitor = new ExtractStaffs();
		ctx.children.stream().filter(c -> c.getClass() != TerminalNodeImpl.class)
				.forEach(c -> score.addStaff((Staff) visitor.visit(c)));
		return score;
	}
}
