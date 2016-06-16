package MVP.presenter;

import MVP.view.commands.DisplayMessageViewCommand;

public class GetCrossSectionByXCommand extends CommonCommand {
	
	public GetCrossSectionByXCommand(MyPresenter myPresenter) {
		super(myPresenter);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length != 2) 
		{
			myPresenter.getView().display("Invalid arguments", new DisplayMessageViewCommand(this.myPresenter.getView()));
			this.help();
		} 
		else 
		{
			myPresenter.getModel().displayCrossSectionByX(Integer.parseInt(args[0]), args[1]);
		}
	}

	@Override
	public void help() {
		myPresenter.getView().display("Displays cross section by <xSection> for maze3d <name> ." + '\n' + '\t'
				+ "--> Syntax: display cross section by X <xSection> <name>", new DisplayMessageViewCommand(this.myPresenter.getView()));
		
	}

}
