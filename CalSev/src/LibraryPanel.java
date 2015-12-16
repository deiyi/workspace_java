import javax.swing.JFrame;
import java.awt.BorderLayout;

import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import javax.swing.JList;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.event.ListSelectionEvent;


@SuppressWarnings("serial")
public class LibraryPanel extends JPanel {

	private JFrame frame;
	private JPanel libraryManagementPanel;
	private JPanel materialManagementPanel;
	private JLabel libraryNameLabel;
	private JButton openLibraryButton;
	private JButton newLibraryButton;
	private JButton deleteMaterialButton;
	private JButton addMaterialButton;
	private JButton saveAsButton;
	private JTable materialPropertiesTable;
	private JScrollPane materialPropertiesScrollPanel;
	private JList<String> availableMaterialsList;
	private JScrollPane availableMaterialsScrollPanel;
	
	private MaterialLibrary managedLibrary;
	
	/**
	 * Create the panel.
	 */
	public LibraryPanel() {
		super();
		initializeLibraryPanel();
	}

	/**
	 * Draw the Library panel and add all its objects.
	 */
	private void initializeLibraryPanel() {
		this.setLayout(new BorderLayout(0, 0));
		
		{
			libraryManagementPanel = new JPanel();
			this.add(libraryManagementPanel, BorderLayout.WEST);
			GridBagLayout gbl_libraryManagementPanel = new GridBagLayout();
			gbl_libraryManagementPanel.columnWidths = new int[]{0, 0};
			gbl_libraryManagementPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
			gbl_libraryManagementPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
			gbl_libraryManagementPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
			libraryManagementPanel.setLayout(gbl_libraryManagementPanel);
			
			openLibraryButton = new JButton(Constants.C_OPEN_LIBRARY_BUTTON);
			openLibraryButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					actionOnClicOpenLibrary();
				}
			});
			GridBagConstraints gbc_openLibraryButton = new GridBagConstraints();
			gbc_openLibraryButton.fill = GridBagConstraints.HORIZONTAL;
			gbc_openLibraryButton.insets = new Insets(0, 0, 5, 0);
			gbc_openLibraryButton.gridx = 0;
			gbc_openLibraryButton.gridy = 0;
			libraryManagementPanel.add(openLibraryButton, gbc_openLibraryButton);
			
			newLibraryButton = new JButton(Constants.C_NEW_LIBRARY_BUTTON);
			newLibraryButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionOnClicNewLibrary();
				}
			});
			GridBagConstraints gbc_newLibraryButton = new GridBagConstraints();
			gbc_newLibraryButton.fill = GridBagConstraints.HORIZONTAL;
			gbc_newLibraryButton.insets = new Insets(0, 0, 5, 0);
			gbc_newLibraryButton.gridx = 0;
			gbc_newLibraryButton.gridy = 1;
			libraryManagementPanel.add(newLibraryButton, gbc_newLibraryButton);
			
			deleteMaterialButton = new JButton(Constants.C_DELETE_MATERIAL_BUTTON);
			deleteMaterialButton.setEnabled(false);
			deleteMaterialButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionOnClicDeleteMaterial();
				}
			});
			
			saveAsButton = new JButton(Constants.C_SAVE_LIBRARY_AS_BUTTON);
			GridBagConstraints gbc_saveAsButton = new GridBagConstraints();
			gbc_saveAsButton.fill = GridBagConstraints.HORIZONTAL;
			gbc_saveAsButton.insets = new Insets(0, 0, 5, 0);
			gbc_saveAsButton.gridx = 0;
			gbc_saveAsButton.gridy = 2;
			libraryManagementPanel.add(saveAsButton, gbc_saveAsButton);
			saveAsButton.setEnabled(false);
			saveAsButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionOnClicSaveLibraryAs();
				}
			});
			
			availableMaterialsScrollPanel = new JScrollPane();
			GridBagConstraints gbc_availableMaterialsScrollPanel = new GridBagConstraints();
			gbc_availableMaterialsScrollPanel.fill = GridBagConstraints.BOTH;
			gbc_availableMaterialsScrollPanel.insets = new Insets(0, 0, 5, 0);
			gbc_availableMaterialsScrollPanel.gridx = 0;
			gbc_availableMaterialsScrollPanel.gridy = 3;
			libraryManagementPanel.add(availableMaterialsScrollPanel, gbc_availableMaterialsScrollPanel);
			
			//String item2[] = {"ASME mat1", "mat2", "KTA mat3", "mat4", "mat5", "mat6", "mat7", "mat8", "mat9"};
			availableMaterialsList = new JList<String>();
			availableMaterialsList.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent arg0) {
					availableMaterialsListSelectionChanged();
				}
			});
			availableMaterialsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			availableMaterialsList.setModel(new DefaultListModel<String>());
			availableMaterialsScrollPanel.setViewportView(availableMaterialsList);
			
			GridBagConstraints gbc_deleteMaterialButton = new GridBagConstraints();
			gbc_deleteMaterialButton.fill = GridBagConstraints.HORIZONTAL;
			gbc_deleteMaterialButton.insets = new Insets(0, 0, 5, 0);
			gbc_deleteMaterialButton.gridx = 0;
			gbc_deleteMaterialButton.gridy = 4;
			libraryManagementPanel.add(deleteMaterialButton, gbc_deleteMaterialButton);
			
			addMaterialButton = new JButton(Constants.C_ADD_MATERIAL_BUTTON);
			addMaterialButton.setEnabled(false);
			addMaterialButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionOnClicAddMaterial();
				}
			});
			GridBagConstraints gbc_addMaterialButton = new GridBagConstraints();
			gbc_addMaterialButton.fill = GridBagConstraints.HORIZONTAL;
			gbc_addMaterialButton.gridx = 0;
			gbc_addMaterialButton.gridy = 5;
			libraryManagementPanel.add(addMaterialButton, gbc_addMaterialButton);
			
			materialManagementPanel = new JPanel();
			this.add(materialManagementPanel, BorderLayout.CENTER);
			GridBagLayout gbl_materialManagementPanel = new GridBagLayout();
			gbl_materialManagementPanel.columnWidths = new int[]{0, 0};
			gbl_materialManagementPanel.rowHeights = new int[]{0, 0, 0};
			gbl_materialManagementPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			gbl_materialManagementPanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
			materialManagementPanel.setLayout(gbl_materialManagementPanel);
		}
		
		{
			libraryNameLabel = new JLabel(Constants.C_MANAGED_LIBRARY_NAME_LABEL);
			GridBagConstraints gbc_libraryNameLabel = new GridBagConstraints();
			gbc_libraryNameLabel.anchor = GridBagConstraints.WEST;
			gbc_libraryNameLabel.insets = new Insets(0, 0, 5, 0);
			gbc_libraryNameLabel.gridx = 0;
			gbc_libraryNameLabel.gridy = 0;
			materialManagementPanel.add(libraryNameLabel, gbc_libraryNameLabel);
			
			materialPropertiesScrollPanel = new JScrollPane();
			GridBagConstraints gbc_materialPropertiesScrollPanel = new GridBagConstraints();
			gbc_materialPropertiesScrollPanel.fill = GridBagConstraints.BOTH;
			gbc_materialPropertiesScrollPanel.gridx = 0;
			gbc_materialPropertiesScrollPanel.gridy = 1;
			materialManagementPanel.add(materialPropertiesScrollPanel, gbc_materialPropertiesScrollPanel);
			
			/*
			List<String> columns = new ArrayList<String>();
	        List<String[]> values = new ArrayList<String[]>();
 
	        columns.add("Property");
	        columns.add("Value");
	        
	        values.add(new String[] {"Name", "ASME mat1"});
	        values.add(new String[] {"Norm", "ASME"});
	        values.add(new String[] {"Fósforo", "0.15"});
	        values.add(new String[] {"Cobre", "0.15"});
	        values.add(new String[] {"Níquel", "0.1"});
	        values.add(new String[] {"Niobio", "0.05"});
	        values.add(new String[] {"Tantalio ", "0.05"});
	        values.add(new String[] {"Cobalto.", "0.05"});
	        values.add(new String[] {"Nitrógeno", "0.05"});
	        values.add(new String[] {"Manganeso", "0.05"});
	        values.add(new String[] {"Molibdeno", "0.05"});
	        values.add(new String[] {"Silicio", "0.05"});
	        values.add(new String[] {"Azufre", "0.1"});
	        values.add(new String[] {"Cromo", "0.05"});
	        values.add(new String[] {"Vanadio", "0.1"});
	        
	        TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());
			materialPropertiesTable = new JTable(tableModel);*/
			materialPropertiesTable = new JTable() {
				public boolean isCellEditable(int row,int column) {
					switch(column){             
			        	case 0:  // select the cell you want make it not editable 
			        		return false;  
			        	default: return true;
			        }  
				}
			}; 
	        new TableCellListener(materialPropertiesTable , new AbstractAction() {
	            public void actionPerformed(ActionEvent e) {
	                TableCellListener tcl = (TableCellListener)e.getSource();
	                cellChangeOnMaterialPropertiesTable(tcl);
	            }
	        });
            
			materialPropertiesScrollPanel.setViewportView(materialPropertiesTable);
		}
	}
	
	/**
	 * Action executed when the Open library button is pressed.
	 * TODO Add code
	 */
	private void actionOnClicOpenLibrary() {
		if (managedLibrary == null) {
			openLibrary();
		} else if (managedLibrary.hasChanged()) {	//if the current library was not saved since the last change
			int dialogResult = JOptionPane.showConfirmDialog(null, Constants.C_WANT_TO_CONTINUE, 
					Constants.C_WARNING_TITLE, JOptionPane.YES_NO_OPTION);
			if(dialogResult == JOptionPane.YES_OPTION){
				openLibrary();
			}
		} else {
			openLibrary();
		}
	}
	
	/**
	 * TODO extension
	 */
	private void openLibrary() {
		//Initialize Open... dialog
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter(Constants.C_LIBRARY_IO_DESCRIPTION,
				Constants.C_LIBRARY_EXTENSION);
        fileChooser.addChoosableFileFilter(filter);
        
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			try {
				managedLibrary = new MaterialLibrary(file);
				setInterfaceAfterOpenLibrary();
			} catch (ParserConfigurationException | SAXException | IOException | ClassCastException e) {
				JOptionPane.showMessageDialog(null, Constants.C_ERROR_READING_XML_FILE, Constants.C_ERROR_DIALOG_TITLE,
						JOptionPane.ERROR_MESSAGE); 
				//e.printStackTrace();
			}
		}
	}
	
	/**
	 * This method sets all the elements of the interface after opening a library.
	 * TODO Verify that it selects nothing in the Jlist
	 */
	private void setInterfaceAfterOpenLibrary() {
		setLibraryNameOnLibraryTab(managedLibrary.getName());
		
		addMaterialButton.setEnabled(true);
		saveAsButton.setEnabled(true);
		deleteMaterialButton.setEnabled(false);
		
		//Clear list before adding the new materials
		DefaultListModel<String> listModel = (DefaultListModel<String>) availableMaterialsList.getModel();
        listModel.removeAllElements();
        availableMaterialsList.setSelectedIndex(-1);	//Selects nothing?
        for(String materialName: managedLibrary.getMaterialNames()) {
        	GUIGeneralMethods.addElementToStringJList(availableMaterialsList, materialName);
        }
		
		//Clear property table
		((DefaultTableModel)materialPropertiesTable.getModel()).setRowCount(0);
		Material selectedMaterial = managedLibrary.getMaterialByName(availableMaterialsList.getSelectedValue());
		showMaterialData(selectedMaterial);
	}
	
	/**
	 * Action executed when the Open library button is pressed.
	 */
	private void actionOnClicNewLibrary() {
		if (managedLibrary == null) {
			createNewLibrary();
		} else if (managedLibrary.hasChanged()) {	//if the current library was not saved since the last change
			int dialogResult = JOptionPane.showConfirmDialog(null, Constants.C_WANT_TO_CONTINUE, 
					Constants.C_WARNING_TITLE, JOptionPane.YES_NO_OPTION);
			if(dialogResult == JOptionPane.YES_OPTION){
				createNewLibrary();
			}
		} else {
			createNewLibrary();
		}
	}
	
	/**
	 * This method creates a new "current managed library".
	 * @return The new "current managed library" name or NULL if Cancel was pressed.
	 */
	private String createNewLibrary() {
		String libraryName= JOptionPane.showInputDialog(frame, Constants.C_INSERT_NEW_LIBRARY_NAME);
		if ((libraryName != null) && (!libraryName.isEmpty())) {	//If Cancel was not pressed and a non-empty name was given
			managedLibrary = new MaterialLibrary(libraryName);
			setLibraryNameOnLibraryTab(libraryName);
			enableButtonsAfterNewLibrary();
		}
		return libraryName;
	}
	
	
	/**
	 * Action executed when the Delete Material button is pressed.
	 */
	private void actionOnClicDeleteMaterial() {
		String selectedMaterialName = availableMaterialsList.getSelectedValue();
		if (selectedMaterialName != null) {
			int dialogResult = JOptionPane.showConfirmDialog(null, selectedMaterialName + Constants.C_WANT_TO_DELETE_MATERIAL, 
					Constants.C_WARNING_TITLE, JOptionPane.YES_NO_OPTION);
			if(dialogResult == JOptionPane.YES_OPTION){
				GUIGeneralMethods.removeElementFromStringJList(availableMaterialsList, selectedMaterialName);			    
				managedLibrary.deleteMaterialByName(selectedMaterialName);
			}
		}
	}
	
	/**
	 * Action executed when the Add Material button is pressed.
	 */
	private void actionOnClicAddMaterial() {
		String materialName= JOptionPane.showInputDialog(Constants.C_INSERT_NEW_MATERIAL_NAME);
		if ((materialName != null) && (!materialName.isEmpty())) {	//If Cancel was not pressed and a non-empty name was given
			try {
				managedLibrary.addMaterial(materialName);
				GUIGeneralMethods.addElementToStringJList(availableMaterialsList, materialName);
			} catch(IllegalArgumentException e) {
				JOptionPane.showMessageDialog(frame, e.getMessage(), Constants.C_ERROR_DIALOG_TITLE,
						JOptionPane.ERROR_MESSAGE); 
				//e.printStackTrace();
			}
		}
	}
	
	/**
	 * Action executed when the Save Library as... button is pressed.
	 */
	private void actionOnClicSaveLibraryAs() {
		//Launch dialog
		JFileChooser saveDialog = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(Constants.C_LIBRARY_IO_DESCRIPTION,
				Constants.C_LIBRARY_EXTENSION);
		saveDialog.setFileFilter(filter);
		saveDialog.setSelectedFile(new File(managedLibrary.getName() + "." + Constants.C_LIBRARY_EXTENSION));
		
		//If the "Save" button was pressed
		if (saveDialog.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
			File path = saveDialog.getSelectedFile();
			if (!path.getName().endsWith(Constants.C_LIBRARY_EXTENSION)) {	//add extension if needed
		        path = new File(path.getAbsolutePath() + "." + Constants.C_LIBRARY_EXTENSION);
			}
			
			//Check if the file already exists and, if so, ask if it should be overwritten
			boolean saveFile = true;
			if (path.exists()) {
				saveFile = false;
				int dialogResult = JOptionPane.showConfirmDialog(null, Constants.C_WANT_TO_OVERWRITE, 
						Constants.C_WARNING_TITLE, JOptionPane.YES_NO_OPTION);
				if(dialogResult == JOptionPane.YES_OPTION){
					saveFile = true;
				}
			}
			
			//If everything is OK to try to save the file
			if (saveFile) {
			    try {
					managedLibrary.writeXMLFile(path);
				} catch (ParserConfigurationException | TransformerException e) {
					JOptionPane.showMessageDialog(null, Constants.C_ERROR_WHILE_EXPORTING_LIBRARY, 
							Constants.C_ERROR_DIALOG_TITLE, JOptionPane.ERROR_MESSAGE);
					//e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * This method sets the value of the library name label.
	 * @param libraryName The library name to show.
	 */
	private void setLibraryNameOnLibraryTab(String libraryName) {
		libraryNameLabel.setText(Constants.C_MANAGED_LIBRARY_NAME_LABEL + libraryName);
	}
	
	/**
	 * This method enables the relevant buttons after creating a new library
	 */
	private void enableButtonsAfterNewLibrary() {
		addMaterialButton.setEnabled(true);
		saveAsButton.setEnabled(true);
		
		DefaultListModel<String> listModel = (DefaultListModel<String>) availableMaterialsList.getModel();
        listModel.removeAllElements();
		
		((DefaultTableModel)materialPropertiesTable.getModel()).setRowCount(0);	//Remove content
		deleteMaterialButton.setEnabled(false);
	}
	
	/**
	 * This is the action executed when the selected material changes in the available materials list.
	 */
	private void availableMaterialsListSelectionChanged() {
		if (availableMaterialsList.getSelectedIndex() != -1) {	//If a material is selected
			deleteMaterialButton.setEnabled(true);
			Material selectedMaterial = managedLibrary.getMaterialByName(availableMaterialsList.getSelectedValue());
			//System.out.println(selectedMaterial.getName());
			showMaterialData(selectedMaterial);
		} else {
			deleteMaterialButton.setEnabled(false);
			((DefaultTableModel)materialPropertiesTable.getModel()).setRowCount(0);	//Remove content
		}
	}
	
	/**
	 * This method shows the material properties on the relevant table.
	 * @param selectedMaterial the material whose properties will be shown.
	 */
	private void showMaterialData(Material selectedMaterial) {
		List<String> columns = new ArrayList<String>();

        columns.add(Constants.C_PROPERTY_COLUMN);
        columns.add(Constants.C_VALUE_COLUMN);
        
        TableModel tableModel = new DefaultTableModel(selectedMaterial.getPropertiesAndValues().toArray(new Object[][] {}),
        		columns.toArray());
		materialPropertiesTable.setModel(tableModel);
	}
	
	/**
	 * This is the action executed when a cell is changed on the material properties table
	 * @param tcl The Table Cell Listener object.
	 */
	private void cellChangeOnMaterialPropertiesTable(TableCellListener tcl) {
		String materialName = availableMaterialsList.getSelectedValue();
		String propertyName = materialPropertiesTable.getModel().getValueAt(tcl.getRow(), 0).toString();
		managedLibrary.setMaterialProperty(materialName, propertyName, tcl.getNewValue().toString());
	}
}

