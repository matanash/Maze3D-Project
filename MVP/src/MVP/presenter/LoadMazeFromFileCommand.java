package MVP.presenter;

public class LoadMazeFromFileCommand extends CommonCommand {
	public LoadMazeFromFileCommand(Presenter p) {
		super(p);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		presenter.getModel().loadMazeFromFile(args[0], args[1]);
	}

	@Override
	public void help() {
		System.out.println("Loads a 3d maze from <file name path> that preserve under <maze name> ." + '\n' + '\t' + "--> Syntax: load maze <file name path> <maze name> ");
	}

}
