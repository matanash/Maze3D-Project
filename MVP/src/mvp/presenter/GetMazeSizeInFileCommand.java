package mvp.presenter;

import mvp.view.commands.DisplayMessageCLICommand;

public class GetMazeSizeInFileCommand extends CommonCommand {

	public GetMazeSizeInFileCommand(MyPresenter myPresenter) {
		super(myPresenter);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length != 1) 
		{
			myPresenter.getView().display("Invalid arguments", new DisplayMessageCLICommand(this.myPresenter.getView()));
			this.help();
		} 
		else 
		{
			myPresenter.getModel().sizeInFile(args[0]);
		}
	}

	@Override
	public void help() {
		myPresenter.getView().display("Displays the maze size in file." + '\n' + '\t' + "--> Syntax: file size <name>", new DisplayMessageCLICommand(this.myPresenter.getView()));

	}

}
