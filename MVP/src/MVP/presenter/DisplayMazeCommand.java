package MVP.presenter;

import MVP.view.commands.DisplayMaze3DViewCommand;

public class DisplayMazeCommand extends CommonCommand {

	public DisplayMazeCommand(MyPresenter myPresenter) {
		super(myPresenter);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args == null)
		{
			myPresenter.getView().display(myPresenter.getModel().getDescriptor(),new DisplayMaze3DViewCommand(this.myPresenter.getView()));
		}

	}

	@Override
	public void help() {}

}
