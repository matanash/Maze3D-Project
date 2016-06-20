package MVP.presenter;

import MVP.view.commands.DisplayMessageViewCommand;

// TODO: Auto-generated Javadoc
/**
 * The Class GetCrossSectionByXCommand.
 */
public class GetCrossSectionByXCommand extends CommonCommand {
	
	/**
	 * Instantiates a new gets the cross section by X command.
	 *
	 * @param myPresenter the my presenter
	 */
	public GetCrossSectionByXCommand(MyPresenter myPresenter) {
		super(myPresenter);
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
			myPresenter.getModel().displayCrossSectionByX(Integer.parseInt(args[0]), args[1]);
		}
	}

	/* (non-Javadoc)
	 * @see MVP.presenter.CommonCommand#help()
	 */
	@Override
	public void help() {
		myPresenter.getView().display("Displays cross section by <xSection> for maze3d <name> ." + '\n' + '\t'
				+ "--> Syntax: display cross section by X <xSection> <name>", new DisplayMessageViewCommand(this.myPresenter.getView()));
		
	}

}
