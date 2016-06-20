package mvc.controller;

import mvc.model.Model;
import mvc.view.View;
/**
 * This class represent Display File Size Command
 * @author Matan Ashkenazi and Noee Cohen
 * @version - 1.0
 */
public class DisplayFileSizeCommand extends CommonCommand {

	public DisplayFileSizeCommand(View view, Model model) {
		super(view, model);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length != 1) {
			this.view.getOut().println("Invalid arguments");
			this.view.getOut().flush();
			this.help();
		} else {
			int size = model.sizeInFile(args[0]);
			if (size == -1){
				this.view.getOut().println("There is no such file!");
				this.view.getOut().flush();
			}
			else
			{
				this.view.getOut().println("The size of the maze in the file is " + size + " bytes.");
				this.view.getOut().flush();
			}
		}
	}

	@Override
	public void help() {
		this.view.getOut().println("Displays the maze's file size ." + '\n' + '\t' + "--> Syntax: file size <name>");
		this.view.getOut().flush();
	}

}
