/*
 * @author Josh Lisko
 * 
 * This frame prompts the user to login to GTalk with credentials.
 * It tests to see if the credentials are correct and if they are,
 * it loads the buddylist for the specified user.
 */

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.Caret;
import javax.swing.text.Document;

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
public class LoginFrame extends JFrame {

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
	private JTextField userNameBox;
	private JPasswordField passwordBox;
	private JButton JLoginButton;
	private String pass;
	private ConnectionManager connectionManager;

	public LoginFrame(ConnectionManager m) {
		super("GTalk Login");
		connectionManager = m;
		initLogin();
	}

	public void clearCredentials() {
		userNameBox.setText("");
		passwordBox.setText("");
		repaint();
	}

	private void initLogin() {
		try {
			String os = System.getProperty("os.name").toLowerCase();
			if (os.indexOf("win") >= 0)
				setSize(200, 120);
			else if (os.indexOf("mac") >= 0)
				setSize(200, 200);
			else
				setSize(500, 500);
			setLocationRelativeTo(null);
			setResizable(false);
			Container loginPane = this.getContentPane();
			loginPane.setLayout(new FlowLayout());
			JLabel userLabel = new JLabel("Username");
			userNameBox = new JTextField(10);
			JLabel passLabel = new JLabel("Password");
			passwordBox = new JPasswordField(10);
			JLoginButton = new JButton("Login");
			loginPane.add(userLabel);
			loginPane.add(userNameBox);
			loginPane.add(passLabel);
			loginPane.add(passwordBox);
			passwordBox.addKeyListener(new KeyAdapter() {
				public void keyReleased(KeyEvent evt) {
					passwordBoxKeyReleased(evt);
				}
			});
			loginPane.add(JLoginButton);
			JLoginButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					System.out.println("JLoginButton.actionPerformed, event="
							+ evt);
					// TODO add your code for JLoginButton.actionPerformed
					connectionManager.login(getUsername(), getPassword());
				}
			});
			setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setUsernameBox(String s) {
		userNameBox.setText(s);
	}

	public void setPasswordBox(String s) {
		passwordBox.setText(s);
	}

	String getUsername() {
		return processUsername(userNameBox.getText());
	}

	String getPassword() {
		return processPassword(passwordBox.getPassword());
	}

	private String processPassword(char[] passGiven) {
		pass = "";
		for (int i = 0; i < passGiven.length; i++)
			pass += Character.toString(passGiven[i]);
		return pass;
	}

	private String processUsername(String userGiven) {
		if (!(userGiven.contains("@gmail.com")))
			userGiven = userGiven + "" + "@gmail.com";
		return userGiven;
	}

	private void passwordBoxKeyReleased(KeyEvent evt) {
		if (evt.getKeyCode() == KeyEvent.VK_ENTER)
			connectionManager.login(getUsername(), getPassword());
	}
}