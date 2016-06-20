package MVP.presenter;

import MVP.view.commands.DisplaySolutionViewCommand;

// TODO: Auto-generated Javadoc
/**
 * The Class DisplaySolutionCommand.
 */
public class DisplaySolutionCommand extends CommonCommand {

	/**
	 * Instantiates a new display solution command.
	 *
	 * @param myPresenter the my presenter
	 */
	public DisplaySolutionCommand(MyPresenter myPresenter) {
		super(myPresenter);
	}

	/* (non-Javadoc)
	 * @see MVP.presenter.CommonCommand#doCommand(java.lang.String[])
	 */
	@Override
	public void doCommand(String[] args) throws Exception {
		if (args == null)
		{
			this.myPresenter.getView().display(this.myPresenter.getModel().getDescriptor(),new DisplaySolutionViewCommand(this.myPresenter.getView()));
		}

	}

	/* (non-Javadoc)
	 * @see MVP.presenter.CommonCommand#help()
	 */
	@Override
	public void help() {}

}
