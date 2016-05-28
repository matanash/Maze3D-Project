package boot;

import algorithms.mazeGenerators.*;
import model.maze3d.Direction;
import model.maze3d.Maze3d;
import model.maze3d.Position;

public class Run 
{
	public static void main(String[] args) throws Exception 
	{
		testMazeGenerator(new SimpleMaze3dGenerator());
		System.out.println();
		testMazeGenerator(new MyMaze3dGenerator());	
	}
	
	private static void testMazeGenerator(Maze3dGenerator mg) throws Exception
	{	
		
		// prints the time it takes the algorithm to run
		System.out.println(mg.measureAlgorithmTime(3,5,7));

		// generate another 3d maze
		Maze3d maze=mg.generate(3,7,5);
		System.out.println(maze);
		
		// get the maze entrance
		Position startPos=maze.getStartPosition();
	
		// print the entrance position
		System.out.print("Start Position is: ");
		System.out.println(startPos); // format "{x,y,z}"
		
		// prints the maze exit position
		System.out.print("Exit Position is: ");
		System.out.println(maze.getGoalPosition());
		
		// get all the possible moves from a position
		Direction[] moves=maze.getPossibleMoves(startPos);
		
		// print the moves
		System.out.println("\nPossible Moves from Start Position are: ");
		for(Direction move : moves)
			System.out.println(move);
		
		System.out.println();
		
		
		
		try
		{
			// get 2d cross sections of the 3d maze
			int[][] maze2dy=maze.getCrossSectionByY(2);
			System.out.println();
			printMaze2dBySection(maze2dy);
			int[][] maze2dx=maze.getCrossSectionByX(5);
			printMaze2dBySection(maze2dx);
			int[][] maze2dz=maze.getCrossSectionByZ(0);
			printMaze2dBySection(maze2dz);
			
			//this should throw an exception!
			maze.getCrossSectionByX(-1);
			
		} 
		catch (IndexOutOfBoundsException e)
		{
			System.out.println("good");
		}
	}
	public static void printMaze2dBySection(int [][] matrix)
	{
		System.out.println("Matrix Section is: ");
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
}