package MVP.view.commands;

import MVP.view.View;

public class DisplayMessageViewCommand extends CommonDisplayCommand {

	public DisplayMessageViewCommand(View view) {
		super(view);

	}

	@Override
	public void display(Object obj) {
		this.view.displayMessage((String)obj);
		

	}

}
