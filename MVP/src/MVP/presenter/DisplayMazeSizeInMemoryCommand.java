package MVP.presenter;

import MVP.view.commands.DisplayMazeSizeInMemoryViewCommand;

// TODO: Auto-generated Javadoc
/**
 * The Class DisplayMazeSizeInMemoryCommand.
 */
public class DisplayMazeSizeInMemoryCommand extends CommonCommand {

	/**
	 * Instantiates a new display maze size in memory command.
	 *
	 * @param p the p
	 */
	public DisplayMazeSizeInMemoryCommand(MyPresenter p) {
		super(p);

	}

	/* (non-Javadoc)
	 * @see MVP.presenter.CommonCommand#doCommand(java.lang.String[])
	 */
	@Override
	public void doCommand(String[] args) throws Exception {
		if (args == null)
		{
			myPresenter.getView().display(myPresenter.getModel().getDescriptor(),new DisplayMazeSizeInMemoryViewCommand(this.myPresenter.getView()));
		}

	}

	/* (non-Javadoc)
	 * @see MVP.presenter.CommonCommand#help()
	 */
	@Override
	public void help() {}

}
