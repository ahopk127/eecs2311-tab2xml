package tab2xml.xmlconversion;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class ValidateXML {
	public static void main(String[] args) {

		System.out.println("output.xml <--> musicxml.xsd? " + validateXMLSchema("musicxml.xsd", "output.xml"));
	}

	public static boolean validateXMLSchema(String musicxmlxsd, String output) {

		try {
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(new File(musicxmlxsd));
			Validator validator = schema.newValidator();
			
			validator.validate(new StreamSource(new File(output)));
		} catch (IOException | SAXException e) {
			System.out.println("Exception: " + e.getMessage());
			return false;
		}
		return true;
	}
}
