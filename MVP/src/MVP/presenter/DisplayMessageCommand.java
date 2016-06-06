package MVP.presenter;

public class DisplayMessageCommand extends CommonCommand {

	public DisplayMessageCommand(Presenter p) {
		super(p);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		presenter.getView().displayMessage(presenter.getModel().getMessage());

	}

	@Override
	public void help() {
		// TODO Auto-generated method stub

	}

}
