package MVP.model;

import java.util.HashMap;
import java.util.Observable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import MVP.presenter.Properties;
import algorithms.search.Solution;
import model.maze3d.Maze3d;

// TODO: Auto-generated Javadoc
/**
 * The Class CommonMaze3DModel.
 */
public abstract class CommonMaze3DModel extends Observable implements Model {

	/** The mazes map. */
	protected HashMap<String, Maze3d> mazesMap;
	
	/** The solution map. */
	protected HashMap<String, Solution> solutionMap;
	
	/** The maze 2 sol. */
	protected HashMap<Maze3d,Solution> maze2sol;
	
	/** The thread pool. */
	protected ExecutorService threadPool;
	
	/** The properties. */
	protected Properties properties;
	
	/**
	 * Instantiates a new common maze 3 D model.
	 */
	public CommonMaze3DModel() {
		mazesMap = new HashMap<String, Maze3d>();	
		solutionMap = new HashMap<String, Solution>();
		maze2sol = new HashMap<Maze3d,Solution>();
		threadPool = Executors.newCachedThreadPool();
		properties = new Properties();
	}
	
	/* (non-Javadoc)
	 * @see MVP.model.Model#getProperties()
	 */
	public Properties getProperties() {
		return properties;
	}
	
	/* (non-Javadoc)
	 * @see MVP.model.Model#setProperties(MVP.presenter.Properties)
	 */
	public void setProperties(Properties properties)
	{
		this.properties = properties;
		ExecutorService bufferdpool = threadPool;
		threadPool = Executors.newFixedThreadPool(properties.getMaxThreads());  //change the number of threads by Properties
		bufferdpool.shutdown();	// initiate orderly shutdown of temporary threadPool

	}
	
	
}
