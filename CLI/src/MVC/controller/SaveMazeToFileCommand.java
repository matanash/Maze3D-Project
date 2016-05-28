package MVC.controller;

import MVC.model.Model;
import MVC.view.View;

public class SaveMazeToFileCommand extends CommonCommand {

	public SaveMazeToFileCommand(View v, Model m) {
		super(v, m);
	}

	@Override
	public void doCommand(String[] args) 
	{
		if (!model.mazeExists(args[0]))
			System.out.println("This maze isn't exists");
		else
			model.saveMazeToFile(args[0],args[1]);
	}

	@Override
	public void help() {
		System.out.println("save maze <name> <file name> (Saving a maze to <file name> path)");

	}

}
