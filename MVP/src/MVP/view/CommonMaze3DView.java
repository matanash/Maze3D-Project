package MVP.view;

import java.util.Observable;

import MVP.view.commands.DisplayCommand;

public abstract class CommonMaze3DView extends Observable implements View {
	
	public void display(Object obj , DisplayCommand dc)
	{
		dc.display(obj);
	}

}
