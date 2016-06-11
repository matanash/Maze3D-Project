package algorithms.search.demo;

import java.util.HashMap;

import domains.Action;
import domains.Searchable;
import domains.State;
import model.maze3d.Direction;
import model.maze3d.Maze3d;
import model.maze3d.Position;
import model.maze3d.domains.Maze3dState;

/**
 * This class adapting between Searchable Object to maze3d object
 * based on Object Adapter Design Pattern
 * @author MatanA
 */
public class Maze3dAdapter implements Searchable {
	
	private Maze3d maze3d ;

	private static final double MOVEMENT_COST = 1.0;
	
	/**
	 * Maze3dAdapter Constructor
	 * @param maze3d - Maze3d the adapter wrapped
	 */
	public Maze3dAdapter(Maze3d maze3d) 
	{
		this.maze3d=maze3d;
	}
	/**
	 * get the wrapped maze3d object
	 * @return
	 */
	public Maze3d getMaze3d() {
		return maze3d;
	}
	/**
	 * set and wrap maze3d object in the adapter
	 * @param maze3d
	 */
	public void setMaze3d(Maze3d maze3d) {
		this.maze3d = maze3d;
	}
	@Override
	public State getStartState() 
	{
		return new Maze3dState(maze3d.getStartPosition());
	}

	@Override
	public State getGoalState() {
		return new Maze3dState(maze3d.getGoalPosition());
	}
	
	/**
	 * This method get the Next Position according to a direction
	 * @param currPos - The current Position
	 * @param dir - The intended direction
	 * @return the Next Position
	 */
	private Position getNextPosition(Position currPos, Direction dir) 
	{	
		switch (dir) 
		{
			case Up:
				return new Position(currPos.getY()-1,currPos.getX(),currPos.getZ());	
			case Down:
				return new Position(currPos.getY()+1,currPos.getX(),currPos.getZ());
			case Left:
				return new Position(currPos.getY(),currPos.getX()-1,currPos.getZ());
			case Right:
				return new Position(currPos.getY(),currPos.getX()+1,currPos.getZ());		
			case Backward:
				return new Position(currPos.getY(),currPos.getX(),currPos.getZ()-1);			
			case Forward:
				return new Position(currPos.getY(),currPos.getX(),currPos.getZ()+1);
			default:
				return null;
		}
	}

	@Override
	public HashMap<Action, State> getAllPossibleActions(State state) 
	{
		Maze3dState maze3dState = (Maze3dState)state;
		Position pos = maze3dState.getCurrPosition();
		Direction[] directions = maze3d.getPossibleMoves(pos);
		
		HashMap<Action, State> actions = new HashMap<Action, State>();
		for (Direction dir: directions) 
		{
			Action action = new Action(dir.toString(), MOVEMENT_COST);
			Maze3dState newMaze3dState = new Maze3dState(getNextPosition(pos, dir));
			actions.put(action, newMaze3dState);
		}
		return actions;
	}

	@Override
	public String toString() {
		return "Maze3dAdapter:" + '\n' +'\t'+ "maze3d:"  + '\n' + maze3d.toString() + "]";
	}

}
