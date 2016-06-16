package MVP.view.commands;

import MVP.view.View;
import model.maze3d.Maze3d;

public class DisplayMaze3DViewCommand extends CommonDisplayCommand {

	public DisplayMaze3DViewCommand(View view) {
		super(view);
	}

	@Override
	public void display(Object obj) {
		this.view.displayMaze((Maze3d)obj);

	}

}
