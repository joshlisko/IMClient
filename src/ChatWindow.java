/*
 * @author Josh Lisko, Chris Overend, Caitlin Henry
 * 
 * This frame displays a conversation between a user and a buddy.
 * It is the second most important GUI frame. It provides the user
 * access to the functionality not availible in the BuddyList.
 * 
 *  **BUG**:
 *  
 * In order to use the find function, you must:
 * Click find
 * Enter the keyword
 * BEFORE YOU CLICK FIND, CLICK THE CHAT AREA
 * click find.
 * 
 * If you do not do this, then the find function will not properly work!
 */

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import org.jivesoftware.smack.RosterEntry;

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
public class ChatWindow extends javax.swing.JFrame {

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

	private JMenuBar jMenuBarChatWindow;
	private JMenuItem jMenuViewLog;
	private JTextPane jTextPaneChatArea;
	private JTextArea jTextAreaInput;
	private JLabel jLabelName;
	private JPanel jPanelName;
	private JScrollPane jScrollPane1;
	private JMenuItem jMenuClose;
	private JMenuItem jMenuRemove;
	private JMenuItem jMenuFind;
	private JMenuItem jMenuNewMessage;
	private JCheckBoxMenuItem jCheckBoxTimestamps;
	private JCheckBoxMenuItem jCheckBoxLogging;
	private JMenu jMenuOptions;
	private JMenu jMenuConversation;
	private RosterEntry buddy;
	private ConnectionManager connectionManager;
	private boolean showTime;
	private boolean logging;
	private ArrayList<Integer> firstIndexList = new ArrayList<Integer>();
	private ArrayList<Integer> lastIndexList = new ArrayList<Integer>();
	private int counter = 0;

	/**
	 * Auto-generated main method to display this JFrame
	 */

	public ChatWindow() {
		super();
		initGUI();
	}

