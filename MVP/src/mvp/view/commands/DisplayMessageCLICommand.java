package mvp.view.commands;

import mvp.cliView.CommonMaze3DCLIView;
import mvp.view.View;

public class DisplayMessageCLICommand extends CommonDisplayCLICommand {

	public DisplayMessageCLICommand(View view) {
		super(view);

	}

	@Override
	public void display(Object obj) {
		((CommonMaze3DCLIView)this.view).getOut().write((String)obj);
		((CommonMaze3DCLIView)this.view).getOut().flush();
		

	}

}
