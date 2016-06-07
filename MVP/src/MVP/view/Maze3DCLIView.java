package MVP.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import algorithms.search.Solution;
import model.maze3d.Maze3d;

public class Maze3DCLIView extends CommonMaze3DCLIView {

	boolean flag;

	public Maze3DCLIView(BufferedReader in, PrintWriter out) {
		super(in, out);
	}

	public void startView() {
		flag = false;
		new Thread(new Runnable() {

			@Override
			public void run() {

				try {
					String bufferLine = null;
					do {
						out.write("Enter Command >>  ");
						out.flush();
						bufferLine = in.readLine().toLowerCase();
						setChanged();
						notifyObservers(bufferLine);
					} while (!flag);
					setChanged();
					notifyObservers("exit");
					in.close();
					out.close();
					

				} catch (IOException e) {
					System.out.println(e.getMessage());
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
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void displaySolution(Solution sol) {
		out.write("The requested Solution is: " + '\n');
		out.write(sol.toString());
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
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void displayMessage(String message) {
		out.write(message);
		out.flush();
	}

	@Override
	public void exitView() {
		flag = true;

	}

}