	public ChatWindow(RosterEntry b, ConnectionManager m) {
		super("Chat with " + b.getUser());
		buddy = b;
		connectionManager = m;
		initGUI();
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			pack();
			setSize(400, 300);
			BoxLayout thisLayout = new BoxLayout(getContentPane(),
					javax.swing.BoxLayout.Y_AXIS);
			getContentPane().setLayout(thisLayout);
			setVisible(true);
			{
				jPanelName = new JPanel();
				GridLayout jPanelNameLayout = new GridLayout(1, 1);
				jPanelNameLayout.setHgap(5);
				jPanelNameLayout.setVgap(5);
				jPanelNameLayout.setColumns(1);
				jPanelName.setLayout(jPanelNameLayout);
				getContentPane().add(jPanelName);
				jPanelName.setPreferredSize(new java.awt.Dimension(394, 22));
				{
					if (buddy.getName() == null)
						jLabelName = new JLabel("  " + buddy.getUser());
					else
						jLabelName = new JLabel("  " + buddy.getName());
					BorderLayout jLabelNameLayout = new BorderLayout();
					jLabelName.setLayout(jLabelNameLayout);
					jPanelName.add(jLabelName);
				}
			}
			{
				jScrollPane1 = new JScrollPane();
				getContentPane().add(jScrollPane1);
				jScrollPane1.setPreferredSize(new java.awt.Dimension(384, 177));
				{
					jTextPaneChatArea = new JTextPane();
					jScrollPane1.setViewportView(jTextPaneChatArea);
					jTextPaneChatArea.setEditable(false);
					jTextPaneChatArea.setPreferredSize(new java.awt.Dimension(
							391, 29));
				}
			}
			{
				jTextAreaInput = new JTextArea();
				getContentPane().add(jTextAreaInput);
				jTextAreaInput
						.setPreferredSize(new java.awt.Dimension(394, 45));
				jTextAreaInput.addKeyListener(new KeyAdapter() {
					public void keyReleased(KeyEvent evt) {
						jTextAreaInputKeyReleased(evt);
					}
				});
			}

			{
				jMenuBarChatWindow = new JMenuBar();
				setJMenuBar(jMenuBarChatWindow);
				{
					jMenuConversation = new JMenu();
					jMenuBarChatWindow.add(jMenuConversation);
					jMenuConversation.setText("Conversation");
					{
						jMenuNewMessage = new JMenuItem();
						jMenuConversation.add(jMenuNewMessage);
						jMenuNewMessage.setText("New Instant Message");
						jMenuNewMessage.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								jMenuNewMessageActionPerformed(evt);
							}
						});
					}
					{
						jMenuFind = new JMenuItem();
						jMenuConversation.add(jMenuFind);
						jMenuFind.setText("Find");
						jMenuFind.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								jMenuFindActionPerformed(evt);
							}
						});
					}
					{
						jMenuViewLog = new JMenuItem();
						jMenuConversation.add(jMenuViewLog);
						jMenuViewLog.setText("View Log");
						jMenuViewLog.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								jMenuViewLogActionPerformed(evt);
							}
						});
					}
					{
						jMenuRemove = new JMenuItem();
						jMenuConversation.add(jMenuRemove);
						jMenuRemove.setText("Remove");
						jMenuRemove.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								jMenuRemoveActionPerformed(evt);
							}
						});
					}
					{
						jMenuClose = new JMenuItem();
						jMenuConversation.add(jMenuClose);
						jMenuClose.setText("Close");
						jMenuClose.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								jMenuCloseActionPerformed(evt);
							}
						});
					}
				}
				{
					jMenuOptions = new JMenu();
					jMenuBarChatWindow.add(jMenuOptions);
					jMenuOptions.setText("Options");
					{
						jCheckBoxLogging = new JCheckBoxMenuItem();
						jMenuOptions.add(jCheckBoxLogging);
						jCheckBoxLogging.setText("Enable Logging");
						jCheckBoxLogging.setSelected(true);
						jCheckBoxLogging
								.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent evt) {
										jCheckBoxLoggingActionPerformed(evt);
									}
								});
						logging = true;
					}
					{
						jCheckBoxTimestamps = new JCheckBoxMenuItem();
						jMenuOptions.add(jCheckBoxTimestamps);
						jCheckBoxTimestamps.setText("Show Timestamps");
						jCheckBoxTimestamps.setSelected(true);
						jCheckBoxTimestamps
								.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent evt) {
										jCheckBoxTimestampsActionPerformed(evt);
									}
								});
						showTime = true;
					}
				}
			}
			setResizable(false);
			setVisible(true);
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

	public void recieveMessage(String s) {
		Document m = jTextPaneChatArea.getDocument();
		String chat;
		if (showTime == true) {
			chat = "\n(" + getDate() + ") " + buddy.getUser() + ":  " + s;
		} else {
			chat = buddy.getUser() + ":  " + s;
		}
		if (logging == true) {
			connectionManager.writeHistory(buddy, chat);
		}
		try {
			m.insertString(m.getLength(), chat, null);
			jTextPaneChatArea.setDocument(m);
			repaint();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void jTextAreaInputKeyReleased(KeyEvent evt) {
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			connectionManager.sendMessage(buddy, jTextAreaInput.getText());
			Document m = jTextPaneChatArea.getDocument();
			String chat;
			if (showTime == true) {
				chat = "\n" + "(" + getDate() + ") " + "me" + ":  "
						+ jTextAreaInput.getText();
			} else {
				chat = "\n" + "me" + ":  " + jTextAreaInput.getText();
			}

			if (logging == true) {
				connectionManager.writeHistory(buddy, chat);
			}
			// System.out.println(jTextAreaInput.getText().substring(0,jTextAreaInput.getText().indexOf("\n")));
			// System.out.println(jTextAreaInput.getText());
			jTextAreaInput.setText("");
			try {
				m.insertString(m.getLength(), chat, null);

				jTextPaneChatArea.setDocument(m);
				jTextPaneChatArea.setAutoscrolls(true);
				repaint();
			} catch (Exception e) {
				e.printStackTrace();
			}
			repaint();
		}
	}

	public JTextPane getTextPane() {
		return jTextPaneChatArea;
	}

	private String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date date = new Date();
		return (dateFormat.format(date));
	}

	private void jMenuViewLogActionPerformed(ActionEvent evt) {
		connectionManager.openHistory(buddy);
	}

	private void jMenuNewMessageActionPerformed(ActionEvent evt) {
		System.out.println("jMenuNewMessage.actionPerformed, event=" + evt);
		connectionManager.log.addLog("jMenuNewMessage.actionPerformed, event="
				+ evt);
		@SuppressWarnings("unused")
		NewMessageFrame temp = new NewMessageFrame(connectionManager);
	}

	private void jCheckBoxLoggingActionPerformed(ActionEvent evt) {
		System.out.println("jCheckBoxLogging.actionPerformed, event=" + evt);
		connectionManager.log.addLog("jCheckBoxLogging.actionPerformed, event="
				+ evt);
		if (jCheckBoxLogging.isSelected())
			logging = true;
		else
			logging = false;
	}

	private void jCheckBoxTimestampsActionPerformed(ActionEvent evt) {
		System.out.println("jCheckBoxTimestamps.actionPerformed, event=" + evt);
		connectionManager.log
				.addLog("jCheckBoxTimestamps.actionPerformed, event=" + evt);
		if (jCheckBoxTimestamps.isSelected())
			showTime = true;
		else
			showTime = false;
	}

	private void jMenuCloseActionPerformed(ActionEvent evt) {
		System.out.println("jMenuClose.actionPerformed, event=" + evt);
		connectionManager.log
				.addLog("jMenuClose.actionPerformed, event=" + evt);
		connectionManager.endChat(buddy, this);
		dispose();
	}

	private void jMenuRemoveActionPerformed(ActionEvent evt) {
		System.out.println("jMenuRemove.actionPerformed, event=" + evt);
		connectionManager.log.addLog("jMenuRemove.actionPerformed, event="
				+ evt);
		connectionManager.endChat(buddy, this);
		connectionManager.removeBuddy(buddy.getUser());
		dispose();

	}

	// THERE IS A BUG HERE, PLEASE SEE ABOVE FOR MORE INFORMATION
	private void jMenuFindActionPerformed(ActionEvent evt) {
		System.out.println("jMenuFind.actionPerformed, event=" + evt);
		connectionManager.log.addLog("jMenuFind.actionPerformed, event=" + evt);
		@SuppressWarnings("unused")
		FindFrame temp = new FindFrame(this);
		System.out.println(this.isActive());
	}

	public void search(String key) {

		if (key == "")
			return;
		else if (key == null)
			return;
		else if (!(jTextPaneChatArea.getText().contains(key))) {
			showInfoDialog(key + " is not in this chat.");
		} else {

			String content = null;
			try {
				Document d = jTextPaneChatArea.getDocument();
				content = d.getText(0, d.getLength()).toLowerCase();
			} catch (BadLocationException e) {
				return;
			}

			key = key.toLowerCase();
			int lastIndex = 0;
			int wordSize = key.length();

			while ((lastIndex = content.indexOf(key, lastIndex)) != -1) {
				int endIndex = lastIndex + wordSize;
				store(lastIndex, endIndex);
				lastIndex = endIndex;
			}

		}
	}

	private void store(int lastIndex, int endIndex) {
		firstIndexList.add(lastIndex);
		lastIndexList.add(endIndex);
	}

	public int showResults() {
		jTextPaneChatArea.select(firstIndexList.get(counter),
				lastIndexList.get(counter));
		return counter++;
	}

	private void showInfoDialog(String m) {
		JOptionPane.showMessageDialog(new JFrame(), m, "Done Searching",
				JOptionPane.INFORMATION_MESSAGE);
	}

}
