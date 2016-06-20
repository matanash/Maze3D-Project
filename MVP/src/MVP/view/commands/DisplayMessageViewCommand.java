package MVP.view.commands;

import MVP.view.View;

// TODO: Auto-generated Javadoc
/**
 * The Class DisplayMessageViewCommand.
 */
public class DisplayMessageViewCommand extends CommonDisplayCommand {

	/**
	 * Instantiates a new display message view command.
	 *
	 * @param view the view
	 */
	public DisplayMessageViewCommand(View view) {
		super(view);

	}

	/* (non-Javadoc)
	 * @see MVP.view.commands.CommonDisplayCommand#display(java.lang.Object)
	 */
	@Override
	public void display(Object obj) {
		this.view.displayMessage((String)obj);
		

	}

}
