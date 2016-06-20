package MVP.view.commands;

import MVP.cliView.CommonMaze3DCLIView;
import MVP.view.View;

// TODO: Auto-generated Javadoc
/**
 * The Class DisplayMazeSizeInFileViewCommand.
 */
public class DisplayMazeSizeInFileViewCommand extends CommonDisplayCommand {

	/**
	 * Instantiates a new display maze size in file view command.
	 *
	 * @param view the view
	 */
	public DisplayMazeSizeInFileViewCommand(View view) {
		super(view);
	}

	/* (non-Javadoc)
	 * @see MVP.view.commands.CommonDisplayCommand#display(java.lang.Object)
	 */
	@Override
	public void display(Object obj) {
		((CommonMaze3DCLIView)this.view).getOut().write("The size of the maze in the file is: ");
		((CommonMaze3DCLIView)this.view).getOut().write(obj.toString());
		((CommonMaze3DCLIView)this.view).getOut().flush();

	}

}
