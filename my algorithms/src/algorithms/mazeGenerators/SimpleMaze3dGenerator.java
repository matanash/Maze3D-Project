package algorithms.mazeGenerators;

import model.maze3d.Maze3d;
import model.maze3d.Position;

/**
 * Simple maze3d Generator - Just find track between Start and Exit Positions
 * First the algorithm curves according Goal Position y Coordinate
 * Second the algorithm curves according Goal Position x Coordinate
 * Third the algorithm curves according Goal Position z Coordinate
 * @author Matan Ashkenazi and Noee Cohen
 * @version - 1.0
 */

public class SimpleMaze3dGenerator extends CommonMaze3dGenerator 
{
	/**
	 * Method which generate the maze , more formally this method find track from start to goal position
	 */
	@Override
	public Maze3d generate(int height, int length, int width) throws Exception 
	{
		this.maze3d = new Maze3d (height,length,width);
		this.maze3d.initializeMaze3dInWalls();
		this.setRandomEmpty();
		this.maze3d.setStartPosition(new Position(0,0,0));
		this.maze3d.setGoalPosition(new Position(height-1,length-1,width-1));
		this.setPossibleTrack();
		return this.maze3d;
	}
	
	/**
	 * Set random Empty Positions in the 3D Maze
	 */
	private void setRandomEmpty()
	{
		int totalOptionalWalls = this.maze3d.getHeight()*this.maze3d.getLength()*this.maze3d.getWidth();
		int numOfEmpty;
		do{
			numOfEmpty = this.rand.nextInt(totalOptionalWalls);
		}
		while (numOfEmpty<totalOptionalWalls/4);
		for (int i=0;i<numOfEmpty;i++)
		{
			Position temp = new Position(this.rand.nextInt(this.maze3d.getHeight()),this.rand.nextInt(this.maze3d.getLength()),this.rand.nextInt(maze3d.getWidth()));
			this.maze3d.setEmpty(temp);
			
		}
		
	}
	
	/**
	 * Find Possible Track between Start and Exit Positions
	 * Guarantee a solution to the maze
	 */
	private void setPossibleTrack() throws Exception
	{
		
		Position tracker = this.maze3d.getStartPosition();
		while (tracker.getY()<this.maze3d.getGoalPosition().getY())
		{
			this.maze3d.setEmpty(tracker);
			tracker.setY(tracker.getY()+1);
		}
		
		if (tracker.getX()<this.maze3d.getGoalPosition().getX())
		{
			while (tracker.getX()<this.maze3d.getGoalPosition().getX())
			{
				this.maze3d.setEmpty(tracker);
				tracker.setX(tracker.getX()+1);
			}
		}
		else
		{
			while (tracker.getX()>this.maze3d.getGoalPosition().getX())
			{
				this.maze3d.setEmpty(tracker);
				tracker.setX(tracker.getX()-1);
			}
		}
		if (tracker.getZ()<this.maze3d.getGoalPosition().getZ())
		{
			while (tracker.getZ()<this.maze3d.getGoalPosition().getZ())
			{
				this.maze3d.setEmpty(tracker);
				tracker.setZ(tracker.getZ()+1);
			}
		}
		else
		{
			while (tracker.getZ()>this.maze3d.getGoalPosition().getZ())
			{
				this.maze3d.setEmpty(tracker);
				tracker.setZ(tracker.getZ()-1);
			}
		}
	}

}
