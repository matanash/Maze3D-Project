package MVP.presenter;

import MVP.view.commands.DisplayMessageViewCommand;

public class SaveMazeToFileCommand extends CommonCommand {

	public SaveMazeToFileCommand(MyPresenter p) {
		super(p);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (!myPresenter.getModel().mazeExists(args[0]))
			System.out.println("This maze isn't exists");
		else
			myPresenter.getModel().saveMazeToFile(args[0], args[1]);
	}

	@Override
	public void help() {
		myPresenter.getView().display("Saves a maze to <file name> path ." + '\n' + '\t' + "--> Syntax: save maze <name> <file name>", new DisplayMessageViewCommand(this.myPresenter.getView()));
	}

}
