package MVP.presenter;

import MVP.view.commands.DisplaySolutionViewCommand;

public class DisplaySolutionCommand extends CommonCommand {

	public DisplaySolutionCommand(MyPresenter myPresenter) {
		super(myPresenter);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args == null)
		{
			this.myPresenter.getView().display(this.myPresenter.getModel().getDescriptor(),new DisplaySolutionViewCommand(this.myPresenter.getView()));
		}

	}

	@Override
	public void help() {}

}
