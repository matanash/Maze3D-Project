package mvc.boot;

import mvc.controller.MyController;
import mvc.model.MyModel;
import mvc.view.MyView;
/**
 * This Class demonstrate MVC Pattern Architecture 
 * @author  - Matan Ashkenazi and Noee Cohen
 * @version - 1.0
 */
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
			e.printStackTrace();
		}
	}

}
