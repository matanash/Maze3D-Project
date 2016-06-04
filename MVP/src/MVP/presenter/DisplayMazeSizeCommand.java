package MVP.presenter;

import MVP.model.Model;
import MVP.view.View;

public class DisplayMazeSizeCommand extends CommonCommand {

	public DisplayMazeSizeCommand(View v, Model m) {
		super(v, m);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length != 1) {
			view.displayMessage("Invalid arguments");
			this.help();
		} else {
			int size = model.sizeInMemory(args[0]);
			if (size == -1)
				view.displayMessage("There is no such maze!");
			else
				view.displayMessage("The size of the maze in the memory is " + size + " bytes.");
		}

	}

	@Override
	public void help() {
		System.out
				.println("Displays the maze size in the memory. " + '\n' + '\t' + "--> Syntax: maze size <maze name>");

	}

}
