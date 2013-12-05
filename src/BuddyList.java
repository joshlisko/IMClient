/*
 * @author Josh Lisko, Chris Overend
 * 
 * This frame displays a buddy list of a user as well as many other components. 
 * This is the main GUI frame and gives the user graphical access to most of
 * the functionality of this program.
 */

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.WindowConstants;

import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.packet.Presence;

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
public class BuddyList extends javax.swing.JFrame implements MouseListener {

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

	private JMenuBar jMenuBarList;
	private JMenu jMenuBuddies;
	private JMenu jMenuAccounts;
	private JMenu jMenuTools;
	private JMenu jMenuShow;
	private JMenuItem jMenuRemoveBuddy;
	private JComboBox jComboBoxStatus;
	private JMenuItem jMenuRegisteredEmail;
	private JMenuItem jMenuDeveloperInfo;
	private JMenuItem jMenuAbout;
	private JMenu jMenuHelp;
	private JList jListOfBuddies;
	private JPanel StatusMoodPanel;
	private JMenuItem jMenuSystemLog1;
	private JMenuItem jMenuQuit;
	private JMenuItem jMenuAddBuddy;
	private JRadioButtonMenuItem jRadioButtonSortActivity;
	private JRadioButtonMenuItem jRadioButtonSortAlphabetically;
	private JCheckBoxMenuItem jCheckBoxMenuShowOfflineBuddies;
	private JMenu jMenuSortBuddies;
	private JMenuItem jMenuItemNewMessage;
	private ButtonGroup jButtonGroup = new ButtonGroup();
	private ConnectionManager connectionManager;
	private ArrayList<RosterEntry> buddyListEntries;
	private boolean showOffline;
	private boolean isAlphabetical;
	private boolean isRecentActivity;

	/**
	 * Auto-generated main method to display this JFrame
	 */

	public BuddyList(ConnectionManager m) {
		super("Buddy List");
		connectionManager = m;
		initGUI();
	}

