package mvp.view;

import mvp.presenter.Properties;
import mvp.view.commands.DisplayCLICommand;

public interface View {
	/**
	 * start the program
	 */
	
	void startView() throws Exception;
	
	void exitView();
	
	void setProperties(Properties prop);
	
	void display(Object obj , DisplayCLICommand dc);
	
}
