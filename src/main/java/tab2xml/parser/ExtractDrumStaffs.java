package tab2xml.parser;

import tab2xml.antlr.DrumTabBaseVisitor;
import tab2xml.antlr.DrumTabParser.LineContext;
import tab2xml.antlr.DrumTabParser.StaffContext;
import tab2xml.model.ScoreItem;
import tab2xml.model.drum.DrumLine;
import tab2xml.model.drum.DrumNote;
import tab2xml.model.drum.DrumStaff;

/**
 * Extract staffs from drum parse tree.
 * 
 * @author amir
 */
public class ExtractDrumStaffs extends DrumTabBaseVisitor<ScoreItem<DrumNote>> {
	public ExtractDrumStaffs() {}
	
	@Override
	public ScoreItem<DrumNote> visitLine(LineContext ctx) {
		final DrumLine line = new DrumLine();
		@SuppressWarnings("unused")
		final ExtractLineItems es = new ExtractLineItems(line, ctx);
		return line;
	}
	
	@Override
	public ScoreItem<DrumNote> visitStaff(StaffContext ctx) {
		final DrumStaff st = new DrumStaff();
		return st;
	}
	
}
