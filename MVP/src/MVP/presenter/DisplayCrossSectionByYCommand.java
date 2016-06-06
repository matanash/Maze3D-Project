package MVP.presenter;

public class DisplayCrossSectionByYCommand extends CommonCommand {

	public DisplayCrossSectionByYCommand(Presenter p) {
		super(p);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length != 2) {
			presenter.getView().displayMessage("Invalid arguments");
			this.help();
		} else {
			try {
				presenter.getView().displayCrossSectionByCommand(
						presenter.getModel().displayCrossSectionByY(Integer.parseInt(args[0]), args[1]));
				presenter.getView().displayMessage(presenter.getModel().getMessage());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void help() {
		System.out.println("Displays cross section by <ySection> for maze3d <name> ." + '\n' + '\t'
				+ "--> Syntax: display cross section by Y <ySection> <name>");

	}

}
