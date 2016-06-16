package MVP.presenter;

import MVP.view.commands.DisplayMazeSizeInFileViewCommand;

public class DisplayMazeSizeInFileCommand extends CommonCommand {

	public DisplayMazeSizeInFileCommand(MyPresenter myPresenter) {
		super(myPresenter);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args == null)
		{
			myPresenter.getView().display(myPresenter.getModel().getDescriptor(),new DisplayMazeSizeInFileViewCommand(this.myPresenter.getView()));
		}

	}

	@Override
	public void help() {}

}
