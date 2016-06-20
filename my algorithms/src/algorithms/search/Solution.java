package algorithms.search;
import java.io.Serializable;
import java.util.ArrayList;

import domains.State;

/**
 * This class represents Solution of searchable problem
 * @author Matan Ashkenazi and Noee Cohen
 * @version - 1.0
 */
public class Solution implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	/**
	 * @param state - state whose presence in this solution is to be tested
	 * @return - true if this Solution contains the specified state
	 */
	public boolean contains(State state)
	{
		if (states.contains(state))
			return true;
		return false;
	}
	/**
	 * Removes the first occurrence of the specified element from this list, if it is present.
	 * If the Solution does not contain the state, it is unchanged. More formally, removes the state with the lowest index in the solution states list.
	 * @param state - state to remove from the solution states list
	 * @return - Returns true if this solution contained the specified state 
	 */
	public boolean remove(State state)
	{
		return states.remove(state);
	}
	
	
}
