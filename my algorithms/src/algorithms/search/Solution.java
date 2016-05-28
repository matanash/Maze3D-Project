package algorithms.search;
import java.util.ArrayList;

import domains.State;

/**
 * This class represents Solution of searchable problem
 * @author MatanA
 *
 */
public class Solution {
	private ArrayList<State> states;

	/**
	 * This method return ArrayList of states 
	 * @return states arrayList represents the solution
	 */
	public ArrayList<State> getStates() 
	{
		return this.states;
	}

	/**
	 * This method set ArrayList of states
	 * @param states - arrayList represents the solution to set
	 */
	public void setStates(ArrayList<State> states) 
	{
		this.states = states;
	}

	@Override
	public String toString() {
		String str ="";
		for (State state: states)	
			str += '\n' + state.toString();
		return str;
	}
	
	
}
