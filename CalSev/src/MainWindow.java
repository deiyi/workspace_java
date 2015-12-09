import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import javax.swing.JList;

public class MainWindow {

	private JFrame frame;
	private JTabbedPane mainTabbedPane;
	private JPanel panelCalculation, panelLibrary;
	private JMenuBar menuBar;
	private JMenu menuFile;
	private JMenu menuHelp;
	private JMenuItem menuItemExit;
	private JMenuItem menuItemHelp;
	private JMenuItem menuItemAbout;
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
	private JPanel libraryManagementPanel;
	private JPanel materialManagementPanel;
	private JLabel libraryNameLabel;
	private JButton openLibraryButton;
	private JButton newLibraryButton;
	private JButton deleteMaterialButton;
	private JButton addMaterialButton;
	private JButton cancelButton;
	private JButton saveAsButton;
	private JTable materialPropertiesTable;
	private JScrollPane materialPropertiesScrollPanel;
	private JList materialsOnLibraryList;
	private JList availableMaterialsList;
	private JScrollPane availableMaterialsScrollPanel;
	private JLabel selectMaterialLabel;

	/**
	 * Launch the application.
	 * @param args: Arguments received by the program.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application and initialize everything.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// Initialize main frame
		frame = new JFrame();
		frame.setBounds(Constants.C_MAIN_PREFERED_POSITION_X_AT_START, Constants.C_MAIN_PREFERED_POSITION_Y_AT_START, 
				Constants.C_MAIN_PREFERED_SIZE_X, Constants.C_MAIN_PREFERED_SIZE_Y);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(Constants.C_MAIN_WINDOW_TITLE);
		
		// Tab panel and their panels
		mainTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(mainTabbedPane, BorderLayout.CENTER);
		
		initializeCalculationPanel();
		initializeLibraryPanel();
		
		// Menu bar
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		createMenus();
	}
	
	/**
	 * Draw the Calculation panel and add all its objects.
	 */
	private void initializeCalculationPanel() {
		panelCalculation = new JPanel();
		mainTabbedPane.addTab(Constants.C_TAB_CALCULATION_TITLE, null, panelCalculation, null);
		
		// Panel calculation
		GridBagLayout gbl_panelCalculation = new GridBagLayout();
		gbl_panelCalculation.columnWidths = new int[] {0};
		gbl_panelCalculation.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panelCalculation.columnWeights = new double[]{1.0};
		gbl_panelCalculation.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		panelCalculation.setLayout(gbl_panelCalculation);
		
		// Calculation materials panel
		{
			calculationMaterialsPanel = new JPanel();
			GridBagConstraints gbc_calculationMaterialsPanel = new GridBagConstraints();
			gbc_calculationMaterialsPanel.insets = new Insets(0, 0, 5, 0);
			gbc_calculationMaterialsPanel.fill = GridBagConstraints.BOTH;
			gbc_calculationMaterialsPanel.gridx = 0;
			gbc_calculationMaterialsPanel.gridy = 0;
			
			panelCalculation.add(calculationMaterialsPanel, gbc_calculationMaterialsPanel);
			GridBagLayout gbl_calculationMaterialsPanel = new GridBagLayout();
			gbl_calculationMaterialsPanel.columnWidths = new int[]{280, 0, 0, 69, 0};
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
			
			calculationMaterialsScrollPanel = new JScrollPane();
			GridBagConstraints gbc_calculationMaterialsScrollPanel = new GridBagConstraints();
			gbc_calculationMaterialsScrollPanel.fill = GridBagConstraints.BOTH;
			gbc_calculationMaterialsScrollPanel.gridheight = 3;
			gbc_calculationMaterialsScrollPanel.gridx = 3;
			gbc_calculationMaterialsScrollPanel.gridy = 1;
			calculationMaterialsPanel.add(calculationMaterialsScrollPanel, gbc_calculationMaterialsScrollPanel);
			
			{
				//String item[] = {"ASME mat1", "mat2", "KTA mat3", "mat4", "mat5", "mat6", "mat7", "mat8", "mat9"};
				materialsOnLibraryList = new JList();
				calculationMaterialsScrollPanel.setViewportView(materialsOnLibraryList);
			}
			
			calculationLibraryLabel = new JLabel(Constants.C_MATERIAL_LIBRARY_NAME);
			GridBagConstraints gbc_calculationLibraryLabel = new GridBagConstraints();
			gbc_calculationLibraryLabel.gridwidth = 3;
			gbc_calculationLibraryLabel.anchor = GridBagConstraints.WEST;
			gbc_calculationLibraryLabel.insets = new Insets(0, 0, 5, 5);
			gbc_calculationLibraryLabel.gridx = 0;
			gbc_calculationLibraryLabel.gridy = 1;
			calculationMaterialsPanel.add(calculationLibraryLabel, gbc_calculationLibraryLabel);
			
			libraryPathTextBox = new JTextField();
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
			panelCalculation.add(calculationStepsPanel, gbc_calculationStepsPanel);
			calculationStepsPanel.setLayout(new BorderLayout(0, 0));
			
			calculationStepsLabel = new JLabel(Constants.C_CALCULATION_STEPS_TITLE);
			calculationStepsPanel.add(calculationStepsLabel, BorderLayout.NORTH);
			
			calculationStepsScrollPanel = new JScrollPane();
			calculationStepsPanel.add(calculationStepsScrollPanel, BorderLayout.CENTER);
			
			{
				/*List<String> columns = new ArrayList<String>();
		        List<String[]> values = new ArrayList<String[]>();
     
		        columns.add("chemical element");
		        columns.add("Ri");
		        columns.add("ASME mat1 SL");
		        columns.add("ASME mat2 SL");
		        columns.add("Le");
		        columns.add("SL calculation method");
		        columns.add("SL (ASME mat1 SL)");
		        columns.add("SL (ASME mat2 SL)");
		        columns.add("SL (ASME mat1 SL) x pi");
		        columns.add("SL (ASME mat2 SL) x pi");

		        values.add(new String[] {"Fósforo", "0.15", "0.025", "0.012", "0.02", "CASO 1", "1.00", "5.00", "0.15", "0.75"});
		        values.add(new String[] {"Cobre", "0.15", "0.20", "0.12", "0.10", "CASO 1", "1.00", "1.00", "0.15", "0.15"});
		        values.add(new String[] {"Níquel", "0.1", "1.00", "0.80", "1.00", "CASO 1", "2.00", "3.00", "0.2", "0.3"});
		        values.add(new String[] {"Niobio", "0.05", "0.01", "NE", "NA", "CASO 2", "3.00", "1.00", "0.15", "0.05"});
		        values.add(new String[] {"Tantalio ", "0.05", "NE", "0.03", "NA", "CASO 2", "1.00", "3.00", "0.05", "0.15"});
		        values.add(new String[] {"Cobalto.", "0.05", "NE", "0.03", "NA", "CASO2", "1.00", "3.00", "0.05", "0.15"});
		        values.add(new String[] {"Nitrógeno", "0.05", "NE", "0.01", "NA", "CASO 2", "1.00", "3.00", "0.05", "0.15"});
		        values.add(new String[] {"Manganeso", "0.05", "1.50", "1.50", "NA", "CASO 3", "5.00", "5.00", "0.25", "0.25"});
		        values.add(new String[] {"Molibdeno", "0.05", "0.60", "0.55", "NA", "CASO 4", "5.00", "4.60", "0.25", "0.23"});
		        values.add(new String[] {"Silicio", "0.05", "0.40", "0.30", "NA", "CASO 4", "3.75", "5.00", "0.1875", "0.25"});
		        values.add(new String[] {"Azufre", "0.1", "0.025", "0.008", "NA", "CASO 4", "1.60", "5.00", "0.16", "0.5"});
		        values.add(new String[] {"Cromo", "0.05", "0.25", "0.20", "NA", "CASO 4", "4.00", "5.00", "0.2", "0.25"});
		        values.add(new String[] {"Vanadio", "0.1", "0.05", "0.02", "NA", "CASO 4", "2.00", "5.00", "0.2", "0.5"});
		        
		        TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());
				
				calculationStepsTable = new JTable(tableModel);*/
				calculationStepsTable = new JTable();
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
			panelCalculation.add(resultsPanel, gbc_resultsPanel);
			resultsPanel.setLayout(new BorderLayout(0, 0));
			
			resultsLabel = new JLabel(Constants.C_RESULTS_TITLE);
			resultsPanel.add(resultsLabel, BorderLayout.NORTH);
			
			resultsScrollPanel = new JScrollPane();
			resultsPanel.add(resultsScrollPanel, BorderLayout.CENTER);
			
			{
				/*List<String> columns = new ArrayList<String>();
		        List<String[]> values = new ArrayList<String[]>();
     
		        columns.add("Material");
		        columns.add("Global SL");

		        values.add(new String[] {"ASME mat1", "2.05"});
		        values.add(new String[] {"KTA mat3", "3.68"});

		        
		        TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());
				resultsTable = new JTable(tableModel);*/
				resultsTable = new JTable();
				resultsScrollPanel.setViewportView(resultsTable);
			}
		}
	}
	
	/**
	 * Draw the Library panel and add all its objects.
	 */
	private void initializeLibraryPanel() {
		panelLibrary = new JPanel();
		mainTabbedPane.addTab(Constants.C_TAB_LIBRARY_TITLE, null, panelLibrary, null);
		panelLibrary.setLayout(new BorderLayout(0, 0));
		
		{
			libraryManagementPanel = new JPanel();
			panelLibrary.add(libraryManagementPanel, BorderLayout.WEST);
			GridBagLayout gbl_libraryManagementPanel = new GridBagLayout();
			gbl_libraryManagementPanel.columnWidths = new int[]{0, 0};
			gbl_libraryManagementPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
			gbl_libraryManagementPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			gbl_libraryManagementPanel.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
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
			
			availableMaterialsScrollPanel = new JScrollPane();
			GridBagConstraints gbc_availableMaterialsScrollPanel = new GridBagConstraints();
			gbc_availableMaterialsScrollPanel.fill = GridBagConstraints.BOTH;
			gbc_availableMaterialsScrollPanel.insets = new Insets(0, 0, 5, 0);
			gbc_availableMaterialsScrollPanel.gridx = 0;
			gbc_availableMaterialsScrollPanel.gridy = 2;
			libraryManagementPanel.add(availableMaterialsScrollPanel, gbc_availableMaterialsScrollPanel);
			
			//String item2[] = {"ASME mat1", "mat2", "KTA mat3", "mat4", "mat5", "mat6", "mat7", "mat8", "mat9"};
			availableMaterialsList = new JList();
			availableMaterialsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			availableMaterialsScrollPanel.setViewportView(availableMaterialsList);
			
			GridBagConstraints gbc_deleteMaterialButton = new GridBagConstraints();
			gbc_deleteMaterialButton.fill = GridBagConstraints.HORIZONTAL;
			gbc_deleteMaterialButton.insets = new Insets(0, 0, 5, 0);
			gbc_deleteMaterialButton.gridx = 0;
			gbc_deleteMaterialButton.gridy = 3;
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
			gbc_addMaterialButton.gridy = 4;
			libraryManagementPanel.add(addMaterialButton, gbc_addMaterialButton);
			
			materialManagementPanel = new JPanel();
			panelLibrary.add(materialManagementPanel, BorderLayout.CENTER);
			GridBagLayout gbl_materialManagementPanel = new GridBagLayout();
			gbl_materialManagementPanel.columnWidths = new int[]{0, 0, 0};
			gbl_materialManagementPanel.rowHeights = new int[]{0, 0, 0, 0};
			gbl_materialManagementPanel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
			gbl_materialManagementPanel.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
			materialManagementPanel.setLayout(gbl_materialManagementPanel);
		}
		
		{
			libraryNameLabel = new JLabel(Constants.C_MANAGED_LIBRARY_NAME_LABEL);
			GridBagConstraints gbc_libraryNameLabel = new GridBagConstraints();
			gbc_libraryNameLabel.anchor = GridBagConstraints.WEST;
			gbc_libraryNameLabel.gridwidth = 2;
			gbc_libraryNameLabel.insets = new Insets(0, 0, 5, 0);
			gbc_libraryNameLabel.gridx = 0;
			gbc_libraryNameLabel.gridy = 0;
			materialManagementPanel.add(libraryNameLabel, gbc_libraryNameLabel);
			
			materialPropertiesScrollPanel = new JScrollPane();
			GridBagConstraints gbc_materialPropertiesScrollPanel = new GridBagConstraints();
			gbc_materialPropertiesScrollPanel.fill = GridBagConstraints.BOTH;
			gbc_materialPropertiesScrollPanel.gridwidth = 2;
			gbc_materialPropertiesScrollPanel.insets = new Insets(0, 0, 5, 5);
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
			materialPropertiesTable = new JTable();
			materialPropertiesScrollPanel.setViewportView(materialPropertiesTable);
			
			cancelButton = new JButton(Constants.C_CANCEL_BUTTON);
			cancelButton.setEnabled(false);
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionOnClicCancelLibraryOperation();
				}
			});
			GridBagConstraints gbc_cancelButton = new GridBagConstraints();
			gbc_cancelButton.insets = new Insets(0, 0, 0, 5);
			gbc_cancelButton.gridx = 0;
			gbc_cancelButton.gridy = 2;
			materialManagementPanel.add(cancelButton, gbc_cancelButton);
			
