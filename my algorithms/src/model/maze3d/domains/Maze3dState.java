package model.maze3d.domains;

import java.io.Serializable;

import domains.State;
import model.maze3d.Position;

/**
 * This class adapting between State Object to Position object
 * based on Object Adapter Design Pattern
 * @author MatanA
 */
public class Maze3dState extends State implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Position currPos;
	
	/**
	 * Maze3dState C'tor
	 * @param currPos - Position to wrapped
	 */
	public Maze3dState(Position currPos) {
		this.currPos = currPos;
		this.setDescription(currPos.toString());
	}
	
	/**
	 * This method get the current Maze3dState of the player
	 * @return Position into the Maze3dState
	 */
	public Position getCurrPosition() {
		return currPos;
	}
	
	/**
	 * This method set the Maze3dState position
	 * @param currPos - Position of Maze3dState to set
	 */
	public void setCurrPosition(Position currPos) {
		this.currPos = currPos;
	}
	
	
	
}
