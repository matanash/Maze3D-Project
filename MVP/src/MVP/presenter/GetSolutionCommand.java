package MVP.presenter;

import MVP.view.commands.DisplayMessageCLICommand;

public class GetSolutionCommand extends CommonCommand {

	public GetSolutionCommand(Presenter p) {
		super(p);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length != 1) 
		{
			presenter.getView().display("Invalid arguments", new DisplayMessageCLICommand(this.presenter.getView()));
			help();
		} 
		//presenter.getView().displaySolution(presenter.getModel().displaySolution(args[0]));
		presenter.getModel().getSolution(args[0]);
			
	}

	@Override
	public void help() {
		System.out.println("Displays the maze's solution. " + '\n' + '\t' + "--> Syntax: display solution <maze name>");

	}

}
