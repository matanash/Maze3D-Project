package MVP.presenter;

import MVP.view.commands.DisplayMessageViewCommand;

// TODO: Auto-generated Javadoc
/**
 * The Class SaveMazeToFileCommand.
 */
public class SaveMazeToFileCommand extends CommonCommand {

	/**
	 * Instantiates a new save maze to file command.
	 *
	 * @param p the p
	 */
	public SaveMazeToFileCommand(MyPresenter p) {
		super(p);
	}

	/* (non-Javadoc)
	 * @see MVP.presenter.CommonCommand#doCommand(java.lang.String[])
	 */
	@Override
	public void doCommand(String[] args) throws Exception {
		if (!myPresenter.getModel().mazeExists(args[0]))
			System.out.println("This maze isn't exists");
		else
			myPresenter.getModel().saveMazeToFile(args[0], args[1]);
	}

	/* (non-Javadoc)
	 * @see MVP.presenter.CommonCommand#help()
	 */
	@Override
	public void help() {
		myPresenter.getView().display("Saves a maze to <file name> path ." + '\n' + '\t' + "--> Syntax: save maze <name> <file name>", new DisplayMessageViewCommand(this.myPresenter.getView()));
	}

}
