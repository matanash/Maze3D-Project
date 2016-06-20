package mvc.controller;

import mvc.model.Model;
import mvc.view.View;

/**
 * This class represent Display Cross Section By X Coordinate Command
 * @author Matan Ashkenazi and Noee Cohen
 * @version - 1.0
 */
public class DisplayCrossSectionByXCommand extends CommonCommand {
	public DisplayCrossSectionByXCommand(View view, Model model) {
		super(view, model);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length != 2) {
			this.view.getOut().println("Invalid arguments");
			this.view.getOut().flush();
			this.help();
		} else {
			try {
				printMaze2dBySection(model.displayCrossSectionByX(Integer.parseInt(args[0]), args[1]));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void help() {
		this.view.getOut().println("Displays cross section by <xSection> for maze3d <name> ." + '\n' + '\t'
				+ "--> Syntax: display cross section by X <xSection> <name>");
		this.view.getOut().flush();

	}

	/**
	 * This method prints 2D Maze
	 * 
	 * @param matrix
	 *            - the 2D Maze represented by Two-dimensional array
	 */
	protected void printMaze2dBySection(int[][] matrix) {
		this.view.getOut().println("The requested Maze Section is: ");
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				this.view.getOut().println(matrix[i][j] + " ");
				this.view.getOut().flush();
			}
			this.view.getOut().println();
			this.view.getOut().flush();
		}
		this.view.getOut().println();
		this.view.getOut().flush();
	}
}
