import javax.swing.JPanel;
import java.awt.BorderLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JList;

@SuppressWarnings("serial")
public class CalculationPanel extends JPanel {

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
	
	/**
	 * Create the panel.
	 */
	public CalculationPanel() {
		super();
		initializeCalculationPanel();
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
				materialsOnLibraryList = new JList<String>();
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
			this.add(calculationStepsPanel, gbc_calculationStepsPanel);
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
			this.add(resultsPanel, gbc_resultsPanel);
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
	 * Action executed when the Browse Library button is pressed.
	 * TODO Add code
	 */
	private void actionOnClicBrowseLibrary() {
		
	}
	
	/**
	 * Action executed when the Add material (>>) button is pressed.
	 * TODO Add code
	 */
	private void actionOnClicAddMaterialsToList() {
		
	}

}

