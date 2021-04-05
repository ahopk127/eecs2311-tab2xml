package tab2xml.parser;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import tab2xml.antlr.DrumTabBaseVisitor;
import tab2xml.antlr.DrumTabParser.CymbalActionsContext;
import tab2xml.antlr.DrumTabParser.CymbalContext;
import tab2xml.antlr.DrumTabParser.CymbalLineContext;
import tab2xml.antlr.DrumTabParser.CymbalTypeContext;
import tab2xml.antlr.DrumTabParser.DrumActionsContext;
import tab2xml.antlr.DrumTabParser.DrumContext;
import tab2xml.antlr.DrumTabParser.DrumLineContext;
import tab2xml.antlr.DrumTabParser.DrumTypeContext;
import tab2xml.model.Bar;
import tab2xml.model.ErrorToken;
import tab2xml.model.LineItem;
import tab2xml.model.LineItemsCollector;
import tab2xml.model.drum.DrumLine;
import tab2xml.model.drum.DrumNote;
import tab2xml.model.drum.DrumType;

public class ExtractLineItems extends DrumTabBaseVisitor<LineItem> {
	private DrumLine line;
	@SuppressWarnings("unused")
	private final ArrayList<ErrorToken> semanticErrors;

	public ExtractLineItems(DrumLine line, DrumLineContext lc) {
		this.line = line;
		List<LineItem> visited = new ArrayList<>();
		semanticErrors = new ArrayList<>();

		int temp = 0;
		while (lc.getChild(temp).getClass() != DrumTypeContext.class)
			temp++;
		DrumType tune = (DrumType) visit(lc.getChild(temp));
		line.add(tune);

		while (lc.getChild(++temp).getClass() != DrumActionsContext.class)
			temp++;
		visited.add(visit(lc.getChild(temp)));

		for (LineItem item : visited) {
			if (item.getClass() == DrumType.class)
				line.add(item);
			else if (item.getClass() == LineItemsCollector.class) {
				LineItemsCollector coll = (LineItemsCollector) item;
				line.addAll(coll.getLineItems());
			}
		}
	}

	public ExtractLineItems(DrumLine line, CymbalLineContext lc) {
		this.line = line;
		List<LineItem> visited = new ArrayList<>();
		semanticErrors = new ArrayList<>();

		int temp = 0;
		while (lc.getChild(temp).getClass() != CymbalTypeContext.class)
			temp++;
		DrumType tune = (DrumType) visit(lc.getChild(temp));
		line.add(tune);

		while (lc.getChild(++temp).getClass() != CymbalActionsContext.class)
			temp++;
		visited.add(visit(lc.getChild(temp)));

		for (LineItem item : visited) {
			if (item.getClass() == DrumType.class)
				line.add(item);
			else if (item.getClass() == LineItemsCollector.class) {
				LineItemsCollector coll = (LineItemsCollector) item;
				line.addAll(coll.getLineItems());
			}
		}
	}

	@Override
	public LineItem visitDrumType(DrumTypeContext ctx) {
		String value = ctx.getChild(0).getText();
		DrumType type = new DrumType(value);
		type.setLineNum(line.getLineNum());
		type.setColumn(ctx.start.getCharPositionInLine());
		type.setPosition(ctx.start.getTokenIndex() + value.length() - 1);
		return type;
	}

	@Override
	public LineItem visitCymbalType(CymbalTypeContext ctx) {
		String value = ctx.getChild(0).getText();
		DrumType type = new DrumType(value);
		type.setLineNum(line.getLineNum());
		type.setColumn(ctx.start.getCharPositionInLine());
		type.setPosition(ctx.start.getTokenIndex() + value.length() - 1);
		return type;
	}

	@Override
	public LineItem visitDrumActions(DrumActionsContext ctx) {
		LineItemsCollector coll = new LineItemsCollector(new ArrayList<LineItem>());
		int numMeasures = (int) ctx.children.stream()
				.filter(c -> c.getClass() == TerminalNodeImpl.class && c.getText().matches(Bar.pattern())).count();
		line.setNumMeasures(numMeasures);
		ctx.children.stream().forEach(c -> coll.add(visit(c)));
		return coll;
	}

