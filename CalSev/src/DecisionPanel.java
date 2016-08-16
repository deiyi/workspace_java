import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DecisionPanel extends JPanel {

	private JLabel referencesText;
	private JLabel panelDiagram;
	
	/**
	 * Create the panel
	 */
	public DecisionPanel() {
		super();
		initializeDecisionPanel();
	}
	
	
	/**
	 * Draw the Calculation panel and add all its objects.
	 */
	private void initializeDecisionPanel() {
		this.setLayout(new BorderLayout(0, 0));
		
		//Add text panel
		referencesText = new JLabel();
		this.add(referencesText, BorderLayout.SOUTH);
		addReferencesText();
		
		//add image		
		try {
			panelDiagram = new JLabel(new ImageIcon(getClass().getResource(Constants.C_METHOD_DIAGRAM_PATH)));
			this.add(panelDiagram, BorderLayout.CENTER);
		} catch (Exception e) {}
		
		panelDiagram.setLayout(new BorderLayout(0, 0));
		
	}
	
	/**
	 * This method sets the text shown in the Text panel
	 */
	private void addReferencesText() {
		referencesText.setText(Constants.C_SOURCES_TEXT);
	}
}
