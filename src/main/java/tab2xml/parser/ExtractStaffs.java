package tab2xml.parser;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import tab2xml.antlr.GuitarTabBaseVisitor;
import tab2xml.antlr.GuitarTabParser.StaffContext;
import tab2xml.antlr.GuitarTabParser.StringContext;
import tab2xml.model.guitar.GuitarString;
import tab2xml.model.guitar.Staff;
import tab2xml.model.guitar.StaffItem;

/**
 * Extract staffs from parse tree.
 * 
 * @author amir
 */
public class ExtractStaffs extends GuitarTabBaseVisitor<StaffItem> {
	@SuppressWarnings("unused")
	private List<ArrayList<Staff>> data;

	public ExtractStaffs(List<ArrayList<Staff>> data) {
		this.data = data;
	}

	@Override
	public StaffItem visitStaff(StaffContext staff) {
		Staff st = new Staff();
		staff.children.stream().filter(c -> c.getClass() != TerminalNodeImpl.class)
				.forEach(c -> st.addString((GuitarString) visit(c)));
		return st;
	}

	public StaffItem visitString(StringContext ctx) {
		GuitarString s = new GuitarString();
		@SuppressWarnings("unused")
		ExtractStringItems es = new ExtractStringItems(s, ctx);
		return s;
	}
}
