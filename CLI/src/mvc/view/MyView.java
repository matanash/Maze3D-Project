package mvc.view;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;

import mvc.controller.Command;
import mvc.controller.Controller;
import mvc.controller.MyController;
/**
 * 
 * @author Matan Ashkenazi and Noee Cohen
 * @version - 1.0
 */
public class MyView implements View 
{
	private MyController ctrl;
	private CLI cli;
	/**
	 * C'tor - this constructor initialize the MyView Concrete object with controller object and initialize the CLI Commands HashMap
	 * @param ctrl - the controller
	 * 
	 */
	public MyView(MyController ctrl)
	{
		this.setCtrl(ctrl);
		HashMap<String, Command> commandsMap = ctrl.initializeCommands();
		this.cli = new CLI(new BufferedReader(new InputStreamReader(System.in)),new PrintWriter(new OutputStreamWriter(System.out)),commandsMap);
		
	}
	@Override
	public void start() throws Exception {
		cli.run();

	}
	
	/**
	 * get the controller data member object
	 * @return - controller object
	 */
	public Controller getCtrl() {
		return this.ctrl;
	}
	
	/**
	 * set the controller data member object
	 * @param ctrl - the controller to set
	 */
	public void setCtrl(MyController ctrl) {
		this.ctrl = ctrl;
	}
	
	/** get the CLI data member object
	 * @return the CLI - the CLI object
	 */
	public CLI getCli() {
		return this.cli;
	}
	/**
	 * set the CLI data member object
	 * @param cli -  the CLI to set
	 */
	public void setCli(CLI cli) {
		this.cli = cli;
	}

	/**
	 * This method get the output stream of the CLI object
	 * @return - the CLI PrintWriter object
	 */
	public PrintWriter getOut() {
		return this.cli.getOut();
	}
	@Override
	public void display(String string) {}
	


}
