import java.util.Arrays;

/**
 * @author david.merayo
 * @version 1.0.0
 * This class contains all the application relevant constants
 */
public class Constants {

	/**
	 * This method concatenates several arrays of elements
	 * @param first first array
	 * @param rest The other arrays
	 * @return An array containing all the elements
	 */
	@SafeVarargs
	private static <T> T[] concatAll(T[] first, T[]... rest) {
	    int totalLength = first.length;
	    for (T[] array : rest) {
	        totalLength += array.length;
	    }
	    T[] result = Arrays.copyOf(first, totalLength);
	    int offset = first.length;
	    for (T[] array : rest) {
	        System.arraycopy(array, 0, result, offset, array.length);
	        offset += array.length;
	    }
	    return result;
	}
	
	/**
	 * This method returns the Le limit for a given element.
	 * @param element The element whose Le we need.
	 * @return The relevant Le limit or 100.0 in any other case.
	 */
	public static String getLeValueFor(String element) {
		for (int i = 0; i< C_CASE2_MATERIAL_PROPERTIES.length; i++){
			if (C_CASE2_MATERIAL_PROPERTIES[i].equals(element)) {
				return C_CASE2_MATERIAL_LE_VALUES[i];
			}
		}
		return "100.0";
	}
	
	/**
	 * This method returns the Ri limit for a given element.
	 * @param element The element whose Ri we need.
	 * @return The relevant Ri limit or 0.0 in any other case.
	 */
	public static double getRiValueFor(String element) {
		for (int i = 0; i< C_ALL_CASES_MATERIAL_PROPERTIES.length; i++){
			if (C_ALL_CASES_MATERIAL_PROPERTIES[i].equals(element)) {
				return C_RI_MATERIAL_PROPERTIES[i];
			}
		}
		for (int i = 0; i< C_MECHANICAL_PROPERTIES.length; i++){
			if (C_MECHANICAL_PROPERTIES[i].equals(element)) {
				return C_RI_MECHANICAL_PROPERTIES[i];
			}
		}
		return 0;
	}
	
	// Application properties
	public static String C_APPLICATION_NAME = "CalSev";
	public static String C_VERSION = "v1.0.2";
	public static String C_APP_NAME_VERSION = C_APPLICATION_NAME + " " + C_VERSION;
	public static String C_MAIN_WINDOW_TITLE = C_APP_NAME_VERSION;
	
	
	// Main window properties
	public static int C_MAIN_PREFERED_POSITION_X_AT_START = 100;
	public static int C_MAIN_PREFERED_POSITION_Y_AT_START = 100;
	public static int C_MAIN_PREFERED_SIZE_X = 640;
	public static int C_MAIN_PREFERED_SIZE_Y = 640;

	
	// Tabs
	public static String C_TAB_CALCULATION_TITLE = "Calculation";
	public static String C_TAB_LIBRARY_TITLE = "Library";
	public static String C_TAB_DECISION_TITLE = "Decision algorithm (*)";
	
	
	// Menus
	public static String C_FILE_MENU_TITLE = "File";
	public static String C_HELP_MENU_TITLE = "Help";
	public static String C_FILE_ITEM_EXIT_TITLE = "Exit";
	public static String C_HELP_ITEM_HELP_TITLE = "Help";
	public static String C_HELP_ITEM_ABOUT_TITLE = "About...";
	public static String C_HELP_ITEM_REFERENCES_TITLE = "References";
	public static String C_HELPING_URL = "file://" + System.getProperty("user.dir") + "/help/help.html";
	
	
	// Calculation tab
	public static String C_SELECT_MATERIALS_LABEL = "Select materials:";
	public static String C_MATERIAL_LIBRARY_NAME = "Materials library: ";
	public static String C_BROWSE_BUTTON = "Browse";
	public static String C_ADD_MATERIALS_FROM_LIBRARY_BUTTON = ">>";
	public static String C_CALCULATION_STEPS_TITLE = "Weighted SL:";
	public static String C_RESULTS_TITLE = "Results:";
	public static String C_CLEAR_DATA_BUTTON = "Clear data";
	
	
	//Library tab
	public static String C_OPEN_LIBRARY_BUTTON = "Open library";
	public static String C_NEW_LIBRARY_BUTTON = "New library";
	public static String C_DELETE_MATERIAL_BUTTON = "Delete material";
	public static String C_ADD_MATERIAL_BUTTON = "Add material";
	public static String C_CANCEL_BUTTON = "Cancel";
	public static String C_SAVE_LIBRARY_AS_BUTTON = "Save as...";
	public static String C_MANAGED_LIBRARY_NAME_LABEL = "Libary name: ";
	public static String C_CHEMICAL_SL_VALUE_COLUMN = "Chemical SL";
	public static String C_MECHANICAL_SL_VALUE_COLUMN = "Mechanical SL";
	public static String C_TOTAL_SL_VALUE_COLUMN = "Total SL";
	public static String C_PROPERTY_COLUMN = "Property";
	
	
	//Sources tab and references
	public static String C_METHOD_DIAGRAM_PATH = "resources/method_diagram.png";
	public static String C_REFERENCES_LIST = "<ul>" +
			"<li>"  + "A. Rodríguez-Prieto (2014): \"Análisis de requisitos tecnológicos de materiales especificados "
					+ "en normativas reguladas y su repercusión sobre la fabricación de recipientes especiales para "
					+ "la industria nuclear\". Ph.D. dissertation, ETSII Universidad Nacional de Educación a "
					+ "Distancia." + "</li>" +
			"<li>"  + "A. Rodríguez-Prieto, A.M. Camacho and M.A. Sebastián (2015): \"Materials selection criteria "
					+ "for nuclear power applications: a decision algorithm\". <i>Journal of the Minerals, Metals &  "
					+ "Materials Society</i>, DOI: 10.1007/s11837-015-1687-y." + "</li>" +
			"</ul>";
	public static String C_SOURCES_TEXT = "<html>" + "(*) Sources:" + C_REFERENCES_LIST + "</html>";

