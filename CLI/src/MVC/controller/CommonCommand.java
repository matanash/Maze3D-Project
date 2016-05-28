package MVC.controller;

import MVC.model.Model;
import MVC.view.View;

public abstract class CommonCommand implements Command {

	protected View view;
	protected Model model;
	public CommonCommand(View v, Model m) 
	{
		this.view = v;
		this.model= m;
	}
	@Override
	public abstract void doCommand(String[] args);

	@Override
	public abstract void help();

}
