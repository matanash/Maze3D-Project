package MVP.presenter;

import MVP.view.commands.DisplayMessageViewCommand;

// TODO: Auto-generated Javadoc
/**
 * The Class GetCrossSectionByZCommand.
 */
public class GetCrossSectionByZCommand extends CommonCommand {

	/**
	 * Instantiates a new gets the cross section by Z command.
	 *
	 * @param p the p
	 */
	public GetCrossSectionByZCommand(MyPresenter p) {
		super(p);
	}

	/* (non-Javadoc)
	 * @see MVP.presenter.CommonCommand#doCommand(java.lang.String[])
	 */
	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length != 2) 
		{
			myPresenter.getView().display("Invalid arguments", new DisplayMessageViewCommand(this.myPresenter.getView()));
			this.help();
		} 
		else 
		{
			myPresenter.getModel().displayCrossSectionByZ(Integer.parseInt(args[0]), args[1]);
		}
	}

	/* (non-Javadoc)
	 * @see MVP.presenter.CommonCommand#help()
	 */
	@Override
	public void help() {
		myPresenter.getView().display("Displays cross section by <zSection> for maze3d <name> ." + '\n' + '\t'
				+ "--> Syntax: displays cross section by Z <zSection> <name> .", new DisplayMessageViewCommand(this.myPresenter.getView()));
	}

}
