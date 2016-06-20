package mvc.controller;

import mvc.model.Model;
import mvc.view.View;

/**
 * This class represents Save Maze to File Command
 * 
 * @author Matan Ashkenazi and Noee Cohen
 * @version - 1.0
 */
public class SaveMazeToFileCommand extends CommonCommand {

	public SaveMazeToFileCommand(View view, Model model) {
		super(view, model);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length!=2)
			this.view.getOut().println("Invalid Arguments");
		else if (!model.mazeExists(args[0])) 
		{
			this.view.getOut().println("This maze isn't exists");
			this.view.getOut().flush();
			help();
		} 
		else
		{
			model.saveMazeToFile(args[0], args[1]);
			this.view.getOut().println("Maze " + args[0] + " save to file " + args[1]);
			this.view.getOut().flush();
		}
	}

	@Override
	public void help() {
		this.view.getOut().println(
				"Saves a maze to <file name> path ." + '\n' + '\t' + "--> Syntax: save maze <name> <file name>");
		this.view.getOut().flush();
	}

}
