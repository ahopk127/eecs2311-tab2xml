package tab2xml.parser;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import tab2xml.antlr.GuitarTabBaseVisitor;
import tab2xml.antlr.GuitarTabParser.SheetContext;
import tab2xml.model.Score;
import tab2xml.model.Staff;

/**
 * Serialize a score into a list of staffs.
 * 
 * @author amir
 *
 */
public class SerializeScore extends GuitarTabBaseVisitor<Score> {
	public List<ArrayList<Staff>> data;

	@Override
	public Score visitSheet(SheetContext ctx) {
		Score score = new Score();
		data = new ArrayList<>();
		ExtractStaffs visitor = new ExtractStaffs(data);
		ctx.children.stream().filter(c -> c.getClass() != TerminalNodeImpl.class)
				.forEach(c -> score.addStaff((Staff) visitor.visit(c)));
		return score;
	}
}