	private void initGUI() { // The code below got mixed up because of the way
								// Jigloo handles things
								// For example, the getJMenuDeveloperInfo method
								// actually returns privacy
								// everything is added correctly, but the method
								// calls are a but jumbled up.
		try {
			this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			{
				jMenuBarList = new JMenuBar();
				setJMenuBar(jMenuBarList);
				{
					jMenuBuddies = new JMenu();
					jMenuBarList.add(jMenuBuddies);
					jMenuBuddies.setText("Buddies");
					{
						jMenuItemNewMessage = new JMenuItem();
						jMenuBuddies.add(jMenuItemNewMessage);
						jMenuItemNewMessage.setText("New Instant Message");
						jMenuItemNewMessage
								.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent evt) {
										jMenuItemNewMessageActionPerformed(evt);
									}
								});
					}
					{
						jMenuShow = new JMenu();
						jMenuBuddies.add(jMenuShow);
						jMenuShow.setText("Show");
						{
							jCheckBoxMenuShowOfflineBuddies = new JCheckBoxMenuItem();
							jMenuShow.add(jCheckBoxMenuShowOfflineBuddies);
							jCheckBoxMenuShowOfflineBuddies
									.setText("Show Offline Buddies");
							jCheckBoxMenuShowOfflineBuddies
									.addActionListener(new ActionListener() {
										public void actionPerformed(
												ActionEvent evt) {
											jCheckBoxMenuShowOfflineBuddiesActionPerformed(evt);
										}
									});

						}
					}
					{
						jMenuSortBuddies = new JMenu();
						jMenuBuddies.add(jMenuSortBuddies);
						jMenuBuddies.add(getJMenuAddBuddy());
						jMenuBuddies.add(getJMenuRemoveBuddy());
						jMenuBuddies.add(getJMenuQuit());

						jMenuSortBuddies.setText("Sort Buddies");
						{
							jRadioButtonSortAlphabetically = new JRadioButtonMenuItem();
							jMenuSortBuddies
									.add(jRadioButtonSortAlphabetically);
							jRadioButtonSortAlphabetically
									.setText("Alphabetically");
							jButtonGroup.add(jRadioButtonSortAlphabetically);
							jRadioButtonSortAlphabetically
									.addActionListener(new ActionListener() {
										public void actionPerformed(
												ActionEvent evt) {
											jRadioButtonSortAlphabeticallyActionPerformed(evt);
										}
									});
						}
						{
							jRadioButtonSortActivity = new JRadioButtonMenuItem();
							jMenuSortBuddies.add(jRadioButtonSortActivity);
							jRadioButtonSortActivity.setText("Recent Activity");
							jButtonGroup.add(jRadioButtonSortActivity);
							jRadioButtonSortActivity.setSelected(true);
							isRecentActivity = true;
							jRadioButtonSortActivity
									.addActionListener(new ActionListener() {
										public void actionPerformed(
												ActionEvent evt) {
											jRadioButtonSortActivityActionPerformed(evt);
										}
									});
						}
					}
				}
				{
					jMenuAccounts = new JMenu();
					jMenuBarList.add(jMenuAccounts);
					jMenuAccounts.setText("Accounts");
					jMenuAccounts.add(getJMenuRegisteredEmail());
				}
				{
					jMenuTools = new JMenu();
					jMenuBarList.add(jMenuTools);
					jMenuBarList.add(getJMenuAccount());
					jMenuTools.setText("Tools");
					jMenuTools.add(getJMenuAbout());
				}
			}
			pack();
			setSize(400, 300);
			JScrollPane scrollPane = new JScrollPane(getJListOfBuddies());

			setVisible(true);
			getContentPane().add(getStatusMoodPanel(), BorderLayout.SOUTH);
			getContentPane().add(scrollPane, BorderLayout.CENTER);
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

	private JMenuItem getJMenuAddBuddy() {
		if (jMenuAddBuddy == null) {
			jMenuAddBuddy = new JMenuItem();
			jMenuAddBuddy.setText("Add Buddy");
			jMenuAddBuddy.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					jMenuAddBuddyActionPerformed(evt);
				}
			});
		}
		return jMenuAddBuddy;
	}

	private JMenuItem getJMenuQuit() {
		if (jMenuQuit == null) {
			jMenuQuit = new JMenuItem();
			jMenuQuit.setText("Logout");
			jMenuQuit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					jMenuQuitActionPerformed(evt);
				}
			});
		}
		return jMenuQuit;
	}

	private JMenuItem getJMenuAbout() {
		if (jMenuSystemLog1 == null) {
			jMenuSystemLog1 = new JMenuItem();
			jMenuSystemLog1.setText("System Log");
			jMenuSystemLog1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					jMenuSystemLog1ActionPerformed(evt);
				}
			});
		}
		return jMenuSystemLog1;
	}

	private JPanel getStatusMoodPanel() {
		if (StatusMoodPanel == null) {
			StatusMoodPanel = new JPanel();
			StatusMoodPanel.setPreferredSize(new java.awt.Dimension(384, 30));
			StatusMoodPanel.add(getJComboBoxStatus());
		}
		return StatusMoodPanel;
	}

	private JList getJListOfBuddies() {
		if (jListOfBuddies == null) {
			ListModel jListOfBuddiesModel = new DefaultComboBoxModel(
					new String[] { "Item One", "Item Two" });
			jListOfBuddies = new JList();
			jListOfBuddies.setPreferredSize(new java.awt.Dimension(384, 163));
			jListOfBuddies.addMouseListener(this);
			jListOfBuddies.setModel(jListOfBuddiesModel);
		}
		return jListOfBuddies;
	}

	public void loadBuddiesIntoList(Roster r) {
		Collection<RosterEntry> entries = r.getEntries();
		ArrayList<RosterEntry> list = new ArrayList<RosterEntry>(entries);

		if (jCheckBoxMenuShowOfflineBuddies.isSelected())
			showOffline = true;
		else
			showOffline = false;

		if (showOffline == false) {
			Iterator<RosterEntry> iter = list.iterator();
			while (iter.hasNext()) {
				RosterEntry temp = iter.next();
				String tempPresence = processPresences(r.getPresence(temp
						.getUser()));
				if (tempPresence.equals("Offline"))
					iter.remove();
			}

		}
		DefaultListModel listModel = new DefaultListModel();
		buddyListEntries = list;

		if (isAlphabetical == true) {
			Collections.sort(list, new CmpByAlphabetical());
		} else if (isRecentActivity == true) {
			Collections.sort(list, new CmpByActivity(r));
		}

		for (RosterEntry entry : list) {
			String s = entry.getUser() + "    ("
					+ processPresences(r.getPresence(entry.getUser())) + ") ";
			if (!(r.getPresence(entry.getUser()).getStatus() == null)) {
				s = s + " Status: "
						+ r.getPresence(entry.getUser()).getStatus();
			}

			listModel.addElement(s);
		}
		jListOfBuddies.setModel(listModel);
		repaint();
	}

	private void jMenuQuitActionPerformed(ActionEvent evt) {
		connectionManager.logout();
	}

	private JMenu getJMenuAccount() {
		if (jMenuHelp == null) {
			jMenuHelp = new JMenu();
			jMenuHelp.setText("Help");
			jMenuHelp.add(getJMenuItem1());
			jMenuHelp.add(getJMenuDeveloperInfo1());
		}
		return jMenuHelp;
	}

	private JMenuItem getJMenuItem1() {
		if (jMenuAbout == null) {
			jMenuAbout = new JMenuItem();
			jMenuAbout.setText("About");
			jMenuAbout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					jMenuAboutActionPerformed(evt);
				}
			});
		}
		return jMenuAbout;
	}

	private JMenuItem getJMenuDeveloperInfo1() {
		if (jMenuDeveloperInfo == null) {
			jMenuDeveloperInfo = new JMenuItem();
			jMenuDeveloperInfo.setText("Developer Info");
			jMenuDeveloperInfo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					jMenuDeveloperInfoActionPerformed(evt);
				}
			});
		}
		return jMenuDeveloperInfo;
	}

	private JMenuItem getJMenuRegisteredEmail() {
		if (jMenuRegisteredEmail == null) {
			jMenuRegisteredEmail = new JMenuItem();
			jMenuRegisteredEmail
					.setText("Display Registered Email with this Account");
			jMenuRegisteredEmail.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					jMenuRegisteredEmailActionPerformed(evt);
				}
			});
		}
		return jMenuRegisteredEmail;
	}

	private JComboBox getJComboBoxStatus() {
		if (jComboBoxStatus == null) {
			ComboBoxModel jComboBoxStatusModel = new DefaultComboBoxModel(
					new String[] { "Available", "Away", "Do Not Disturb",
							"Invisible", "**Custom Status**",
							"Erase Custom Status" });
			jComboBoxStatus = new JComboBox();
			jComboBoxStatus.setModel(jComboBoxStatusModel);
			jComboBoxStatus.setPreferredSize(new java.awt.Dimension(383, 26));
			jComboBoxStatus.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					jComboBoxStatusActionPerformed(evt);
				}
			});
		}
		return jComboBoxStatus;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		if (arg0.getClickCount() == 2) {
			connectionManager.newChat(buddyListEntries.get(jListOfBuddies
					.getSelectedIndex()));
		}

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	private static String processPresences(Presence p) {
		if (p.getMode() == null
				&& p.getType() == Presence.Type.valueOf("available")) {
			return "Online";
		}
		if (p.getMode() == Presence.Mode.valueOf("dnd")) {
			return "Busy";
		}
		if (p.getMode() == Presence.Mode.valueOf("chat")) {
			return "Online";
		}
		if (p.getMode() == Presence.Mode.valueOf("away")) {
			return "Away";
		}
		if (p.getMode() == Presence.Mode.valueOf("xa")) {
			return "Idle";
		}
		// if (p.getMode().equals(Presence.Mode.valueOf(")))
		return "Offline";
	}

	private JMenuItem getJMenuRemoveBuddy() {
		if (jMenuRemoveBuddy == null) {
			jMenuRemoveBuddy = new JMenuItem();
			jMenuRemoveBuddy.setText("Remove Buddy");
			jMenuRemoveBuddy.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					jMenuRemoveBuddyActionPerformed(evt);
				}
			});
		}
		return jMenuRemoveBuddy;
	}

	private void jMenuAddBuddyActionPerformed(ActionEvent evt) {
		System.out.println("jMenuAddBuddy.actionPerformed, event=" + evt);
		connectionManager.log.addLog("jMenuAddBuddy.actionPerformed, event="
				+ evt);
		@SuppressWarnings("unused")
		AddBuddyFrame temp = new AddBuddyFrame(connectionManager);
	}

	private void jMenuRemoveBuddyActionPerformed(ActionEvent evt) {
		System.out.println("jMenuRemoveBuddy.actionPerformed, event=" + evt);
		connectionManager.log.addLog("jMenuRemove.actionPerformed, event="
				+ evt);
		@SuppressWarnings("unused")
		RemoveBuddyFrame temp = new RemoveBuddyFrame(connectionManager);
	}

	private void jMenuDeveloperInfoActionPerformed(ActionEvent evt) {
		System.out.println("jMenuDeveloperInfo.actionPerformed, event=" + evt);
		connectionManager.log
				.addLog("jMenuDeveloperInfo.actionPerformed, event=" + evt);
		@SuppressWarnings("unused")
		DeveloperInfoFrame temp = new DeveloperInfoFrame();
	}

	private void jMenuAboutActionPerformed(ActionEvent evt) {
		System.out.println("jMenuAbout.actionPerformed, event=" + evt);
		connectionManager.log
				.addLog("jMenuAbout.actionPerformed, event=" + evt);
		@SuppressWarnings("unused")
		AboutFrame temp = new AboutFrame();
	}

	private void jMenuSystemLog1ActionPerformed(ActionEvent evt) {
		System.out.println("jMenuSystemLog1.actionPerformed, event=" + evt);
		connectionManager.log.addLog("jMenuSystemLog.actionPerformed, event="
				+ evt);
		connectionManager.log.setVisible(true);
	}

	private void jMenuRegisteredEmailActionPerformed(ActionEvent evt) {
		System.out
				.println("jMenuRegisteredEmail.actionPerformed, event=" + evt);
		connectionManager.log
				.addLog("jMenuRegisteredEmail.actionPerformed, event=" + evt);
		JOptionPane.showMessageDialog(new JFrame(),
				connectionManager.getUserEmail(), "Registered Email",
				JOptionPane.INFORMATION_MESSAGE);
	}

	private void jCheckBoxMenuShowOfflineBuddiesActionPerformed(ActionEvent evt) {
		loadBuddiesIntoList(connectionManager.loadRoster());
		repaint();
	}

	private void jRadioButtonSortAlphabeticallyActionPerformed(ActionEvent evt) {
		System.out
				.println("jRadioButtonSortAlphabetically.actionPerformed, event="
						+ evt);
		connectionManager.log
				.addLog("jRadioButtonSortAlphabetically.actionPerformed, event="
						+ evt);
		if (jRadioButtonSortAlphabetically.isSelected()) {
			isAlphabetical = true;
			isRecentActivity = false;
		} else {
			isAlphabetical = false;
			isRecentActivity = false;
		}
		loadBuddiesIntoList(connectionManager.loadRoster());
		repaint();
	}

	private void jRadioButtonSortActivityActionPerformed(ActionEvent evt) {
		System.out.println("jRadioButtonSortActivity.actionPerformed, event="
				+ evt);
		connectionManager.log
				.addLog("jRadioButtonSortActivity.actionPerformed, event="
						+ evt);
		if (jRadioButtonSortActivity.isSelected()) {
			isAlphabetical = false;
			isRecentActivity = true;
		} else {
			isAlphabetical = false;
			isRecentActivity = false;
		}
		loadBuddiesIntoList(connectionManager.loadRoster());
		repaint();
	}

	private void jMenuItemNewMessageActionPerformed(ActionEvent evt) {
		System.out.println("jMenuItemNewMessage.actionPerformed, event=" + evt);
		connectionManager.log
				.addLog("jMenuItemNewMessage.actionPerformed, event=" + evt);
		@SuppressWarnings("unused")
		NewMessageFrame temp = new NewMessageFrame(connectionManager);

	}

	private void jComboBoxStatusActionPerformed(ActionEvent evt) {
		Presence presence = new Presence(Presence.Type.valueOf("available"),
				"", 0, Presence.Mode.valueOf("available"));
		String s = (String) jComboBoxStatus.getSelectedItem();
		System.out.println(s);
		if (s.equals("Available")) {
			presence = new Presence(Presence.Type.valueOf("available"), "", 0,
					Presence.Mode.valueOf("available"));
			connectionManager.setPresence(presence);
		} else if (s.equals("Away")) {
			presence = new Presence(Presence.Type.valueOf("available"), "", 0,
					Presence.Mode.valueOf("away"));
			connectionManager.setPresence(presence);
		} else if (s.equals("Do Not Disturb")) {
			presence = new Presence(Presence.Type.valueOf("available"), "", 0,
					Presence.Mode.valueOf("dnd"));
			connectionManager.setPresence(presence);
		} else if (s.equals("Invisible")) {
			presence = new Presence(Presence.Type.valueOf("unavailable"));
		} else if (s.equals("**Custom Status**")) {
			DialogBox status = new DialogBox(connectionManager);
			status.setLabel("Status: ");
			status.setVisible(true);
		} else if (s.equals("Erase Custom Status")) {
			connectionManager.setStatus("");
		}
		connectionManager.setPresence(presence);
	}

}
