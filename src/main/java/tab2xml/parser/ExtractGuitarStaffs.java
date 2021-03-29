package tab2xml.parser;

import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import tab2xml.antlr.GuitarTabBaseVisitor;
import tab2xml.antlr.GuitarTabParser.StaffContext;
import tab2xml.antlr.GuitarTabParser.StringContext;

import tab2xml.model.ScoreItem;
import tab2xml.model.guitar.GuitarString;
import tab2xml.model.guitar.GuitarNote;
import tab2xml.model.guitar.GuitarStaff;

/**
 * Extract staffs from guitar parse tree.
 * 
 * @author amir
 */
public class ExtractGuitarStaffs extends GuitarTabBaseVisitor<ScoreItem<GuitarNote>> {
	public ExtractGuitarStaffs() {
	}

	@Override
	public ScoreItem<GuitarNote> visitStaff(StaffContext staff) {
		GuitarStaff st = new GuitarStaff();
		staff.children.stream().filter(c -> c.getClass() != TerminalNodeImpl.class)
				.forEach(c -> st.add((GuitarString) visit(c)));
		if (st.size() == 4 || st.size() == 5)
			st.getLines().forEach(s -> s.tune().setBass(true));
		return st;
	}

	@Override
	public ScoreItem<GuitarNote> visitString(StringContext ctx) {
		GuitarString string = new GuitarString();
		@SuppressWarnings("unused")
		ExtractStringItems es = new ExtractStringItems(string, ctx);
		return string;
	}
}
