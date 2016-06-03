package MVC.controller;

import MVC.model.Model;
import MVC.view.View;

public class SolveMazeCommand extends CommonCommand {

	public SolveMazeCommand(View v, Model m) {
		super(v, m);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length == 2)
			model.solveMaze(args[0], args[1]);
		else {
			System.out.println("Invalid arguments");
			this.help();
		}
		/*
		 * if (solution == null) System.out.println(
		 * "The solving proccess could not have been complete."); /*else
		 * System.out.println("The maze " + args[0] + "'s solution is ready");
		 */
	}

	@Override
	public void help() {
		System.out.println(
				"Solves maze <name> with <algorithm> algorithm ." + '\n' + '\t' + "--> Syntax: solve <name> <algorithm>");

	}

}
