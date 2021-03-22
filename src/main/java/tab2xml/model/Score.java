package tab2xml.model;

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
import tab2xml.model.Score;
import tab2xml.model.guitar.Staff;
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

	public static void main(String[] args) {

		@SuppressWarnings("unused")
		String input = "-----------------------\r\n" + "E|--0-----------------------|-------------------------|\r\n"
				+ "B|------------------3-----5-|-2-----------------------|\r\n"
				+ "G|------------------3-------|-2-----------------------|\r\n"
				+ "D|------------------5-------|-2-----------------------|\r\n"
				+ "A|--------------------------|-0-----------------------|\r\n"
				+ "D|--------------------------|-------------------------|\r\n" + "\r\n" + "\r\n"
				+ "E|----------------------------------|---------------------------------||\r\n"
				+ "B|------3-------3-------2-------2---|-----3-------3-------2-----------||\r\n"
				+ "G|------2-------2-------2-------2---|-----2-------2-------2-------2---||\r\n"
				+ "D|------3---3-------2-----------5---|-----3---3-------2---------------||\r\n"
				+ "A|--------------------------0-------|-------------------------0-------||\r\n"
				+ "D|--0-------------------------------|-0-------------------------------||\r\n" + "\r\n"
				+ "-----------------------";

		String input2 = "E|---------------------------|-15p12-10p9-12p10-6p5-8p6-----|\r\n"
				+ "B|---------------------------|--------------------------8-5-|\r\n"
				+ "G|---------------------------|------------------------------|\r\n"
				+ "D|--[7]----------------------|------------------------------|\r\n"
				+ "A|--[7]----------------------|------------------------------|\r\n"
				+ "D|--[7]----------------------|------------------------------|\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "|-----------0-----||----------0--------4|----------0--------4|\r\n"
				+ "|---------0---0---||----------0--------||----------0--------||\r\n"
				+ "|-------1-------1-||*---------1-------*||*---------1-------*||\r\n"
				+ "|-----2-----------||*---------2-------*||*---------2-------*||\r\n"
				+ "|---2-------------||------2---2--------||------2---2--------||\r\n"
				+ "|-0---------------||--0-------0--------||--0-------0--------||\r\n"
				+ "";

		InputStream stream = new ByteArrayInputStream(input2.getBytes(StandardCharsets.UTF_8));
		GuitarTabLexer lexer = null;
		try {
			lexer = new GuitarTabLexer(CharStreams.fromStream(stream, StandardCharsets.UTF_8));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (lexer != null) {

			CommonTokenStream tokens = new CommonTokenStream(lexer);
			GuitarTabParser parser = new GuitarTabParser(tokens);

			ErrorListener listener = new ErrorListener();

			parser.removeErrorListeners();
			parser.addErrorListener(listener);

			ParseTree root = parser.sheet();

			SerializeScore ss = new SerializeScore();
			Score sheet = ss.visit(root);

			System.out.println("staffs: " + sheet.size());
			System.out.println(sheet.toString());
			System.out.println(sheet.numberOfMeasures());
		}
	}
}
