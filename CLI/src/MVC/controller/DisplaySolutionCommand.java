package MVC.controller;
import MVC.model.Model;
import MVC.view.View;


public class DisplaySolutionCommand extends CommonCommand 
{

	public DisplaySolutionCommand(View v, Model m) {
		super(v, m);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doCommand(String[] args) throws Exception
	{
		if (model.displaySolution(args[0]) == null) 
			System.out.println("There is no maze in that name.");
		else
			System.out.println(model.displaySolution(args[0]));
	}

	@Override
	public void help() 
	{
		System.out.println("display solution <name> (Displaying maze's solution)");

	}

}
