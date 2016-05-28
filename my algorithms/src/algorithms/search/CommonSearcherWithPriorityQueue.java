package algorithms.search;

import java.util.PriorityQueue;

import domains.Searchable;
import domains.State;

/**
 * This abstract class represents Common Searcher with priority queue implementation
 * This abstract class implements Searcher interface
 * @author MatanA
 *
 */
public abstract class CommonSearcherWithPriorityQueue extends CommonSearcher {

	protected PriorityQueue<State> openList;
	protected PriorityQueue<State> closedList;
	
	/**
	 * CommonSearcherWithPriorityQueue Constructor
	 */
	public CommonSearcherWithPriorityQueue() 
	{
		openList = new PriorityQueue<State>();
		closedList = new PriorityQueue<State>();
	}
	
	@Override
	public abstract Solution search(Searchable s);

}
