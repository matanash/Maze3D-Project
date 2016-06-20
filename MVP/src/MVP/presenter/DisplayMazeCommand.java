package MVP.presenter;

import MVP.view.commands.DisplayMaze3DViewCommand;

// TODO: Auto-generated Javadoc
/**
 * The Class DisplayMazeCommand.
 */
public class DisplayMazeCommand extends CommonCommand {

	/**
	 * Instantiates a new display maze command.
	 *
	 * @param myPresenter the my presenter
	 */
	public DisplayMazeCommand(MyPresenter myPresenter) {
		super(myPresenter);
	}

	/* (non-Javadoc)
	 * @see MVP.presenter.CommonCommand#doCommand(java.lang.String[])
	 */
	@Override
	public void doCommand(String[] args) throws Exception {
		if (args == null)
		{
			myPresenter.getView().display(myPresenter.getModel().getDescriptor(),new DisplayMaze3DViewCommand(this.myPresenter.getView()));
		}

	}

	/* (non-Javadoc)
	 * @see MVP.presenter.CommonCommand#help()
	 */
	@Override
	public void help() {}

}
