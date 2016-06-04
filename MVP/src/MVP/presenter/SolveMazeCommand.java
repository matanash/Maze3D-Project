package MVP.presenter;

import MVP.model.Model;
import MVP.view.View;

public class SolveMazeCommand extends CommonCommand {

	public SolveMazeCommand(View v, Model m) {
		super(v, m);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length == 2)
			model.solveMaze(args[0], args[1]);
		else {
			view.displayMessage("Invalid arguments");
			this.help();
		}
	}

	@Override
	public void help() {
		System.out.println(
				"Solves maze <name> with <algorithm> algorithm ." + '\n' + '\t' + "--> Syntax: solve <name> <algorithm>");

	}

}
