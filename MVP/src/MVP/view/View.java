package MVP.view;

import MVP.presenter.Properties;
import MVP.view.commands.DisplayCommand;
import algorithms.search.Solution;
import model.maze3d.Maze3d;
import model.maze3d.Position;

// TODO: Auto-generated Javadoc
/**
 * The Interface View.
 */
public interface View {
	
	/**
	 * start the program.
	 *
	 * @throws Exception the exception
	 */
	
	void startView() throws Exception;
	
	/**
	 * Exit view.
	 */
	void exitView();
	
	/**
	 * Sets the properties.
	 *
	 * @param prop the new properties
	 */
	void setProperties(Properties prop);
	
	/**
	 * Display.
	 *
	 * @param obj the obj
	 * @param dc the dc
	 */
	void display(Object obj , DisplayCommand dc);
	
	/**
	 * Display message.
	 *
	 * @param message the message
	 */
	void displayMessage(String message);

	/**
	 * Display cross section by command.
	 *
	 * @param matrix the matrix
	 */
	void displayCrossSectionByCommand(int[][] matrix);

	/**
	 * Display solution.
	 *
	 * @param solution the solution
	 */
	void displaySolution(Solution solution);

	/**
	 * Display maze.
	 *
	 * @param maze3d the maze 3 d
	 */
	void displayMaze(Maze3d maze3d);
	
	/**
	 * Display position.
	 *
	 * @param position the position
	 */
	void displayPosition (Position position);
	
}
