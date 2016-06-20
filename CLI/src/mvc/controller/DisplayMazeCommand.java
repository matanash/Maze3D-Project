package mvc.controller;

import mvc.model.Model;
import mvc.view.View;

/**
 * This class represent Display Maze Command
 * 
 * @author Matan Ashkenazi and Noee Cohen
 * @version - 1.0
 */
class DisplayMazeCommand extends CommonCommand {

	public DisplayMazeCommand(View view, Model model) {
		super(view, model);

	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length != 1) {
			this.view.getOut().println("Invalid arguments");
			this.view.getOut().flush();
			this.help();
		}
		else if (!model.mazeExists(args[0]))
			this.view.getOut().println("Couldn't find maze!");
		else 
		{
			this.view.getOut().println("The requested maze is:");
			this.view.getOut().flush();
			this.view.getOut().println(model.display(args[0]));
			this.view.getOut().flush();
		}
	}

	@Override
	public void help() {
		this.view.getOut()
				.println("Displays the whole maze <maze name>. " + '\n' + '\t' + "--> Syntax: display <maze name>");
		this.view.getOut().flush();
	}

}
