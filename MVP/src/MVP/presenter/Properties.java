package MVP.presenter;

import java.io.Serializable;

public class Properties implements Serializable{

	private static final long serialVersionUID = 1L;
	protected boolean debugMode; 		// on or off debug mode
	protected int mazeMaxHeight; 		// Max Maze Height
	protected int mazeMaxLength; 		// Max Maze Length
	protected int mazeMaxWidth; 		// Max Maze Width
	protected String generateAlgorithm; // Maze generating algorithm.
	protected String solveAlgorithm; 	// maze solving algorithm.
	protected int maxThreads; 			// Max threads In ThreadPool
	protected String ui; 				// which UI to present.
	
	public Properties() {
		setDefaults();
	}
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
	public boolean isDebugMode() {
		return debugMode;
	}
	public void setDebugMode(boolean debugMode) {
		this.debugMode = debugMode;
	}
	public int getMazeMaxHeight() {
		return mazeMaxHeight;
	}
	public void setMazeMaxHeight(int mazeMaxHeight) {
		this.mazeMaxHeight = mazeMaxHeight;
	}
	public int getMazeMaxLength() {
		return mazeMaxLength;
	}
	public void setMazeMaxLength(int mazeMaxLength) {
		this.mazeMaxLength = mazeMaxLength;
	}
	public int getMazeMaxWidth() {
		return mazeMaxWidth;
	}
	public void setMazeMaxWidth(int mazeMaxWidth) {
		this.mazeMaxWidth = mazeMaxWidth;
	}
	public String getGenerateAlgorithm() {
		return generateAlgorithm;
	}
	public void setGenerateAlgorithm(String generateAlgorithm) {
		this.generateAlgorithm = generateAlgorithm;
	}
	public String getSolveAlgorithm() {
		return solveAlgorithm;
	}
	public void setSolveAlgorithm(String solveAlgorithm) {
		this.solveAlgorithm = solveAlgorithm;
	}
	public int getMaxThreads() {
		return maxThreads;
	}
	public void setMaxThreads(int maxThreads) {
		this.maxThreads = maxThreads;
	}
	public String getUi() {
		return ui;
	}
	public void setUi(String ui) {
		this.ui = ui;
	}
	@Override
	public String toString() {
		return "Properties" + '\n' + "DebugMode= " + debugMode + '\n' +"MazeMaxHeight= " + mazeMaxHeight + '\n' + "MazeMaxLength= "
				+ mazeMaxLength + '\n' + "MazeMaxWidth= " + mazeMaxWidth + '\n' + "Gnerate Algorithm= " + generateAlgorithm
				+ '\n' + "SolveAlgorithm= " + solveAlgorithm + '\n' + "MaxThreads= " + maxThreads + '\n' + "UI Type= " + ui;
	}
	
}
