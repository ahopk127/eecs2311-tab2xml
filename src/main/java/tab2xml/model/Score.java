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
import tab2xml.parser.SerializeScore;
import tab2xml.model.Score;

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
		int acc = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < staffs.size(); i++) {
			Staff staff = staffs.get(i);
			List<GuitarString> strings = staff.getStrings();
			for (int j = 0; j < strings.size(); j++) {
				for (StringItem item : strings.get(j).getItems()) {
					int count = item.getPosition() - acc;
					acc += count + (item.toString().length() / 2);
					while (--count > 0)
						sb.append("-");

					sb.append(item.toString());
				}
				sb.append("\n");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public static void main(String[] args) {

		String input = "|-----------0-----|-0---------------|\r\n" + "|---------0---0---|-0---------------|\r\n"
				+ "|-------1-------1-|-1---------------|\r\n" + "|-----2-----------|-2---------------|\r\n"
				+ "|---2-------------|-2---------------|\r\n" + "|-0---------------|-0---------------|\r\n" + "\r\n"
				+ "|-----------0-----|-0---------------|\r\n" + "|---------0---0---|-0---------------|\r\n"
				+ "|-------1-------1-|-1---------------|\r\n" + "|-----2-----------|-2---------------|\r\n"
				+ "|---2-------------|-2---------------|\r\n" + "|-0---------------|-0---------------|";

		input += "\r\n";
		InputStream stream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
		GuitarTabLexer lexer = null;
		try {
			lexer = new GuitarTabLexer(CharStreams.fromStream(stream, StandardCharsets.UTF_8));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (lexer != null) {

			CommonTokenStream tokens = new CommonTokenStream(lexer);
			GuitarTabParser parser = new GuitarTabParser(tokens);

			ParseTree root = parser.sheet();

			SerializeScore ss = new SerializeScore();
			Score sheet = ss.visit(root);

			for (Staff staff : sheet.getStaffs()) {
				for (StringItem item : staff) {
					Note note = (Note) item;
					System.out.print(note.toString() + "(" + note.getMeasure() + "," + note.getStringNum() + ") ");
				}
				System.out.println();
			}
		}
	}
}
