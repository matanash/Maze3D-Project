package algorithms.mazeGenerators;

import model.maze3d.Maze3d;

/**
 * Interface for Maze3d generators
 * @author Matan Ashkenazi and Noee Cohen
 * @version - 1.0
 **/
public interface Maze3dGenerator 
{
	/**
	 * @param height - the maze height / floors
	 * @param length - the maze length / rows
	 * @param width  - the maze width / columns
	 * @return - the generated Maze3d 
	 * @throws Exception - Exception
	 */
	public Maze3d generate (int height,int length,int width) throws Exception;
	/**
	 * Evaluate how much time (in seconds) generate method run
	 * @param height - the maze height / floors
	 * @param length - the maze length / rows
	 * @param width  - the maze width / columns
	 * @return - string represents the measure time take the algorithm to run
	 * @throws Exception - Exception
	 */
	public String measureAlgorithmTime(int height,int length,int width) throws Exception;
}
