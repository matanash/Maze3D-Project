package MVP.view.commands;

import MVP.view.CommonMaze3DCLIView;
import MVP.view.View;


public class DisplayCrossSectionByCLICommand extends CommonDisplayCLICommand {

	public DisplayCrossSectionByCLICommand(View view) {
		super(view);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void display(Object obj) {
		try {
			((CommonMaze3DCLIView)this.view).getOut().write("The requested Maze Section is: " + '\n');
			int [][] crossSection = (int[][])obj;
			for (int i = 0; i < crossSection.length; i++) {
				for (int j = 0; j < crossSection[0].length; j++) {
					((CommonMaze3DCLIView)this.view).getOut().write(crossSection[i][j] + " ");
				}
				((CommonMaze3DCLIView)this.view).getOut().append('\n');
			}
			((CommonMaze3DCLIView)this.view).getOut().append('\n');
			((CommonMaze3DCLIView)this.view).getOut().flush();
		} catch (Exception e) {
			((CommonMaze3DCLIView)this.view).getOut().println(e.getMessage());
		}
	}

}
