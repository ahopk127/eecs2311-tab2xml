package tab2xml.parser;

import java.util.ArrayList;
import java.util.List;

import tab2xml.antlr.GuitarTabBaseVisitor;
import tab2xml.antlr.GuitarTabParser.SheetContext;
import tab2xml.model.Score;
import tab2xml.model.Staff;

public class SerializeScore extends GuitarTabBaseVisitor<Score> {
	public List<ArrayList<Staff>> data;

	@Override
	public Score visitSheet(SheetContext ctx) {
		Score score = new Score();
		data = new ArrayList<>();
		ExtractStaffs visitor = new ExtractStaffs(data);

		for (int i = 0; i < ctx.getChildCount(); i++) {
			if (i == ctx.getChildCount() - 1)
				continue;
			score.addStaff((Staff) visitor.visit(ctx.getChild(i)));
		}
		return score;
	}
}
