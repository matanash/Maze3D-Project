package MVP.presenter;

import MVP.model.Model;
import MVP.view.View;

public class DisplayFileSizeCommand extends CommonCommand {

	public DisplayFileSizeCommand(View v, Model m) {
		super(v, m);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length != 1) {
			view.displayMessage("Invalid arguments");
			this.help();
		} else {
			int size = model.sizeInFile(args[0]);
			if (size == -1)
				view.displayMessage("There is no such file!");
			else
				view.displayMessage("The size of the maze in the file is " + size + " bytes.");
		}
	}

	@Override
	public void help() {
		System.out.println("Displays the maze's file size ." + '\n' + '\t' + "--> Syntax: file size <name>");

	}

}
