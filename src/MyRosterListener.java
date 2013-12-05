/*
 * @author Chris Overend
 * 
 * This class listens for changes in the buddy list and
 * adjusts the roster as needed.
 */

import java.util.Collection;

import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterListener;
import org.jivesoftware.smack.packet.Presence;

public class MyRosterListener implements RosterListener {

	ConnectionManager m;

	public MyRosterListener(ConnectionManager m) {
		super();
		this.m = m;
	}

	@Override
	/*
	 * Priority: core
	 * 
	 * @param arg0 - the entries added
	 * 
	 * @return void has no return value this will reload the buddy list after a
	 * new friend is added
	 */
	public void entriesAdded(Collection<String> arg0) {
		Roster r = m.loadRoster();
		m.loadBuddyList(r);

	}

	@Override
	/*
	 * Priority: core
	 * 
	 * @param arg0 - the entries added
	 * 
	 * @return void has no return value this will reload the buddy list after a
	 * friend is removed
	 */
	public void entriesDeleted(Collection<String> arg0) {
		Roster r = m.loadRoster();
		m.loadBuddyList(r);

	}

	@Override
	/*
	 * Priority: Core
	 * 
	 * @param arg0 - the entries added
	 * 
	 * @return void has no return value this will reload the buddy list after an
	 * operation of a friend is performed
	 */
	public void entriesUpdated(Collection<String> arg0) {
		Roster r = m.loadRoster();
		m.loadBuddyList(r);

	}

	@Override
	/*
	 * Priority: high
	 * 
	 * @param arg0 - the change in presence
	 * 
	 * @return void has no return value this will reload the buddy list after a
	 * friend's presence changes
	 */
	public void presenceChanged(Presence arg0) {
		Roster r = m.loadRoster();
		m.loadBuddyList(r);

	}
}
