package MVP.view.commands;

import MVP.view.View;
import algorithms.search.Solution;

// TODO: Auto-generated Javadoc
/**
 * The Class DisplaySolutionViewCommand.
 */
public class DisplaySolutionViewCommand extends CommonDisplayCommand {

	/**
	 * Instantiates a new display solution view command.
	 *
	 * @param view the view
	 */
	public DisplaySolutionViewCommand(View view) {
		super(view);
	}

	/* (non-Javadoc)
	 * @see MVP.view.commands.CommonDisplayCommand#display(java.lang.Object)
	 */
	@Override
	public void display(Object obj) {
		this.view.displaySolution((Solution)obj);


	}

}
