package MVC.controller;

import MVC.model.Model;
import MVC.view.View;

public class DisplayFileSizeCommand extends CommonCommand {

	public DisplayFileSizeCommand(View v, Model m) {
		super(v, m);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length != 1) {
			System.out.println("Invalid arguments");
			this.help();
		} else {
			int size = model.sizeInFile(args[0]);
			if (size == -1)
				System.out.println("There is no such file!");
			else
				System.out.println("The size of the maze in the file is " + size + " bytes.");
		}
	}

	@Override
	public void help() {
		System.out.println("Displays the maze's file size ." + '\n' + '\t' + "--> Syntax: file size <name>");

	}

}
