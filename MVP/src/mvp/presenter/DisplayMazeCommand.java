package mvp.presenter;

import mvp.view.commands.DisplayMazeCLICommand;

public class DisplayMazeCommand extends CommonCommand {

	public DisplayMazeCommand(MyPresenter myPresenter) {
		super(myPresenter);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args == null)
		{
			myPresenter.getView().display(myPresenter.getModel().getDescriptor(),new DisplayMazeCLICommand(this.myPresenter.getView()));
		}

	}

	@Override
	public void help() {}

}
