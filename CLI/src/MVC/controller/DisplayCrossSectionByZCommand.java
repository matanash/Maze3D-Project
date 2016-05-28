package MVC.controller;

import MVC.model.Model;
import MVC.view.View;

public class DisplayCrossSectionByZCommand extends CommonCommand 
{
	

	
	public DisplayCrossSectionByZCommand(View v, Model m) {
		super(v, m);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doCommand(String[] args)
	{
		if (!model.mazeExists(args[0]))
			System.out.println("This maze isn't exists");
		else
		{
			printMaze2dBySection(model.displayCrossSectionByZ(args[0],Integer.parseInt(args[1])));
		}

	}

	private static void printMaze2dBySection(int [][] matrix)
	{
		System.out.println("The requested Maze Section is: ");
		for (int i=0;i<matrix.length;i++)
		{
			for (int j=0;j<matrix[0].length;j++)
			{
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	@Override
	public void help() 
	{
		System.out.println("display cross section by Z for <name> by <zLayer> (dispay cross section by Z Layer for maze)");
	}

}
