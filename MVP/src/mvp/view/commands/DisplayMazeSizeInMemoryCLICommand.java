package mvp.view.commands;

import mvp.cliView.CommonMaze3DCLIView;
import mvp.view.View;

public class DisplayMazeSizeInMemoryCLICommand extends CommonDisplayCLICommand {

	public DisplayMazeSizeInMemoryCLICommand(View view) {
		super(view);
	}

	@Override
	public void display(Object obj) {
		((CommonMaze3DCLIView)this.view).getOut().write("The size of the maze in the file is ");
		((CommonMaze3DCLIView)this.view).getOut().write(obj.toString());
		((CommonMaze3DCLIView)this.view).getOut().write(" bytes .");
		((CommonMaze3DCLIView)this.view).getOut().flush();

	}

}
