
package MVP.presenter;

import MVP.view.commands.DisplayMessageViewCommand;
/**
 * The Class LoadMazeFromFileCommand.
 * @author Matan Ashkenazi and Noee Cohen
 * @version - 1.0
 */
public class LoadMazeFromFileCommand extends CommonCommand {
	
	/**
	 * Instantiates a new load maze from file command.
	 *
	 * @param p the p
	 */
	public LoadMazeFromFileCommand(MyPresenter p) {
		super(p);
	}

	/* (non-Javadoc)
	 * @see MVP.presenter.CommonCommand#doCommand(java.lang.String[])
	 */
	@Override
	public void doCommand(String[] args) throws Exception {
		myPresenter.getModel().loadMazeFromFile(args[0], args[1]);
	}

	/* (non-Javadoc)
	 * @see MVP.presenter.CommonCommand#help()
	 */
	@Override
	public void help() {
		myPresenter.getView().display("Loads a 3d maze from <file name path> that preserve under <maze name> ." + '\n' + '\t' + "--> Syntax: load maze <file name path> <maze name> ", new DisplayMessageViewCommand(this.myPresenter.getView()));
	}
	

}
