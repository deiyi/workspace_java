import java.io.File;
import java.io.IOException;

import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.xml.sax.SAXException;

/**
 * @author david.merayo
 * @version 1.0.0
 * This class manages standard XML actions.
 */
public class XMLManager {
	
	/**
	 * This method writes the content of a library in an xml file.
	 * @param library The material library to export.
	 * @param path The file where the information will be saved.
	 * @throws ParserConfigurationException If an error occurred while parsing the information.
	 * @throws TransformerException If an error occurred while writing the file
	 */
	public static void writeLibrary(MaterialLibrary library, File path) throws ParserConfigurationException, TransformerException {
		//Initial preparation
		DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
		Document document = documentBuilder.newDocument();
		
		//Create library tag
		Element libraryElement = document.createElement(Constants.C_GLOBAL_XML_TAG);
		document.appendChild(libraryElement);
		
		//Add library attributes
		Attr libNameAttribute = document.createAttribute(Constants.C_NAME_PROPERTY);
		libNameAttribute.setValue(library.getName());
		libraryElement.setAttributeNode(libNameAttribute);
		
		//for each material on the library
		for(Material material: library.getAllMaterial()) {
			
			//Create material tag
			Element materialElement = document.createElement(Constants.C_MATERIAL_XML_TAG);
			libraryElement.appendChild(materialElement);
			
			//for each property on the material
			List<String[]> materialProperties = material.getPropertiesAndValues();
			for (String[] couple: materialProperties) {
				
				//Create property tag
				Element propertyElement = document.createElement(couple[0]);
				materialElement.appendChild(propertyElement);

				//Add property attribute
				Attr valueAttribute = document.createAttribute(Constants.C_VALUE_XML_PROPERTY);
				valueAttribute.setValue(couple[1]);
				propertyElement.setAttributeNode(valueAttribute);
			}
		}
		
		//Save information as an .xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource domSource = new DOMSource(document);
		StreamResult streamResult = new StreamResult(path);
		transformer.transform(domSource, streamResult);

		//Show xml information in the terminal
		//StreamResult result = new StreamResult(System.out);
		//transformer.transform(domSource, result);
	}
	
	/**
	 * This method parses the information contained in an XML file.
	 * @param file The file to read from
	 * @return A structure with the information contained in the file.
	 * @throws ParserConfigurationException If an error occurred while parsing the file.
	 * @throws SAXException If a general SAX exception occurred.
	 * @throws IOException If a problem occurred while reading the file.
	 */
	public static Document loadXMLFromString(File file) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(file);
		doc.getDocumentElement().normalize();
		
		return doc;
	}
}
