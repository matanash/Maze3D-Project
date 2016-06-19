package MVP.presenter;

import MVP.view.commands.DisplayMessageViewCommand;
import model.maze3d.Position;

public class SolveMazeCommand extends CommonCommand {

	public SolveMazeCommand(MyPresenter presenter) {
		super(presenter);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length == 2){
			myPresenter.getModel().solveMaze(args[0], args[1]);
		}
		
		else if (args.length == 4)
		{
			Position characterPosition;
			String algo = this.myPresenter.getModel().getProperties().getSolveAlgorithm().toLowerCase();
			int y = Integer.parseInt(args[1]);
			int x = Integer.parseInt(args[2]);
			int z = Integer.parseInt(args[3]);
			characterPosition = new Position(y, x, z);
			myPresenter.getModel().solveMaze(args[0], algo, characterPosition);
		}	
		else 
		{
			myPresenter.getView().display("Invalid arguments ", new DisplayMessageViewCommand(this.myPresenter.getView()));
			this.help();
		}
	}

	@Override
	public void help() {
		myPresenter.getView().display("Solves maze <name> with <algorithm> algorithm ." + '\n' + '\t' + "--> Syntax: solve <name> <algorithm>", new DisplayMessageViewCommand(this.myPresenter.getView()));
	}

}
