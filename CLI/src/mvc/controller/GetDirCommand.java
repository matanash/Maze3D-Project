package mvc.controller;

import java.io.File;

import mvc.model.Model;
import mvc.view.View;

/**
 * This class represents Get directory Command
 * 
 * @author - Matan Ashkenazi and Noee Cohen
 * @version - 1.0
 */
public class GetDirCommand extends CommonCommand {

	public GetDirCommand(View view, Model model) {
		super(view, model);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length != 1) {
			this.view.getOut().println("Invalid arguments");
			this.view.getOut().flush();
			this.help();
		} else {
			String path = args[0];
			File file = new File(path);
			if (file.isDirectory()) {
				this.view.getOut().println(model.dir(path));
				this.view.getOut().flush();
			} else {
				this.view.getOut().println("There is no such directory");
				this.view.getOut().flush();
			}
		}

	}

	@Override
	public void help() {
		this.view.getOut().println("Displays a list of files and subdirectories in a directory. " + '\n' + '\t'
				+ "--> Syntax: dir <path>");
		this.view.getOut().flush();
	}

}
