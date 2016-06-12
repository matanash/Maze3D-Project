package MVP.presenter;

import MVP.view.commands.DisplayMessageCLICommand;

public class GetCrossSectionByZCommand extends CommonCommand {

	public GetCrossSectionByZCommand(Presenter p) {
		super(p);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length != 2) 
		{
			presenter.getView().display("Invalid arguments", new DisplayMessageCLICommand(this.presenter.getView()));
			this.help();
		} 
		else 
		{
			presenter.getModel().displayCrossSectionByZ(Integer.parseInt(args[0]), args[1]);
		}
	}

	@Override
	public void help() {
		System.out.println("Displays cross section by <zSection> for maze3d <name> ." + '\n' + '\t'
				+ "--> Syntax: displays cross section by Z <zSection> <name> .");
	}

}
