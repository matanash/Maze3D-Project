package MVP.presenter;

import MVP.view.commands.DisplayPositionViewCommand;

public class DisplayPositionCommand extends CommonCommand {

	public DisplayPositionCommand(MyPresenter presenter) {
		super(presenter);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args == null)
		{
			myPresenter.getView().display(myPresenter.getModel().getDescriptor(),new DisplayPositionViewCommand(this.myPresenter.getView()));
		}

	}

	@Override
	public void help() {}

}
