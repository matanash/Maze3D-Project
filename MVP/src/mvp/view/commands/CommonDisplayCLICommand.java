package mvp.view.commands;

import mvp.view.View;

public abstract class CommonDisplayCLICommand implements DisplayCLICommand 
{

	View view ;
	
	public CommonDisplayCLICommand(View  view) {
		this.view = view ;
	}

	public View getView() {
		return this.view;
	}

	public void setView(View  view) {
		this.view = view;
	}
	
	public abstract void display(Object obj);

}
