/*
 * @author Chris Overend
 * 
 * This class listens and processes incoming packets so
 * that a user can chat with a buddy.
 */

import java.util.HashMap;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;

public class MyPacketListener implements PacketListener {
	private ConnectionManager connectionManager;

	public MyPacketListener(ConnectionManager conn) {
		super();
		this.connectionManager = conn;
	}

	@Override
	/*
	 * Priority: High
	 * 
	 * @param packet
	 * 
	 * @return void \n this method checks for incoming packets and when a packet
	 * it receives, it checks to see if it is a message and if so will create a
	 * new chat if one is not already present
	 */
	public void processPacket(Packet packet) {
		try {
			if (packet instanceof Message) {
				Message message = (Message) packet;
				System.out.println(message.getBody());
				String id = message.getFrom().substring(0,
						message.getFrom().indexOf("/"));
				Roster r = connectionManager.loadRoster();
				if (!(r.contains(id))) {
					connectionManager.addBuddy(id);
				}
				HashMap<RosterEntry, Chat> chatMap = connectionManager
						.getChatMap();
				RosterEntry buddy = r.getEntry(id);
				if (!(chatMap.containsKey(buddy))) {
					connectionManager.newChat(buddy);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
