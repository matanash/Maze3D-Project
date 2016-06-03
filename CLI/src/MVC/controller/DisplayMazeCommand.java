package MVC.controller;

import MVC.model.Model;
import MVC.view.View;

public class DisplayMazeCommand extends CommonCommand {

	public DisplayMazeCommand(View v, Model m) {
		super(v, m);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length != 1) {
			System.out.println("Invalid arguments");
			this.help();
		} else {
			System.out.println("The requested maze is:");
			System.out.println(model.display(args[0]));
		}
	}

	@Override
	public void help() {
		System.out.println("Displays the whole maze <maze name>. " + '\n' + '\t' + "--> Syntax: display <maze name>");

	}

}
