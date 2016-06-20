package algorithms.search;

import domains.Searchable;

/**
 * This interface define functionality of solution Searcher
 * @author Matan Ashkenazi and Noee Cohen
 * @version - 1.0
 */
public interface Searcher 
{
	/**
	 * all concrete class implements Searcher interface required to implemet search method that define search solution in searchable problem
	 * @param - The searchable problem required to seek a possible solution from
	 * @return  - possible solution on the searchable problem
	 */
	Solution search(Searchable s);
}
