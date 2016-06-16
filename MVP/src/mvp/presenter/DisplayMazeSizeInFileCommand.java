package mvp.presenter;

import mvp.view.commands.DisplayMazeSizeInFileCLICommand;

public class DisplayMazeSizeInFileCommand extends CommonCommand {

	public DisplayMazeSizeInFileCommand(MyPresenter myPresenter) {
		super(myPresenter);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args == null)
		{
			myPresenter.getView().display(myPresenter.getModel().getDescriptor(),new DisplayMazeSizeInFileCLICommand(this.myPresenter.getView()));
		}

	}

	@Override
	public void help() {}

}
