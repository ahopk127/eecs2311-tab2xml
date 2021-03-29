package tab2xml.parser;

import tab2xml.antlr.DrumTabBaseVisitor;
import tab2xml.antlr.DrumTabParser.LineContext;
import tab2xml.antlr.DrumTabParser.StaffContext;

import tab2xml.model.ScoreItem;
import tab2xml.model.drum.DrumLine;
import tab2xml.model.drum.DrumStaff;

/**
 * Extract staffs from drum parse tree.
 * 
 * @author amir
 */
public class ExtractDrumStaffs extends DrumTabBaseVisitor<ScoreItem> {
    public ExtractDrumStaffs() {
    }

    @Override
    public ScoreItem visitStaff(StaffContext ctx) {
	DrumStaff st = new DrumStaff();
	return st;
    }

    @Override
    public ScoreItem visitLine(LineContext ctx) {
	DrumLine line = new DrumLine();
	@SuppressWarnings("unused")
	ExtractLineItems es = new ExtractLineItems(line, ctx);
	return line;
    }

}
