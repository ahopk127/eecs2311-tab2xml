package tab2xml.parser;

import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import tab2xml.antlr.DrumTabBaseVisitor;
import tab2xml.antlr.DrumTabParser.SheetContext;
import tab2xml.model.drum.Score;
import tab2xml.model.drum.Staff;


/**
 * Serialize a score into a list of staffs.
 * 
 * @author amir
 *
 */
public class SerializeScoreDrum extends DrumTabBaseVisitor<Score> {

	@Override
	public Score visitSheet(SheetContext ctx) {
		Score score = new Score();
		ExtractStaffsDrum visitor = new ExtractStaffsDrum();
		ctx.children.stream().filter(c -> c.getClass() != TerminalNodeImpl.class)
				.forEach(c -> score.addStaff((Staff) visitor.visit(c)));
		return score;
	}
}
