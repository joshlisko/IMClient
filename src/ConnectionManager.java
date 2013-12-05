/*
 * @author Josh Lisko, Chris Overend
 * 
 * This is the main class. Before you can access the buddy list, you must first login via
 * the LoginFrame. After a successful login, the buddylist loads your buddies into a jScrollPane.
 * The buddylist frame has a jMenuBarList at the top, the scrollPane in the middle, and
 * a jComboBox that sets the user's status at the bottom. 
 * 
 * There is a fair amount of functionality in this program:
 * Start a chat via a double click on the desired buddy.
 * Start a chat with a buddy not on your list via the new message menu item.
 * Show or hide offline buddies.
 * Sort buddies alphabetically or by recent activity.
 * Add a buddy.
 * Remove a buddy.
 * Logout.
 * Display the registered email with the account that is logged in.
 * Display a system log.
 * Display a frame describing what this program exactly is.
 * Display a frame describing who the developers are.
 * Find/Search for a keyword in a chat.
 * View a log of a chat with a buddy.
 * Remove a buddy via the ChatWindow.
 * Close a chat window.
 * Enable or disable chat logging.
 * Enable or disable timestamps.
 * 
 * Setting privacy settings was an initial goal of this program but due to time constraints,
 * this goal was not met.
 * 
 * One bug that has not been solved deals with the find function. The only way to successfully
 * use the find function is to click the find menu, enter the keyword, select the chat area, and
 * then hit the find button.
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Collection;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;

public class ConnectionManager {

	private ChatManager chatmanager;
	private BuddyList buddyList;
	private static LoginFrame loginFrame;
	private Roster roster;
	private XMPPConnection connection;
	private HashMap<RosterEntry, Chat> chats;
	private HashMap<RosterEntry, ChatWindow> windows;
	public SystemLogFrame log = new SystemLogFrame();
	private Presence currentPresence;

	/*
	 * Priority: Core
	 * 
	 * @param args
	 * 
	 * @return void the method runs the program
	 */
	public static void main(String[] args) {
		new ConnectionManager().initApp();
	}

	/*
	 * Priority: Core
	 * 
	 * @param username, password
	 * 
	 * @return boolean returns true if a user can login and false if the user
	 * cannot log in the method attempts to login with credentials
	 */
	public boolean login(String username, String password) {
		ConnectionConfiguration connectionConfig = new ConnectionConfiguration(
				"talk.google.com", 5222, "gmail.com");
		connection = new XMPPConnection(connectionConfig);

		try {
			connection.connect();
		} catch (XMPPException ex) {
			JOptionPane
					.showMessageDialog(new JFrame(),
							"Check Your Internet Connection",
							"Failed to connect to " + connection.getHost(),
							JOptionPane.WARNING_MESSAGE);
			loginFrame.clearCredentials();
			return false;
		}
		try {
			connection.login(username, password);
			System.out.println("Logged in as " + connection.getUser());
			log.addLog("Logged in as " + connection.getUser());
			loginFrame.dispose();

			Presence presence = new Presence(Presence.Type.available);
			connection.sendPacket(presence);
			buddyList = new BuddyList(this);
			roster = loadRoster();
			printRoster();
			loadBuddyList(roster);
			roster.addRosterListener(new MyRosterListener(this));
			chatmanager = connection.getChatManager();
			chats = new HashMap<RosterEntry, Chat>();
			windows = new HashMap<RosterEntry, ChatWindow>();
			connection.addPacketListener(new MyPacketListener(this),
					new MyPacketFilter());
			currentPresence = new Presence(Presence.Type.valueOf("available"));
			return true;

		} catch (XMPPException ex) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Wrong Username or Password", "Error",
					JOptionPane.ERROR_MESSAGE);
			// loginFrame.clearCredentials();
			return false;
		}

	}

	/*
	 * Priority: Preferred
	 * 
	 * @param
	 * 
	 * @return void the method logs the user out of the program
	 */
	public void logout() {
		connection.disconnect();
		buddyList.dispose();
		System.exit(-1);
	}

	/*
	 * Priority: Core
	 * 
	 * @param buddy
	 * 
	 * @return void the method takes a buddy and opens a new chat with said
	 * buddy.
	 */
	public void newChat(RosterEntry buddy) {
		try {
			Chat newChat = null;
			chatmanager = null;
			File history = new File(buddy.getUser() + ".txt");
			if (!(history.exists())) {
				history.createNewFile();
			}

			ChatWindow chatWindow = new ChatWindow(buddy, this);
			chatmanager = connection.getChatManager();
			newChat = chatmanager.createChat(buddy.getUser(),
					new MyMessageListener(chatWindow, buddy, this));
			chats.put(buddy, newChat);
			windows.put(buddy, chatWindow);

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
			log.addLog(ex.toString());
		}
	}

	/*
	 * Priority: Core
	 * 
	 * @param id
	 * 
	 * @return void the method takes a string and opens a new chat with the
	 * buddy associated with the id.
	 */
	public void newChat(String id) {
		Roster buddies = this.connection.getRoster();
		RosterEntry buddy = buddies.getEntry(id);
		newChat(buddy);
	}

	/*
	 * Priority: Preferred
	 * 
	 * @param buddy, window
	 * 
	 * @return void the method takes a buddy and a window and closes the chat
	 * window associated with the buddy.
	 */
	public void endChat(RosterEntry buddy, ChatWindow window) {
		if (roster.contains(buddy.getUser())) {
			chats.remove(buddy);
			windows.remove(buddy);
		}
	}

	/*
	 * Priority: Core
	 * 
	 * @param buddy, message
	 * 
	 * @return void the method sends a specified message to a specified buddy.
	 */
	public void sendMessage(RosterEntry buddy, String message) {

		try {
			Chat chat = chats.get(buddy);
			chat.sendMessage(message);
		} catch (XMPPException e) {

		}
	}

	/*
	 * Priority: Core
	 * 
	 * @param buddy, message
	 * 
	 * @return void the method receives a specified message from a specified
	 * buddy.
	 */
	public void recieveNewMessage(RosterEntry buddy, Message message) {
		try {
			windows.get(buddy).recieveMessage(message.getBody());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/*
	 * Priority: Preferred
	 * 
	 * @param id
	 * 
	 * @return void the method adds a buddy associated with the given id.
	 */
	public void addBuddy(String id) {
		Roster buddies = this.connection.getRoster();
		String[] groups = null;
		try {
			buddies.createEntry(id, id, groups);
		} catch (XMPPException e) {

		}

		
	}

	/*
	 * Priority: Preferred
	 * 
	 * @param id
	 * 
	 * @return void the method removes a buddy associated with the given id.
	 */
	public void removeBuddy(String id) {
		RosterEntry buddy = connection.getRoster().getEntry(id);
		try {
			connection.getRoster().removeEntry(buddy);
		} catch (XMPPException e) {

		}
	}

	/*
	 * Priority: Desirable
	 * 
	 * @param presence
	 * 
	 * @return void the method sets the presence of the user with the given
	 * presence.
	 */
	public void setPresence(Presence presence) {
		if (!(currentPresence.getStatus() == null)) {
			presence.setStatus(currentPresence.getStatus());
		}
		Packet packet = (Packet) presence;
		currentPresence = presence;
		System.out.println(presence.getMode());
		connection.sendPacket(packet);
	}

	/*
	 * Priority: Desirable
	 * 
	 * @param status
	 * 
	 * @return void the method sets the status of the user with the given
	 * status.
	 */
	public void setStatus(String status) {
		Presence.Mode s = currentPresence.getMode();
		currentPresence.setStatus(status);
		currentPresence.setMode(s);
		Packet packet = (Packet) currentPresence;
		connection.sendPacket(packet);
	}

	/*
	 * Priority: Desirable
	 * 
	 * @param entry, message
	 * 
	 * @return void the method writes the current chat to a text file for a
	 * specified buddy and message.
	 */
	public void writeHistory(RosterEntry entry, String message) {
		try {
			FileWriter fWriter = new FileWriter(entry.getUser() + ".txt", true);
			BufferedWriter bWriter = new BufferedWriter(fWriter);
			bWriter.write(message);
			bWriter.newLine();
			bWriter.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/*
	 * Priority: Desirable
	 * 
	 * @param entry
	 * 
	 * @return void the method opens the history of a specified buddy.
	 */
	public void openHistory(RosterEntry entry) {
		File history = new File(entry.getUser() + ".txt");
		try {
			if (history.exists()) {
				String[] cmd = { "notepad", entry.getUser() + ".txt" };
				Runtime runtime = Runtime.getRuntime();
				@SuppressWarnings("unused")
				Process proc = runtime.exec(cmd);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/*
	 * Priority: Preferred
	 * 
	 * @param
	 * 
	 * @return void the method prints the user's roster to the console as well
	 * as the system log.
	 */
	public void printRoster() {
		roster = loadRoster();
		Collection<RosterEntry> entries = roster.getEntries();
		for (RosterEntry entry : entries) {
			System.out.println(String.format("Buddy:%1$s - Status:%2$s",
					entry.getName(), entry.getStatus()));
			log.addLog(String.format("Buddy:%1$s - Status:%2$s",
					entry.getName(), entry.getStatus()));
		}
	}

	/*
	 * Priority: Core
	 * 
	 * @param
	 * 
	 * @return void the method initiates the program.
	 */
	private void initApp() {
		loginFrame = new LoginFrame(this);
	}

	/*
	 * Priority: Core
	 * 
	 * @param
	 * 
	 * @return Roster the method returns the roster of the user's connection.
	 */
	public Roster loadRoster() {
		return connection.getRoster();
	}

	/*
	 * Priority: Core
	 * 
	 * @param r
	 * 
	 * @return void the method loads the roster of the user's connection into
	 * the GUI frame.
	 */
	public void loadBuddyList(Roster r) {
		buddyList.loadBuddiesIntoList(r);
	}

	/*
	 * Priority: Preferred
	 * 
	 * @param
	 * 
	 * @return HashMap<RosterEntry, Chat> the method returns a hashmap of buddys
	 * to chats.
	 */
	public HashMap<RosterEntry, Chat> getChatMap() {
		return this.chats;
	}

	/*
	 * Priority: Desirable
	 * 
	 * @param
	 * 
	 * @return String the method returns the current user's email.
	 */
	public String getUserEmail() {
		String[] temp = connection.getUser().split("/Smack");
		return temp[0];
	}

	/*
	 * Priority: Preferred
	 * 
	 * @param
	 * 
	 * @return Roster the method returns the roster of the user.
	 */
	public Roster getRoster() {
		return roster;
	}

	/*
	 * Priority: Preferred
	 * 
	 * @param
	 * 
	 * @return XMPPConnection the method returns the connection of the user.
	 */
	public XMPPConnection getConnection() {
		return connection;
	}

}
