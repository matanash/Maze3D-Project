package MVP.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.util.Observable;
import java.util.Observer;

import algorithms.search.Solution;
import model.maze3d.Maze3d;

public class MyView extends Observable implements View, Observer {

	private BufferedReader in;
	private Writer out;
	private CLIView cli;	
		
	public MyView(BufferedReader in, Writer out)
	{		
		this.in = in;
		this.out = out;		
		cli = new CLIView(in, out);
		cli.addObserver(this);
	}
	
	@Override
	public void update(Observable obs, Object arg) {
		if (obs == cli) {
			this.setChanged();
			this.notifyObservers(arg);			
		}		
	}
	
	@Override
	public void displayMessage(String message) {
		try {
			out.write(message);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	@Override
	public void start() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {				
				try {
					cli.start();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});	
		thread.start();
	}

	@Override
	public void displayMaze(Maze3d maze3d) {
		try{
			out.write("The requested maze is: "+'\n');
			out.write(maze3d.toString());
			out.flush();
		}catch (IOException e)
		{
			System.out.println(e.getMessage());
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		
		
	}

	@Override
	public void displayCrossSectionByCommand(int[][] matrix) {
		try{
			out.write("The requested Maze Section is: "+'\n');
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix[0].length; j++) {
					out.write(matrix[i][j] + " ");
				}
				out.append('\n');
			}
			out.append('\n');
			out.flush();
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		
	}

	@Override
	public void displaySolution(Solution sol) {
		try {
			out.write("The requested Solution is: "+'\n');
			out.write(sol.toString());
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
}
