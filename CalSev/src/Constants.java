
public class Constants {
	
	// Main window properties
	public static int C_MAIN_PREFERED_POSITION_X_AT_START = 100;
	public static int C_MAIN_PREFERED_POSITION_Y_AT_START = 100;
	public static int C_MAIN_PREFERED_SIZE_X = 640;
	public static int C_MAIN_PREFERED_SIZE_Y = 480;
	
	// General window
	public static String C_APPLICATION_NAME = "CalSev";
	public static String C_VERSION = "v1.0.0";
	public static String C_APP_NAME_VERSION = C_APPLICATION_NAME + " " + C_VERSION;
	public static String C_MAIN_WINDOW_TITLE = C_APP_NAME_VERSION;

	// Tabs
	public static String C_TAB_CALCULATION_TITLE = "Calculation";
	public static String C_TAB_LIBRARY_TITLE = "Library";
	
	// Menus
	public static String C_FILE_MENU_TITLE = "File";
	public static String C_HELP_MENU_TITLE = "Help";
	public static String C_FILE_ITEM_EXIT_TITLE = "Exit";
	public static String C_HELP_ITEM_HELP_TITLE = "Help";
	public static String C_HELP_ITEM_ABOUT_TITLE = "About...";
	
	// Calculation tab
	public static String C_SELECT_MATERIALS_LABEL = "Select materials:";
	public static String C_MATERIAL_LIBRARY_NAME = "Materials library: ";
	
	public static String C_BROWSE_BUTTON = "Browse";
	public static String C_ADD_MATERIALS_FROM_LIBRARY_BUTTON = ">>";
	
	public static String C_CALCULATION_STEPS_TITLE = "Calculation steps:";
	public static String C_RESULTS_TITLE = "Results:";
	
	//Library tab
	public static String C_OPEN_LIBRARY_BUTTON = "Open library";
	public static String C_NEW_LIBRARY_BUTTON = "New library";
	public static String C_DELETE_MATERIAL_BUTTON = "Delete material";
	public static String C_ADD_MATERIAL_BUTTON = "Add material";
	public static String C_CANCEL_BUTTON = "Cancel";
	public static String C_SAVE_LIBRARY_AS_BUTTON = "Save as...";

	public static String C_MANAGED_LIBRARY_NAME_LABEL = "Libary name: ";
	
	public static String C_VALUE_COLUMN = "Value";
	public static String C_PROPERTY_COLUMN = "Property";
	
	//Dialogs messages
	public static String C_INSERT_NEW_LIBRARY_NAME = "Insert new library name:";
	public static String C_INSERT_NEW_MATERIAL_NAME = "Insert new material name:";
	public static String C_WANT_TO_CONTINUE = "The previous library was modified but the changes were not saved.\n"
			+ "Do you like to continue?";
	public static String C_WANT_TO_DELETE_MATERIAL = " was selected to be removed.\n"
			+ "Do you like to continue?";
	public static String C_WARNING_TITLE = "Warning";
	
	
	//Error messages
	public static String C_ERROR_DIALOG_TITLE = "Error";
	public static String C_ERROR_WHILE_BROWSING_HELP = "An error occurred while we were trying to find somebody"
			+ " who could help you.";
	public static String C_GLOBAL_ERROR = "Something odd occurred and the appliation will finish.\n"
			+ "I've seen things you people wouldn't believe: Attack ships on fire off the shoulder of Orión.\n"
			+ "I've watched c-beams glitter in the dark near the Tannhäuser Gate.\n"
			+ "All those... moments will be lost in time, like tears... in rain. Time to die";

	public static String C_ERROR_PROPERTY_NOT_FOUND = "The given property does not exist in this material.";
	public static String C_ERROR_REPEATED_NAME = "The given material name already exist in this library.";
	public static String C_ERROR_MATERIAL_NOT_FOUND = "The given material was not found.";
	
	// About window properties
	public static int C_ABOUT_PREFERED_POSITION_X_AT_START = 100;
	public static int C_ABOUT_PREFERED_POSITION_Y_AT_START = 100;
	public static int C_ABOUT_PREFERED_SIZE_X = 450;
	public static int C_ABOUT_PREFERED_SIZE_Y = 300;
	
	//About window
	public static String C_ABOUT_WINDOW_TITLE = "About " + C_MAIN_WINDOW_TITLE;
	public static String C_OK_BUTTON = "OK";
	public static String C_ABOUT_INFO = "<html>" + C_APP_NAME_VERSION +
		"<br>" + "Copyright (C) 2015  David MERAYO FERNÁNDEZ" + "<br>" + 
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
	
}
