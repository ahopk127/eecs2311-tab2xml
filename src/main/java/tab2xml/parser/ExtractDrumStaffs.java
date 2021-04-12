package tab2xml.parser;

import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import tab2xml.antlr.DrumTabBaseVisitor;
import tab2xml.antlr.DrumTabParser.CymbalLineContext;
import tab2xml.antlr.DrumTabParser.DrumLineContext;
import tab2xml.antlr.DrumTabParser.StaffContext;
import tab2xml.model.ScoreItem;
import tab2xml.model.drum.DrumLine;
import tab2xml.model.drum.DrumStaff;

/**
 * Extract staffs from a drum {@code ParseTree}. This visitor class is generalized to the
 * staff scope in the list of rules.
 * 
 * @author amir
 */
public class ExtractDrumStaffs extends DrumTabBaseVisitor<ScoreItem> {
	public ExtractDrumStaffs() {
	}

	@Override
	public ScoreItem visitStaff(StaffContext ctx) {
		DrumStaff st = new DrumStaff();
		ctx.children.stream().filter(c -> c.getClass() != TerminalNodeImpl.class)
				.forEach(c -> st.add((DrumLine) visit(c)));
		return st;
	}

	@Override
	public ScoreItem visitDrumLine(DrumLineContext ctx) {
		DrumLine line = new DrumLine();
		line.setDrum(true);
		@SuppressWarnings("unused")
		ExtractLineItems es = new ExtractLineItems(line, ctx);
		return line;
	}

	@Override
	public ScoreItem visitCymbalLine(CymbalLineContext ctx) {
		DrumLine line = new DrumLine();
		line.setCymbal(true);
		@SuppressWarnings("unused")
		ExtractLineItems es = new ExtractLineItems(line, ctx);
		return line;
	}
}
