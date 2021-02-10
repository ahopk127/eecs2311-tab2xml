package tab2xml.xmlconversion;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLElement {
	private Document doc;
	private Element element;
	
	public XMLElement(String tag, Document doc) {
		this.doc = doc;
		this.element = doc.createElement(tag);
	}
	
	public XMLElement(String tag, MusicSheet sheet) {
		this.element = sheet.getDoc().createElement(tag);
	}
	
	public void append(XMLElement e) {
		element.appendChild(e.getElement());
	}
	
	public void setAttribute(String name, String value) {
		element.setAttribute(name, value);
	}
	
	public void setText(String text) {
		element.setTextContent(text);
	}
	
	public String getTagName() {
		return element.getTagName();
	}
	
	public Element getElement() {
		return this.element;
	}
}