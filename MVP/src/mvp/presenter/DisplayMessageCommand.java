package mvp.presenter;

import mvp.view.commands.DisplayMessageCLICommand;

public class DisplayMessageCommand extends CommonCommand {

	public DisplayMessageCommand(MyPresenter p) {
		super(p);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args == null)
		{
			myPresenter.getView().display(myPresenter.getModel().getDescriptor(), new DisplayMessageCLICommand(this.myPresenter.getView()));
		}
	}

	@Override
	public void help() {}

}
