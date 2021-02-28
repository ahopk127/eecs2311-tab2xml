package tab2xml.parser;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import tab2xml.antlr.GuitarTabBaseVisitor;
import tab2xml.antlr.GuitarTabParser.FretContext;
import tab2xml.antlr.GuitarTabParser.HammerPullContext;
import tab2xml.antlr.GuitarTabParser.HammeronContext;
import tab2xml.antlr.GuitarTabParser.HarmonicContext;
import tab2xml.antlr.GuitarTabParser.PulloffContext;
import tab2xml.antlr.GuitarTabParser.SlideContext;
import tab2xml.antlr.GuitarTabParser.StringContext;
import tab2xml.antlr.GuitarTabParser.StringItemsContext;
import tab2xml.antlr.GuitarTabParser.TuneContext;

import tab2xml.model.*;

/**
 * Extract string contents from parse tree.
 * 
 * @author amir
 *
 */
public class ExtractStringItems extends GuitarTabBaseVisitor<StringItem> {
	private List<StringItem> stringItems;
	private GuitarString s;

	/**
	 * Construct a sample parse tree visitor from a specified {@code GuitarString}
	 * and ({@code StringContext}.
	 * 
	 * @param s  the guitar string model
	 * @param sc the corresponding string context
	 */
	public ExtractStringItems(GuitarString s, StringContext sc) {
		stringItems = new ArrayList<>();
		this.s = s;
		List<StringItem> visited = new ArrayList<>();

		Tune tune = (Tune) visit(sc.getChild(0));
		s.setTune(tune.getTune());
		visited.add(tune);
		visited.add(visit(sc.getChild(1)));

		for (StringItem item : visited) {
			if (item.getClass() == Tune.class)
				stringItems.add(item);
			else if (item.getClass() == StringItemsCollector.class) {
				StringItemsCollector coll = (StringItemsCollector) item;
				stringItems.addAll(coll.getStringItems());
			}
		}

		stringItems = stringItems.stream().filter(i -> i != null).collect(Collectors.toList());
		s.addAllItems(stringItems);
	}

	/**
	 * Return a list of all retrieved data.
	 * 
	 * @return the list of string items extracted
	 */
	public List<StringItem> getStringItems() {
		return stringItems;
	}

	@Override
	public StringItem visitTune(TuneContext ctx) {
		String value = ctx.getChild(0).getText();
		Tune tune;
		if (!value.equals("|"))
			tune = new Tune(value, false);
		else
			tune = new Tune(Tune.standardTuning[(s.getStringNum() - 1) % 6][0], true);
		tune.setStringNum(s.getStringNum());
		return tune;
	}

	@Override
	public StringItem visitStringItems(StringItemsContext ctx) {
		StringItemsCollector coll = new StringItemsCollector(new ArrayList<StringItem>());
		int numMeasures = (int) ctx.children.stream().filter(c -> c.getClass() == TerminalNodeImpl.class).count();
		s.setNumMeasures(numMeasures);
		ctx.children.stream().forEach(c -> coll.add(visit(c)));
		return coll;
	}

	@Override
	public StringItem visitSlide(SlideContext ctx) {
		Note start = (Note) visit(ctx.getChild(0));
		start.setStartSlide(true);
		Note stop = (Note) visit(ctx.getChild(2));
		stop.setStopSlide(true);
		Slide slide = new Slide(start, stop);
		return slide;
	}

	@Override
	public StringItem visitPulloff(PulloffContext ctx) {
		Note start = (Note) visit(ctx.getChild(0));
		start.setStartPull(true);
		Note stop = (Note) visit(ctx.getChild(2));
		stop.setStopPull(true);
		PullOff pullOff = new PullOff(start, stop);
		return pullOff;
	}

	@Override
	public StringItem visitHammeron(HammeronContext ctx) {
		Note start = (Note) visit(ctx.getChild(0));
		start.setStartHammer(true);
		Note stop = (Note) visit(ctx.getChild(2));
		stop.setStopHammer(true);
		HammerOn hammerOn = new HammerOn(start, stop);
		return hammerOn;
	}

	@Override
	public StringItem visitHammerPull(HammerPullContext ctx) {
		List<Note> notes = new ArrayList<>();
		for (int i = 0; i < ctx.getChildCount(); i++) {
			ParseTree child = ctx.getChild(i);
			if (child.getClass() != TerminalNodeImpl.class) {
				Note note = (Note) visit(child);
				notes.add(note);
			}
		}
		Note start = (Note) notes.get(0);
		start.setStartChain(true);
		Note stop = (Note) notes.get(notes.size() - 1);
		stop.setStopChain(true);
		List<Note> subList = notes.subList(1, notes.size() - 1);
		List<Note> middle = new ArrayList<>(subList);
		start.setMiddle(middle);

		HammerPull hammerPull = new HammerPull(start, middle, stop);
		return hammerPull;
	}

	@Override
	public StringItem visitHarmonic(HarmonicContext ctx) {
		Note note = (Note) visit(ctx.getChild(1));
		Harmonic harmonic = new Harmonic(note);
		note.setHarmonic(true);
		return harmonic;
	}

	@Override
	public StringItem visitFret(FretContext ctx) {
		Token token = ctx.FRET_NUM().getSymbol();
		int length = token.getText().length();
		int column = token.getCharPositionInLine() + length;
		String value = ctx.getChild(0).getText();
		Fret fret = new Fret(value, column);
		Note note = new Note(fret, s);
		note.setOctave(Tune.standardTuning[(s.getStringNum() - 1) % 6][1]);
		return note;
	}

	@Override
	public StringItem visitTerminal(TerminalNode node) {
		Token token = node.getSymbol();
		int length = token.getText().length();
		int column = token.getCharPositionInLine() + length;
		Bar bar = new Bar();
		bar.setStringNum(s.getStringNum());
		bar.setPosition(column);
		return bar;
	}
}
