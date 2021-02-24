package tab2xml.parser;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
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

public class ExtractStringItems extends GuitarTabBaseVisitor<StringItem> {
	private List<StringItem> stringItems;
	private GuitarString s;

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

	@Override
	public StringItem visitTune(TuneContext ctx) {
		String value = ctx.getChild(0).getText();
		Tune tune;

		if (!value.equals("|"))
			tune = new Tune(value);
		else
			tune = new Tune(Tune.standardTuning[GuitarString.getStringNum()]);

		return tune;
	}

	@Override
	public StringItem visitStringItems(StringItemsContext ctx) {
		StringItemsCollector coll = new StringItemsCollector(new ArrayList<StringItem>());
		int numMeasures = (int) ctx.children.stream().filter(c -> c.getClass() ==  TerminalNodeImpl.class).count();
		s.setNumMeasures(numMeasures);
		ctx.children.stream().filter(c -> c.getClass() != TerminalNodeImpl.class).forEach(c -> coll.add(visit(c)));
		return coll;
	}

	@Override
	public StringItem visitSlide(SlideContext ctx) {
		Note start = (Note) visit(ctx.getChild(0));
		Note stop = (Note) visit(ctx.getChild(2));
		Slide slide = new Slide(start, stop);
		return slide;
	}

	@Override
	public StringItem visitPulloff(PulloffContext ctx) {
		Note start = (Note) visit(ctx.getChild(0));
		Note stop = (Note) visit(ctx.getChild(2));
		PullOff pullOff = new PullOff(start, stop);
		return pullOff;
	}

	@Override
	public StringItem visitHammeron(HammeronContext ctx) {
		Note start = (Note) visit(ctx.getChild(0));
		Note stop = (Note) visit(ctx.getChild(2));
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
		Note start = notes.get(0);
		Note stop = notes.get(notes.size() - 1);
		List<Note> middle = notes.subList(1,  notes.size() - 1);
		HammerPull hammerPull = new HammerPull(start, middle, stop);
		return hammerPull;
	}

	@Override
	public StringItem visitHarmonic(HarmonicContext ctx) {
		Note fret = (Note) visit(ctx.getChild(0));
		Harmonic harmonic = new Harmonic(fret);
		return harmonic;
	}

	@Override
	public StringItem visitFret(FretContext ctx) {
		Token token = ctx.FRET_NUM().getSymbol();
		int column = token.getCharPositionInLine() + 1;
		String value = ctx.getChild(0).getText();
		Fret fret = new Fret(value, column);
		Note note = new Note(fret, s);
		return note;
	}

	public List<StringItem> getStringItems() {
		return stringItems;
	}
}
