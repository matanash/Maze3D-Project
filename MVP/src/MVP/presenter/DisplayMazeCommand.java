package MVP.presenter;

import MVP.view.commands.DisplayMazeCLICommand;

public class DisplayMazeCommand extends CommonCommand {

	public DisplayMazeCommand(Presenter presenter) {
		super(presenter);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args == null)
		{
			presenter.getView().display(presenter.getModel().getDescriptor(),new DisplayMazeCLICommand(this.presenter.getView()));
		}

	}

	@Override
	public void help() {}

}
