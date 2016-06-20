package MVP.view.commands;

import MVP.view.View;
import model.maze3d.Position;

// TODO: Auto-generated Javadoc
/**
 * The Class DisplayPositionViewCommand.
 */
public class DisplayPositionViewCommand extends CommonDisplayCommand {

	/**
	 * Instantiates a new display position view command.
	 *
	 * @param view the view
	 */
	public DisplayPositionViewCommand(View view) {
		super(view);
	}

	/* (non-Javadoc)
	 * @see MVP.view.commands.CommonDisplayCommand#display(java.lang.Object)
	 */
	@Override
	public void display(Object obj) {
		this.view.displayPosition((Position)obj);

	}

}
