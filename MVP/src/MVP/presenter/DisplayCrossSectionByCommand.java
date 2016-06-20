package MVP.presenter;

import MVP.view.commands.DisplayCrossSectionByViewCommand;

// TODO: Auto-generated Javadoc
/**
 * The Class DisplayCrossSectionByCommand.
 */
public class DisplayCrossSectionByCommand extends CommonCommand {

	/**
	 * Instantiates a new display cross section by command.
	 *
	 * @param preseneter the preseneter
	 */
	public DisplayCrossSectionByCommand(MyPresenter preseneter) {
		super(preseneter);
	}

	/* (non-Javadoc)
	 * @see MVP.presenter.CommonCommand#doCommand(java.lang.String[])
	 */
	@Override
	public void doCommand(String[] args) throws Exception {
		if (args == null)
		{
			myPresenter.getView().display(myPresenter.getModel().getDescriptor(),new DisplayCrossSectionByViewCommand(this.myPresenter.getView()));
		}

	}

	/* (non-Javadoc)
	 * @see MVP.presenter.CommonCommand#help()
	 */
	@Override
	public void help() {}

}
