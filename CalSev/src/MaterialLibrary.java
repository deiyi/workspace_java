import java.util.Vector;

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
	
	
	public MaterialLibrary(String libraryName) {
		materialList = new Vector<Material>();
		setName(libraryName);
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
}
