package MVC.controller;

import MVC.model.Model;
import MVC.view.View;

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
		System.out.println("Loads a maze from file path ." + '\n' + '\t' + "--> Syntax: loads a new maze from <file name> preserve under <maze name> ");
	}

}
