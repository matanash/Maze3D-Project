package MVP.presenter;

import MVP.view.commands.DisplayMessageViewCommand;

// TODO: Auto-generated Javadoc
/**
 * The Class GetSolutionCommand.
 */
public class GetSolutionCommand extends CommonCommand {

	/**
	 * Instantiates a new gets the solution command.
	 *
	 * @param p the p
	 */
	public GetSolutionCommand(MyPresenter p) {
		super(p);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see MVP.presenter.CommonCommand#doCommand(java.lang.String[])
	 */
	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length != 1) 
		{
			myPresenter.getView().display("Invalid arguments", new DisplayMessageViewCommand(this.myPresenter.getView()));
			help();
		} 
		//presenter.getView().displaySolution(presenter.getModel().displaySolution(args[0]));
		myPresenter.getModel().getSolution(args[0]);
			
	}

	/* (non-Javadoc)
	 * @see MVP.presenter.CommonCommand#help()
	 */
	@Override
	public void help() {
		myPresenter.getView().display("Displays the maze's solution. " + '\n' + '\t' + "--> Syntax: display solution <maze name>", new DisplayMessageViewCommand(this.myPresenter.getView()));
	}

}
