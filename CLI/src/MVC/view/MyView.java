package MVC.view;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;

import MVC.controller.Command;
import MVC.controller.Controller;
import MVC.controller.MyController;

public class MyView implements View 
{
	private MyController ctrl;
	private CLI cli;
	
	public MyView(MyController ctrl)
	{
		this.setCtrl(ctrl);
		HashMap<String, Command> commandsMap = ctrl.initializeCommands();
		this.cli = new CLI(new BufferedReader(new InputStreamReader(System.in)),new PrintWriter(new OutputStreamWriter(System.out)),commandsMap);
		
	}
	@Override
	public void start() throws Exception {
		System.out.println("---------------Welcome to the MAZE 3D Game---------------");
		cli.start();

	}
	public Controller getCtrl() {
		return this.ctrl;
	}
	public void setCtrl(MyController ctrl) {
		this.ctrl = ctrl;
	}
	/**
	 * @return the cli
	 */
	public CLI getCli() {
		return this.cli;
	}
	/**
	 * @param cli the cli to set
	 */
	public void setCli(CLI cli) {
		this.cli = cli;
	}
	

}
