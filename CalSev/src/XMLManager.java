import java.io.File;
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

/**
 * 
 */

/**
 * @author david.merayo
 *
 */
public class XMLManager {

	/**
	 * This method creates a new manager for all XML operations.
	 */
	public XMLManager() {
	}
	
	/**
	 * This method writes the content of a library in an xml file.
	 * @param library The material library to export.
	 * @param path The file where the information will be saved.
	 * @throws ParserConfigurationException If an error occurred while parsing the information.
	 * @throws TransformerException If an error occurred while writing the file
	 */
	public void writeLibrary(MaterialLibrary library, File path) throws ParserConfigurationException, TransformerException {
		//Initial preparation
		DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
		Document document = documentBuilder.newDocument();
		
		//Library tag
		Element libraryElement = document.createElement("MaterialLibrary");
		document.appendChild(libraryElement);
		
		Attr libNameAttribute = document.createAttribute(Constants.C_NAME_PROPERTY);
		libNameAttribute.setValue(library.getName());
		libraryElement.setAttributeNode(libNameAttribute);
		
		//for each material on the library
		for(Material material: library.getAllMaterial()) {
			
			Element materialElement = document.createElement("Material");
			libraryElement.appendChild(materialElement);
			
			//for each property on the material
			List<String[]> materialProperties = material.getPropertiesAndValues();
			for (String[] couple: materialProperties) {
				
				Element propertyElement = document.createElement(couple[0]);
				materialElement.appendChild(propertyElement);

				Attr valueAttribute = document.createAttribute("Value");
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

		//Show file in the terminal
		StreamResult result = new StreamResult(System.out);
		transformer.transform(domSource, result);
	}
	
	
}
