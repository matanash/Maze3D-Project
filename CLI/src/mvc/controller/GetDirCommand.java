package mvc.controller;

import java.io.File;

import mvc.model.Model;
import mvc.view.View;

public class GetDirCommand extends CommonCommand {

	public GetDirCommand(View v, Model m) {
		super(v, m);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length != 1) {
			System.out.println("Invalid arguments");
			this.help();
		} else {
			String path = args[0];
			File file = new File(path);
			if (this.view == null)
				System.out.println("123");
			if (file.isDirectory())
				model.dir(path);
			else
				System.out.println("There is no such directory");
		}

	}

	@Override
	public void help() {
		System.out.println(
				"Displays a list of files and subdirectories in a directory. " + '\n' + '\t' + "--> Syntax: dir <path>");

	}

}
