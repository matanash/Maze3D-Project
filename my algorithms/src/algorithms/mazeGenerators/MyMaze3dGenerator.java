package algorithms.mazeGenerators;

import model.maze3d.Direction;
import model.maze3d.Maze3d;
import model.maze3d.Position;

/**
 * MyMaze3dGenerator Concrete Class
 * This class Generate Maze according to DFS Algorithm
 * @author Matan Ashkenazi and Noee Cohen
 * @version - 1.0
 */

public class MyMaze3dGenerator extends CommonMaze3dGenerator 
{
	/**
	 * Method which generate the maze according to DFS Algorithm
	 */
	@Override
	public Maze3d generate(int height, int length, int width) throws Exception 
	{
		this.maze3d = new Maze3d(height,length,width);
		maze3d.initializeMaze3dInWalls();
		maze3d.setStartPosition(new Position(0,0,0));
		DFS(maze3d.getStartPosition());
		return this.maze3d;
	}
	
	
	/**
	 * Private Method to empty Positions according DFS Algorithm
	 * @param - current Position to activate DFS in a recursive call 
	 */
	private void DFS(Position currPos) 
	{
		Direction[] directions = this.maze3d.getPossibleDirections(currPos);
		Position neighbor1,neighbor2;
		try{
			this.maze3d.setGoalPosition(currPos);
		}
		catch (Exception e){}
		for (int i=0;i<directions.length;i++)
		{
			switch (directions[i]) 
			{
				case Up: // Up
					neighbor1 = new Position(currPos.getY()-1,currPos.getX(),currPos.getZ());
					neighbor2 = new Position(currPos.getY()-2,currPos.getX(),currPos.getZ());
					if(!maze3d.isInMaze(neighbor2))
						continue;
					if (!maze3d.isPositionEmpty(neighbor2))
					{
						maze3d.setEmpty(neighbor1);
						maze3d.setEmpty(neighbor2);
						DFS(neighbor2);
					}
					break;
				case Down: // Down
					neighbor1 = new Position(currPos.getY()+1,currPos.getX(),currPos.getZ());
					neighbor2 = new Position(currPos.getY()+2,currPos.getX(),currPos.getZ());
					if(!maze3d.isInMaze(neighbor2))
						continue;
					if (!maze3d.isPositionEmpty(neighbor2))
					{
						maze3d.setEmpty(neighbor1);
						maze3d.setEmpty(neighbor2);
						DFS(neighbor2);
					}
					break;
					
					
				case Left: // Left
					neighbor1 = new Position(currPos.getY(),currPos.getX()-1,currPos.getZ());
					neighbor2 = new Position(currPos.getY(),currPos.getX()-2,currPos.getZ());
					if(!maze3d.isInMaze(neighbor2))
						continue;
					if (!maze3d.isPositionEmpty(neighbor2))
					{
						maze3d.setEmpty(neighbor1);
						maze3d.setEmpty(neighbor2);
						DFS(neighbor2);
					}
					break;
				case Right: // Right
					neighbor1 = new Position(currPos.getY(),currPos.getX()+1,currPos.getZ());
					neighbor2 = new Position(currPos.getY(),currPos.getX()+2,currPos.getZ());
					if(!maze3d.isInMaze(neighbor2))
						continue;
					if (!maze3d.isPositionEmpty(neighbor2))
					{
						maze3d.setEmpty(neighbor1);
						maze3d.setEmpty(neighbor2);
						DFS(neighbor2);
					}
					break;
					
	
				case Backward: // Backward
					neighbor1 = new Position(currPos.getY(),currPos.getX(),currPos.getZ()-1);
					neighbor2 = new Position(currPos.getY(),currPos.getX(),currPos.getZ()-2);
					if(!maze3d.isInMaze(neighbor2))
						continue;
					if (!maze3d.isPositionEmpty(neighbor2))
					{
						maze3d.setEmpty(neighbor1);
						maze3d.setEmpty(neighbor2);
						DFS(neighbor2);
					}
					break;
					
				case Forward: // Forward
					neighbor1 = new Position(currPos.getY(),currPos.getX(),currPos.getZ()+1);
					neighbor2 = new Position(currPos.getY(),currPos.getX(),currPos.getZ()+2);
					if(!maze3d.isInMaze(neighbor2))
						continue;
					if (!maze3d.isPositionEmpty(neighbor2))
					{
						maze3d.setEmpty(neighbor1);
						maze3d.setEmpty(neighbor2);
						DFS(neighbor2);
					}
					break;
				
				default:
					break; 
			}
		}
		
		
	}

	
	
	
}
	
