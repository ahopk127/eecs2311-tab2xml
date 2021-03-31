package tab2xml.model;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

import tab2xml.ImmutablePair;

public class Measure<E extends Note> implements Comparable<Measure<E>>, Iterable<E> {
	private int measure;
	final ImmutablePair<Range, Range> range;
	private TreeSet<E> notes = new TreeSet<>();

	public Measure(ImmutablePair<Range, Range> range) {
		this.range = range;
	}

	public Measure(int measure, ImmutablePair<Range, Range> range) {
		this.measure = measure;
		this.range = range;
	}

	public Measure(int measure, ImmutablePair<Range, Range> range, Collection<E> notes) {
		this.measure = measure;
		this.notes.addAll(notes);
		this.range = range;
	}

	public void processDuration() {
		TreeSet<E> durationNotes = new TreeSet<>(new DurationComparator());
		durationNotes.addAll(notes);

		for (E n : durationNotes) {
			E next = durationNotes.higher(n);
			if (next ==  null) {
				Range pairRange = new Range(this);
				pairRange.setStart(n.getColumn());
				pairRange.setStop(this.range.getFirst().getStop());
				double width = width() / pairRange.size();
				n.setDurationVal(duration(width));
				continue;
			}
			Range pairRange = new Range(this);
			pairRange.setStart(n.getColumn());
			pairRange.setStop(next.getColumn());
			double width = width() / pairRange.size();
			n.setDurationVal(duration(width));
		}

		for (E n : notes) {
			for (E n2 : durationNotes) {
				if (n.getColumn() == n2.getColumn()) {
					n.setDurationVal(n2.getDurationVal());
					if (n.getNotes().size() > 0)
						n.getNotes().forEach(group -> group.setDurationVal(n2.getDurationVal()));
				}
					
			}
		}
	}

	public int getMeasure() {
		return measure;
	}

	public void setMeasure(int measure) {
		this.measure = measure;
	}

	public ImmutablePair<Range, Range> getRange() {
		return range;
	}

	public boolean add(E note) {
		if (note != null)
			notes.add(note);
		return true;
	}

	public boolean addAll(Collection<E> notes) {
		if (notes != null)
			for (E n : notes)
				if (!add(n))
					return false;
		return true;
	}

	public TreeSet<E> getNotes() {
		return notes;
	}

	public double width() {
		return range.getFirst().size();
	}

	public double duration(double x) {
		if (x >= 0.5 && x <= 1.5)
			return 1;
		else if (x >= 1.5 && x <= 3)
			return 2;
		else if (x >= 3 && x <= 6)
			return 4;
		else if (x >= 6 && x <= 12)
			return 8;
		else if (x >= 12 && x <= 24)
			return 16;
		else if (x >= 24 && x <= 48)
			return 32;
		else if (x >= 48 && x <= 96)
			return 64;
		else if (x >= 96 && x <= 192)
			return 128;
		else
			return 1;
	}

	public static class DurationComparator implements Comparator<LineItem> {
		@Override
		public int compare(LineItem i1, LineItem i2) {
			if (i1.getColumn() == i2.getColumn())
				return 0;
			return i1.compareTo(i2);
		}
	}

	@Override
	public Iterator<E> iterator() {
		return new MeasureIterator(this);
	}

	@Override
	public int compareTo(Measure<E> m) {
		return this.getMeasure() - m.getMeasure();
	}

	private class MeasureIterator implements Iterator<E> {
		TreeSet<E> notes;

		public MeasureIterator(Measure<E> measure) {
			notes = measure.getNotes();
		}

		@Override
		public boolean hasNext() {
			return !notes.isEmpty();
		}

		@Override
		public E next() {
			return notes.pollFirst();
		}
	}

	@Override
	public String toString() {
		return notes.toString();
	}
}
