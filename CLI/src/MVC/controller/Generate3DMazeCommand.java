package MVC.controller;

import MVC.model.Model;
import MVC.view.View;

public class Generate3DMazeCommand extends CommonCommand {

	public Generate3DMazeCommand(View v, Model m) {
		super(v, m);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length != 4) {
			System.out.println("Invalid arguments");
			help();
		} else {
			try {
				model.generate3dMaze(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]),
						Integer.parseInt(args[3]));
				System.out.println("The maze has been created");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void help() {
		System.out.println("Generates a new 3D Maze. " + '\n' + '\t'
				+ "--> Syntax: generate 3d maze <maze name> <height> <length> <width>");

	}

}
