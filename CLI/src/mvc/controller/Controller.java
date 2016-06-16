package mvc.controller;

import java.util.HashMap;

public interface Controller {
	/**
	 * 
	 * @param message
	 */
	public	void display(String message);
	/**
	 * @return
	 */
	public  HashMap<String, Command> initializeCommands();
}
