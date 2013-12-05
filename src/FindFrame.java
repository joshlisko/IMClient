/*
 * @author Josh Lisko
 * 
 * This frame takes a keyword specified by the user and 
 * sends it to the ChatWindow class so that it can search and
 * find the keyword in a chat.
 */

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
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
public class FindFrame extends javax.swing.JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel jLabelFind;
	private JButton jButtonFind;
	private JTextField jTextFieldKeyword;
	private ChatWindow chatWindow;
	{
		// Set Look & Feel
		try {
			javax.swing.UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Auto-generated main method to display this JFrame
	 */

	public FindFrame(ChatWindow w) {
		super("Find");
		chatWindow = w;
		initGUI();
	}

	private void initGUI() {
		try {
			FlowLayout thisLayout = new FlowLayout();
			getContentPane().setLayout(thisLayout);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				jLabelFind = new JLabel();
				getContentPane().add(jLabelFind);
				jLabelFind.setText("Keyword");
			}
			{
				jTextFieldKeyword = new JTextField();
				getContentPane().add(jTextFieldKeyword);
				jTextFieldKeyword.setPreferredSize(new java.awt.Dimension(119,
						20));
			}
			{
				jButtonFind = new JButton();
				getContentPane().add(jButtonFind);
				jButtonFind.setText("Find");
				jButtonFind.setPreferredSize(new java.awt.Dimension(113, 23));
				jButtonFind.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						jButtonFindActionPerformed(evt);
					}
				});
			}
			pack();
			setSize(200, 100);
			setLocationRelativeTo(null);
			setVisible(true);
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

	private void jButtonFindActionPerformed(ActionEvent evt) {
		System.out.println("jButtonFind.actionPerformed, event=" + evt);
		String keyword = jTextFieldKeyword.getText();
		chatWindow.search(keyword);
		chatWindow.showResults();
		jButtonFind.setText("Find Next");
	}

}
