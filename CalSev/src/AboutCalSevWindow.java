import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class AboutCalSevWindow extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JPanel buttonPanel;
	private JLabel aboutInfoLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AboutCalSevWindow dialog = new AboutCalSevWindow();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AboutCalSevWindow() {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle(Constants.C_ABOUT_WINDOW_TITLE);
		setResizable(false);
		setBounds(Constants.C_ABOUT_PREFERED_POSITION_X_AT_START, Constants.C_ABOUT_PREFERED_POSITION_Y_AT_START, 
				Constants.C_ABOUT_PREFERED_SIZE_X, Constants.C_ABOUT_PREFERED_SIZE_Y);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			aboutInfoLabel = new JLabel(Constants.C_ABOUT_INFO);
			contentPanel.add(aboutInfoLabel, BorderLayout.CENTER);
		}
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
	 * Action performed when the OK button is clicked.
	 */
	private void actionOnClicOK() {
		setVisible(false);
		dispose();
	}

}
