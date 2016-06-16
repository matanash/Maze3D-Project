package MVP.presenter;

import MVP.view.commands.DisplayMessageViewCommand;

public class GetMazeSizeInMemoryCommand extends CommonCommand {

	public GetMazeSizeInMemoryCommand(MyPresenter p) {
		super(p);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length != 1) {
			myPresenter.getView().display("Invalid arguments", new DisplayMessageViewCommand(this.myPresenter.getView()));
			this.help();
		} 
		else 
		{
			myPresenter.getModel().sizeInMemory(args[0]);
		}

	}

	@Override
	public void help() {
		myPresenter.getView().display("Displays the maze size in the memory. " + '\n' + '\t' + "--> Syntax: maze size <maze name>", new DisplayMessageViewCommand(this.myPresenter.getView()));
	}

}
