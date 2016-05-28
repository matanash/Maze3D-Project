package boot;

import MVC.controller.MyController;
import MVC.model.MyModel;
import MVC.view.MyView;

public class Run 
{

	public static void main(String[] args)
	{
		try 
		{
			MyController ctrl = new MyController();
			MyModel model= new MyModel(ctrl);
			MyView view = new MyView(ctrl);
			ctrl.setModel(model);
			ctrl.setView(view);
			ctrl.setCommands();
			view.getCli().setCommands(ctrl.getCommands());
			view.start();
		} catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
