package MVP.presenter;

import MVP.view.commands.DisplayMessageCLICommand;

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
			presenter.getView().display("Invalid arguments", new DisplayMessageCLICommand(this.presenter.getView()));
			this.help();
		}
	}

	@Override
	public void help() {
		System.out.println(
				"Solves maze <name> with <algorithm> algorithm ." + '\n' + '\t' + "--> Syntax: solve <name> <algorithm>");

	}

}
