package MVP.presenter;

import MVP.view.commands.DisplayCrossSectionByViewCommand;

public class DisplayCrossSectionByCommand extends CommonCommand {

	public DisplayCrossSectionByCommand(MyPresenter preseneter) {
		super(preseneter);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args == null)
		{
			myPresenter.getView().display(myPresenter.getModel().getDescriptor(),new DisplayCrossSectionByViewCommand(this.myPresenter.getView()));
		}

	}

	@Override
	public void help() {}

}
