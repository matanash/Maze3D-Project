package MVP.view.commands;

import MVP.view.CommonMaze3DCLIView;
import MVP.view.View;

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
