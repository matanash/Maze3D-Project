package MVP.presenter;

import MVP.model.Model;
import MVP.view.View;

public class DisplayMazeCommand extends CommonCommand {

	public DisplayMazeCommand(View v, Model m) {
		super(v, m);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		
		if (args.length != 1) 
		{
			view.displayMessage("Invalid arguments");
			this.help();
		} else 
		{
			view.displayMaze(model.getMaze3d(args[0]));
		}
		
	}

	@Override
	public void help() {
		System.out.println("Displays the whole maze <maze name>. " + '\n' + '\t' + "--> Syntax: display <maze name>");

	}

}
