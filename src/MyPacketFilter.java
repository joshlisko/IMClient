/*
 * @author Chris Overend
 * 
 * This class filters packets based upon the privacy settings set
 * by the user. This class is not effective because such functionality
 * has not been implemented.
 */

import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.packet.Packet;

public class MyPacketFilter implements PacketFilter {

	public MyPacketFilter() {
		super();

	}

	@Override
	/*
	 * Priority: High
	 * 
	 * @param packet - validates incoming packets
	 * 
	 * @return void - has not return value this method is used to check incoming
	 * packets and validate them
	 */
	public boolean accept(Packet packet) {
		return true;

	}

}