	@Override
	public LineItem visitCymbalActions(CymbalActionsContext ctx) {
		LineItemsCollector coll = new LineItemsCollector(new ArrayList<LineItem>());
		int numMeasures = (int) ctx.children.stream()
				.filter(c -> c.getClass() == TerminalNodeImpl.class && c.getText().matches(Bar.pattern())).count();
		line.setNumMeasures(numMeasures);
		ctx.children.stream().forEach(c -> coll.add(visit(c)));
		return coll;
	}

	@Override
	public LineItem visitCymbal(CymbalContext ctx) {
		String value = ctx.getText();
		int column = ctx.getStart().getCharPositionInLine() + value.length() - 1;
		int position = ctx.getStart().getTokenIndex() - 1;
		DrumNote note = null;

		if (value.equals("x")) { // strike cymbal or hi-hat
			for (int ID : DrumType.cymbalIDs) { // cymbals 
				if (line.drumtype().getDrumType().matches(DrumType.drumSet.get(ID).get(0))) {
					note = new DrumNote(line.drumtype());
					break;
				}
			}
			if (line.drumtype().getDrumType().matches(DrumType.drumSet.get(43).get(0))) { // closed hi-hats
				note = new DrumNote(line.drumtype());
			}

			if (note == null) { // add to errors

			}

		} else if (value.matches("X")) { // loose hi-hat or hit crash cymbal
			if (line.drumtype().getDrumType().matches(DrumType.drumSet.get(50).get(0))) {
				note = new DrumNote(line.drumtype());
			} else if (line.drumtype().getDrumType().matches(DrumType.drumSet.get(58).get(0))) {
				note = new DrumNote(line.drumtype());
			} else if (line.drumtype().getDrumType().matches(DrumType.drumSet.get(43).get(0))) {
				note = new DrumNote(line.drumtype());
			} else if (line.drumtype().getDrumType().matches(DrumType.drumSet.get(45).get(0))) {
				note = new DrumNote(line.drumtype());
			} else if (line.drumtype().getDrumType().matches(DrumType.drumSet.get(47).get(0))) {
				note = new DrumNote(line.drumtype());
			}

			if (note == null) { // add to errors

			}

		} else if (value.equals("o")) { // open hi-hat
			if (line.drumtype().getDrumType().matches(DrumType.drumSet.get(47).get(0)))
				note = new DrumNote(line.drumtype());
			else {
				for (int ID : DrumType.drumIDs) {
					if (line.drumtype().getDrumType().matches(DrumType.drumSet.get(ID).get(0))) {
						note = new DrumNote(line.drumtype());
						break;
					}
				}
				if (note == null) { // add to errors

				}
			}

		} else if (value.equals("#")) { // choke cymbal
			if (line.drumtype().getDrumType().matches(DrumType.drumSet.get(43).get(0)))
				note = new DrumNote(line.drumtype());

			if (note == null) { // add to errors

			}

		} else if (value.equals("s")) { // splash cymbal
			if (line.drumtype().getDrumType().matches(DrumType.drumSet.get(56).get(0)))
				note = new DrumNote(line.drumtype());

			if (note == null) { // add to errors

			}

		} else if (value.equals("c")) { // Chinese cymbal
			if (line.drumtype().getDrumType().matches(DrumType.drumSet.get(53).get(0)))
				note = new DrumNote(line.drumtype());
			if (note == null) { // add to errors

			}

		} else if (value.equals("b")) { // ride bell
			if (line.drumtype().getDrumType().matches(DrumType.drumSet.get(54).get(0)))
				note = new DrumNote(line.drumtype());
			if (note == null) { // add to errors

			}

		} else if (value.equals("p")) { // pedal hi-hat
			if (line.drumtype().getDrumType().matches(DrumType.drumSet.get(45).get(0)))
				note = new DrumNote(line.drumtype());
			if (note == null) { // add to errors

			}
		}

		if (note != null) {
			note.setLineNum(line.getLineNum());
			note.setColumn(column);
			note.setPosition(position);
		}
		return note;
	}

