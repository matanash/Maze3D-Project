package MVP.model;

import MVP.presenter.Properties;
import model.maze3d.Maze3d;
import model.maze3d.Position;

// TODO: Auto-generated Javadoc
//import model.maze3d.Maze3d;
/**
 * this Interface define Maze3d Model functionality.
 *
 * @author MatanA
 */
public interface Model {
	
	/**
	 * Dir.
	 *
	 * @param path the path
	 */
	void dir(String path);
	
	/**
	 * Generate 3 d maze.
	 *
	 * @param name the name
	 * @param height the height
	 * @param length the length
	 * @param width the width
	 * @throws Exception the exception
	 */
	void generate3dMaze(String name,int height, int length, int width) throws Exception;
	
	/**
	 * Solve maze.
	 *
	 * @param name the name
	 * @param algorithm the algorithm
	 */
	void solveMaze(String name,String algorithm);

	/**
	 * Solve maze.
	 *
	 * @param name the name
	 * @param algorithm the algorithm
	 * @param characterPosition the character position
	 */
	void solveMaze(String name, String algorithm, Position characterPosition);
	
	/**
	 * Gets the maze 3 D.
	 *
	 * @param name the name
	 * @return the maze 3 D
	 */
	void getMaze3D(String name);
	
	/**
	 * Gets the start position.
	 *
	 * @param name the name
	 * @return the start position
	 */
	void getStartPosition(String name);

	/**
	 * Gets the goal position.
	 *
	 * @param name the name
	 * @return the goal position
	 */
	void getGoalPosition(String name);
	
	/**
	 * Display cross section by Y.
	 *
	 * @param yLayer the y layer
	 * @param name the name
	 */
	void displayCrossSectionByY(int yLayer, String name);
	
	/**
	 * Display cross section by X.
	 *
	 * @param xLayer the x layer
	 * @param name the name
	 */
	void displayCrossSectionByX(int xLayer,String name);

	/**
	 * Display cross section by Z.
	 *
	 * @param zLayer the z layer
	 * @param name the name
	 */
	void displayCrossSectionByZ(int zLayer,String name);
	
	/**
	 * Save maze to file.
	 *
	 * @param name the name
	 * @param fileName the file name
	 */
	void saveMazeToFile(String name, String fileName);
	
	/**
	 * Load maze from file.
	 *
	 * @param name the name
	 * @param fileName the file name
	 */
	void loadMazeFromFile(String name, String fileName);
	
	/**
	 * Size in file.
	 *
	 * @param filename the filename
	 */
	void sizeInFile(String filename);
	
	/**
	 * Size in memory.
	 *
	 * @param name the name
	 */
	void sizeInMemory(String name);
	
	/**
	 * Gets the solution.
	 *
	 * @param name the name
	 * @return the solution
	 */
	void getSolution(String name);
	
	/**
	 * Maze exists.
	 *
	 * @param name the name
	 * @return true, if successful
	 */
	boolean mazeExists(String name);
	
	/**
	 * Solution exists.
	 *
	 * @param name the name
	 * @return true, if successful
	 */
	boolean solutionExists(String name);
	
	/**
	 * Solution exists.
	 *
	 * @param m3d the m 3 d
	 * @return true, if successful
	 */
	boolean solutionExists(Maze3d m3d);
	
	/**
	 * Gets the descriptor.
	 *
	 * @return the descriptor
	 */
	Object getDescriptor();
	
	/**
	 * Save G zip maps.
	 */
	void saveGZipMaps();
	
	/**
	 * Load G zip maps.
	 */
	void loadGZipMaps();
	
	/**
	 * Sets the properties.
	 *
	 * @param properties the new properties
	 */
	void setProperties(Properties properties);
	
	/**
	 * Gets the properties.
	 *
	 * @return the properties
	 */
	public Properties getProperties();
	
	/**
	 * Exit model.
	 */
	void exitModel();

}
