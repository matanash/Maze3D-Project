package MVP.presenter;

import MVP.view.commands.DisplaySolutionCLICommand;

public class DisplaySolutionCommand extends CommonCommand {

	public DisplaySolutionCommand(Presenter presenter) {
		super(presenter);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args == null)
		{
			this.presenter.getView().display(this.presenter.getModel().getDescriptor(),new DisplaySolutionCLICommand(this.presenter.getView()));
		}

	}

	@Override
	public void help() {}

}
