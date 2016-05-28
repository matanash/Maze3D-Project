package algorithms.mazeGenerators;

import model.maze3d.Maze3d;

/**
 * Interface for Maze3d generators
 **/
public interface Maze3dGenerator 
{
	public Maze3d generate (int height,int length,int width) throws Exception;
	public String measureAlgorithmTime(int height,int length,int width) throws Exception;
}
