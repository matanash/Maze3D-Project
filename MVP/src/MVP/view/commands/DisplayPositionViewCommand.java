package MVP.view.commands;

import MVP.view.View;
import model.maze3d.Position;

public class DisplayPositionViewCommand extends CommonDisplayCommand {

	public DisplayPositionViewCommand(View view) {
		super(view);
	}

	@Override
	public void display(Object obj) {
		this.view.displayPosition((Position)obj);

	}

}
