package MVP.presenter;

import MVP.view.commands.DisplayMessageCLICommand;

public class GetMazeSizeInMemoryCommand extends CommonCommand {

	public GetMazeSizeInMemoryCommand(Presenter p) {
		super(p);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length != 1) {
			presenter.getView().display("Invalid arguments", new DisplayMessageCLICommand(this.presenter.getView()));
			this.help();
		} 
		else 
		{
			presenter.getModel().sizeInMemory(args[0]);
		}

	}

	@Override
	public void help() {
		System.out
				.println("Displays the maze size in the memory. " + '\n' + '\t' + "--> Syntax: maze size <maze name>");

	}

}
