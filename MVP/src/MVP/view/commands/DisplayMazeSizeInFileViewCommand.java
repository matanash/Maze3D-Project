package MVP.view.commands;

import MVP.cliView.CommonMaze3DCLIView;
import MVP.view.View;

public class DisplayMazeSizeInFileViewCommand extends CommonDisplayCommand {

	public DisplayMazeSizeInFileViewCommand(View view) {
		super(view);
	}

	@Override
	public void display(Object obj) {
		((CommonMaze3DCLIView)this.view).getOut().write("The size of the maze in the file is: ");
		((CommonMaze3DCLIView)this.view).getOut().write(obj.toString());
		((CommonMaze3DCLIView)this.view).getOut().flush();

	}

}
