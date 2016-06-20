package MVP.view.commands;

import MVP.view.View;
import model.maze3d.Maze3d;

// TODO: Auto-generated Javadoc
/**
 * The Class DisplayMaze3DViewCommand.
 */
public class DisplayMaze3DViewCommand extends CommonDisplayCommand {

	/**
	 * Instantiates a new display maze 3 D view command.
	 *
	 * @param view the view
	 */
	public DisplayMaze3DViewCommand(View view) {
		super(view);
	}

	/* (non-Javadoc)
	 * @see MVP.view.commands.CommonDisplayCommand#display(java.lang.Object)
	 */
	@Override
	public void display(Object obj) {
		this.view.displayMaze((Maze3d)obj);

	}

}
