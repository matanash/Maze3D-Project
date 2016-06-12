package MVP.presenter;

import MVP.view.commands.DisplayCrossSectionByCLICommand;

public class DisplayCrossSectionByCommand extends CommonCommand {

	public DisplayCrossSectionByCommand(Presenter preseneter) {
		super(preseneter);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args == null)
		{
			presenter.getView().display(presenter.getModel().getDescriptor(),new DisplayCrossSectionByCLICommand(this.presenter.getView()));
		}

	}

	@Override
	public void help() {}

}
