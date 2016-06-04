package MVP.presenter;

import MVP.model.Model;
import MVP.view.View;

public class DisplaySolutionCommand extends CommonCommand {

	public DisplaySolutionCommand(View v, Model m) {
		super(v, m);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length!=1){
			view.displayMessage("Invalid arguments");
			help();
		}	
		else if (model.displaySolution(args[0]) == null)
			view.displayMessage("There is no maze in that name .");
		else
			view.displaySolution(model.displaySolution(args[0]));
	}

	@Override
	public void help() {
		System.out.println("Displays the maze's solution. " + '\n' + '\t' + "--> Syntax: display solution <maze name>");

	}

}
