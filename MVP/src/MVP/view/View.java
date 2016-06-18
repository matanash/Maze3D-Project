package MVP.view;

import MVP.presenter.Properties;
import MVP.view.commands.DisplayCommand;
import algorithms.search.Solution;
import model.maze3d.Maze3d;
import model.maze3d.Position;

public interface View {
	/**
	 * start the program
	 */
	
	void startView() throws Exception;
	
	void exitView();
	
	void setProperties(Properties prop);
	
	void display(Object obj , DisplayCommand dc);
	
	void displayMessage(String message);

	void displayCrossSectionByCommand(int[][] matrix);

	void displaySolution(Solution solution);

	void displayMaze(Maze3d maze3d);
	
	void displayPosition (Position position);
	
}
