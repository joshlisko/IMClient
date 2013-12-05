/*
 * @author Chris Overend
 * 
 * This class provides a way to sort buddies alphabetically in the buddy list.
 */

import java.util.Comparator;

import org.jivesoftware.smack.RosterEntry;

public class CmpByAlphabetical implements Comparator<RosterEntry> {

	@Override
	/*
	 * Priority: Desirable
	 * 
	 * @param arg0, arg1
	 * 
	 * @return int returns 1 if first bigger than second, 0 if both equal, and
	 * -1 if second bigger than first the method was created in order to sort
	 * the roster based on alphabetical order
	 */
	public int compare(RosterEntry arg0, RosterEntry arg1) {
		int retVal = arg0.getUser().compareTo(arg1.getUser());
		return retVal;
	}

}