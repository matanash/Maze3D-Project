package MVP.presenter;

import MVP.view.commands.DisplayMessageViewCommand;

// TODO: Auto-generated Javadoc
/**
 * The Class GetMazeSizeInFileCommand.
 */
public class GetMazeSizeInFileCommand extends CommonCommand {

	/**
	 * Instantiates a new gets the maze size in file command.
	 *
	 * @param myPresenter the my presenter
	 */
	public GetMazeSizeInFileCommand(MyPresenter myPresenter) {
		super(myPresenter);
	}

	/* (non-Javadoc)
	 * @see MVP.presenter.CommonCommand#doCommand(java.lang.String[])
	 */
	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length != 1) 
		{
			myPresenter.getView().display("Invalid arguments", new DisplayMessageViewCommand(this.myPresenter.getView()));
			this.help();
		} 
		else 
		{
			myPresenter.getModel().sizeInFile(args[0]);
		}
	}

	/* (non-Javadoc)
	 * @see MVP.presenter.CommonCommand#help()
	 */
	@Override
	public void help() {
		myPresenter.getView().display("Displays the maze size in file." + '\n' + '\t' + "--> Syntax: file size <name>", new DisplayMessageViewCommand(this.myPresenter.getView()));

	}

}
