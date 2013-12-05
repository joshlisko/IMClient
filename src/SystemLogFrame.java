/*
 * @author Josh Lisko
 * 
 * This frame displays a log of critical actions taken by the program.
 */

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;

/**
 * This code was edited or generated using CloudGarden's Jigloo
 * SWT/Swing GUI Builder, which is free for non-commercial
 * use. If Jigloo is being used commercially (ie, by a corporation,
 * company or business for any purpose whatever) then you
 * should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details.
 * Use of Jigloo implies acceptance of these licensing terms.
 * A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
 * THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
 * LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
/**
 * @author Administrator
 * 
 */
public class SystemLogFrame extends javax.swing.JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JScrollPane jScrollPaneLog;
	public JTextPane jTextPaneLog = new JTextPane();

	/**
	 * Auto-generated main method to display this JFrame
	 */

	public SystemLogFrame() {
		super("System Log");
		initGUI();
	}

	public void addLog(String log) {
		String temp = jTextPaneLog.getText();
		String temp2 = temp + "\n" + log;
		jTextPaneLog.setText(temp2);
		repaint();
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{

				jScrollPaneLog = new JScrollPane(jTextPaneLog);

				getContentPane().add(jScrollPaneLog, BorderLayout.CENTER);
			}
			pack();
			setSize(400, 300);
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

}
