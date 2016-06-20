package mvc.controller;

import mvc.model.Model;
import mvc.view.View;

/**
 * This class represents Genreate 3D Maze Command
 * 
 * @author Matan Ashkenazi and Noee Cohen
 * @version - 1.0
 */
public class Generate3DMazeCommand extends CommonCommand {

	public Generate3DMazeCommand(View view, Model model) {
		super(view, model);

	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length != 4) {
			this.view.getOut().println("Invalid arguments");
			this.view.getOut().flush();
			help();
		} else {
			try {
				model.generate3dMaze(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]),
						Integer.parseInt(args[3]));
				this.view.getOut().println("The maze has been created");
				this.view.getOut().flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void help() {
		this.view.getOut().println("Generates a new 3D Maze. " + '\n' + '\t'
				+ "--> Syntax: generate 3d maze <maze name> <height> <length> <width>");
		this.view.getOut().flush();

	}

}
