package algorithms.search;

import java.util.HashMap;
import java.util.HashSet;

import domains.Action;
import domains.Searchable;
import domains.State;


/**
 * This class is concrete class of Searcher interface
 * This class represents DFS Searcher that search Solution in a searchable problem according to DFS Algorithm
 * @author Matan Ashkenazi and Noee Cohen
 * @version - 1.0
 */
public class DFSSearcher extends CommonSearcher {

	private HashSet<State> visitedStates = new HashSet<State>();
	private Solution solution;
	
	/**
	 * This method finds a solution to a Searchable problem
	 * @param s the searchable problem
	 * @return solution
	 */
	@Override	
	public Solution search(Searchable s) {
		
		dfs(s, s.getStartState());
		return this.solution;
		
	}
	
	/**
	 * @param s - searchable problem
	 * @param currState - current State in the searchable problem to search from
	 */
	private void dfs(Searchable s, State currState) 
	{
		
		if (currState.equals(s.getGoalState()))
		{
			this.solution = backtrack(currState);
			return;
		}
		
		this.visitedStates.add(currState);
		
		HashMap<Action,State> actions = s.getAllPossibleActions(currState);
		for(State neighbor: actions.values())
		{
			if (!this.visitedStates.contains(neighbor)) 
			{
				neighbor.setCameFrom(currState);
				increaseEvaluatedNodes();
				dfs(s, neighbor);
			}
		}
		return;
	}


}
