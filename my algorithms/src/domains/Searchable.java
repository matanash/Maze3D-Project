package domains;

import java.util.HashMap;

/**
 * This class represents an searchable object.
 * It can be subclassed to represent an object that the application wants to have searched. 
 * @author MatanA
 */
public interface Searchable 
{
	State getStartState();
	State getGoalState();
	HashMap<Action, State> getAllPossibleActions(State state);
}

