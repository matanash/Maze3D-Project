package algorithms.search;

import java.util.ArrayList;

import domains.State;

/**
 * This abstract class represents Common Searcher
 * This abstract class implements Searcher interface
 * @author Matan Ashkenazi and Noee Cohen
 * @version - 1.0
 */
public abstract class CommonSearcher implements Searcher 
{
	
	private int evaluatedNodes = 0;
	
	/**
	 * This method get Solution backtrack from Entrance Position to Exit Position
	 * @param state - to backtrack from it
	 * @return Solution to Searchable problem 
	 */
	protected Solution backtrack(State state) 
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
	}
	/**
	 * This method get the current evaluated nodes the algorithm open
	 * @return the current evaluated node the algorithm open 
	 */
	public int getEvaluatedNodes() {
		return this.evaluatedNodes;
	}
	/**
	 * This method increase the evaluated nodes that the algorithm open
	 */
	protected void increaseEvaluatedNodes() {
		this.evaluatedNodes += 1;
	}
}
