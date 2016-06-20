package MVP.presenter;

import MVP.view.commands.DisplayMessageViewCommand;

// TODO: Auto-generated Javadoc
/**
 * The Class DisplayMessageCommand.
 */
public class DisplayMessageCommand extends CommonCommand {

	/**
	 * Instantiates a new display message command.
	 *
	 * @param p the p
	 */
	public DisplayMessageCommand(MyPresenter p) {
		super(p);
	}

	/* (non-Javadoc)
	 * @see MVP.presenter.CommonCommand#doCommand(java.lang.String[])
	 */
	@Override
	public void doCommand(String[] args) throws Exception {
		if (args == null) {
			myPresenter.getView().display(myPresenter.getModel().getDescriptor(),
					new DisplayMessageViewCommand(this.myPresenter.getView()));
		}
	}

	/* (non-Javadoc)
	 * @see MVP.presenter.CommonCommand#help()
	 */
	@Override
	public void help() {
	}

}
