package tab2xml.parser;

import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import tab2xml.antlr.GuitarTabBaseVisitor;
import tab2xml.antlr.GuitarTabParser.StaffContext;
import tab2xml.antlr.GuitarTabParser.StringContext;

import tab2xml.model.ScoreItem;
import tab2xml.model.guitar.GuitarString;
import tab2xml.model.guitar.GuitarStaff;

/**
 * Extract staffs from a guitar {@code ParseTree}. This visitor class is
 * generalized to the staff scope in the list of rules.
 * 
 * @author amir
 */
public class ExtractGuitarStaffs extends GuitarTabBaseVisitor<ScoreItem> {
	public ExtractGuitarStaffs() {
	}

	@Override
	public ScoreItem visitStaff(StaffContext ctx) {
		GuitarStaff st = new GuitarStaff();
		ctx.children.stream().filter(c -> c.getClass() != TerminalNodeImpl.class)
				.forEach(c -> st.add((GuitarString) visit(c)));
		if (st.size() == 4 || st.size() == 5)
			st.getLines().forEach(s -> s.tune().setBass(true));
		return st;
	}

	@Override
	public ScoreItem visitString(StringContext ctx) {
		GuitarString string = new GuitarString();
		@SuppressWarnings("unused")
		ExtractStringItems es = new ExtractStringItems(string, ctx);
		return string;
	}
}
