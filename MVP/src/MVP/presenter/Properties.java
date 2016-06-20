package MVP.presenter;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class Properties.
 */
public class Properties implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The debug mode. */
	protected boolean debugMode; 		// on or off debug mode
	
	/** The maze max height. */
	protected int mazeMaxHeight; 		// Max Maze Height
	
	/** The maze max length. */
	protected int mazeMaxLength; 		// Max Maze Length
	
	/** The maze max width. */
	protected int mazeMaxWidth; 		// Max Maze Width
	
	/** The generate algorithm. */
	protected String generateAlgorithm; // Maze generating algorithm.
	
	/** The solve algorithm. */
	protected String solveAlgorithm; 	// maze solving algorithm.
	
	/** The max threads. */
	protected int maxThreads; 			// Max threads In ThreadPool
	
	/** The ui. */
	protected String ui; 				// which UI to present.
	
	/**
	 * Instantiates a new properties.
	 */
	public Properties() {
		setDefaults();
	}
	
	/**
	 * Sets the defaults.
	 */
	public void setDefaults() {
		this.debugMode = false;
		this.mazeMaxHeight = 35;
		this.mazeMaxLength = 35;
		this.mazeMaxWidth = 35;
		this.generateAlgorithm = "MyMaze3dGenerator";
		this.solveAlgorithm = "DFS";
		this.maxThreads = 10;
		this.ui = "GUI";

	}
	
	/**
	 * Checks if is debug mode.
	 *
	 * @return true, if is debug mode
	 */
	public boolean isDebugMode() {
		return debugMode;
	}
	
	/**
	 * Sets the debug mode.
	 *
	 * @param debugMode the new debug mode
	 */
	public void setDebugMode(boolean debugMode) {
		this.debugMode = debugMode;
	}
	
	/**
	 * Gets the maze max height.
	 *
	 * @return the maze max height
	 */
	public int getMazeMaxHeight() {
		return mazeMaxHeight;
	}
	
	/**
	 * Sets the maze max height.
	 *
	 * @param mazeMaxHeight the new maze max height
	 */
	public void setMazeMaxHeight(int mazeMaxHeight) {
		this.mazeMaxHeight = mazeMaxHeight;
	}
	
	/**
	 * Gets the maze max length.
	 *
	 * @return the maze max length
	 */
	public int getMazeMaxLength() {
		return mazeMaxLength;
	}
	
	/**
	 * Sets the maze max length.
	 *
	 * @param mazeMaxLength the new maze max length
	 */
	public void setMazeMaxLength(int mazeMaxLength) {
		this.mazeMaxLength = mazeMaxLength;
	}
	
	/**
	 * Gets the maze max width.
	 *
	 * @return the maze max width
	 */
	public int getMazeMaxWidth() {
		return mazeMaxWidth;
	}
	
	/**
	 * Sets the maze max width.
	 *
	 * @param mazeMaxWidth the new maze max width
	 */
	public void setMazeMaxWidth(int mazeMaxWidth) {
		this.mazeMaxWidth = mazeMaxWidth;
	}
	
	/**
	 * Gets the generate algorithm.
	 *
	 * @return the generate algorithm
	 */
	public String getGenerateAlgorithm() {
		return generateAlgorithm;
	}
	
	/**
	 * Sets the generate algorithm.
	 *
	 * @param generateAlgorithm the new generate algorithm
	 */
	public void setGenerateAlgorithm(String generateAlgorithm) {
		this.generateAlgorithm = generateAlgorithm;
	}
	
	/**
	 * Gets the solve algorithm.
	 *
	 * @return the solve algorithm
	 */
	public String getSolveAlgorithm() {
		return solveAlgorithm;
	}
	
	/**
	 * Sets the solve algorithm.
	 *
	 * @param solveAlgorithm the new solve algorithm
	 */
	public void setSolveAlgorithm(String solveAlgorithm) {
		this.solveAlgorithm = solveAlgorithm;
	}
	
	/**
	 * Gets the max threads.
	 *
	 * @return the max threads
	 */
	public int getMaxThreads() {
		return maxThreads;
	}
	
	/**
	 * Sets the max threads.
	 *
	 * @param maxThreads the new max threads
	 */
	public void setMaxThreads(int maxThreads) {
		this.maxThreads = maxThreads;
	}
	
	/**
	 * Gets the ui.
	 *
	 * @return the ui
	 */
	public String getUi() {
		return ui;
	}
	
	/**
	 * Sets the ui.
	 *
	 * @param ui the new ui
	 */
	public void setUi(String ui) {
		this.ui = ui;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Properties" + '\n' + "DebugMode= " + debugMode + '\n' +"MazeMaxHeight= " + mazeMaxHeight + '\n' + "MazeMaxLength= "
				+ mazeMaxLength + '\n' + "MazeMaxWidth= " + mazeMaxWidth + '\n' + "Gnerate Algorithm= " + generateAlgorithm
				+ '\n' + "SolveAlgorithm= " + solveAlgorithm + '\n' + "MaxThreads= " + maxThreads + '\n' + "UI Type= " + ui;
	}
	
}
