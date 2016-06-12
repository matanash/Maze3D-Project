package MVP.presenter;

import MVP.view.commands.DisplayMessageCLICommand;

public class DisplayMessageCommand extends CommonCommand {

	public DisplayMessageCommand(Presenter p) {
		super(p);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args == null)
		{
			presenter.getView().display(presenter.getModel().getDescriptor(), new DisplayMessageCLICommand(this.presenter.getView()));
		}
	}

	@Override
	public void help() {}

}
