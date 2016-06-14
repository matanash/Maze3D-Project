package MVP.view;

import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import model.maze3d.Maze3d;
import model.maze3d.Position;


public class Maze3DDisplayerWidget extends Canvas {

	Maze3d maze3d;
	Position characterPosition;
	String crossSection;
	//boolean isSolved;

	/**
	 * C'tor - set the MazeDisplayer Widget with XZ Cross Section
	 * @param parent - parent of the widget
	 * @param style - style of the widget
	 */
	public Maze3DDisplayerWidget(Composite parent, int style) 
	{
		super(parent, style);
		this.crossSection = "XZ";	// Floor Section	
	}
	
	/**
	 * update the the character position
	 * @param position Character position
	 */
	public void setCharacterPosition(Position position) {
		this.characterPosition = position;
		redraw();
	}
	
	/**
	 * Set the Maze3D displayed by this widget
	 * @param maze - The maze to display
	 * @param characterPosition - The character starting position
	 */
	public void setMaze(Maze3d maze3d, Position characterPosition) {
		this.maze3d = maze3d;
		setCharacterPosition(characterPosition);
	}
	/**
	 * Set the Cross Section displayed by this widget
	 * @param crossSection -  View Cross Section
	 */
	
	public void setCrossSection(String crossSection) {
		this.crossSection = crossSection;
		redraw();
	}

	/**
	 * Display that the maze was solved successfully
	 * @param issolved - Whether or not the maze was solved
	 */
	/*public void setSolved(boolean isSolved) {
		this.isSolved = isSolved;
		redraw();
	}*/

}
