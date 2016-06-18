package MVP.presenter;

import MVP.view.commands.DisplayMessageViewCommand;

public class GetStartPositionCommand extends CommonCommand {

	public GetStartPositionCommand(MyPresenter presenter) {
		super(presenter);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length != 1) 
		{
			myPresenter.getView().display("Invalid arguments", new DisplayMessageViewCommand(this.myPresenter.getView()));
			this.help();
		} 
		myPresenter.getModel().getStartPosition(args[0]);

	}

	@Override
	public void help() {}

}
