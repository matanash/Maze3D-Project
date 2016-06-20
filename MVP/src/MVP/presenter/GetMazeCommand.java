package MVP.presenter;

import MVP.view.commands.DisplayMessageViewCommand;

// TODO: Auto-generated Javadoc
/**
 * The Class GetMazeCommand.
 */
public class GetMazeCommand extends CommonCommand {

	/**
	 * Instantiates a new gets the maze command.
	 *
	 * @param myPresenter the my presenter
	 */
	public GetMazeCommand(MyPresenter myPresenter) {
		super(myPresenter);
	}

	/* (non-Javadoc)
	 * @see MVP.presenter.CommonCommand#doCommand(java.lang.String[])
	 */
	@Override
	public void doCommand(String[] args) throws Exception {
		
		if (args.length != 1) 
		{
			myPresenter.getView().display("Invalid arguments", new DisplayMessageViewCommand(this.myPresenter.getView()));
			this.help();
		} 
		myPresenter.getModel().getMaze3D(args[0]);
		
	}

	/* (non-Javadoc)
	 * @see MVP.presenter.CommonCommand#help()
	 */
	@Override
	public void help() {
		myPresenter.getView().display("Displays the whole maze <maze name>. " + '\n' + '\t' + "--> Syntax: display <maze name>", new DisplayMessageViewCommand(this.myPresenter.getView()));
	}

}
