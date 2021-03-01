package tab2xml.listeners;

import java.util.LinkedList;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ErrorNode;

import tab2xml.antlr.GuitarTabBaseListener;

public class ErrorListener extends GuitarTabBaseListener {
	private static LinkedList<Token> errors = new LinkedList<>();

	@Override
	public void visitErrorNode(ErrorNode node) {
		errors.add(node.getSymbol());
	}

	public LinkedList<Token> getErrNodes() {
		return errors;
	}
}
