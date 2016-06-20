package MVP.presenter;

import MVP.view.commands.DisplayMessageViewCommand;

// TODO: Auto-generated Javadoc
/**
 * The Class GetMazeSizeInMemoryCommand.
 */
public class GetMazeSizeInMemoryCommand extends CommonCommand {

	/**
	 * Instantiates a new gets the maze size in memory command.
	 *
	 * @param p the p
	 */
	public GetMazeSizeInMemoryCommand(MyPresenter p) {
		super(p);
	}

	/* (non-Javadoc)
	 * @see MVP.presenter.CommonCommand#doCommand(java.lang.String[])
	 */
	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length != 1) {
			myPresenter.getView().display("Invalid arguments", new DisplayMessageViewCommand(this.myPresenter.getView()));
			this.help();
		} 
		else 
		{
			myPresenter.getModel().sizeInMemory(args[0]);
		}

	}

	/* (non-Javadoc)
	 * @see MVP.presenter.CommonCommand#help()
	 */
	@Override
	public void help() {
		myPresenter.getView().display("Displays the maze size in the memory. " + '\n' + '\t' + "--> Syntax: maze size <maze name>", new DisplayMessageViewCommand(this.myPresenter.getView()));
	}

}
