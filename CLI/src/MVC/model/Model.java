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
	
	void generate3dMaze(String name,int height, int length, int width) throws Exception;
	
	void solveMaze(String name,String method);

	Maze3d display(String name);
	
	int[][] displayCrossSectionByY(int yLayer, String name) throws Exception;
	
	int [][] displayCrossSectionByX(int xLayer,String name) throws Exception;

	int [][] displayCrossSectionByZ(int zLayer,String name) throws Exception;
	
	void saveMazeToFile(String name, String fileName);
	
	void loadMazeFromFile(String name, String fileName);
	
	int sizeInFile(String name);
	
	int sizeInMemory(String name);
	
	Solution displaySolution(String name);
	
	boolean mazeExists(String name);

	
	
	
	
	
	
	
	
}
