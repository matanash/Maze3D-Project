package MVP.presenter;

import MVP.view.commands.DisplayMessageViewCommand;

public class GetMazeSizeInFileCommand extends CommonCommand {

	public GetMazeSizeInFileCommand(MyPresenter myPresenter) {
		super(myPresenter);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length != 1) 
		{
			myPresenter.getView().display("Invalid arguments", new DisplayMessageViewCommand(this.myPresenter.getView()));
			this.help();
		} 
		else 
		{
			myPresenter.getModel().sizeInFile(args[0]);
		}
	}

	@Override
	public void help() {
		myPresenter.getView().display("Displays the maze size in file." + '\n' + '\t' + "--> Syntax: file size <name>", new DisplayMessageViewCommand(this.myPresenter.getView()));

	}

}
