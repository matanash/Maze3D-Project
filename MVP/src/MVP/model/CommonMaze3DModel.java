package MVP.model;

import java.util.HashMap;
import java.util.Observable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import algorithms.search.Solution;
import model.maze3d.Maze3d;

public abstract class CommonMaze3DModel extends Observable implements Model {

	protected HashMap<String, Maze3d> mazesMap;
	protected HashMap<String, Solution> solutionMap;
	protected HashMap<Maze3d,Solution> solutionToMazeMap;
	protected ExecutorService threadPool;
	
	public CommonMaze3DModel() {
		mazesMap = new HashMap<String, Maze3d>();	
		solutionMap = new HashMap<String, Solution>();
		solutionToMazeMap = new HashMap<Maze3d, Solution>();
		threadPool = Executors.newCachedThreadPool();

	}
	
}
