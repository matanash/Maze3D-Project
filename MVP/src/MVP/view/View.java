package MVP.view;

import java.io.PrintWriter;

import MVP.presenter.Properties;
import MVP.view.commands.DisplayCLICommand;

public interface View {
	/**
	 * start the program
	 */
	
	void startView() throws Exception;
	
	void exitView();
	
	PrintWriter getOut();
	
	void display(Object obj , DisplayCLICommand dc);
	
	void setProperties(Properties prop);
	
	
}
