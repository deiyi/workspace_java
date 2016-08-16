import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

/**
 * @author david.merayo
 * @version 1.0.0
 * This class creates the Calculation Panel and manages its actions.
 */
@SuppressWarnings("serial")
public class CalculationPanel extends JPanel {

	//GUI elements
	private JPanel calculationMaterialsPanel;
	private JPanel calculationStepsPanel;
	private JPanel resultsPanel;
	private JLabel resultsLabel;
	private JScrollPane resultsScrollPanel;
	private JTable resultsTable;
	private JLabel calculationStepsLabel;
	private JScrollPane calculationStepsScrollPanel;
	private JTable calculationStepsTable;
	private JLabel calculationLibraryLabel;
	private JTextField libraryPathTextBox;
	private JButton browseLibraryButton;
	private JButton addLibraryToCalculationButton;
	private JScrollPane calculationMaterialsScrollPanel;
	private JList<String> materialsOnLibraryList;
	private JLabel selectMaterialLabel;
	
	//Class properties
	private MaterialLibrary calculationMaterials;
	private JButton clearDataButton;
	
	
	/**
	 * Create the panel.
	 */
	public CalculationPanel() {
		super();
		initializeCalculationPanel();
		
		calculationMaterials = new MaterialLibrary(" ");
	}
	
	
	/**
	 * Draw the Calculation panel and add all its objects.
	 */
	private void initializeCalculationPanel() {
		
		// Panel calculation
		GridBagLayout gbl_panelCalculation = new GridBagLayout();
		gbl_panelCalculation.columnWidths = new int[] {0};
		gbl_panelCalculation.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panelCalculation.columnWeights = new double[]{1.0};
		gbl_panelCalculation.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		this.setLayout(gbl_panelCalculation);
		
		// Calculation materials panel
		{
			calculationMaterialsPanel = new JPanel();
			GridBagConstraints gbc_calculationMaterialsPanel = new GridBagConstraints();
			gbc_calculationMaterialsPanel.insets = new Insets(0, 0, 5, 0);
			gbc_calculationMaterialsPanel.fill = GridBagConstraints.BOTH;
			gbc_calculationMaterialsPanel.gridx = 0;
			gbc_calculationMaterialsPanel.gridy = 0;
			
			this.add(calculationMaterialsPanel, gbc_calculationMaterialsPanel);
			GridBagLayout gbl_calculationMaterialsPanel = new GridBagLayout();
			gbl_calculationMaterialsPanel.columnWidths = new int[]{204, 0, 27, 117, 0};
			gbl_calculationMaterialsPanel.rowHeights = new int[]{25, 0, 0, 0, 0};
			gbl_calculationMaterialsPanel.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_calculationMaterialsPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			calculationMaterialsPanel.setLayout(gbl_calculationMaterialsPanel);
			
			selectMaterialLabel = new JLabel(Constants.C_SELECT_MATERIALS_LABEL);
			GridBagConstraints gbc_selectMaterialLabel = new GridBagConstraints();
			gbc_selectMaterialLabel.anchor = GridBagConstraints.NORTHWEST;
			gbc_selectMaterialLabel.insets = new Insets(0, 0, 5, 0);
			gbc_selectMaterialLabel.gridx = 3;
			gbc_selectMaterialLabel.gridy = 0;
			calculationMaterialsPanel.add(selectMaterialLabel, gbc_selectMaterialLabel);
			
			clearDataButton = new JButton(Constants.C_CLEAR_DATA_BUTTON);
			clearDataButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					actionOnClicClearData();
				}
			});
			GridBagConstraints gbc_clearDataButton = new GridBagConstraints();
			gbc_clearDataButton.fill = GridBagConstraints.HORIZONTAL;
			gbc_clearDataButton.gridwidth = 2;
			gbc_clearDataButton.insets = new Insets(0, 0, 5, 5);
			gbc_clearDataButton.gridx = 1;
			gbc_clearDataButton.gridy = 1;
			calculationMaterialsPanel.add(clearDataButton, gbc_clearDataButton);
			
			calculationMaterialsScrollPanel = new JScrollPane();
			GridBagConstraints gbc_calculationMaterialsScrollPanel = new GridBagConstraints();
			gbc_calculationMaterialsScrollPanel.fill = GridBagConstraints.BOTH;
			gbc_calculationMaterialsScrollPanel.gridheight = 3;
			gbc_calculationMaterialsScrollPanel.gridx = 3;
			gbc_calculationMaterialsScrollPanel.gridy = 1;
			calculationMaterialsPanel.add(calculationMaterialsScrollPanel, gbc_calculationMaterialsScrollPanel);
			
			{
				materialsOnLibraryList = new JList<String>();
				materialsOnLibraryList.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent arg0) {
						materialsOnLibraryListSelectionChanged();
					}
				});
				calculationMaterialsScrollPanel.setViewportView(materialsOnLibraryList);
			}
			
			calculationLibraryLabel = new JLabel(Constants.C_MATERIAL_LIBRARY_NAME);
			GridBagConstraints gbc_calculationLibraryLabel = new GridBagConstraints();
			gbc_calculationLibraryLabel.anchor = GridBagConstraints.WEST;
			gbc_calculationLibraryLabel.insets = new Insets(0, 0, 5, 5);
			gbc_calculationLibraryLabel.gridx = 0;
			gbc_calculationLibraryLabel.gridy = 1;
			calculationMaterialsPanel.add(calculationLibraryLabel, gbc_calculationLibraryLabel);
			
			libraryPathTextBox = new JTextField();
			libraryPathTextBox.getDocument().addDocumentListener(new DocumentListener() {	//Add change listeners
			    public void changedUpdate(DocumentEvent e) {
			    	checkEnabledButtons();
			    }
			    public void removeUpdate(DocumentEvent e) {
			    	checkEnabledButtons();
			    }
			    public void insertUpdate(DocumentEvent e) {
			    	checkEnabledButtons();
			    }
			
			    public void checkEnabledButtons() {
			       if (libraryPathTextBox.getText().isEmpty()) {
			    	   addLibraryToCalculationButton.setEnabled(false);	//The button is disabled when the textField is empty
			       } else {
			    	   addLibraryToCalculationButton.setEnabled(true);
			       }
			    }
			});
			GridBagConstraints gbc_libraryPathTextBox = new GridBagConstraints();
			gbc_libraryPathTextBox.insets = new Insets(0, 0, 5, 5);
			gbc_libraryPathTextBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_libraryPathTextBox.gridx = 0;
			gbc_libraryPathTextBox.gridy = 2;
			calculationMaterialsPanel.add(libraryPathTextBox, gbc_libraryPathTextBox);
			libraryPathTextBox.setColumns(10);
			
			browseLibraryButton = new JButton(Constants.C_BROWSE_BUTTON);
			browseLibraryButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionOnClicBrowseLibrary();
				}
			});
			GridBagConstraints gbc_browseLibraryButton = new GridBagConstraints();
			gbc_browseLibraryButton.insets = new Insets(0, 0, 5, 5);
			gbc_browseLibraryButton.gridx = 1;
			gbc_browseLibraryButton.gridy = 2;
			calculationMaterialsPanel.add(browseLibraryButton, gbc_browseLibraryButton);
			
			addLibraryToCalculationButton = new JButton(Constants.C_ADD_MATERIALS_FROM_LIBRARY_BUTTON);
			addLibraryToCalculationButton.setEnabled(false);
			addLibraryToCalculationButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionOnClicAddMaterialsToList();
				}
			});
			GridBagConstraints gbc_addLibraryToCalculationButton = new GridBagConstraints();
			gbc_addLibraryToCalculationButton.insets = new Insets(0, 0, 5, 5);
			gbc_addLibraryToCalculationButton.gridx = 2;
			gbc_addLibraryToCalculationButton.gridy = 2;
			calculationMaterialsPanel.add(addLibraryToCalculationButton, gbc_addLibraryToCalculationButton);
		}
		
		// Calculation steps panel
		{
			calculationStepsPanel = new JPanel();
			GridBagConstraints gbc_calculationStepsPanel = new GridBagConstraints();
			gbc_calculationStepsPanel.insets = new Insets(0, 0, 5, 0);
			gbc_calculationStepsPanel.fill = GridBagConstraints.BOTH;
			gbc_calculationStepsPanel.gridx = 0;
			gbc_calculationStepsPanel.gridy = 1;
			this.add(calculationStepsPanel, gbc_calculationStepsPanel);
			calculationStepsPanel.setLayout(new BorderLayout(0, 0));
			
			calculationStepsLabel = new JLabel(Constants.C_CALCULATION_STEPS_TITLE);
			calculationStepsPanel.add(calculationStepsLabel, BorderLayout.NORTH);
			
			calculationStepsScrollPanel = new JScrollPane();
			calculationStepsPanel.add(calculationStepsScrollPanel, BorderLayout.CENTER);
			
			{
				calculationStepsTable = new JTable() {
					public boolean isCellEditable(int row,int column) {
						return false; 
					}
				}; 
				calculationStepsScrollPanel.setViewportView(calculationStepsTable);
			}
		}
		
		// Calculation results panel
		{
			resultsPanel = new JPanel();
			GridBagConstraints gbc_resultsPanel = new GridBagConstraints();
			gbc_resultsPanel.fill = GridBagConstraints.BOTH;
			gbc_resultsPanel.gridx = 0;
			gbc_resultsPanel.gridy = 2;
			this.add(resultsPanel, gbc_resultsPanel);
			resultsPanel.setLayout(new BorderLayout(0, 0));
			
			resultsLabel = new JLabel(Constants.C_RESULTS_TITLE);
			resultsPanel.add(resultsLabel, BorderLayout.NORTH);
			
			resultsScrollPanel = new JScrollPane();
			resultsPanel.add(resultsScrollPanel, BorderLayout.CENTER);
			
			{
				resultsTable = new JTable() {
					public boolean isCellEditable(int row,int column) {
						return false; 
					}
				}; 
				resultsScrollPanel.setViewportView(resultsTable);
			}
		}
	}
	
	
	/**
	 * Action executed when the Browse Library button is pressed.
	 */
	private void actionOnClicBrowseLibrary() {
		//Initialize Open... dialog
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter(Constants.C_LIBRARY_IO_DESCRIPTION,
				Constants.C_LIBRARY_EXTENSION);
        fileChooser.addChoosableFileFilter(filter);
        
        //If approved, write the path on the TextField
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			libraryPathTextBox.setText(file.getPath());
		}
	}
	
	
	/**
	 * Action executed when the Add material (>>) button is pressed.
	 */
	private void actionOnClicAddMaterialsToList() {
		try {
			MaterialLibrary libraryToAdd = new MaterialLibrary(new File(libraryPathTextBox.getText()));
			calculationMaterials.mergeAndCheck(libraryToAdd);
			
			//Add materials to the materials list
			for(String name: libraryToAdd.getMaterialNames()) {
				GUIGeneralMethods.addElementToStringJList(materialsOnLibraryList, name);
			}
			libraryPathTextBox.setText("");
			
		} catch (ClassCastException | ParserConfigurationException | SAXException | IOException e) {
			JOptionPane.showMessageDialog(null, Constants.C_ERROR_READING_XML_FILE, Constants.C_ERROR_DIALOG_TITLE,
					JOptionPane.ERROR_MESSAGE); 
			e.printStackTrace();
		} catch(IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), Constants.C_ERROR_DIALOG_TITLE, JOptionPane.ERROR_MESSAGE); 
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Action executed when the Clear data button is pressed.
	 */
	private void actionOnClicClearData() {
		if (!calculationMaterials.isEmpty()) {
			int dialogResult = JOptionPane.showConfirmDialog(null, Constants.C_WANT_TO_CLEAR_DATA, 
					Constants.C_WARNING_TITLE, JOptionPane.YES_NO_OPTION);
			if(dialogResult == JOptionPane.YES_OPTION){
				clearCalculationData();
			}
		} else {
			clearCalculationData();
		}
	}
	
	
	/**
	 * This method clears all calculation data.
	 */
	private void clearCalculationData() {
		calculationMaterials.clear();
		clearAllCalculationTabData();
	}

	/**
	 * This methods clears all information stored in the Calculation tab.
	 */
	private void clearAllCalculationTabData() {
		libraryPathTextBox.setText("");
		addLibraryToCalculationButton.setEnabled(false);
		
		//Clear data on materials list
		DefaultListModel<String> listModel = (DefaultListModel<String>) materialsOnLibraryList.getModel();
        listModel.removeAllElements();
		
        //Clear data on JTables
		((DefaultTableModel)resultsTable.getModel()).setRowCount(0);	//Remove content
		((DefaultTableModel)calculationStepsTable.getModel()).setRowCount(0);	//Remove content
	}
	
	/**
	 * This method is executed when the material selection changed.
	 */
	private void materialsOnLibraryListSelectionChanged() {
		MaterialLibrary auxLibrary = new MaterialLibrary(" ");
		
		int[] selectedMaterials = materialsOnLibraryList.getSelectedIndices();
		if (selectedMaterials.length > 0) {
			//Get all the selected materials and add them to the auxiliary library
			for (int index: selectedMaterials){
				String selectedMaterial = materialsOnLibraryList.getModel().getElementAt(index);
				auxLibrary.addMaterial(calculationMaterials.getMaterialByName(selectedMaterial));
			}
			
			performCalculationAndShowResults(auxLibrary);
		} else {
	        //Clear data on JTables
			((DefaultTableModel)resultsTable.getModel()).setRowCount(0);
			((DefaultTableModel)calculationStepsTable.getModel()).setRowCount(0);
		}
	}

	/**
	 * This method performs the calculations and shows the results.
	 * @param library The library containing the materials used to perform the calculus.
	 */
	private void performCalculationAndShowResults(MaterialLibrary library) {
		LinkedHashMap<String, LinkedHashMap<String, String>> calculationResults = performCalculation(library);
		
		showSLCalculationSteps(calculationResults);
		showSLFinalResults(calculationResults);
	}


	/**
	 * This method shows the intermediate weighted SL for each material and property.
	 * @param calculationResults The values to be shown.
	 */
	private void showSLCalculationSteps(LinkedHashMap<String, LinkedHashMap<String, String>> calculationResults) {
		//Show results
        List<String> columns = new ArrayList<String>();
        List<String[]> values = new ArrayList<String[]>();

        //Set column titles
        columns.add(Constants.C_NAME_PROPERTY);
        for(String elementName: Constants.C_SHOWN_PROPERTIES) {
            columns.add(elementName);
        }
        
        //for each material
        for (String materialName: calculationResults.keySet()) {
        	LinkedHashMap<String, String> materialSLData = calculationResults.get(materialName);
        	
        	Vector<String> row = new Vector<String>();
        	row.add(materialName);
        	for(String propertyName: Constants.C_SHOWN_PROPERTIES) {
        		double value = new Double(materialSLData.get(propertyName));
        		row.add(GUIGeneralMethods.round(value, 2));
        		//System.out.println(materialName + "\t" + propertyName + ": " + materialSLData.get(propertyName));
        	}
        	values.add(row.toArray(new String[] {}));
        }

        TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());
        calculationStepsTable.setModel(tableModel);
	}


	/**
	 * This method shows the final SL results.
	 * @param calculationResults The intermediate calculation SL results.
	 */
	private void showSLFinalResults(LinkedHashMap<String, LinkedHashMap<String, String>> calculationResults) {
		LinkedHashMap<String, Double> finalChemicalResults = calculateFinalChemicalResults(calculationResults);
		LinkedHashMap<String, Double> finalMechanicalResults = calculateFinalMechanicalResults(calculationResults);
		
		//Show results
        List<String> columns = new ArrayList<String>();
        List<String[]> values = new ArrayList<String[]>();

        //Set column titles
        columns.add(Constants.C_NAME_PROPERTY);
        columns.add(Constants.C_CHEMICAL_SL_VALUE_COLUMN);
        columns.add(Constants.C_MECHANICAL_SL_VALUE_COLUMN);
        columns.add(Constants.C_TOTAL_SL_VALUE_COLUMN);
        
        //for each material
        for (String materialName: finalChemicalResults.keySet()) {
    		double chemicalSLValue = new Double(finalChemicalResults.get(materialName));
    		double mechanicalSLValue = new Double(finalMechanicalResults.get(materialName));
    		double totalSLValue = new Double(chemicalSLValue * Constants.C_RI_CHEMICAL_MECHANICAL_TOTAL[0]
    				+ mechanicalSLValue * Constants.C_RI_CHEMICAL_MECHANICAL_TOTAL[1]);
    		
            values.add(new String[] {materialName, GUIGeneralMethods.round(chemicalSLValue, 2), 
            		GUIGeneralMethods.round(mechanicalSLValue, 2), GUIGeneralMethods.round(totalSLValue, 2)});
        }

        TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());
		resultsTable.setModel(tableModel);
	}


	/**
	 * This method calculates the final chemical methodology results.
	 * @param calculationResults The intermediate SL for each property and material.
	 * @return The final SL for each material.
	 */
	private LinkedHashMap<String, Double> calculateFinalChemicalResults(
			LinkedHashMap<String, LinkedHashMap<String, String>> calculationResults) {
		LinkedHashMap<String, Double> finalResults = new LinkedHashMap<String, Double>();
		
		//for each material and each property, sum
		for(String materialName: calculationResults.keySet()) {
			double finalSLValue = 0;
			for (String propertyName: Constants.C_ALL_CASES_MATERIAL_PROPERTIES) {
				finalSLValue += Double.parseDouble(calculationResults.get(materialName).get(propertyName));
			}
			finalResults.put(materialName, finalSLValue);
		}
		
		return finalResults;
	}


	/**
	 * This method calculates the final mechanical methodology results.
	 * @param calculationResults The intermediate SL for each property and material.
	 * @return The final SL for each material.
	 */
	private LinkedHashMap<String, Double> calculateFinalMechanicalResults(
			LinkedHashMap<String, LinkedHashMap<String, String>> calculationResults) {
		LinkedHashMap<String, Double> finalResults = new LinkedHashMap<String, Double>();
		
		//for each material and each property, sum
		for(String materialName: calculationResults.keySet()) {
			double finalSLValue = 0;
			for (String propertyName: Constants.C_MECHANICAL_PROPERTIES) {
				finalSLValue += Double.parseDouble(calculationResults.get(materialName).get(propertyName));
			}
			finalResults.put(materialName, finalSLValue);
		}
		
		return finalResults;
	}


	/**
	 * This method calculates all the weighted stringency levels
	 * @param library The library containing the materials used to perform the calculus.
	 * @return The weighted stringency levels for each material.
	 */
	private LinkedHashMap<String, LinkedHashMap<String, String>> performCalculation(MaterialLibrary library) {
		LinkedHashMap<String, LinkedHashMap<String, String>> result = new LinkedHashMap<String, LinkedHashMap<String, String>>();
		
		//For each material considered in the calculus
		for (Material material: library.getAllMaterial()) {
			String materialName = material.getName();
			LinkedHashMap<String, String> materialStringencyResults = new LinkedHashMap<String, String>();
			
			//for each material property
			for (String property: material.getPropertyNames()) {
				double stringencyLevel = 1.0 ;	//Initialize variable, just in case...
				double ri = Constants.getRiValueFor(property);
				
				//Calculation is different depending on the case
				if (Arrays.asList(Constants.C_CASE1_MATERIAL_PROPERTIES).contains(property)) {
					double minLs = calculateMinLs(library, property);
					stringencyLevel = case1Calculation(material, property, minLs);
				} else if (Arrays.asList(Constants.C_CASE2_MATERIAL_PROPERTIES).contains(property)) {
					stringencyLevel = case2Calculation(material, property);
				} else if (Arrays.asList(Constants.C_CASE3_MATERIAL_PROPERTIES).contains(property)) {
					double maxLs = calculateMaxLs(library, property);
					stringencyLevel = case3Calculation(material, property, maxLs);
				} else if (Arrays.asList(Constants.C_MECHANICAL_PROPERTIES).contains(property)) {
					double maxLs = calculateMaxLs(library, property);
					stringencyLevel = case3Calculation(material, property, maxLs);
				}
				
				//Stringecy level is weighted
				materialStringencyResults.put(property, Double.toString(stringencyLevel * ri));
			}
			result.put(materialName, materialStringencyResults);
		}
		
		return result;
	}


	/**
	 * This method calculates the maximum Ls value for a given property for all the materials in a given library.
	 * @param library The library containing the materials
	 * @param property The relevant property we are dealing with
	 * @return The maximum Ls value or 0 if it is never specified (in this case, the Case 3 calculation will always return 1.0).
	 */
	private double calculateMaxLs(MaterialLibrary library, String property) {
		double maximum = 0;
		
		for(Material material: library.getAllMaterial()){
			try {    
				double numericPropValue = Double.parseDouble(material.getPropertyValue(property));
				maximum = numericPropValue > maximum ? numericPropValue : maximum;
		    } catch(NumberFormatException nfe) {}	//Fail while parsing double
		}
		
		return maximum;
	}


	/**
	 * This method calculates the minimum Ls value for a given property for all the materials in a given library.
	 * @param library The library containing the materials
	 * @param property The relevant property we are dealing with
	 * @return The minimum Ls value or 100 if it is never specified (in this case, the Case 1 calculation will always return 1.0).
	 */
	private double calculateMinLs(MaterialLibrary library, String property) {
		double minimum = 100;
		
		for(Material material: library.getAllMaterial()){
			try {    
				double numericPropValue = Double.parseDouble(material.getPropertyValue(property));
		        minimum = numericPropValue < minimum ? numericPropValue : minimum;
		    } catch(NumberFormatException nfe) {}	//Fail while parsing double
		}
		
		return minimum;
	}
	
	
	/**
	 * This method performs the case 1 calculations.
	 * @param material Material whose properties are been treated
	 * @param property The property to treat
	 * @param minLs The minimum Ls value found in the library.
	 * @return The Stringency Level related to this property.
	 */
	private double case1Calculation(Material material, String property, double minLs) {
		String propertyValue = material.getPropertyValue(property);
		
		if (GUIGeneralMethods.isNumeric(propertyValue)) {
			double valueLs = Double.parseDouble(propertyValue);
			double resultSL = 5.0 * minLs / valueLs;
			resultSL = resultSL < 1.0 ? 1.0 : resultSL;
			resultSL = resultSL > 5.0 ? 5.0 : resultSL;
			
			return resultSL;

		} else {	//If requirement is specified or is not a number
			return 1.0;		
		}
	}


	/**
	 * This method performs the case 2 calculations.
	 * @param material Material whose properties are been treated
	 * @param property The property to treat
	 * @return The Stringency Level related to this property.
	 */
	private double case2Calculation(Material material, String property) {
		String propertyValue = material.getPropertyValue(property);
		
		//If requirement is not specified or is not a number
		if (!GUIGeneralMethods.isNumeric(propertyValue)) {
			return 1.0;		
			
		} else {
			double valueLs = Double.parseDouble(propertyValue);
			double valueLe = Double.parseDouble(Constants.getLeValueFor(property));
			double ratio = valueLs / valueLe;
			
			if (ratio >= 1.0) {
				return 1.0;
			} else if ((ratio < 1.0) && (ratio >= 0.9)) {
				return 2.0;
			} else if ((ratio < 0.9) && (ratio >= 0.8)) {
				return 3.0;
			} else if ((ratio < 0.8) && (ratio >= 0.7)) {
				return 4.0;
			} else {	//ratio < 0.7
				return 5.0;
			}
		}
	}
	
	
	/**
	 * This method performs the case 3 calculations.
	 * @param material Material whose properties are been treated
	 * @param property The property to treat
	 * @param maxLs The maximum Ls value found in the library.
	 * @return The Stringency Level related to this property.
	 */
	private double case3Calculation(Material material, String property, double maxLs) {
		String propertyValue = material.getPropertyValue(property);
		
		if (GUIGeneralMethods.isNumeric(propertyValue)) {
			double valueLs = Double.parseDouble(propertyValue);
			double resultSL = 5.0 * valueLs / maxLs;
			resultSL = resultSL < 1.0 ? 1.0 : resultSL;
			resultSL = resultSL > 5.0 ? 5.0 : resultSL;
			
			return resultSL;
			
		} else {	//If requirement is not specified or is not a number
			return 1.0;		
		}
	}
}

