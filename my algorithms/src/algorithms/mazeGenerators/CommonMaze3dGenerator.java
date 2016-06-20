package algorithms.mazeGenerators;

/**
 * An abstract class for all Maze3d Generators
 *@author Matan Ashkenazi and Noee Cohen
 *@Version - 1.0
 */

import java.util.Random;

import model.maze3d.Maze3d;

public abstract class CommonMaze3dGenerator implements Maze3dGenerator 
{
	public Maze3d maze3d;
	protected Random rand = new Random();
	
	/**
	 * Evaluate how much time (in seconds) generate method run
	 */
	public String measureAlgorithmTime(int height,int length,int width) throws Exception
	{
		StringBuilder sb = new StringBuilder();
		sb.append("It takes ");
		
		Double startTime = new Double(System.currentTimeMillis());
		this.maze3d = generate(height,length,width);
		Double finishTime = new Double(System.currentTimeMillis());
		sb.append((finishTime.doubleValue()-startTime.doubleValue())/1000);
		sb.append(" Seconds to Generate 3D Maze with "+ height+"X"+length+"X"+width+" Dimensions");
		return sb.toString();
	}
	
	
	/**
	 * Abstract method witch every generator Concrete Algorithm will implement
	 */
	public abstract Maze3d generate (int height,int length,int width) throws Exception;
}
