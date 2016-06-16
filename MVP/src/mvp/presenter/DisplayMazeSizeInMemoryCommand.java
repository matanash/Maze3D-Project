package mvp.presenter;

import mvp.view.commands.DisplayMazeSizeInMemoryCLICommand;

public class DisplayMazeSizeInMemoryCommand extends CommonCommand {

	public DisplayMazeSizeInMemoryCommand(MyPresenter p) {
		super(p);

	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args == null)
		{
			myPresenter.getView().display(myPresenter.getModel().getDescriptor(),new DisplayMazeSizeInMemoryCLICommand(this.myPresenter.getView()));
		}

	}

	@Override
	public void help() {}

}
