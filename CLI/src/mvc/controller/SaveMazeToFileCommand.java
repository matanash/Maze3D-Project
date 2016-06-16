package mvc.controller;

import mvc.model.Model;
import mvc.view.View;

public class SaveMazeToFileCommand extends CommonCommand {

	public SaveMazeToFileCommand(View v, Model m) {
		super(v, m);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (!model.mazeExists(args[0]))
			System.out.println("This maze isn't exists");
		else
			model.saveMazeToFile(args[0], args[1]);
	}

	@Override
	public void help() {
		System.out.println("Saves a maze to <file name> path ." + '\n' + '\t' + "--> Syntax: save maze <name> <file name>");

	}

}
