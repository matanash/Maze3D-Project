package mvc.controller;

import java.util.HashMap;
/**
 * Defines what every Controller type must implement and Controller interface functionality .
 * @author Matan Ashkenazi and Noee Cohen
 * @version - 1.0
 */
public interface Controller {
	/**
	 * initialize the commands HashMap that maps between the command name and the Command object
	 * @return - HashMap represents the view and model possible executable commands
	 */
	public  HashMap<String, Command> initializeCommands();
}
