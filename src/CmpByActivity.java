/*
 * @author Chris Overend
 * 
 * This class provides a way to sort buddies by online activity.
 */

import java.util.Comparator;

import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.packet.Presence;

public class CmpByActivity implements Comparator<RosterEntry> {
	private Roster roster;

	public CmpByActivity(Roster r) {
		super();
		this.roster = r;
	}

	/*
	 * Priority: Desirable
	 * 
	 * @param arg0, arg1 - 2 RosterEntries to compare
	 * 
	 * @return int - returns 1 if first is bigger than second, 0 if equal, and
	 * -1 if second bigger than first this method takes in 2 roster entries and
	 * compares them to see which is had more recent activity (ie. online, busy,
	 * away, etc.)
	 */
	@Override
	public int compare(RosterEntry arg0, RosterEntry arg1) {
		Presence Presence0 = roster.getPresence(arg0.getUser());
		Presence Presence1 = roster.getPresence(arg1.getUser());
		Presence.Type type0 = Presence0.getType();
		Presence.Type type1 = Presence1.getType();
		Presence.Mode mode0 = Presence0.getMode();
		Presence.Mode mode1 = Presence1.getMode();
		String string0 = "";
		String string1 = "";
		int retVal = type0.compareTo(type1);
		if (retVal == 0) {
			if (mode0 == null) {
				string0 = "a";
			} else if (mode0 == Presence.Mode.valueOf("dnd")) {
				string0 = "b";
			} else if (mode0 == Presence.Mode.valueOf("away")) {
				string0 = "c";
			} else if (mode0 == Presence.Mode.valueOf("xa")) {
				string0 = "c";
			}
			if (mode1 == null) {
				string1 = "a";
			} else if (mode1 == Presence.Mode.valueOf("dnd")) {
				string1 = "b";
			} else if (mode1 == Presence.Mode.valueOf("away")) {
				string1 = "c";
			} else if (mode1 == Presence.Mode.valueOf("xa")) {
				string1 = "c";
			}
			retVal = string0.compareTo(string1);
		}
		return retVal;
	}

}
