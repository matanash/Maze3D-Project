package mvc.boot;

import mvc.controller.MyController;
import mvc.model.MyModel;
import mvc.view.MyView;

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
			view.start();
			
		} catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
