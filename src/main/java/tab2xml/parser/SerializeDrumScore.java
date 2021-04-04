package tab2xml.parser;

import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import tab2xml.antlr.DrumTabBaseVisitor;
import tab2xml.antlr.DrumTabParser.SheetContext;
import tab2xml.model.Score;
import tab2xml.model.drum.DrumStaff;

public class SerializeDrumScore extends DrumTabBaseVisitor<Score<DrumStaff>> {

	@Override
	public Score<DrumStaff> visitSheet(SheetContext ctx) {
		Score<DrumStaff> score = new Score<>();
		ExtractDrumStaffs visitor = new ExtractDrumStaffs();
		ctx.children.stream().filter(c -> c.getClass() != TerminalNodeImpl.class)
				.forEach(c -> score.addStaff((DrumStaff) visitor.visit(c)));
		return score;
	}
}
