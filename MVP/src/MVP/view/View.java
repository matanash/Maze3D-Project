package MVP.view;

import algorithms.search.Solution;
import model.maze3d.Maze3d;

public interface View {
	/**
	 * start the program
	 */
	public void startView() throws Exception;
	
	public void exitView();

	void displayMaze(Maze3d maze);
	
	void displaySolution(Solution sol);
	
	void displayCrossSectionByCommand(int[][] matrix);

	void displayMessage(String message);
}
