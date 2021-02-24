package tab2xml.xmlconversion;

import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

/**
 * A representation of a music sheet in musicXML format.
 * 
 * @author amir
 */
public class MusicSheet {
	private Document doc;
	private DocumentBuilder dBuilder;
	private DocumentBuilderFactory dbFactory;

	/**
	 * Construct a music sheet with a specified document.
	 * 
	 * @param doc the document of the music sheet
	 * @param db  an instance of a document builder
	 * @param dbf an instance of a document builder factory
	 */
	public MusicSheet(Document doc, DocumentBuilder db, DocumentBuilderFactory dbf) {
		this.dbFactory = DocumentBuilderFactory.newInstance();

		try {
			this.dBuilder = dbFactory.newDocumentBuilder();
			this.doc = dBuilder.newDocument();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Appends an XML element to the music sheet.
	 * 
	 * @param e element to append to the document
	 */
	public void append(XMLElement e) {
		this.doc.appendChild(e.getElement());
	}

	/**
	 * Returns a reference to this musicsheet's document.
	 * 
	 * @return the XML document of this music sheet
	 */
	public Document getDoc() {
		return this.doc;
	}

	/**
	 * This method transforms the current music sheet to XML as a string.
	 * 
	 * @return XML representation of the XML document as a string
	 */
	public String toXML() {
		String xml;
		try {
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "-//Recordare//DTD MusicXML 3.1 Partwise//EN");
			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "http://www.musicxml.org/dtds/partwise.dtd");
			StringWriter sw = new StringWriter();
			transformer.transform(new DOMSource(doc), new StreamResult(sw));

			String output = sw.getBuffer().toString().replace("\n|\r", "");
			xml = output;

		} catch (Exception e) {
			xml = "error converting input.";
			e.printStackTrace();
		}
		return xml;
	}
}
