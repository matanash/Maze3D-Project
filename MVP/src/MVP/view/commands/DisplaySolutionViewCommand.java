package MVP.view.commands;

import MVP.view.View;
import algorithms.search.Solution;

public class DisplaySolutionViewCommand extends CommonDisplayCommand {

	public DisplaySolutionViewCommand(View view) {
		super(view);
	}

	@Override
	public void display(Object obj) {
		this.view.displaySolution((Solution)obj);


	}

}
