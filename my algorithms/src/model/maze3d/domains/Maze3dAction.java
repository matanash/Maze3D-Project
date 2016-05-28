package model.maze3d.domains;

import domains.Action;
import model.maze3d.Direction;

/**
 * This class adapting between Action Object to Direction object
 * based on Object Adapter Design Pattern
 * @author MatanA
 */

public class Maze3dAction extends Action {
	
	public static final double mazeMovementCost = 1;
	private Direction dir;
	
	/**
	 * Maze3dAction C'tor
	 * @param dir - Direction to wrapped
	 */
	public Maze3dAction(Direction dir) {
		super(dir.toString(), mazeMovementCost);
	}
	

	/**
	 * This method get the Maze3dAction Direction
	 * @return Direction into Maze3dAction Object
	 */
	public Direction getMove() {
		return dir;
	}
	
	/**
	 * This method set the Maze3dAction direction
	 * @param dir - Direction of Maze3dAction to set
	 */
	public void setMove(Direction dir) {
		this.dir = dir;
	}
}
