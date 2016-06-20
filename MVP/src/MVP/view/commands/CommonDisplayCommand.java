package MVP.view.commands;

import MVP.view.View;

// TODO: Auto-generated Javadoc
/**
 * The Class CommonDisplayCommand.
 */
public abstract class CommonDisplayCommand implements DisplayCommand 
{

	/** The view. */
	protected View view ;
	
	/**
	 * Instantiates a new common display command.
	 *
	 * @param view the view
	 */
	public CommonDisplayCommand(View  view) {
		this.view = view ;
	}

	/**
	 * Gets the view.
	 *
	 * @return the view
	 */
	public View getView() {
		return this.view;
	}

	/**
	 * Sets the view.
	 *
	 * @param view the new view
	 */
	public void setView(View  view) {
		this.view = view;
	}
	
	/* (non-Javadoc)
	 * @see MVP.view.commands.DisplayCommand#display(java.lang.Object)
	 */
	public abstract void display(Object obj);

}
