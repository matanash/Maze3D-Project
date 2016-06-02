package MVC.controller;

import MVC.model.Model;
import MVC.view.View;

public class DisplayCrossSectionByXCommand extends CommonCommand {
	public DisplayCrossSectionByXCommand(View v, Model m) {
		super(v, m);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doCommand(String[] args) throws Exception 
	{
		if (args.length != 2) 
		{
			System.out.println("Invalid arguments");
			this.help();
		} 
		else 
		{
			try 
			{
				printMaze2dBySection(model.displayCrossSectionByX(Integer.parseInt(args[0]), args[1]));
			} 
			catch (NumberFormatException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static void printMaze2dBySection(int[][] matrix) {
		System.out.println("The requested Maze Section is: ");
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	@Override
	public void help() {
		System.out.println(
				"display cross section by X <xSection> for maze <name>  (dispay cross section by X Layer for maze)");

	}

}
