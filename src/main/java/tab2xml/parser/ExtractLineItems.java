package tab2xml.parser;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import tab2xml.antlr.DrumTabBaseVisitor;
import tab2xml.antlr.DrumTabParser.CymbalContext;
import tab2xml.antlr.DrumTabParser.DrumContext;
import tab2xml.antlr.DrumTabParser.DrumTypeContext;
import tab2xml.antlr.DrumTabParser.LineContext;
import tab2xml.antlr.DrumTabParser.LineItemsContext;
import tab2xml.antlr.GuitarTabParser.StringItemsContext;
import tab2xml.model.ErrorToken;
import tab2xml.model.LineItem;
import tab2xml.model.LineItemsCollector;
import tab2xml.model.drum.DrumLine;
import tab2xml.model.drum.DrumNote;
import tab2xml.model.drum.DrumType;
import tab2xml.model.guitar.Bar;
import tab2xml.model.guitar.Tune;

public class ExtractLineItems extends DrumTabBaseVisitor<LineItem> {
	private DrumLine line;
	private final ArrayList<ErrorToken> semanticErrors;

	public ExtractLineItems(DrumLine line, LineContext lc) {
		this.line = line;
		List<LineItem> visited = new ArrayList<>();
		semanticErrors = new ArrayList<>();

		int temp = 0;
		while (lc.getChild(temp).getClass() != DrumTypeContext.class)
			temp++;
		Tune tune = (Tune) visit(lc.getChild(temp));
		line.add(tune);

		while (lc.getChild(++temp).getClass() != StringItemsContext.class)
			temp++;
		visited.add(visit(lc.getChild(temp)));

		for (LineItem item : visited) {
			if (item.getClass() == Tune.class)
				line.add(item);
			else if (item.getClass() == LineItemsCollector.class) {
				LineItemsCollector coll = (LineItemsCollector) item;
				line.addAll(coll.getStringItems());
			}
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
		int numMeasures = (int) ctx.children.stream()
				.filter(c -> c.getClass() == TerminalNodeImpl.class && c.getText().matches(Bar.pattern())).count();
		line.setNumMeasures(numMeasures);
		ctx.children.stream().forEach(c -> coll.add(visit(c)));
		return coll;
	}

	@Override
	public LineItem visitCymbal(CymbalContext ctx) {
		// TODO logic of cymbals


		// each note has:
		//		  <note>
		//	        <unpitched>
		//	          <display-step>A</display-step>
		//	          <display-octave>5</display-octave>
		//	          </unpitched>
		//	        <duration>2</duration>
		//	        <instrument id="P1-I50"/>
		//	        <voice>1</voice>
		//	        <type>eighth</type>
		//	        <stem>up</stem>
		//	        <notehead>x</notehead>
		//	        <beam number="1">begin</beam>
		//	        </note>
		//	      <note>

		String value = ctx.getText();
		int column = ctx.getStart().getCharPositionInLine() + value.length();
		int position = ctx.getStart().getTokenIndex();
		DrumNote note = null;

		if (value.equals("x")) { // strike cymbal or hi-hat
			for (int ID : DrumType.cymbalIDs) { // cymbals 
				if (line.drumtype().getDrumType().matches(DrumType.drumSet.get(ID).get(0)))
					note = new DrumNote(line.drumtype());
			}
			if (line.drumtype().getDrumType().matches(DrumType.drumSet.get(43).get(0))) { // hi-hats
				note = new DrumNote(line.drumtype());
			} else if (line.drumtype().getDrumType().matches(DrumType.drumSet.get(45).get(0))) {
				note = new DrumNote(line.drumtype());
			} else if (line.drumtype().getDrumType().matches(DrumType.drumSet.get(47).get(0))) {
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
					if (line.drumtype().getDrumType().matches(DrumType.drumSet.get(ID).get(0)))
						note = new DrumNote(line.drumtype());
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
		DrumNote note = null;

		if (value.equals("o")) { // strike
			for (int ID : DrumType.drumIDs) {
				if (line.drumtype().getDrumType().matches(DrumType.drumSet.get(ID).get(0)))
					note = new DrumNote(line.drumtype());
			}
			if (note == null) { // add to errors
				
			}

		} else if (value.equals("O")) { // accent
			for (int ID : DrumType.drumIDs) {
				if (line.drumtype().getDrumType().matches(DrumType.drumSet.get(ID).get(0)))
					note = new DrumNote(line.drumtype());
			}
			
			if (note == null) { // add to errors
				
			}

		} else if (value.equals("g")) { // ghost note

		} else if (value.equals("f")) {

		} else if (value.equals("d")) {

		} else if (value.equals("b")) {

		} else if (value.equals("B")) {

		} else if (value.equals("@")) {

		}
		return note;
	}

	@Override
	public LineItem visitTerminal(TerminalNode node) {
		Token token = node.getSymbol();

		if (!token.getText().matches(Bar.pattern()))
			return null;

		String value = token.getText();
		String start = value.substring(0, value.indexOf("|"));
		int column = token.getCharPositionInLine();

		Bar bar = new Bar();
		bar.setLineNum(line.getLineNum());
		bar.setColumn(column);
		bar.setPosition(token.getTokenIndex() + value.length() - 2);
		//bar.setTune(s.getTune()); set drum type 


		if (value.equals("||"))
			bar.setDoubleBar(true);

		return bar;
	}
}
