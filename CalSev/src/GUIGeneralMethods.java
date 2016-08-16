import java.awt.Desktop;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 * @author david.merayo
 * @version 1.0.0
 * This class contains some standard methods
 */
public class GUIGeneralMethods {

	/**
	 * This method removes an element (String) from a JList<String>
	 * @param stringList JList where the element should be removed
	 * @param elementToRemove Item to be removed.
	 * @throws ArrayIndexOutOfBoundsException If the element is not found.
	 */
	public static void removeElementFromStringJList(JList<String> stringList, String elementToRemove) throws ArrayIndexOutOfBoundsException {
		int materialToBeRemovedIndex = -1;
		DefaultListModel<String> listModel = new DefaultListModel<String>();
	    for(int i = 0; i < stringList.getModel().getSize(); i++) {
	    	String element = stringList.getModel().getElementAt(i).toString();
	    	listModel.addElement(element);
	    	
	    	if (element.equals(elementToRemove)) {
	    		materialToBeRemovedIndex = i;
	    	}
	    }       
	    
	    listModel.remove(materialToBeRemovedIndex);
	    stringList.setModel(listModel);
	    stringList.setSelectedIndex(listModel.getSize() - 1);
	}
	
	/**
	 * This method adds an element (String) to the given JList
	 * @param stringList The JList where the element will be added
	 * @param elementToAdd The element to add.
	 */
	public static void addElementToStringJList(JList<String> stringList, String elementToAdd) {
		DefaultListModel<String> listModel = new DefaultListModel<String>();
	    for(int i = 0; i < stringList.getModel().getSize(); i++) {
	    	listModel.addElement(stringList.getModel().getElementAt(i).toString());
	    }        
	    listModel.addElement(elementToAdd);
	    stringList.setModel(listModel);
	    
	    stringList.setSelectedIndex(listModel.getSize() - 1);
	}

	/**
	 * This method opens a web page in the default browser.
	 * @param uri URI direction for the web-page to be opened.
	 * @throws IOException if there is a problem while browsing.
	 */
	public static void openWebPage(URI uri) throws IOException {
	    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
	    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
	        desktop.browse(uri);
	    }
	}

	/**
	 * This method opens a web page in the default browser.
	 * @param url URL direction to the web to be opened.
	 * @throws URISyntaxException if there is a syntactic error in the URL.
	 * @throws IOException if there is a problem while browsing.
	 */
	public static void openWebPage(URL url) throws URISyntaxException, IOException {
        openWebPage(url.toURI());
	}
	
	/**
	 * This method checks whether a string is a digit or not
	 * @param str String to be checked
	 * @return true if it could be parsed as double and false in any other case.
	 */
	public static boolean isNumeric(String str) {  
	    try {    
	        Double.parseDouble(str);    
	    } catch(NumberFormatException nfe) {    
	        return false;    
	    }    
	    
	    return true;    
	}
	
	/**
	 * This method round a decimal number as required
	 * @param value The number to be rounded.
	 * @param places Decimal places required.
	 * @return The rounded String.
	 */
	public static String round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return new Double(bd.doubleValue()).toString();
	}
}
