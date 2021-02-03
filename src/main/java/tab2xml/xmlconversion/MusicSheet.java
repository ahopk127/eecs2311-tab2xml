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

public class MusicSheet {
	// attributes of a music sheet:
	private Document doc;
	private DocumentBuilder dBuilder;
	private DocumentBuilderFactory dbFactory;

	public MusicSheet(Document doc, DocumentBuilder db, DocumentBuilderFactory dbf) {
		this.dbFactory = DocumentBuilderFactory.newInstance();
		
		try {
			this.dBuilder = dbFactory.newDocumentBuilder();
			this.doc = dBuilder.newDocument();			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	public void append(XMLElement e) {
		this.doc.appendChild(e.getElement());
	}
	
	public void appendToDoc(XMLElement e) {
		this.doc.appendChild(e.getElement());
	}
	
	public Document getDoc() {
		return this.doc;
	}
	
	public String toXML() {
		String xml;
		try {
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
		    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		    transformer.setOutputProperty(OutputKeys.INDENT, "yes");

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
