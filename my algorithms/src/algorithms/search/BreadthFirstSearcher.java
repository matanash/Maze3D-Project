package algorithms.search;

import domains.Action;

/**
 * @author MatanA
 * This Class is Concrete class of Searcher Interface
 * This class represents Breadth First Searcher witch search Solution in a searchable problem according to Breadth First Search Algorithm
 */
public class BreadthFirstSearcher extends BFSSearcher {

	
	protected double getMovementCost (Action action) 
	{
		return MOVEMENT_COST;
		
	}
}
