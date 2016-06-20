package MVP.presenter;

import MVP.view.commands.DisplayPositionViewCommand;

// TODO: Auto-generated Javadoc
/**
 * The Class DisplayPositionCommand.
 */
public class DisplayPositionCommand extends CommonCommand {

	/**
	 * Instantiates a new display position command.
	 *
	 * @param presenter the presenter
	 */
	public DisplayPositionCommand(MyPresenter presenter) {
		super(presenter);
	}

	/* (non-Javadoc)
	 * @see MVP.presenter.CommonCommand#doCommand(java.lang.String[])
	 */
	@Override
	public void doCommand(String[] args) throws Exception {
		if (args == null)
		{
			myPresenter.getView().display(myPresenter.getModel().getDescriptor(),new DisplayPositionViewCommand(this.myPresenter.getView()));
		}

	}

	/* (non-Javadoc)
	 * @see MVP.presenter.CommonCommand#help()
	 */
	@Override
	public void help() {}

}
