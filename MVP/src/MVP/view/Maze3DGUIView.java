package MVP.view;

import model.maze3d.Maze3d;
import model.maze3d.Position;

public class Maze3DGUIView extends CommonMaze3DGUIView {
	Maze3d maze;
	Position characterPosition;
	String viewCrossSection;
	Maze3DWindow mazeWindow;
	//ObjectInitializer objectInitializer;
	Thread solutionDisplayerThread;
	boolean isSolved;
}
