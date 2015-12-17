import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.io.IOException;

import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author david.merayo
 * @version 1.0.0
 * This class manages the application main window: the class creates the frame and fills it.
 * This class also contains the main method.
 */
public class MainWindow {

	//GUI elements
	private JFrame frame;
	private JTabbedPane mainTabbedPane;
	private JPanel panelCalculation, panelLibrary;
	private JMenuBar menuBar;
	private JMenu menuFile;
	private JMenu menuHelp;
	private JMenuItem menuItemExit;
	private JMenuItem menuItemHelp;
	private JMenuItem menuItemAbout;
	
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
					JOptionPane.showMessageDialog(null, Constants.C_GLOBAL_ERROR, Constants.C_ERROR_DIALOG_TITLE,
							JOptionPane.ERROR_MESSAGE); 
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
		
		panelCalculation = new CalculationPanel();
		mainTabbedPane.addTab(Constants.C_TAB_CALCULATION_TITLE, null, panelCalculation, null);
		panelLibrary = new LibraryPanel();
		mainTabbedPane.addTab(Constants.C_TAB_LIBRARY_TITLE, null, panelLibrary, null);
		
		// Menu bar
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		createMenus();
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
	 * TODO Add a relevant help web-site
	 */
	private void actionOnClicHelp() {
		try {
			GUIGeneralMethods.openWebPage(new URL("www.microsiervos.com/"));
		} catch (URISyntaxException | IOException e) {
			JOptionPane.showMessageDialog(frame, Constants.C_ERROR_WHILE_BROWSING_HELP, Constants.C_ERROR_DIALOG_TITLE,
					JOptionPane.ERROR_MESSAGE); 
			//e.printStackTrace();
		}
	}
	
	/**
	 * Action executed when the About menu-item is clicked.
	 */
	private void actionOnClicAbout() {
		AboutCalSevWindow aboutWin = new AboutCalSevWindow();
		aboutWin.setVisible(true);
	}

}
