package MVP.presenter;

import MVP.model.Model;
import MVP.view.View;

public class DisplayMessageCommand extends CommonCommand {

	public DisplayMessageCommand(View v,Model m) {
		super(v,m);
	}
	
	@Override
	public void doCommand(String[] args) throws Exception {
		view.displayMessage(model.getMessage());

	}

	@Override
	public void help() {
		// TODO Auto-generated method stub

	}

}
