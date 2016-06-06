package MVP.presenter;

public class DisplaySolutionCommand extends CommonCommand {

	public DisplaySolutionCommand(Presenter p) {
		super(p);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length!=1){
			presenter.getView().displayMessage("Invalid arguments");
			help();
		}	
		else if (presenter.getModel().displaySolution(args[0]) == null)
			presenter.getView().displayMessage("There is no maze in that name .");
		else
			presenter.getView().displaySolution(presenter.getModel().displaySolution(args[0]));
	}

	@Override
	public void help() {
		System.out.println("Displays the maze's solution. " + '\n' + '\t' + "--> Syntax: display solution <maze name>");

	}

}
