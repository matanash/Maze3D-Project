package MVP.view.commands;

import MVP.view.View;


// TODO: Auto-generated Javadoc
/**
 * The Class DisplayCrossSectionByViewCommand.
 */
public class DisplayCrossSectionByViewCommand extends CommonDisplayCommand {

	/**
	 * Instantiates a new display cross section by view command.
	 *
	 * @param view the view
	 */
	public DisplayCrossSectionByViewCommand(View view) {
		super(view);
	}

	/* (non-Javadoc)
	 * @see MVP.view.commands.CommonDisplayCommand#display(java.lang.Object)
	 */
	@Override
	public void display(Object obj) {
		this.view.displayCrossSectionByCommand((int[][])obj);
	}

}
