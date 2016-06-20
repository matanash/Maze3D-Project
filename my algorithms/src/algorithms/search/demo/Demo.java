package algorithms.search.demo;

import algorithms.mazeGenerators.MyMaze3dGenerator;
//import algorithms.mazeGenerators.SimpleMaze3dGenerator;
import algorithms.search.BFSSearcher;
import algorithms.search.BreadthFirstSearcher;
import algorithms.search.DFSSearcher;
import model.maze3d.Maze3d;
import model.maze3d.Position;

/**
 * This class demonstrate used of Solution Searchers Algorithms
 * @author Matan Ashkenazi and Noee Cohen
 * @version - 1.0
 */
public class Demo 
{

	/**
	 * This method generate maze and solve it with 3 different algorithms
	 * @param height - Maze height required to generate
	 * @param length - Maze length required to generate
	 * @param width  - Maze width required to generate
	 */
	public void run(int height,int length,int width) 
	{
		try{
			// Maze Generation with DFS Algorithm
			MyMaze3dGenerator gen = new MyMaze3dGenerator();
			//SimpleMaze3dGenerator gen = new SimpleMaze3dGenerator();
			Maze3d maze3d = gen.generate(height,length,width);

			Position entrance = maze3d.getStartPosition();
			System.out.println("Maze Entrance \tPosition: " + entrance);
			Position goal = maze3d.getGoalPosition();
			System.out.println("Maze Exit \tPosition: " + goal);
			
			System.out.println(maze3d);
			Maze3dAdapter m3dAdapter = new Maze3dAdapter(maze3d);
			
			// DFS
			System.out.println("\nDFS Solution: ");
			DFSSearcher dfs = new DFSSearcher();
			System.out.println(dfs.search(m3dAdapter));
			System.out.println("The Number of evaluated nodes with DFS Algorithm = " +dfs.getEvaluatedNodes());
			
			// Breadth First Search
			System.out.println("\nBredthFirstSearch Solution: ");
			BreadthFirstSearcher bredthFirstSearcher = new BreadthFirstSearcher();
			System.out.println(bredthFirstSearcher.search(m3dAdapter));
			System.out.println("The Number of evaluated nodes with BreadthFirstSearch Algorithm = " +bredthFirstSearcher.getEvaluatedNodes());
			
			//Best First Search
			System.out.println("\nBFS Solution: ");
			BFSSearcher bfs = new BFSSearcher();
			System.out.println(bfs.search(m3dAdapter));
			System.out.println("The Number of evaluated nodes with Best First Search Algorithm = " +bfs.getEvaluatedNodes());
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		

	}

}
