package MVC.controller;

import java.io.File;

import MVC.model.Model;
import MVC.view.View;

public class GetDirCommand extends CommonCommand {

	public GetDirCommand(View v, Model m) {
		super(v, m);
	}

	@Override
	public void doCommand(String[] args) 
	{
		String path = args[0];
		File file = new File(path);
		if (this.view==null)
			System.out.println("123");
		if (file.isDirectory())
			model.dir(path);
		else
			System.out.println("There is no such directory");
	}

	@Override
	public void help() {
		System.out.println("Display all files and directories in <path>");

	}

}
