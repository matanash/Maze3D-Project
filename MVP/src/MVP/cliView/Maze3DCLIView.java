package MVP.cliView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import MVP.presenter.Properties;
import algorithms.search.Solution;
import model.maze3d.Maze3d;
import model.maze3d.Position;

/**
 * The Class Maze3DCLIView.
 */
public class Maze3DCLIView extends CommonMaze3DCLIView {

	/** The exit flag. */
	boolean exitFlag;

	/**
	 * Instantiates a new maze 3 DCLI view.
	 *
	 * @param in the in
	 * @param out the out
	 */
	public Maze3DCLIView(BufferedReader in, PrintWriter out) {
		super(in, out);
	}

	/* (non-Javadoc)
	 * @see MVP.view.View#startView()
	 */
	public void startView() {
		exitFlag = false;
		new Thread(new Runnable() {

			@Override
			public void run() {
				out.print("Enter Command >>  ");
				out.flush();
				try {
					String bufferLine = null;
					do {
						
						bufferLine = in.readLine().toLowerCase();
						setChanged();
						notifyObservers(bufferLine);
					} while (!exitFlag);
					setChanged();
					notifyObservers("exit");
					in.close();
					out.close();
					

				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}).start();

	}
	
	/* (non-Javadoc)
	 * @see MVP.view.View#displayMaze(model.maze3d.Maze3d)
	 */
	@Override
	public void displayMaze(Maze3d maze3d) {
		try {
			out.write("The requested maze is: " + '\n');
			out.write(maze3d.toString());
			out.flush();
		} catch (Exception e) {
			out.println(e.getMessage());
		}

	}

	/* (non-Javadoc)
	 * @see MVP.view.View#displaySolution(algorithms.search.Solution)
	 */
	@Override
	public void displaySolution(Solution solution) {
		out.write("The requested Solution is: " + '\n');
		out.write(solution.toString());
		out.flush();

	}

	/* (non-Javadoc)
	 * @see MVP.view.View#displayCrossSectionByCommand(int[][])
	 */
	@Override
	public void displayCrossSectionByCommand(int[][] matrix) {
		try {
			out.write("The requested Maze Section is: " + '\n');
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix[0].length; j++) {
					out.write(matrix[i][j] + " ");
				}
				out.append('\n');
			}
			out.append('\n');
			out.flush();
		} catch (Exception e) {
			out.println(e.getMessage());
		}
		
	}

	/* (non-Javadoc)
	 * @see MVP.view.View#displayMessage(java.lang.String)
	 */
	@Override
	public void displayMessage(String message) {
		out.write(message);
		out.flush();
	}

	/* (non-Javadoc)
	 * @see MVP.view.View#exitView()
	 */
	@Override
	public void exitView() {
		this.exitFlag = true;

	}
	
	/* (non-Javadoc)
	 * @see MVP.view.View#setProperties(MVP.presenter.Properties)
	 */
	@Override
	public void setProperties(Properties prop) 
	{
		if (!prop.getUi().equals("CLI"))
		{
			setChanged();
			notifyObservers("switchUi switch");
		}
	}

	/* (non-Javadoc)
	 * @see MVP.view.View#displayPosition(model.maze3d.Position)
	 */
	@Override
	public void displayPosition(Position position) {}

	

}