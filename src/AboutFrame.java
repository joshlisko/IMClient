/*
 * @author Josh Lisko
 * 
 * This frame displays information about this program.
 */

import java.awt.BorderLayout;

import javax.swing.JTextPane;
import javax.swing.WindowConstants;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class AboutFrame extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	{
		// Set Look & Feel
		try {
			javax.swing.UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JTextPane JTextPaneAbout;

	/**
	 * Auto-generated main method to display this JFrame
	 */

	public AboutFrame() {
		super("About");
		initGUI();
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				JTextPaneAbout = new JTextPane();
				JTextPaneAbout
						.setText("This IM project was built by Jenny Cha, Caitlin Henry, Josh Lisko, and Chris Overend. "
								+ "This program uses the Smack API and the GUI form was heavily influenced by Pidgin. This code was developed at UVA during the fall semester in 2010 as a project for CS 2110.");
				getContentPane().add(JTextPaneAbout, BorderLayout.CENTER);
				JTextPaneAbout.setEditable(false);

			}
			pack();
			setLocationRelativeTo(null);
			setSize(400, 300);
			setVisible(true);
			setResizable(false);
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

}
