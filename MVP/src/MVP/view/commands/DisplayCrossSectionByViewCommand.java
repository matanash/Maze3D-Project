package MVP.view.commands;

import MVP.view.View;


public class DisplayCrossSectionByViewCommand extends CommonDisplayCommand {

	public DisplayCrossSectionByViewCommand(View view) {
		super(view);
	}

	@Override
	public void display(Object obj) {
		this.view.displayCrossSectionByCommand((int[][])obj);
	}

}