	@Override
	public LineItem visitDrum(DrumContext ctx) {
		String value = ctx.getText();
		int column = ctx.getStart().getCharPositionInLine() + value.length() - 1;
		int position = ctx.getStart().getTokenIndex() - 1;
		DrumNote note = null;

		if (value.equals("o")) { // strike
			for (int ID : DrumType.drumIDs) {
				if (line.drumtype().getDrumType().matches(DrumType.drumSet.get(ID).get(0))) {
					note = new DrumNote(line.drumtype());
					break;
				}
			}
			if (note == null) { // add to errors

			}

		} else if (value.equals("O")) { // accent
			for (int ID : DrumType.drumIDs) {
				if (line.drumtype().getDrumType().matches(DrumType.drumSet.get(ID).get(0))) {
					note = new DrumNote(line.drumtype());
					break;
				}
			}

			if (note == null) { // add to errors

			}

		} else if (value.equals("g")) { // ghost note
			for (int ID : DrumType.drumIDs) {
				if (line.drumtype().getDrumType().matches(DrumType.drumSet.get(ID).get(0))) {
					note = new DrumNote(line.drumtype());
					break;
				}
			}
			if (note == null) { // add to errors

			}
		} else if (value.equals("f")) {
			for (int ID : DrumType.drumIDs) {
				if (line.drumtype().getDrumType().matches(DrumType.drumSet.get(ID).get(0))) {
					note = new DrumNote(line.drumtype());
					break;
				}
			}
			if (note == null) { // add to errors

			}
		} else if (value.equals("d")) {
			for (int ID : DrumType.drumIDs) {
				if (line.drumtype().getDrumType().matches(DrumType.drumSet.get(ID).get(0))) {
					note = new DrumNote(line.drumtype());
					break;
				}
			}
			if (note == null) { // add to errors

			}
		} else if (value.equals("b")) {
			for (int ID : DrumType.drumIDs) {
				if (line.drumtype().getDrumType().matches(DrumType.drumSet.get(ID).get(0))) {
					note = new DrumNote(line.drumtype());
					break;
				}
			}
			if (note == null) { // add to errors

			}
		} else if (value.equals("B")) {
			for (int ID : DrumType.drumIDs) {
				if (line.drumtype().getDrumType().matches(DrumType.drumSet.get(ID).get(0))) {
					note = new DrumNote(line.drumtype());
					break;
				}
			}
			if (note == null) { // add to errors

			}
		} else if (value.equals("@")) { // snare rim
			for (int ID : DrumType.drumIDs) {
				if (line.drumtype().getDrumType().matches(DrumType.drumSet.get(ID).get(0))) {
					note = new DrumNote(line.drumtype());
					break;
				}
			}
			if (note == null) { // add to errors

			}
		}

		if (note != null) {
			note.setLineNum(line.getLineNum());
			note.setColumn(column);
			note.setPosition(position);
		}

		return note;
	}

	@Override
	public LineItem visitTerminal(TerminalNode node) {
		Token token = node.getSymbol();

		if (!token.getText().matches(Bar.pattern()))
			return null;

		String value = token.getText();
		//String start = value.substring(0, value.indexOf("|"));
		int column = token.getCharPositionInLine() + value.length() - 1;

		Bar bar = new Bar();
		bar.setDrumType(line.drumtype());
		bar.setLineNum(line.getLineNum());
		bar.setColumn(column);
		bar.setPosition(token.getTokenIndex());
		bar.setRightPos(token.getTokenIndex() + value.length() - 1);
		bar.setLeftPos(token.getTokenIndex());

		if (value.equals("||"))
			bar.setDoubleBar(true);

		return bar;
	}
}
