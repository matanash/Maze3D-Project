package MVC.controller;

import MVC.model.Model;
import MVC.view.View;

public class Generate3DMazeCommand extends CommonCommand {
	
	public Generate3DMazeCommand(View v, Model m) {
		super(v, m);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doCommand(String[] args) {
		try {
			if (model.mazeExists(args[0]))
				System.out.println("This maze isn't exists");	
			else
			{
				model.generate3dMaze(args[0], Integer.parseInt(args[1]),Integer.parseInt(args[2]),Integer.parseInt(args[3]));
				System.out.println("The maze has been created");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void help() {
		System.out.println("generate 3d maze <name> <height> <length> <width> (genetaring a new 3d maze)");
		
	}

}
