package MVP.presenter;

import MVP.view.commands.DisplayMessageViewCommand;

// TODO: Auto-generated Javadoc
/**
 * The Class GetCrossSectionByYCommand.
 */
public class GetCrossSectionByYCommand extends CommonCommand {

	/**
	 * Instantiates a new gets the cross section by Y command.
	 *
	 * @param myPresenter the my presenter
	 */
	public GetCrossSectionByYCommand(MyPresenter myPresenter) {
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
			myPresenter.getModel().displayCrossSectionByY(Integer.parseInt(args[0]), args[1]);
		}
	}

	/* (non-Javadoc)
	 * @see MVP.presenter.CommonCommand#help()
	 */
	@Override
	public void help() {
		myPresenter.getView().display("Displays cross section by <ySection> for maze3d <name> ." + '\n' + '\t'
				+ "--> Syntax: display cross section by Y <ySection> <name>", new DisplayMessageViewCommand(this.myPresenter.getView()));
	}

}
