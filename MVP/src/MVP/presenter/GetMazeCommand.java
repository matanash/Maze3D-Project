package MVP.presenter;

import MVP.view.commands.DisplayMessageCLICommand;

public class GetMazeCommand extends CommonCommand {

	public GetMazeCommand(Presenter presenter) {
		super(presenter);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		
		if (args.length != 1) 
		{
			presenter.getView().display("Invalid arguments", new DisplayMessageCLICommand(this.presenter.getView()));
			this.help();
		} 
		presenter.getModel().getMaze3D(args[0]);
		
	}

	@Override
	public void help() {
		System.out.println("Displays the whole maze <maze name>. " + '\n' + '\t' + "--> Syntax: display <maze name>");

	}

}