	// References window properties
	public static int C_REFERENCES_PREFERED_POSITION_X_AT_START = 100;
	public static int C_REFERENCES_PREFERED_POSITION_Y_AT_START = 100;
	public static int C_REFERENCES_PREFERED_SIZE_X = 640;
	public static int C_REFERENCES_PREFERED_SIZE_Y = 240;
	
	//References window
	public static String C_REFERENCES_WINDOW_TITLE = "References";
	public static String C_REFERENCES_INFO = "<html>" + C_REFERENCES_LIST + "</html>";
	
	
	
	//Dialogs messages
	public static String C_INSERT_NEW_LIBRARY_NAME = "Insert new library name:";
	public static String C_INSERT_NEW_MATERIAL_NAME = "Insert new material name:";
	public static String C_WANT_TO_CONTINUE = "The previous library was modified but the changes were not saved.\n"
			+ "Do you like to continue?";
	public static String C_WANT_TO_DELETE_MATERIAL = " was selected to be removed.\n"
			+ "Do you like to continue?";
	public static String C_WANT_TO_OVERWRITE = "The selected file already exists.\n"
			+ "Do you like to overwrite it?";
	public static String C_WANT_TO_CLEAR_DATA = "Do you really want to clear all calculation data?";
	public static String C_WARNING_TITLE = "Warning";

	
	//xml tags and properties
	public static String C_LIBRARY_EXTENSION = "xml";
	public static String C_LIBRARY_IO_DESCRIPTION = "Material library";
	public static String C_GLOBAL_XML_TAG = "MaterialLibrary";
	public static String C_MATERIAL_XML_TAG = "Material";
	public static String C_VALUE_XML_PROPERTY = "Value";
	
	
	//Error messages
	public static String C_ERROR_DIALOG_TITLE = "Error";
	public static String C_ERROR_WHILE_BROWSING_HELP = "An error occurred while we were trying to find somebody"
			+ " who could help you.";
	public static String C_GLOBAL_ERROR = "Something odd occurred and the appliation will finish.\n"
			+ "I've seen things you people wouldn't believe: Attack ships on fire off the shoulder of Orion.\n"
			+ "I've watched c-beams glitter in the dark near the Tannhauser Gate.\n"
			+ "All those... moments will be lost in time, like tears... in rain. Time to die";
	public static String C_ERROR_PROPERTY_NOT_FOUND = "The given property does not exist in this material.";
	public static String C_ERROR_REPEATED_NAME = "The given material name already exist in this library.";
	public static String C_ERROR_MATERIAL_NOT_FOUND = "The given material was not found.";
	public static String C_ERROR_WHILE_EXPORTING_LIBRARY = "Something odd occurred while the library was been exported.";
	public static String C_ERROR_READING_XML_FILE = "An error occurred while reading a library.";
	public static String C_REPEATED_MATERIALS_LOADED = "Materials with repeated names are not allowed.";
	
	
	// About window properties
	public static int C_ABOUT_PREFERED_POSITION_X_AT_START = 100;
	public static int C_ABOUT_PREFERED_POSITION_Y_AT_START = 100;
	public static int C_ABOUT_PREFERED_SIZE_X = 450;
	public static int C_ABOUT_PREFERED_SIZE_Y = 300;
	
	
	//About window
	public static String C_ABOUT_WINDOW_TITLE = "About " + C_MAIN_WINDOW_TITLE;
	public static String C_OK_BUTTON = "OK";
	public static String C_ABOUT_INFO = "<html>" + C_APP_NAME_VERSION +
		"<br>" + "Copyright (C) 2015  David MERAYO FERNANDEZ" + "<br>" + 
		"<br>" + "This program is free software: you can redistribute it and/or modify it under "
				+ "the terms of the GNU General Public License as published by the Free Software "
				+ "Foundation, either version 3 of the License, or (at your option) any later version." +
		"<br>" + "This program is distributed in the hope that it will be useful, but WITHOUT ANY "
				+ "WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR"
				+ " A PARTICULAR PURPOSE.  See the GNU General Public License for more details." +
		"<br>" + "You should have received a copy of the GNU General Public License along with "
				+ "this program.  If not, see http://www.gnu.org/licenses/" + "</html>";
	
	
	//Material constants
	public static String C_NAME_PROPERTY = "Name";
	public static String[] C_NAME_STANDARD = {C_NAME_PROPERTY, "Standard"};
	public static String[] C_CASE1_MATERIAL_PROPERTIES = {"Si", "V"};
	public static Double[] C_RI_CASE1_MATERIAL_PROPERTIES = {0.08, 0.15};
	
