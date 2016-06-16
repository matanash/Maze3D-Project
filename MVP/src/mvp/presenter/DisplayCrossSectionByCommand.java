package mvp.presenter;

import mvp.view.commands.DisplayCrossSectionByCLICommand;

public class DisplayCrossSectionByCommand extends CommonCommand {

	public DisplayCrossSectionByCommand(MyPresenter preseneter) {
		super(preseneter);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args == null)
		{
			myPresenter.getView().display(myPresenter.getModel().getDescriptor(),new DisplayCrossSectionByCLICommand(this.myPresenter.getView()));
		}

	}

	@Override
	public void help() {}

}
