package mvc.controller;
/**
 * This Interface Defines what a command functionality
 * @author  - Matan Ashkenazi and Noee Cohen
 * @version - 1.0
 */
public interface Command{
	/**
	 * This method execute the command
	 * @param args - the arguments required to execute the specific command
	 * @throws Exception - a Generic Exception
	 */
	void doCommand(String[] args) throws Exception;
	/**
	 * This method explain what the command do and how the command and (display the syntax).
	 */
	void help();
	
}
