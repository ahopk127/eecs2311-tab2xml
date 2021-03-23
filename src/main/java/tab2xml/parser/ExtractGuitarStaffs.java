package tab2xml.parser;

import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import tab2xml.antlr.GuitarTabBaseVisitor;
import tab2xml.antlr.GuitarTabParser.StaffContext;
import tab2xml.antlr.GuitarTabParser.StringContext;

import tab2xml.model.StaffItem;
import tab2xml.model.guitar.GuitarString;
import tab2xml.model.guitar.GuitarStaff;

/**
 * Extract staffs from parse tree.
 * 
 * @author amir
 */
public class ExtractGuitarStaffs extends GuitarTabBaseVisitor<StaffItem> {
	public ExtractGuitarStaffs() {
	}

	@Override
	public StaffItem visitStaff(StaffContext staff) {
		GuitarStaff st = new GuitarStaff();
		staff.children.stream().filter(c -> c.getClass() != TerminalNodeImpl.class)
				.forEach(c -> st.addString((GuitarString) visit(c)));
		if (st.size() == 4 || st.size() == 5)
			st.getStrings().forEach(s -> s.tune().setBass(true));
		return st;
	}

	@Override
	public StaffItem visitString(StringContext ctx) {
		GuitarString string = new GuitarString();
		@SuppressWarnings("unused")
		ExtractStringItems es = new ExtractStringItems(string, ctx);
		return string;
	}
}
