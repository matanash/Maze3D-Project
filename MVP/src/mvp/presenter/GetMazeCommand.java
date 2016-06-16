package mvp.presenter;

import mvp.view.commands.DisplayMessageCLICommand;

public class GetMazeCommand extends CommonCommand {

	public GetMazeCommand(MyPresenter myPresenter) {
		super(myPresenter);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		
		if (args.length != 1) 
		{
			myPresenter.getView().display("Invalid arguments", new DisplayMessageCLICommand(this.myPresenter.getView()));
			this.help();
		} 
		myPresenter.getModel().getMaze3D(args[0]);
		
	}

	@Override
	public void help() {
		myPresenter.getView().display("Displays the whole maze <maze name>. " + '\n' + '\t' + "--> Syntax: display <maze name>", new DisplayMessageCLICommand(this.myPresenter.getView()));
	}

}
