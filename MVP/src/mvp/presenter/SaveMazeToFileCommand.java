package mvp.presenter;

import mvp.view.commands.DisplayMessageCLICommand;

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
		myPresenter.getView().display("Saves a maze to <file name> path ." + '\n' + '\t' + "--> Syntax: save maze <name> <file name>", new DisplayMessageCLICommand(this.myPresenter.getView()));
	}

}
