package MVP.presenter;

public class DisplayFileSizeCommand extends CommonCommand {

	public DisplayFileSizeCommand(Presenter p) {
		super(p);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length != 1) {
			presenter.getView().displayMessage("Invalid arguments");
			this.help();
		} else {
			int size = presenter.getModel().sizeInFile(args[0]);
			if (size == -1)
				presenter.getView().displayMessage("There is no such file!");
			else
				presenter.getView().displayMessage("The size of the maze in the file is " + size + " bytes.");
		}
	}

	@Override
	public void help() {
		System.out.println("Displays the maze's file size ." + '\n' + '\t' + "--> Syntax: file size <name>");

	}

}
