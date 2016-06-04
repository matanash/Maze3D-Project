package MVP.presenter;

import MVP.model.Model;
import MVP.view.View;

public class DisplayCrossSectionByZCommand extends CommonCommand {

	public DisplayCrossSectionByZCommand(View v, Model m) {
		super(v, m);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length != 2) {
			view.displayMessage("Invalid arguments");
			this.help();
		} else {
			try {
				view.displayCrossSectionByCommand(model.displayCrossSectionByZ(Integer.parseInt(args[0]), args[1]));
				view.displayMessage(model.getMessage());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void help() {
		System.out.println("Displays cross section by <zSection> for maze3d <name> ." + '\n' + '\t'
				+ "--> Syntax: displays cross section by Z <zSection> <name> .");
	}

}
