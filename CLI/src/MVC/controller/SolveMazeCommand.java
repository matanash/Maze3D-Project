package MVC.controller;
import MVC.model.Model;
import MVC.view.View;
import algorithms.search.Solution;

public class SolveMazeCommand extends CommonCommand 
{

	public SolveMazeCommand(View v, Model m) 
	{
		super(v, m);
	}

	@Override
	public void doCommand(String[] args) 
	{
		new Thread(new Runnable() 
		{

			@Override
			public void run() 
			{
				Solution solution = model.solveMaze(args[0], args[1]);
				if (solution == null)
					System.out.println("The solving proccess could not have been complete.");
				else
					System.out.println("The maze " + args[0] + "'s solution is ready");
			}
		}).start();

	}

	@Override
	public void help() 
	{
		System.out.println("solve <name> <algorithm> (Solve maze <name> with <algorithm> algorithm");

	}

}
