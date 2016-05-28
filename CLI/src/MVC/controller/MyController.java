package MVC.controller;

import java.util.HashMap;

import MVC.model.Model;
import MVC.view.View;

public class MyController implements Controller 
{

	private View view;
	private Model model;
	HashMap<String, Command> commands;
	
	public MyController() {}
	
	public View getView() 
	{
		return this.view;
	}

	public void setView(View view) 
	{
		this.view = view;
	}

	public Model getModel() 
	{
		return this.model;
	}

	public void setModel(Model model) 
	{
		this.model = model;
	}
	
	/**
	 * @return the commands
	 */
	public HashMap<String, Command> getCommands() {
		return this.commands;
	}
	
	/**
	 * @param commands the commands to set
	 */
	public void setCommands() {
		this.commands = initializeCommands();
		
	}
	

	public HashMap<String, Command> initializeCommands() 
	{
		HashMap<String, Command> commandsMap = new HashMap<String, Command>();
		commandsMap.put("dir", new GetDirCommand(this.view,this.model));
		commandsMap.put("generate 3d maze", new Generate3DMazeCommand(this.view,this.model));
		commandsMap.put("display", new DisplayMazeCommand(this.view, this.model));
		commandsMap.put("display cross section by Y", new DisplayCrossSectionByYCommand(this.view, this.model));
		commandsMap.put("display cross section by X", new DisplayCrossSectionByXCommand(this.view, this.model));
		commandsMap.put("display cross section by Z", new DisplayCrossSectionByZCommand(this.view, this.model));
		commandsMap.put("save maze", new SaveMazeToFileCommand(this.view, this.model));
		commandsMap.put("load maze", new LoadMazeFromFileCommand(this.view, this.model));
		commandsMap.put("maze size", new DisplayMazeSizeCommand(this.view, this.model));
		commandsMap.put("file size", new DisplayFileSizeCommand(this.view, this.model));
		commandsMap.put("solve", new SolveMazeCommand(this.view,this.model));
		commandsMap.put("display solution", new DisplaySolutionCommand(this.view, this.model));
		commandsMap.put("exit", new ExitCommand(this.view,this.model));
		return commandsMap;
	}

	@Override
	public void display(String message) {
		// TODO Auto-generated method stub
		
	}

}