package MVC.model;

import algorithms.search.Solution;
import model.maze3d.Maze3d;
/**
 * this Interface define Maze3d Model functionality
 * @author MatanA
 *
 */
public interface Model {
	
	String dir(String path);
	
	Maze3d generate3dMaze(String name,int height, int length, int width) throws Exception;
	
	Solution solveMaze(String name,String method);

	Maze3d display(String name);
	
	int [][] displayCrossSectionByY(String name, int yLayer);
	
	int [][] displayCrossSectionByX(String name, int xLayer);
	
	int [][] displayCrossSectionByZ(String name, int zLayer);
	
	void saveMazeToFile(String name, String fileName);
	
	void loadMazeFromFile(String name, String fileName);
	
	int sizeInFile(String name);
	
	int sizeInMemory(String name);
	
	Solution displaySolution(String name);
	
	boolean mazeExists(String name);
	
	
	
	
	
	
	
}
