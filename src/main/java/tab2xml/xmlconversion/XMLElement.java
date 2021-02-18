package tab2xml.xmlconversion;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * An XML element wrapper.
 * 
 * @author amir
 */
public class XMLElement {
	private Document doc;
	private Element element;

	/**
	 * Construct an element with a specified tag and its corresponding music sheet.
	 * 
	 * @param tag the name of the element
	 * @param musicSheet the music sheet corresponding to this element
	 */
	public XMLElement(String tag, MusicSheet musicSheet) {
		this.element = musicSheet.getDoc().createElement(tag);
	}

	/**
	 * Append an XML element to this element.
	 * 
	 * @param e the element to append to this element
	 */
	public void append(XMLElement e) {
		element.appendChild(e.getElement());
	}

	/**
	 * Append multiple elements to this element.
	 * 
	 * @param elements the elements to append to this element
	 */
	public void append(XMLElement... elements) {
		for (XMLElement e : elements)
			element.appendChild(e.getElement());
	}

	/**
	 * Set the value of a specified attribute of this element.
	 * 
	 * @param name the attribute for tag
	 * @param value the value of the specified attribute
	 */
	public void setAttribute(String name, String value) {
		element.setAttribute(name, value);
	}

	/**
	 * Set the content of this element.
	 * 
	 * @param text the content to put between the opening and closing tag
	 */
	public void setText(String text) {
		element.setTextContent(text);
	}

	/**
	 * Return the tag name of this element.
	 * 
	 * @return the tag name of this element
	 */
	public String getTagName() {
		return element.getTagName();
	}

	/**
	 * Return a reference to this element.
	 * 
	 * @return a reference to this element
	 */
	public Element getElement() {
		return this.element;
	}

	/**
	 * Return a reference to this element's document.
	 * 
	 * @return a reference to this element's document
	 */
	public Document getDoc() {
		return this.doc;
	}
}
