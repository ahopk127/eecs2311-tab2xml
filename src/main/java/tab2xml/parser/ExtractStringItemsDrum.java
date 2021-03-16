package tab2xml.parser;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import tab2xml.antlr.DrumTabBaseVisitor;
import tab2xml.antlr.DrumTabParser.DrumTypeContext;
import tab2xml.antlr.DrumTabParser.LineContext;
import tab2xml.antlr.DrumTabParser.LineItemsContext;
import tab2xml.model.drum.DrumType;
import tab2xml.model.drum.Line;

import tab2xml.model.guitar.StringItem;
import tab2xml.model.guitar.StringItemsCollector;


public class ExtractStringItemsDrum extends DrumTabBaseVisitor<StringItem>{
	private Line s;
	private final static double EPSILON = 0.001;

	/**
	 * Construct a sample parse tree visitor from a specified {@code GuitarString}
	 * and {@code StringContext}.
	 * 
	 * @param string the guitar string model
	 * @param sc     the corresponding string context
	 */
	public ExtractStringItemsDrum(Line string, LineContext sc) {
		this.s = string;
		List<StringItem> visited = new ArrayList<>();

		DrumType tune = (DrumType) visit(sc.getChild(0));
		string.setDrumType(tune.getDrumType());
		visited.add(tune);
		visited.add(visit(sc.getChild(1)));

		for (StringItem item : visited) {
			if (item.getClass() == DrumType.class)
				string.add(item);
			else if (item.getClass() == StringItemsCollector.class) {
				StringItemsCollector coll = (StringItemsCollector) item;
				string.addAll(coll.getStringItems());
			}
		}
	}
	@Override
	public StringItem visitDrumType(DrumTypeContext ctx) {
		String value = ctx.getChild(0).getText();
		DrumType drumType;
		if (!value.equals("|"))
			drumType = new DrumType(value);
		else 
			drumType = null;
			
		return drumType;
	}
	@Override
	public StringItem visitLineItems(LineItemsContext ctx) {
		StringItemsCollector coll = new StringItemsCollector(new ArrayList<StringItem>());
		int numMeasures = (int) ctx.children.stream().filter(c -> c.getClass() == TerminalNodeImpl.class
				&& Pattern.matches("\\*?(\\||\\d+)\\||\\|\\*?", c.getText())).count();
		s.setNumMeasures(numMeasures);
		ctx.children.stream().forEach(c -> coll.add(visit(c)));
		return coll;
	}
	//TODO literally all the other stuff
}
