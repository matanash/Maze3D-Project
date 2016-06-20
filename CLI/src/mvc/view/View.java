package mvc.view;

import java.io.PrintWriter;

/**
 * This Interface define what every view type must implement and the view functionality
 * @author Matan Ashkenazi and Noee Cohen
 * @version - 1.0
 */
public interface View {
	
	/**
	 * Start the CLI
	 */
	void start() throws Exception;
	
	/**
	 * Display string to the view facade
	 * @param string -the string to view
	 */
	void display(String string);
	
	/**
	 * This Method return the OutputStream of the view facade
	 * @return
	 */
	PrintWriter getOut() ;
}
