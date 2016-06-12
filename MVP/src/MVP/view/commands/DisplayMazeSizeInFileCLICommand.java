package MVP.view.commands;

import MVP.view.CommonMaze3DCLIView;
import MVP.view.View;

public class DisplayMazeSizeInFileCLICommand extends CommonDisplayCLICommand {

	public DisplayMazeSizeInFileCLICommand(View view) {
		super(view);
	}

	@Override
	public void display(Object obj) {
		((CommonMaze3DCLIView)this.view).getOut().write("The size of the maze in the file is: ");
		((CommonMaze3DCLIView)this.view).getOut().write((int)obj);
		((CommonMaze3DCLIView)this.view).getOut().flush();

	}

}
