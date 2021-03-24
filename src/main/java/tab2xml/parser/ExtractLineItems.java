package tab2xml.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import tab2xml.antlr.DrumTabBaseVisitor;
import tab2xml.antlr.DrumTabParser.CymbalContext;
import tab2xml.antlr.DrumTabParser.DrumContext;
import tab2xml.antlr.DrumTabParser.DrumTypeContext;
import tab2xml.antlr.DrumTabParser.LineContext;
import tab2xml.antlr.DrumTabParser.LineItemsContext;
import tab2xml.model.LineItem;
import tab2xml.model.LineItemsCollector;
import tab2xml.model.drum.DrumLine;
import tab2xml.model.drum.DrumType;

public class ExtractLineItems extends DrumTabBaseVisitor<LineItem> {
	private DrumLine line;
	private final static double EPSILON = 0.001;

	public ExtractLineItems(DrumLine line, LineContext lc) {
		this.line = line;
		List<LineItem> visited = new ArrayList<>();

		DrumType type = new DrumType();
		for (LineItem item : visited) {
			//TODO: collectLineItms 
		}
	}

	@Override
	public LineItem visitDrumType(DrumTypeContext ctx) {
		String value = ctx.getChild(0).getText();
		DrumType type = new DrumType(value);
		return type;
	}

	@Override
	public LineItem visitLineItems(LineItemsContext ctx) {
		LineItemsCollector coll = new LineItemsCollector(new ArrayList<LineItem>());
		int numMeasures = (int) ctx.children.stream().filter(c -> c.getClass() == TerminalNodeImpl.class
				&& Pattern.matches("\\*?(\\||\\d+)\\||\\|\\*?", c.getText())).count();
		line.setNumMeasures(numMeasures);
		ctx.children.stream().forEach(c -> coll.add(visit(c)));
		return coll;
	}

	@Override
	public LineItem visitCymbal(CymbalContext ctx) {
		// TODO logic of cymbals
		return super.visitCymbal(ctx);
	}

	@Override
	public LineItem visitDrum(DrumContext ctx) {
		// TODO logic of drums 
		return super.visitDrum(ctx);
	}
}
