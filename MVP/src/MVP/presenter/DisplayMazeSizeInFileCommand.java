package MVP.presenter;

import MVP.view.commands.DisplayMazeSizeInFileCLICommand;

public class DisplayMazeSizeInFileCommand extends CommonCommand {

	public DisplayMazeSizeInFileCommand(Presenter presenter) {
		super(presenter);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args == null)
		{
			presenter.getView().display(presenter.getModel().getDescriptor(),new DisplayMazeSizeInFileCLICommand(this.presenter.getView()));
		}

	}

	@Override
	public void help() {}

}
