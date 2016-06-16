package mvp.presenter;

import mvp.view.commands.DisplayMessageCLICommand;

public class ExitCommand extends CommonCommand {

	public ExitCommand(MyPresenter p) {
		super(p);

	}

	@Override
	public void doCommand(String[] args) throws Exception {
		myPresenter.getView().display("|----------------------------|", new DisplayMessageCLICommand(this.myPresenter.getView()));
		myPresenter.getView().display("|   Thank you for playing!   |", new DisplayMessageCLICommand(this.myPresenter.getView()));
		myPresenter.getView().display("|----------------------------|", new DisplayMessageCLICommand(this.myPresenter.getView()));

		this.myPresenter.getModel().saveGZipMaps();
		this.myPresenter.getModel().exitModel();
		this.myPresenter.getView().exitView();
		// close presenter thread ?
		System.exit(0);
	}

	@Override
	public void help() {
		myPresenter.getView().display("Exit and close the maze game" + '\n' + '\t' + "--> exit", new DisplayMessageCLICommand(this.myPresenter.getView()));

	}

}
