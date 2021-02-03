package tab2xml.xmlconversion;

import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import tab2xml.parser.Instrument;

import java.io.StringWriter;

public class Transform {
	private ArrayList<ArrayList<Object>> data;
	MusicSheet musicSheet;
	private Document doc;
	private DocumentBuilder dBuilder;
	private DocumentBuilderFactory dbFactory;

	public Transform(ArrayList<ArrayList<Object>> data, Instrument instrument) {
		this.data = data;
		this.dbFactory = DocumentBuilderFactory.newInstance();

		try {
			this.dBuilder = dbFactory.newDocumentBuilder();
			this.doc = dBuilder.newDocument();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		this.musicSheet = new MusicSheet(doc, dBuilder, dbFactory);

		switch (instrument) {
		case GUITAR:
			generateGuitar();
			break;
		case DRUM:
			generateDrum();
		default:
			throw new IllegalArgumentException("Unsupported instrument: " + instrument);
		}
	}

	private void generateGuitar() {
		generateSamplePlaceHolder();
		// root element:

		// parse data here:
	}

	private void generateDrum() {
		generateSamplePlaceHolder();
		// root element:
		
		// parse data here:
	}

	private void generateSamplePlaceHolder() {
		// root element
		XMLElement root = new XMLElement("score-partwise", musicSheet);
		musicSheet.appendToDoc(root);

		int measureCount = 10;
		
		for (int i = 0; i < measureCount; i++) { 
			XMLElement measure = new XMLElement("measure", musicSheet);
			root.append(measure);
		
			XMLElement note = new XMLElement("note", musicSheet);
			note.setText("sample attributes of note " + i);
			measure.append(note);
			
		}
	}

	public String toXML() {
		return musicSheet.toXML();
	}

}
