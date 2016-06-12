package MVP.presenter;

import MVP.view.commands.DisplayMessageCLICommand;

public class GetMazeSizeInFileCommand extends CommonCommand {

	public GetMazeSizeInFileCommand(Presenter presenter) {
		super(presenter);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length != 1) 
		{
			presenter.getView().display("Invalid arguments", new DisplayMessageCLICommand(this.presenter.getView()));
			this.help();
		} 
		else 
		{
			presenter.getModel().sizeInFile(args[0]);
		}
	}

	@Override
	public void help() {
		System.out.println("Displays the maze size in file." + '\n' + '\t' + "--> Syntax: file size <name>");

	}

}
