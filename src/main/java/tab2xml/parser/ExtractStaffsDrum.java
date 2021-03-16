package tab2xml.parser;

import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import tab2xml.antlr.DrumTabBaseVisitor;
import tab2xml.antlr.DrumTabParser.LineContext;
import tab2xml.antlr.DrumTabParser.StaffContext;
import tab2xml.model.drum.Line;
import tab2xml.model.drum.Staff;
import tab2xml.model.guitar.StaffItem;




	public class ExtractStaffsDrum extends DrumTabBaseVisitor<StaffItem> {
		public ExtractStaffsDrum() {
		}

		@Override
		public StaffItem visitStaff(StaffContext staff) {
			Staff st = new Staff();

			staff.children.stream().filter(c -> c.getClass() != TerminalNodeImpl.class)
					.forEach(c -> st.addString((Line) visit(c)));
			return st;
		}

		@Override
		public StaffItem visitLine(LineContext ctx) {
			Line s = new Line();

			@SuppressWarnings("unused")
			ExtractStringItemsDrum es = new ExtractStringItemsDrum(s, ctx);
			return s;
		}
	}


