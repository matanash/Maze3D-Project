package MVP.presenter;

public class SaveMazeToFileCommand extends CommonCommand {

	public SaveMazeToFileCommand(Presenter p) {
		super(p);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (!presenter.getModel().mazeExists(args[0]))
			System.out.println("This maze isn't exists");
		else
			presenter.getModel().saveMazeToFile(args[0], args[1]);
	}

	@Override
	public void help() {
		System.out.println("Saves a maze to <file name> path ." + '\n' + '\t' + "--> Syntax: save maze <name> <file name>");

	}

}
