package tab2xml.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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
import tab2xml.model.LineItem;
import tab2xml.model.LineItemsCollector;
import tab2xml.model.drum.DrumLine;
import tab2xml.model.drum.DrumType;
import tab2xml.model.guitar.Bar;
import tab2xml.model.guitar.Tune;

public class ExtractLineItems extends DrumTabBaseVisitor<LineItem> {
	private DrumLine line;
	private final String ins = null; // drum type instrument
	private final static double EPSILON = 0.001;

	public ExtractLineItems(DrumLine line, LineContext lc) {
		this.line = line;
		List<LineItem> visited = new ArrayList<>();

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

		//		CYMBALS the values it can be 
		//		: 'x'	
		//		| 'X'
		//		| 'o' // only hi-hat can be o - open hi-hat
		//		| '#'
		//		| 'c'
		//		| 'b'	
		//		; 

		
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
		
		// if its x
//		if (x) {
//			// drum type: fist uncle.
//			
//			DrumNote note = new DrumNote(Instrument.drumSet[ins][stepIndex], Instrument.drumSet[ins][octaveIndex]);
//			note.setInsturmentID(Instrument.drumSet[13][0]);
////	        <stem>up</stem>
////	        <notehead>x</notehead>
//		}
//
//		if (X) {
//			DrumNote note = new DrumNote(Instrument.drumSet[13][3], Instrument.drumSet[13][4]);
//			note.setInsturmentID(Instrument.drumSet[13][0]);
//		}
//
//		if (o) {
//			DrumNote note = new DrumNote(Instrument.drumSet[13][3], Instrument.drumSet[13][4]);
//			note.setInsturmentID(Instrument.drumSet[13][0]);
//		}
//
//		if (#) {
//			DrumNote note = new DrumNote(Instrument.drumSet[13][3], Instrument.drumSet[13][4]);
//			note.setInsturmentID(Instrument.drumSet[13][0]);
//		}
//
//		if (c) {
//			DrumNote note = new DrumNote(Instrument.drumSet[13][3], Instrument.drumSet[13][4]);
//			note.setInsturmentID(Instrument.drumSet[13][0]);
//		}
//		
//		if (b) {
//			DrumNote note = new DrumNote(Instrument.drumSet[13][3], Instrument.drumSet[13][4]);
//			note.setInsturmentID(Instrument.drumSet[13][0]);
//		}
//		
//		if (o) {
//			DrumNote note = new DrumNote(Instrument.drumSet[13][3], Instrument.drumSet[13][4]);
//			note.setInsturmentID(Instrument.drumSet[13][0]);
//		}

		return super.visitCymbal(ctx);
	}

	@Override
	public LineItem visitDrum(DrumContext ctx) {
		return null;
		// if its x
//		if (x) {
//			// drum type: fist uncle.
//			
//			DrumNote note = new DrumNote(Instrument.drumSet[13][3], Instrument.drumSet[13][4]);
//			note.setInsturmentID(Instrument.drumSet[13][0]);
//		}
//
//		if (X) {
//			DrumNote note = new DrumNote(Instrument.drumSet[13][3], Instrument.drumSet[13][4]);
//			note.setInsturmentID(Instrument.drumSet[13][0]);
//		}
//
//		if (o) {
//			DrumNote note = new DrumNote(Instrument.drumSet[13][3], Instrument.drumSet[13][4]);
//			note.setInsturmentID(Instrument.drumSet[13][0]);
//		}
//
//		if (#) {
//			DrumNote note = new DrumNote(Instrument.drumSet[13][3], Instrument.drumSet[13][4]);
//			note.setInsturmentID(Instrument.drumSet[13][0]);
//		}
//
//		if (c) {
//			DrumNote note = new DrumNote(Instrument.drumSet[13][3], Instrument.drumSet[13][4]);
//			note.setInsturmentID(Instrument.drumSet[13][0]);
//		}
//		
//		if (b) {
//			DrumNote note = new DrumNote(Instrument.drumSet[13][3], Instrument.drumSet[13][4]);
//			note.setInsturmentID(Instrument.drumSet[13][0]);
//		}
//		
//		if (o) {
//			DrumNote note = new DrumNote(Instrument.drumSet[13][3], Instrument.drumSet[13][4]);
//			note.setInsturmentID(Instrument.drumSet[13][0]);
//		}
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

		// end repeat *||
		if (start.equals("*")) {
			bar.setDoubleBar(true);
			bar.setRepeat(true);
			bar.setStop(true);
		}

		// start repeat: ||*
		if (value.charAt(value.length() - 1) == '*') {
			bar.setDoubleBar(true);
			bar.setRepeat(true);
			bar.setStart(true);
		}

		if (value.equals("||"))
			bar.setDoubleBar(true);

		return bar;
	}
	
	private static boolean isNumeric(String s) {
		return Pattern.matches("\\d+", s);
	}
}
