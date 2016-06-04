package MVP.view;

import algorithms.search.Solution;
import model.maze3d.Maze3d;

public interface View {
	/**
	 * start the program
	 */
	public void start() throws Exception;

	void displayMaze(Maze3d maze);
	
	void displaySolution(Solution sol);
	
	void displayCrossSectionByCommand(int[][] matrix);

	void displayMessage(String message);
}
