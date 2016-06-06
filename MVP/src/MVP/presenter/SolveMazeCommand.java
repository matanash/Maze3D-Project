package MVP.presenter;

public class SolveMazeCommand extends CommonCommand {

	public SolveMazeCommand(Presenter p) {
		super(p);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length == 2){
			presenter.getModel().solveMaze(args[0], args[1]);
		}
				
		else {
			presenter.getView().displayMessage("Invalid arguments");
			this.help();
		}
	}

	@Override
	public void help() {
		System.out.println(
				"Solves maze <name> with <algorithm> algorithm ." + '\n' + '\t' + "--> Syntax: solve <name> <algorithm>");

	}

}
