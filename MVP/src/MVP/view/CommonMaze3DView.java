package MVP.view;

import java.util.Observable;

import MVP.view.commands.DisplayCommand;

// TODO: Auto-generated Javadoc
/**
 * The Class CommonMaze3DView.
 */
public abstract class CommonMaze3DView extends Observable implements View {
	
	/* (non-Javadoc)
	 * @see MVP.view.View#display(java.lang.Object, MVP.view.commands.DisplayCommand)
	 */
	public void display(Object obj , DisplayCommand dc)
	{
		dc.display(obj);
	}

}
