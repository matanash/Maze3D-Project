package mvp.presenter;

import mvp.view.commands.DisplaySolutionCLICommand;

public class DisplaySolutionCommand extends CommonCommand {

	public DisplaySolutionCommand(MyPresenter myPresenter) {
		super(myPresenter);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args == null)
		{
			this.myPresenter.getView().display(this.myPresenter.getModel().getDescriptor(),new DisplaySolutionCLICommand(this.myPresenter.getView()));
		}

	}

	@Override
	public void help() {}

}
