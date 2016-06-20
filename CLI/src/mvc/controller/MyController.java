package mvc.controller;

import java.util.HashMap;

import mvc.model.Model;
import mvc.view.MyView;
/**
 * This class represents concrete Controller and responsible to link between the Model and the View facades .
 * @author Matan Ashkenazi and Noee Cohen
 * @version - 1.0
 */
public class MyController implements Controller 
{

	private MyView view;
	private Model model;
	HashMap<String, Command> commands;

	public MyController() {}
	/**
	 * This method get the view object
	 * @return - reference to the view
	 */
	public MyView getView() 
	{
		return this.view;
	}
	/**
	 * This method set the model object
	 * @param - the view to set
	 */
	public void setView(MyView view) 
	{
		this.view = view;
	}
	/**
	 * This method get the model object
	 * @return - reference to the model
	 */
	public Model getModel() 
	{
		return this.model;
	}
	/**
	 * This method set the model object
	 * @param - the model to set
	 */
	public void setModel(Model model) 
	{
		this.model = model;
	}
	
	/**
	 * This method get the commands HashMap the Controller hold
	 * @return -the commands HashMap
	 */
	public HashMap<String, Command> getCommands() {
		return this.commands;
	}
	
	/**
	 * This method set the commands HashMap the controller hold
	 * @param commands the commands to set
	 */
	public void setCommands() {
		this.commands = initializeCommands();
		this.view.getCli().setCommands(commands);
	}
	
	/**
	 * This method initialize the HashMap of the possible Commands
	 * @return - the commands HashMap after initialize
	 */
	public HashMap<String, Command> initializeCommands() 
	{
		HashMap<String, Command> commandsMap = new HashMap<String, Command>();
		
		commandsMap.put("dir", new GetDirCommand(this.view,this.model));
		commandsMap.put("generate 3d maze", new Generate3DMazeCommand(this.view,this.model));
		commandsMap.put("display", new DisplayMazeCommand(this.view, this.model));
		commandsMap.put("display cross section by y", new DisplayCrossSectionByYCommand(this.view, this.model));
		commandsMap.put("display cross section by x", new DisplayCrossSectionByXCommand(this.view, this.model));
		commandsMap.put("display cross section by z", new DisplayCrossSectionByZCommand(this.view, this.model));
		commandsMap.put("save maze", new SaveMazeToFileCommand(this.view, this.model));
		commandsMap.put("load maze", new LoadMazeFromFileCommand(this.view, this.model));
		commandsMap.put("maze size", new DisplayMazeSizeCommand(this.view, this.model));
		commandsMap.put("file size", new DisplayFileSizeCommand(this.view, this.model));
		commandsMap.put("solve", new SolveMazeCommand(this.view,this.model));
		commandsMap.put("display solution", new DisplaySolutionCommand(this.view, this.model));
		commandsMap.put("exit", new ExitCommand(this.view,this.model));
		return commandsMap;
	}

}