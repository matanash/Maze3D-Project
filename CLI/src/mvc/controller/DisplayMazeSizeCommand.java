package mvc.controller;

import mvc.model.Model;
import mvc.view.View;

/**
 * This class represents Display maze size command
 * 
 * @author Matan Ashkenazi and Noee Cohen
 * @version - 1.0
 */
public class DisplayMazeSizeCommand extends CommonCommand {

	public DisplayMazeSizeCommand(View v, Model m) {
		super(v, m);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length != 1) {
			this.view.getOut().println("Invalid arguments");
			this.view.getOut().flush();
			this.help();
		} else {
			int size = model.sizeInMemory(args[0]);
			if (size == -1) {
				this.view.getOut().println("There is no such maze!");
				this.view.getOut().flush();
			} else {
				this.view.getOut().println("The size of the maze in the memory is " + size + " bytes.");
				this.view.getOut().flush();
			}
		}

	}

	@Override
	public void help() {
		this.view.getOut()
				.println("Displays the maze size in the memory. " + '\n' + '\t' + "--> Syntax: maze size <maze name>");
		this.view.getOut().flush();
	}

}
