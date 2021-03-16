package tab2xml.model.drum;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.nio.charset.StandardCharsets;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import tab2xml.antlr.GuitarTabLexer;
import tab2xml.antlr.GuitarTabParser;
import tab2xml.listeners.ErrorListener;
import tab2xml.model.guitar.StringItem;
import tab2xml.parser.SerializeScore;

/**
 * A representation of a score sheet.
 * 
 * @author amir
 *
 */
public class Score {
	private List<Staff> staffs;

	/**
	 * Construct an empty score.
	 * 
	 */
	public Score() {
		this.staffs = new ArrayList<>();
		Staff.setAccumulateMeasure(0);
	}

	/**
	 * Add a specified staff, <b>s></b> to this score.
	 * 
	 * @param s the staff to add to this score
	 */
	public void addStaff(Staff s) {
		this.staffs.add(s);
	}

	/**
	 * Return the total number of staffs in this score.
	 * 
	 * @return the number of staffs in this score
	 */
	public int size() {
		return staffs.size();
	}

	/**
	 * Return a list of staffs.
	 * 
	 * @return the list of staffs in this score
	 */
	public List<Staff> getStaffs() {
		return staffs;
	}

	public int getNoteCount() {
		int total = 0;
		for (Staff staff : staffs)
			total += staff.getNoteCount();
		return total;
	}

	/**
	 * Return the number of measures in this score.
	 * 
	 * @return the number of measures in this score
	 */
	public int numberOfMeasures() {
		int count = 0;
		for (int i = 0; i < staffs.size(); i++) {
			Staff staff = staffs.get(i);
			count += staff.numberOfMeasures();
		}
		return count;
	}

	/**
	 * Return an array of staffs with the notes representing the score as a string.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Staff staff : staffs) {
			for (StringItem item : staff) {
				sb.append(item.toString());
				sb.append(" ");
			}
			sb.append("\n");
			sb.append("\n");
		}
		return sb.toString();
	}
}