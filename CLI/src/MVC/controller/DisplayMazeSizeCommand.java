package MVC.controller;

import MVC.model.Model;
import MVC.view.View;

public class DisplayMazeSizeCommand extends CommonCommand {

	public DisplayMazeSizeCommand(View v, Model m) {
		super(v, m);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doCommand(String[] args) 
	{
		int size = model.sizeInMemory(args[0]);
		if (size == -1)
			System.out.println("There is no such maze!");
		else
			System.out.println("The size of the maze in the memory is " + size + " bytes.");

	}

	@Override
	public void help() 
	{
		System.out.println("maze size <name> (Display the size of the maze in the memory)");

	}

}
