package mvp.presenter;

import mvp.view.commands.DisplayMessageCLICommand;

public class GetCrossSectionByYCommand extends CommonCommand {

	public GetCrossSectionByYCommand(MyPresenter myPresenter) {
		super(myPresenter);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length != 2) 
		{
			myPresenter.getView().display("Invalid arguments", new DisplayMessageCLICommand(this.myPresenter.getView()));
			this.help();
		} 
		else 
		{
			myPresenter.getModel().displayCrossSectionByY(Integer.parseInt(args[0]), args[1]);
		}
	}

	@Override
	public void help() {
		myPresenter.getView().display("Displays cross section by <ySection> for maze3d <name> ." + '\n' + '\t'
				+ "--> Syntax: display cross section by Y <ySection> <name>", new DisplayMessageCLICommand(this.myPresenter.getView()));
	}

}
