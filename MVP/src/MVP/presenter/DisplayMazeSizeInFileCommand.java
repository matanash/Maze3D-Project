package MVP.presenter;

import MVP.view.commands.DisplayMazeSizeInFileViewCommand;

// TODO: Auto-generated Javadoc
/**
 * The Class DisplayMazeSizeInFileCommand.
 */
public class DisplayMazeSizeInFileCommand extends CommonCommand {

	/**
	 * Instantiates a new display maze size in file command.
	 *
	 * @param myPresenter the my presenter
	 */
	public DisplayMazeSizeInFileCommand(MyPresenter myPresenter) {
		super(myPresenter);
	}

	/* (non-Javadoc)
	 * @see MVP.presenter.CommonCommand#doCommand(java.lang.String[])
	 */
	@Override
	public void doCommand(String[] args) throws Exception {
		if (args == null)
		{
			myPresenter.getView().display(myPresenter.getModel().getDescriptor(),new DisplayMazeSizeInFileViewCommand(this.myPresenter.getView()));
		}

	}

	/* (non-Javadoc)
	 * @see MVP.presenter.CommonCommand#help()
	 */
	@Override
	public void help() {}

}
