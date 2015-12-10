/**
 * 
 */

/**
 * @author david.merayo
 *
 */
public class MaterialLibrary {
	private String name;
	private boolean hasBeenModified = true;
	
	public MaterialLibrary(String libraryName) {
		setName(libraryName);
	}
	
	/**
	 * This method sets the library name
	 * @param libraryName The library name
	 */
	public void setName(String libraryName) {
		name = libraryName;
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
}
