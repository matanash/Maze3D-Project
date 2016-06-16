package mvp.model;

import java.util.HashMap;
import java.util.Observable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import algorithms.search.Solution;
import model.maze3d.Maze3d;
import mvp.presenter.Properties;

public abstract class CommonMaze3DModel extends Observable implements Model {

	protected HashMap<String, Maze3d> mazesMap;
	protected HashMap<String, Solution> solutionMap;
	protected HashMap<Maze3d,Solution> maze2sol;
	protected ExecutorService threadPool;
	protected Properties properties;
	
	public CommonMaze3DModel() {
		mazesMap = new HashMap<String, Maze3d>();	
		solutionMap = new HashMap<String, Solution>();
		maze2sol = new HashMap<Maze3d,Solution>();
		threadPool = Executors.newCachedThreadPool();
		properties = new Properties();
	}
	
	public Properties getProperties() {
		return properties;
	}
	
	public void setProperties(Properties properties)
	{
		this.properties = properties;
		ExecutorService bufferdpool = threadPool;
		threadPool = Executors.newFixedThreadPool(properties.getMaxThreads());  //change the number of threads by Properties
		bufferdpool.shutdown();	// initiate orderly shutdown of temporary threadPool

	}
	
}
