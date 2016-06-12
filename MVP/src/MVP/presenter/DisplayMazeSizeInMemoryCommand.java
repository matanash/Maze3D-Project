package MVP.presenter;

import MVP.view.commands.DisplayMazeSizeInMemoryCLICommand;

public class DisplayMazeSizeInMemoryCommand extends CommonCommand {

	public DisplayMazeSizeInMemoryCommand(Presenter p) {
		super(p);

	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args == null)
		{
			presenter.getView().display(presenter.getModel().getDescriptor(),new DisplayMazeSizeInMemoryCLICommand(this.presenter.getView()));
		}

	}

	@Override
	public void help() {}

}
