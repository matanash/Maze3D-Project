package MVP.presenter;

import MVP.view.commands.DisplayMessageViewCommand;

public class LoadMazeFromFileCommand extends CommonCommand {
	public LoadMazeFromFileCommand(MyPresenter p) {
		super(p);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		myPresenter.getModel().loadMazeFromFile(args[0], args[1]);
	}

	@Override
	public void help() {
		myPresenter.getView().display("Loads a 3d maze from <file name path> that preserve under <maze name> ." + '\n' + '\t' + "--> Syntax: load maze <file name path> <maze name> ", new DisplayMessageViewCommand(this.myPresenter.getView()));
	}
	

}
