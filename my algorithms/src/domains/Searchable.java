package domains;

import java.util.HashMap;

/**
 * This class represents an searchable object.
 * It can be subclassed to represent an object that the application wants to have searched. 
 * @author Matan Ashkenazi and Noee Cohen
 * @version - 1.0
 */
public interface Searchable 
{
	/**
	 * Get the Start state of any searchable problem
	 * @return - state represent the start state of the problem
	 */
	State getStartState();
	/**
	 * Get the Goal state of any searchable problem
	 * @return - state represent the start state of the problem
	 */
	State getGoalState();
	/**
	 * Get all possible action can be reach from known state of any searchable problem
	 * @param state -  represent known state in searchable problem
	 * @return - Hash map that maps between any possible action to its desirable state 
	 */
	HashMap<Action, State> getAllPossibleActions(State state);
}

