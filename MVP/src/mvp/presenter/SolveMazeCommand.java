package mvp.presenter;

import mvp.view.commands.DisplayMessageCLICommand;

public class SolveMazeCommand extends CommonCommand {

	public SolveMazeCommand(MyPresenter p) {
		super(p);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length == 2){
			myPresenter.getModel().solveMaze(args[0], args[1]);
		}
				
		else {
			myPresenter.getView().display("Invalid arguments", new DisplayMessageCLICommand(this.myPresenter.getView()));
			this.help();
		}
	}

	@Override
	public void help() {
		myPresenter.getView().display("Solves maze <name> with <algorithm> algorithm ." + '\n' + '\t' + "--> Syntax: solve <name> <algorithm>", new DisplayMessageCLICommand(this.myPresenter.getView()));
	}

}
