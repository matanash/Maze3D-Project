package mvp.guiView.Widgets;

import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import algorithms.search.Solution;
import model.maze3d.Maze3d;
import model.maze3d.Position;
import model.maze3d.domains.Maze3dState;

public abstract class MazeDisplayer extends Canvas {

	protected Maze3d mazeData; // current displayed maze
	protected Position charPosition; // current character position
	protected Solution solution; // available solution
	
	public MazeDisplayer(Composite parent, int style) {
		super(parent, style);
		charPosition = new Position(0,0,0);
		solution = new Solution();
	}
	
	public void setMazeData(Maze3d mazeData) {
		this.mazeData = mazeData;

		Display.getDefault().syncExec(new Runnable() {
			public void run() { // redraws the MazeDisplyer.
				redraw();
			}
		});
	}
	
	/**
	 * Receiving new updated character Position, redraws the widget, and
	 * removing that Position from the available Solution<Position>.
	 * 
	 * @param position
	 *            - new Position to update the current displayed character
	 *            position.
	 */
	public void setCharPosition(Position position) {
		this.charPosition = position;

		// if the user have reached a position in the solution it will remove that position from the solution.
		if ((solution != null) && (solution.contains(new Maze3dState(position)))) 
			solution.remove(new Maze3dState(position));

		Display.getDefault().syncExec(new Runnable() {
			public void run() { // redraws the MazeDisplyer.
				redraw();
			}
		});
	}

	/**
	 * If a request to present a solution was received will redraw the widget
	 * with the updated solution.
	 * 
	 * @param solution
	 *            - ArrayList of Position.
	 */
	public void setSolution(Solution solution) {
		this.solution = solution;

		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				redraw(); // redraws the widget with updated solution.
			}
		});
	}
}
