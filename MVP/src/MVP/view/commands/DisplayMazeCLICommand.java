package MVP.view.commands;

import MVP.view.CommonMaze3DCLIView;
import MVP.view.View;
import model.maze3d.Maze3d;

public class DisplayMazeCLICommand extends CommonDisplayCLICommand {

	public DisplayMazeCLICommand(View view) {
		super(view);
	}

	@Override
	public void display(Object obj) {
		
		((CommonMaze3DCLIView)this.view).getOut().write("The requested maze is: " + '\n');
		((CommonMaze3DCLIView)this.view).getOut().write(((Maze3d)obj).toString());
		((CommonMaze3DCLIView)this.view).getOut().flush();
	}

}
