package MVP.presenter;

public class DisplayMazeSizeCommand extends CommonCommand {

	public DisplayMazeSizeCommand(Presenter p) {
		super(p);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length != 1) {
			presenter.getView().displayMessage("Invalid arguments");
			this.help();
		} else {
			int size = presenter.getModel().sizeInMemory(args[0]);
			if (size == -1)
				presenter.getView().displayMessage("There is no such maze!");
			else
				presenter.getView().displayMessage("The size of the maze in the memory is " + size + " bytes.");
		}

	}

	@Override
	public void help() {
		System.out
				.println("Displays the maze size in the memory. " + '\n' + '\t' + "--> Syntax: maze size <maze name>");

	}

}
