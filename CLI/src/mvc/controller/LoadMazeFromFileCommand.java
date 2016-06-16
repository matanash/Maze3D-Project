package mvc.controller;

import mvc.model.Model;
import mvc.view.View;

public class LoadMazeFromFileCommand extends CommonCommand {
	public LoadMazeFromFileCommand(View v, Model m) {
		super(v, m);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		model.loadMazeFromFile(args[0], args[1]);

	}

	@Override
	public void help() {
		System.out.println("Loads a 3d maze from <file name path> that preserve under <maze name> ." + '\n' + '\t' + "--> Syntax: load maze <file name path> <maze name> ");
	}

}
