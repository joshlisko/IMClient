/*
 * @author Chris Overend
 * 
 * This class listens for new messages and processes them so 
 * that a user can chat with a buddy.
 */

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.packet.Message;

class MyMessageListener implements MessageListener {
	private ChatWindow chatWin;

	public MyMessageListener(ChatWindow c, RosterEntry e, ConnectionManager m) {
		super();
		chatWin = c;
	}

	/*
	 * Priority: core
	 * 
	 * @param chat, message - the chat for the message, the incoming message
	 * 
	 * @return void has no return value this is created to process incoming
	 * messages and print them out into the chat windows
	 */
	public void processMessage(Chat chat, Message message) {
		chatWin.recieveMessage(message.getBody());
	}

}