import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 
 */

/**
 * @author david.merayo
 *
 */
public class MaterialLibrary {
	private String name;
	private Vector<Material> materialList;
	private boolean hasBeenModified = true;
	
	/**
	 * This method creates a new material library and assign its name
	 * @param libraryName The name to be set.
	 */
	public MaterialLibrary(String libraryName) {
		materialList = new Vector<Material>();
		setName(libraryName);
	}

	/**
	 * This method creates a new material library based on a xml file.
	 * @param inputLibrary the file where the information is read from.
	 * @throws ParserConfigurationException If an error occurred while parsing the file.
	 * @throws SAXException If a general SAX exception occurred.
	 * @throws IOException If a problem occurred while reading the file.
	 */
	public MaterialLibrary(File inputLibrary) throws ParserConfigurationException, SAXException, IOException, ClassCastException {
		materialList = new Vector<Material>();
		Document xmlInformation = XMLManager.loadXMLFromString(inputLibrary);
		loadMaterialInformation(xmlInformation);
		setModifiedStatus(false);
	}
	
	/**
	 * This method creates the materials from a given xml data structure.
	 * @param xmlInformation The xml information containing a library (supposed to be read from a xml file).
	 * TODO remove printf
	 */
	private void loadMaterialInformation(Document xmlInformation) throws ClassCastException {
		setName(xmlInformation.getDocumentElement().getAttribute(Constants.C_NAME_PROPERTY));
		System.out.println(getName() + "\n----------------------------");

		NodeList materialNodesList = xmlInformation.getElementsByTagName(Constants.C_MATERIAL_XML_TAG);
		//For each material on the library
		for (int temp = 0; temp < materialNodesList.getLength(); temp++) {

			Node materialNode = materialNodesList.item(temp);
			System.out.println("\nCurrent Element: " + materialNode.getNodeName());
			
			NodeList propertyNodesList = materialNode.getChildNodes();
			HashMap<String, String> materialProperties = new HashMap<String, String>();
			//For each property on the material
			for (int temp2 = 0; temp2 < propertyNodesList.getLength(); temp2++) {
				
				Node propertyNode = propertyNodesList.item(temp2);
				String propertyName = propertyNode.getNodeName();
				String propertyValue = ((Element) propertyNode).getAttribute(Constants.C_VALUE_XML_PROPERTY);
				materialProperties.put(propertyName, propertyValue);
				System.out.println("\t" + propertyName + ": " + propertyValue);
			}
			
			//create material and set properties
			Material createdMaterial = new Material(materialProperties.get(Constants.C_NAME_PROPERTY));
			for(String key: materialProperties.keySet()) {
				createdMaterial.setProperty(key, materialProperties.get(key));
			}
			materialList.addElement(createdMaterial);
		}
	}
	
	/**
	 * This method sets the library name
	 * @param libraryName The library name
	 */
	public void setName(String libraryName) {
		name = libraryName;
		setModifiedStatus(true);
	}
	
	/**
	 * This method returns the library name
	 * @return The library name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * This method sets the modification status
	 * @param modified The modification status to set.
	 */
	private void setModifiedStatus(boolean modified) {
		hasBeenModified = modified;
	}
	
	/**
	 * This method indicated whether the current library has been modified or not.
	 * @return true if the current library has been modified since the last time it was saved.
	 */
	public boolean hasChanged() {
		return hasBeenModified;
	}
	
	/**
	 * This method adds a new material to the library
	 * @param materialName The new material name.
	 * @throws IllegalArgumentException If the "Name" property does not exist in this material.
	 */
	public void addMaterial(String materialName) {
		if (getMaterialNames().contains(materialName)) {
			throw new IllegalArgumentException(Constants.C_ERROR_REPEATED_NAME);
		}
		materialList.addElement(new Material(materialName));
		setModifiedStatus(true);
	}
	
	/**
	 * This method returns an element from the library.
	 * @param index The material index
	 * @throws ArrayIndexOutOfBoundsException If index < 0 or index > sizeOf()
	 */
	public void deleteMaterial(int index) throws ArrayIndexOutOfBoundsException {
		materialList.remove(index);
		setModifiedStatus(true);
	}
	
	/**
	 * This method returns an element from the library.
	 * @param index The material index
	 * @throws ArrayIndexOutOfBoundsException If index < 0 or index > sizeOf()
	 */
	public void deleteMaterialByName(String materialName) throws ArrayIndexOutOfBoundsException {
		materialList.remove(getMaterialByName(materialName));
		setModifiedStatus(true);
	}
	
	/**
	 * This method returns a vector with all the material's names in the library.
	 * @return The material's names in the library
	 */
	public Vector<String> getMaterialNames() {
		Vector<String> result = new Vector<String>();
		for (Material material : materialList) {
			result.addElement(material.getName());
		}
		return result;
	}
	
	/**
	 * This method gets a material by its name.
	 * @param materialName the name of the material we are looking for
	 * @return The material object
	 * @throws IllegalArgumentException If the material is not in the library.
	 */
	public Material getMaterialByName(String materialName) throws IllegalArgumentException {
		for (Material material : materialList) {
			if (material.getName().equals(materialName)) {
				return material;
			}
		}
		throw new IllegalArgumentException(Constants.C_ERROR_MATERIAL_NOT_FOUND);
	}
	
	/**
	 * This method gets a material by its index.
	 * @param index the index related to the asked material
	 * @return The material object
	 * @throws ArrayIndexOutOfBoundsException If index < 0 or index > sizeOf()
	 */
	public Material getMaterialByIndex(int index) throws ArrayIndexOutOfBoundsException {
		return materialList.elementAt(index);
	}
	
	/**
	 * This method sets a property on a given material.
	 * @param materialName The material whose property should be modified.
	 * @param propertyName The property to be modified.
	 * @param propertyValue The new value.
	 * @throws IllegalArgumentException If the material is not in the library.
	 */
	public void setMaterialProperty(String materialName, String propertyName, String propertyValue) throws IllegalArgumentException {
		Material referedMaterial = getMaterialByName(materialName);
		referedMaterial.setProperty(propertyName, propertyValue);
		setModifiedStatus(true);
	}
	
	/**
	 * This method gets all the materials contained in this library.
	 * @return The materials on the library.
	 */
	public Vector<Material> getAllMaterial() {
		return materialList;
	}
	
	/**
	 * This method writes the library information into an xml file.
	 * @param path File where the information will be stored.
	 * @throws ParserConfigurationException If an error occurred while parsing the information.
	 * @throws TransformerException If an error occurred while writing the file.
	 */
	public void writeXMLFile(File path) throws ParserConfigurationException, TransformerException {
		XMLManager.writeLibrary(this, path);
		setModifiedStatus(false);	//Once saved, it is considered as not modified
	}
}
