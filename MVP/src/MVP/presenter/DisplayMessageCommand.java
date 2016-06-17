package MVP.presenter;

import MVP.view.commands.DisplayMessageViewCommand;

public class DisplayMessageCommand extends CommonCommand {

	public DisplayMessageCommand(MyPresenter p) {
		super(p);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args == null) {
			myPresenter.getView().display(myPresenter.getModel().getDescriptor(),
					new DisplayMessageViewCommand(this.myPresenter.getView()));
		}
	}

	@Override
	public void help() {
	}

}
