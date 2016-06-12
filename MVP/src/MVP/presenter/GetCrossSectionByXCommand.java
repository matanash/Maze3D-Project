package MVP.presenter;

import MVP.view.commands.DisplayMessageCLICommand;

public class GetCrossSectionByXCommand extends CommonCommand {
	
	public GetCrossSectionByXCommand(Presenter presenter) {
		super(presenter);
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
			presenter.getModel().displayCrossSectionByX(Integer.parseInt(args[0]), args[1]);
		}
	}

	@Override
	public void help() {
		System.out.println("Displays cross section by <xSection> for maze3d <name> ." + '\n' + '\t'
				+ "--> Syntax: display cross section by X <xSection> <name>");

	}

}
