package mvc.controller;

import mvc.model.Model;
import mvc.view.View;
/**
 * This Class represents Solve Maze Command
 * @author Matan Ashkenazi and Noee Cohen
 * @version - 1.0
 */
public class SolveMazeCommand extends CommonCommand {

	public SolveMazeCommand(View view, Model model) {
		super(view, model);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length != 2){
			
			this.view.getOut().println("Invalid arguments");
			this.view.getOut().flush();
			this.help();
		}
		else if (!this.model.mazeExists(args[0]))
		{
			this.view.getOut().println("There isn't such maze called " + args[0] + " try solve another one.");
			this.view.getOut().flush();
		}
		else if (!(args[1].toUpperCase().equals("BFS") || args[1].toUpperCase().equals("BREADTHFIRSTSEARCH")
				|| args[1].toUpperCase().equals("DFS")))
		{
			this.view.getOut().println("There isn't such algorithm called " + args[1] + ", try another one");
			this.view.getOut().flush();
		}
		else
		{
			model.solveMaze(args[0], args[1]);
			this.view.getOut().println("The maze3d " +args[1].toUpperCase() + " Solution based is ready");
			this.view.getOut().flush();
		}
		
	}

	@Override
	public void help() {
		this.view.getOut().println(
				"Solves maze <name> with <algorithm> algorithm ." + '\n' + '\t' + "--> Syntax: solve <name> <algorithm>");
		this.view.getOut().flush();
	}

}
