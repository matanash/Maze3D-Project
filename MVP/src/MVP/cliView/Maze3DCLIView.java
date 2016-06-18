package MVP.cliView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import MVP.presenter.Properties;
import algorithms.search.Solution;
import model.maze3d.Maze3d;
import model.maze3d.Position;


public class Maze3DCLIView extends CommonMaze3DCLIView {

	boolean exitFlag;

	public Maze3DCLIView(BufferedReader in, PrintWriter out) {
		super(in, out);
	}

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

	@Override
	public void displaySolution(Solution solution) {
		out.write("The requested Solution is: " + '\n');
		out.write(solution.toString());
		out.flush();

	}

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

	@Override
	public void displayMessage(String message) {
		out.write(message);
		out.flush();
	}

	@Override
	public void exitView() {
		this.exitFlag = true;

	}
	
	@Override
	public void setProperties(Properties prop) 
	{
		if (!prop.getUi().equals("CLI"))
		{
			setChanged();
			notifyObservers("switchUi switch");
		}
	}

	@Override
	public void displayPosition(Position position) {}

	

}