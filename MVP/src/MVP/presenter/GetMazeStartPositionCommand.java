package MVP.presenter;

import MVP.view.commands.DisplayMessageViewCommand;

// TODO: Auto-generated Javadoc
/**
 * The Class GetMazeStartPositionCommand.
 */
public class GetMazeStartPositionCommand extends CommonCommand {

	/**
	 * Instantiates a new gets the maze start position command.
	 *
	 * @param presenter the presenter
	 */
	public GetMazeStartPositionCommand(MyPresenter presenter) {
		super(presenter);
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
		myPresenter.getModel().getStartPosition(args[0]);

	}

	/* (non-Javadoc)
	 * @see MVP.presenter.CommonCommand#help()
	 */
	@Override
	public void help() {}

}