	public static String[] C_CASE2_MATERIAL_PROPERTIES = {"Ni", "Cu", "P"};
	public static Double[] C_RI_CASE2_MATERIAL_PROPERTIES = {0.15, 0.23, 0.23};
	public static String[] C_CASE2_MATERIAL_LE_VALUES = {"1.0", "0.1", "0.02"};
	
	public static String[] C_CASE3_MATERIAL_PROPERTIES = {"Mn", "Mo"};
	public static Double[] C_RI_CASE3_MATERIAL_PROPERTIES = {0.08, 0.08};

	public static String[] C_MECHANICAL_PROPERTIES = {"UTS", "Yp","Elongation"};
	public static Double[] C_RI_MECHANICAL_PROPERTIES = {0.4, 0.2, 0.4};
	
	public static String[] C_ALL_CASES_MATERIAL_PROPERTIES = concatAll(C_CASE3_MATERIAL_PROPERTIES, 
			C_CASE2_MATERIAL_PROPERTIES, C_CASE1_MATERIAL_PROPERTIES);
	public static String[] C_MATERIAL_PROPERTIES = concatAll(C_NAME_STANDARD, C_ALL_CASES_MATERIAL_PROPERTIES, C_MECHANICAL_PROPERTIES);
	public static Double[] C_RI_MATERIAL_PROPERTIES = concatAll(C_RI_CASE3_MATERIAL_PROPERTIES, C_RI_CASE2_MATERIAL_PROPERTIES,
			C_RI_CASE1_MATERIAL_PROPERTIES);
	public static String[] C_SHOWN_PROPERTIES = concatAll(C_ALL_CASES_MATERIAL_PROPERTIES, C_MECHANICAL_PROPERTIES);
	

	public static Double[] C_RI_CHEMICAL_MECHANICAL_TOTAL = {0.667, 0.333};
}
