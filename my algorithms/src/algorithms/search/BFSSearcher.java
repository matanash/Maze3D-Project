package algorithms.search;

//import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import domains.Action;
import domains.Searchable;
import domains.State;
import model.maze3d.Direction;


/**
 * @author MatanA
 * This class is concrete class of Searcher interface
 * This class represents Best First Searcher witch search Solution in a searchable problem according to Best First Search Algorithm
 */
public class BFSSearcher extends CommonSearcherWithPriorityQueue 
{
	protected static final double MOVEMENT_COST = 1.0;
	
	@Override
	public Solution search(Searchable s) 
	{
		this.openList.add(s.getStartState());
		
		while (!this.openList.isEmpty()) 
		{
			State state = this.openList.poll();
			closedList.add(state);
			
			//counting the number of evaluated nodes and each state that visited
			increaseEvaluatedNodes();
			
			if (state.equals(s.getGoalState())) 
				return backtrack(state);
			
			HashMap<Action, State> actions = s.getAllPossibleActions(state);
			for (Entry<Action, State> entry: actions.entrySet()) 
			{
				Action action = entry.getKey();
				State successor = entry.getValue();
				
				if (!this.openList.contains(successor) && !closedList.contains(successor)) 
				{
					successor.setCameFrom(state);
					successor.setCost(state.getCost() + this.getMovementCost(action));
					this.openList.add(successor);
				}
				else if (state.getCost() + this.getMovementCost(action) < successor.getCost()) 
				{
					
					successor.setCameFrom(state);
					successor.setCost(state.getCost() + this.getMovementCost(action));
					
					// update the cost priority in the priorityQueue 
					this.openList.remove(successor);
					this.openList.add(successor);					
				}
			}
		}
		return null; // will never happen
	}
	
	/**
	 * This method return movement cost by Direction 
	 * @param action - action to get movement cost from it
	 * @return the movement cost by this action 
	 */
	protected double getMovementCost (Action action) 
	{
		
		if (action.getDescription()==Direction.Up.toString() ||action.getDescription()==Direction.Down.toString())
			return MOVEMENT_COST;
		if (action.getDescription()==Direction.Left.toString() ||action.getDescription()==Direction.Right.toString())
			return MOVEMENT_COST*2;
		if (action.getDescription()==Direction.Backward.toString() ||action.getDescription()==Direction.Forward.toString())
			return MOVEMENT_COST*3;
		return 0;
	}
	
	/**
	 * This method rum backtrack from Exit Position to Entrance Position and return track between them
	 * @param state - to backtrack from it
	 * @return Solution to Searchable problem 
	 */
	/*private Solution backtrack(State state) 
	{
		State currState = state;
		ArrayList<State> states = new ArrayList<State>();
		while (currState != null) 
		{
			states.add(0, currState);
			currState = currState.getCameFrom();
		}
		Solution solution = new Solution();
		solution.setStates(states);
		return solution;
	}*/
	
}
