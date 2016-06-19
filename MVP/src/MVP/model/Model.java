package MVP.model;

import MVP.presenter.Properties;
import model.maze3d.Maze3d;
import model.maze3d.Position;

//import model.maze3d.Maze3d;
/**
 * this Interface define Maze3d Model functionality
 * @author MatanA
 *
 */
public interface Model {
	
	void dir(String path);
	
	void generate3dMaze(String name,int height, int length, int width) throws Exception;
	
	void solveMaze(String name,String algorithm);

	void solveMaze(String name, String algorithm, Position characterPosition);
	
	void getMaze3D(String name);
	
	void getStartPosition(String name);

	void getGoalPosition(String name);
	
	void displayCrossSectionByY(int yLayer, String name);
	
	void displayCrossSectionByX(int xLayer,String name);

	void displayCrossSectionByZ(int zLayer,String name);
	
	void saveMazeToFile(String name, String fileName);
	
	void loadMazeFromFile(String name, String fileName);
	
	void sizeInFile(String filename);
	
	void sizeInMemory(String name);
	
	void getSolution(String name);
	
	boolean mazeExists(String name);
	
	boolean solutionExists(String name);
	
	boolean solutionExists(Maze3d m3d);
	
	Object getDescriptor();
	
	void saveGZipMaps();
	
	void loadGZipMaps();
	
	void setProperties(Properties properties);
	
	public Properties getProperties();
	
	void exitModel();

}
