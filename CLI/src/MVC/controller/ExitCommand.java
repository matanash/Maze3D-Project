package MVC.controller;

import MVC.model.Model;
import MVC.view.View;

public class ExitCommand extends CommonCommand{

	
	public ExitCommand(View v, Model m) {
		super(v, m);
		
	}

	@Override
	public void doCommand(String[] args) throws Exception
	{
		System.out.println("|----------------------------|");
		System.out.println("|   Thank you for playing!   |");
		System.out.println("|----------------------------|");
		System.exit(0);
	}

	@Override
	public void help() {
		System.out.println("Exit and close the maze game");
		
	}

}
