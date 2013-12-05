/*
 *  @author Josh Lisko
 *  
 *  This frame takes a buddy specified by the user and adds it to the user's buddy list.
 */
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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

public class AddBuddyFrame extends javax.swing.JFrame {

	{
		// Set Look & Feel
		try {
			javax.swing.UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static final long serialVersionUID = 1L;

	/**
	 * Auto-generated main method to display this JFrame
	 */

	ConnectionManager connectionManager;
	private JPanel jPanelNewMessage;
	private JTextField jTextFieldBuddy;
	private JButton jButtonOK;
	private JLabel jLabelBuddy;

	public AddBuddyFrame(String m) {
		super(m);
		initGUI();
	}

	public AddBuddyFrame(ConnectionManager m) {
		super("Add Buddy");
		connectionManager = m;
		initGUI();
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				jPanelNewMessage = new JPanel();
				getContentPane().add(jPanelNewMessage, BorderLayout.CENTER);
				{
					jLabelBuddy = new JLabel();
					jPanelNewMessage.add(jLabelBuddy);
					jLabelBuddy.setText("Buddy: ");
				}
				{
					jTextFieldBuddy = new JTextField();
					jPanelNewMessage.add(jTextFieldBuddy);
					jTextFieldBuddy.setPreferredSize(new java.awt.Dimension(
							132, 23));
					jTextFieldBuddy.addKeyListener(new KeyAdapter() {
						public void keyReleased(KeyEvent evt) {
							jTextFieldBuddyKeyReleased(evt);
						}
					});
				}
				{
					jButtonOK = new JButton();
					jPanelNewMessage.add(jButtonOK);
					jButtonOK.setText("OK");
					jButtonOK.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							addBuddy(jTextFieldBuddy.getText());
						}
					});

				}
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

	private void addBuddy(String id) {
		try {
			connectionManager.addBuddy(id);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), "Can Not Find User",
					"Error", JOptionPane.ERROR_MESSAGE);
			jTextFieldBuddy.setText("");
		}
		dispose();
	}

	private void jTextFieldBuddyKeyReleased(KeyEvent evt) {
		if (evt.getKeyCode() == KeyEvent.VK_ENTER)
			addBuddy(jTextFieldBuddy.getText());
	}

}
