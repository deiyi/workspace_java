import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @author david.merayo
 * @version 1.0.0
 * This class manages the material behavior.
 */
public class Material {
	private LinkedHashMap<String, String> properties;
	
	/**
	 * This method creates a new material.
	 * @param materialName The new material name.
	 * TODO add by-default properties.
	 */
	public Material(String materialName) {
		properties = new LinkedHashMap<String, String>();
		
		for(String propertyName: Constants.C_MATERIAL_PROPERTIES) {
			setProperty(propertyName, "");
		}
		setName(materialName);
	}
	
	/**
	 * This method sets the material name (the property is created or modified).
	 * @param value The new name.
	 */
	public void setName(String value) {
		properties.put(Constants.C_NAME_PROPERTY, value);
	}
	
	/**
	 * This method adds a new property (or replaces its value) to this material.
	 * @param property The property name
	 * @param value The value for the given property
	 */
	public void setProperty(String property, String value) {
		properties.put(property, value);
	}
	
	/**
	 * This method gets the value of a given material's property.
	 * @param property The property whose value we ask for.
	 * @return The value related to the given property name or "" if the property does not exist.
	 */
	public String getPropertyValue(String property) {
		String result = properties.get(property);
		if (result == null) {
			return "";
		}
		return result.toString();
	}
	
	/**
	 * This method returns all the properties in this material
	 * @return The properties in this material.
	 */
	public Set<String> getPropertyNames() {
		return properties.keySet();
	}
	
	/**
	 * This method returns this material's name.
	 * @return The material name.
	 */
	public String getName() {
		return getPropertyValue(Constants.C_NAME_PROPERTY);
	}
	
	/**
	 * This method returns the property-value pairs for this material
	 * @return All property-material pairs for this material.
	 */
	public List<String[]> getPropertiesAndValues() {
		List<String[]> result = new ArrayList<String[]>();
		
		Iterator<Entry<String, String>> entries = properties.entrySet().iterator();
		while (entries.hasNext()) {
			Entry<String, String> thisEntry = (Entry<String, String>) entries.next();
			String key = thisEntry.getKey();
			String value = thisEntry.getValue();
			result.add(new String[] {key, value});
		}
		
		return result;
	}
}
