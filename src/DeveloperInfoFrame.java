//@author Josh Lisko

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
public class DeveloperInfoFrame extends javax.swing.JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextPane jTextPane1;

	/**
	 * Auto-generated main method to display this JFrame
	 */

	public DeveloperInfoFrame() {
		super("Developer Info");
		initGUI();
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				jTextPane1 = new JTextPane();
				getContentPane().add(jTextPane1, BorderLayout.CENTER);
				jTextPane1
						.setText("Project Manager: Jenny Cha(SEAS 2013 Computer Science and Computer Engineering) \nQuality Assurance: Caitlin Henry(CLAS 2013 Computer Science) \nSoftware Architect: Chris Overend(SEAS 2013 Computer Science) \nSwing Designer: Josh Lisko(SEAS 2014 Systems Engineering and Computer Science)");
				jTextPane1.setEditable(false);
			}
			pack();
			setLocationRelativeTo(null);
			setSize(600, 300);
			setVisible(true);
			setResizable(false);
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

}
