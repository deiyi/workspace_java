import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class ReferencesWindow extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JPanel buttonPanel;
	private JLabel referencesInfoLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ReferencesWindow dialog = new ReferencesWindow();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ReferencesWindow() {
		//General dialog appearance
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle(Constants.C_REFERENCES_WINDOW_TITLE);
		setResizable(false);
		setBounds(Constants.C_REFERENCES_PREFERED_POSITION_X_AT_START, Constants.C_REFERENCES_PREFERED_POSITION_Y_AT_START, 
				Constants.C_REFERENCES_PREFERED_SIZE_X, Constants.C_REFERENCES_PREFERED_SIZE_Y);
		getContentPane().setLayout(new BorderLayout());
		
		//Information panel
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			referencesInfoLabel = new JLabel(Constants.C_REFERENCES_INFO);
			contentPanel.add(referencesInfoLabel, BorderLayout.CENTER);
		}
		
		//Button panel
		{
			buttonPanel = new JPanel();
			buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPanel, BorderLayout.SOUTH);
			{
				okButton = new JButton(Constants.C_OK_BUTTON);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						actionOnClicOK();
					}
				});
				okButton.setActionCommand(Constants.C_OK_BUTTON);
				buttonPanel.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
	/**
	 * Action performed when the OK button is clicked (the dialog disappears).
	 */
	private void actionOnClicOK() {
		setVisible(false);
		dispose();
	}

}
