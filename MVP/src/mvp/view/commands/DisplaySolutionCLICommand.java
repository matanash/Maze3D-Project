package mvp.view.commands;

import algorithms.search.Solution;
import mvp.cliView.CommonMaze3DCLIView;
import mvp.view.View;

public class DisplaySolutionCLICommand extends CommonDisplayCLICommand {

	public DisplaySolutionCLICommand(View view) {
		super(view);
	}

	@Override
	public void display(Object obj) {
		((CommonMaze3DCLIView)this.view).getOut().write("The requested Solution is: " + '\n');
		((CommonMaze3DCLIView)this.view).getOut().write(((Solution)obj).toString());
		((CommonMaze3DCLIView)this.view).getOut().flush();

	}

}
