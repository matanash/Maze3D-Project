package mvp.view.commands;

import model.maze3d.Maze3d;
import mvp.cliView.CommonMaze3DCLIView;
import mvp.view.View;

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
