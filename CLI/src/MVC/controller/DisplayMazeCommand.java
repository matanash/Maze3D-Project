package MVC.controller;

import MVC.model.Model;
import MVC.view.View;

public class DisplayMazeCommand extends CommonCommand {

	public DisplayMazeCommand(View v, Model m) {
		super(v, m);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doCommand(String[] args) 
	{
		if (!model.mazeExists(args[0]))
			System.out.println("This maze is already exists");
		else
		{
			System.out.println("The requested maze is:");
			System.out.println(model.display(args[0]));
		}
	}

	@Override
	public void help() 
	{
		System.out.println("display <name> (Displaying the whole maze)");

	}

}
