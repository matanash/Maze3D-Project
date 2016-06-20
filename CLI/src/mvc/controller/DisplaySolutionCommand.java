package mvc.controller;

import mvc.model.Model;
import mvc.view.View;

/**
 * This class represents Display Solution Command
 * 
 * @author Matan Ashkenazi and Noee Cohen
 * @version - 1.0
 */
public class DisplaySolutionCommand extends CommonCommand {

	public DisplaySolutionCommand(View view, Model model) {
		super(view, model);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (model.displaySolution(args[0]) == null){
			this.view.getOut().println("There is no maze in that name.");
			this.view.getOut().flush();
		}
		else
		{
			this.view.getOut().println(model.displaySolution(args[0]));
			this.view.getOut().flush();
		}
	}

	@Override
	public void help() {
		this.view.getOut()
				.println("Displays the maze's solution. " + '\n' + '\t' + "--> Syntax: display solution <maze name>");
		this.view.getOut().flush();
	}

}
