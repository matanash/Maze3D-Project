package MVP.presenter;

import MVP.view.commands.DisplayMessageViewCommand;

// TODO: Auto-generated Javadoc
/**
 * The Class ExitCommand.
 */
public class ExitCommand extends CommonCommand {

	/**
	 * Instantiates a new exit command.
	 *
	 * @param p the p
	 */
	public ExitCommand(MyPresenter p) {
		super(p);

	}

	/* (non-Javadoc)
	 * @see MVP.presenter.CommonCommand#doCommand(java.lang.String[])
	 */
	@Override
	public void doCommand(String[] args) throws Exception {
		myPresenter.getView().display("|----------------------------|", new DisplayMessageViewCommand(this.myPresenter.getView()));
		myPresenter.getView().display("|   Thank you for playing!   |", new DisplayMessageViewCommand(this.myPresenter.getView()));
		myPresenter.getView().display("|----------------------------|", new DisplayMessageViewCommand(this.myPresenter.getView()));

		this.myPresenter.getModel().saveGZipMaps();
		this.myPresenter.getModel().exitModel();
		this.myPresenter.getView().exitView();
		// close presenter thread ?
		System.exit(0);
	}

	/* (non-Javadoc)
	 * @see MVP.presenter.CommonCommand#help()
	 */
	@Override
	public void help() {
		myPresenter.getView().display("Exit and close the maze game" + '\n' + '\t' + "--> exit", new DisplayMessageViewCommand(this.myPresenter.getView()));

	}

}
