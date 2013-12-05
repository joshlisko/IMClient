/*
 * @author Chris Overend
 * 
 * This frame takes a custom status specified by the user and
 * sets said status as the user's status.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/*
 This code was edited or generated using CloudGarden's Jigloo
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
public class DialogBox extends javax.swing.JFrame {

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

	private JTextField idTextField;
	private JButton cancelButton;
	private JButton enterName;
	private JLabel buddyLabel;
	private ConnectionManager connectionManager;

	public DialogBox(ConnectionManager conn) {
		super();
		this.connectionManager = conn;
		initGUI();
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			this.setResizable(false);
			{
				idTextField = new JTextField();
				getContentPane().add(idTextField);
				idTextField.setBounds(78, 13, 202, 22);
			}
			{
				buddyLabel = new JLabel();
				getContentPane().add(buddyLabel);
				buddyLabel.setText("");
				buddyLabel.setBounds(17, 15, 59, 16);
			}
			{
				enterName = new JButton();
				getContentPane().add(enterName);
				enterName.setText("OK");
				enterName.setBounds(29, 44, 80, 22);
				enterName.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						enterNameActionPerformed(evt);
					}
				});
			}
			{
				cancelButton = new JButton();
				getContentPane().add(cancelButton);
				cancelButton.setText("Cancel");
				cancelButton.setBounds(167, 43, 92, 22);
			}
			pack();
			this.setSize(314, 107);
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

	public void setLabel(String label) {
		buddyLabel.setText(label);
	}

	private void enterNameActionPerformed(ActionEvent evt) {
		if (buddyLabel.getText().equals("Buddy: ")) {
			connectionManager.addBuddy(idTextField.getText());
			this.dispose();
		}
		if (buddyLabel.getText().equals("Status: ")) {
			connectionManager.setStatus(idTextField.getText());
			this.dispose();
		}
		if (buddyLabel.getText().equals("Remove: ")) {
			connectionManager.removeBuddy(idTextField.getText());
		}
	}

}
