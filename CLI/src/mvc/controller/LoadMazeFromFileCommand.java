package mvc.controller;

import mvc.model.Model;
import mvc.view.View;

/**
 * This class represents Load Maze From File Command
 * 
 * @author Matan Ashkenazi and Noee Cohen
 * @version - 1.0
 */
public class LoadMazeFromFileCommand extends CommonCommand {
	public LoadMazeFromFileCommand(View view, Model model) {
		super(view, model);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length != 2) {
			this.view.getOut().println("Invalid arguments");
			this.view.getOut().flush();
			this.help();
		}
		model.loadMazeFromFile(args[0], args[1]);
		this.view.getOut().println("Maze " + args[1] + " loaded from file: " +  args[0]);
	}

	@Override
	public void help() {
		this.view.getOut().println("Loads a 3d maze from <file name path> that preserve under <maze name> ." + '\n'
				+ '\t' + "--> Syntax: load maze <file name path> <maze name> ");
		this.view.getOut().flush();
	}

}