			saveAsButton = new JButton(Constants.C_SAVE_LIBRARY_AS_BUTTON);
			saveAsButton.setEnabled(false);
			saveAsButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionOnClicSaveLibraryAs();
				}
			});
			GridBagConstraints gbc_saveAsButton = new GridBagConstraints();
			gbc_saveAsButton.gridx = 1;
			gbc_saveAsButton.gridy = 2;
			materialManagementPanel.add(saveAsButton, gbc_saveAsButton);
		}
	}
	
	/**
	 * Create menus and fill them with options and properties.
	 */
	private void createMenus() {
		// File menu
		menuFile = new JMenu(Constants.C_FILE_MENU_TITLE);
		menuBar.add(menuFile);
		
		menuItemExit = new JMenuItem(Constants.C_FILE_ITEM_EXIT_TITLE);
		menuItemExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionOnClicExit();		// Action triggered when the Exit item is clicked.
			}
		});
		menuFile.add(menuItemExit);
		
		// Help menu
		menuHelp = new JMenu(Constants.C_HELP_MENU_TITLE);
		menuBar.add(menuHelp);
		
		menuItemHelp = new JMenuItem(Constants.C_HELP_ITEM_HELP_TITLE);
		menuItemHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionOnClicHelp();
			}
		});
		menuHelp.add(menuItemHelp);
		
		menuItemAbout = new JMenuItem(Constants.C_HELP_ITEM_ABOUT_TITLE);
		menuItemAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionOnClicAbout();
			}
		});
		menuHelp.add(menuItemAbout);
	}
	
	/**
	 * Action executed when the Exit menu-item is clicked.
	 */
	private void actionOnClicExit() {
		frame.setVisible(false);
		frame.dispose();
	}
	
	/**
	 * Action executed when the Help menu-item is clicked.
	 */
	private void actionOnClicHelp() {
		
	}
	
	/**
	 * Action executed when the About menu-item is clicked.
	 */
	private void actionOnClicAbout() {
		AboutCalSevWindow aboutWin = new AboutCalSevWindow();
		aboutWin.setVisible(true);
	}
	
	/**
	 * Action executed when the Browse Library button is pressed.
	 */
	private void actionOnClicBrowseLibrary() {
		
	}
	
	/**
	 * Action executed when the Add material (>>) button is pressed.
	 */
	private void actionOnClicAddMaterialsToList() {
		
	}
	
	/**
	 * Action executed when the Open library button is pressed.
	 */
	private void actionOnClicOpenLibrary() {
		
	}
	
	/**
	 * Action executed when the Open library button is pressed.
	 */
	private void actionOnClicNewLibrary() {
		
	}
	
	/**
	 * Action executed when the Delete MAterial button is pressed.
	 */
	private void actionOnClicDeleteMaterial() {
		
	}
	
	/**
	 * Action executed when the Add Material button is pressed.
	 */
	private void actionOnClicAddMaterial() {
		
	}
	
	/**
	 * Action executed when the Cancel Library operation button is pressed.
	 */
	private void actionOnClicCancelLibraryOperation() {
		
	}
	
	/**
	 * Action executed when the Save Library as... button is pressed.
	 */
	private void actionOnClicSaveLibraryAs() {
		
	}
	
	
}
