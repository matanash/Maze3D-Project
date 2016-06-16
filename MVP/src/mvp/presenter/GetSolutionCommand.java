package mvp.presenter;

import mvp.view.commands.DisplayMessageCLICommand;

public class GetSolutionCommand extends CommonCommand {

	public GetSolutionCommand(MyPresenter p) {
		super(p);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length != 1) 
		{
			myPresenter.getView().display("Invalid arguments", new DisplayMessageCLICommand(this.myPresenter.getView()));
			help();
		} 
		//presenter.getView().displaySolution(presenter.getModel().displaySolution(args[0]));
		myPresenter.getModel().getSolution(args[0]);
			
	}

	@Override
	public void help() {
		myPresenter.getView().display("Displays the maze's solution. " + '\n' + '\t' + "--> Syntax: display solution <maze name>", new DisplayMessageCLICommand(this.myPresenter.getView()));
	}

}
