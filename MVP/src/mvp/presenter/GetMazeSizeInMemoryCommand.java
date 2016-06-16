package mvp.presenter;

import mvp.view.commands.DisplayMessageCLICommand;

public class GetMazeSizeInMemoryCommand extends CommonCommand {

	public GetMazeSizeInMemoryCommand(MyPresenter p) {
		super(p);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length != 1) {
			myPresenter.getView().display("Invalid arguments", new DisplayMessageCLICommand(this.myPresenter.getView()));
			this.help();
		} 
		else 
		{
			myPresenter.getModel().sizeInMemory(args[0]);
		}

	}

	@Override
	public void help() {
		myPresenter.getView().display("Displays the maze size in the memory. " + '\n' + '\t' + "--> Syntax: maze size <maze name>", new DisplayMessageCLICommand(this.myPresenter.getView()));
	}

}
