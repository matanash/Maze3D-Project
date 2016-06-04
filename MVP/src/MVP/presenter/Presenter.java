package MVP.presenter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import MVP.model.Model;
import MVP.view.View;


public class Presenter implements Observer {
	View view;
	Model model;
	
	HashMap<String, Command> modelCommandsMap;
	HashMap<String, Command> viewCommandsMap;

	public Presenter(View view, Model model) {

		this.view = view;
		this.model = model;
		initCommandsMaps();
	}

	@Override
	public void update(Observable obs, Object arg) 
	{
			new Thread(new Runnable() 
			{
				@Override
				public void run() 
				{
					try 
					{
						if (arg.getClass() == String.class) 
						{
							if (obs == view) 
							{
								int n;
								String commandName = (String) arg;
								String[] splitedCommand = commandName.split("\\s+");
								for (n = splitedCommand.length - 1; n >= 0; n--) 
								{
									StringBuilder connectedCommand = new StringBuilder();
									for (int i = 0; i <= n; i++) 
									{
										if (i != n)
											connectedCommand.append(splitedCommand[i] + " ");
										else
											connectedCommand.append(splitedCommand[i]);
									}
									if (viewCommandsMap.containsKey(connectedCommand.toString())) 
									{
										String[] args = new String[splitedCommand.length - n - 1];
										for (int j = 0; j < args.length; j++) 
										{
											args[j] = splitedCommand[n + 1 + j];
										}
										viewCommandsMap.get(connectedCommand.toString()).doCommand(args);
										break;
									}
								}
								if (n < 0) 
								{
									view.displayMessage(
											"Invalid command, you can use the \"help\" command to see the command-name list.");
								}
							}
							else if (obs == model) 
							{
								String commandName = (String) arg;
								Command command = modelCommandsMap.get(commandName);
								command.doCommand(null);
							}
							
						}
						else 
						{
							view.displayMessage("Invalid Object type. the type is: " + arg.getClass().getName()
									+ " and not String!");
						}
						
					}catch (IOException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}, "commandProccessingLoop").start();

		}
	/**
	 * @return Initiated HashMap that maps Strings to Commands.
	 */
	public void initCommandsMaps() 
	{
		this.viewCommandsMap = new HashMap<String, Command>();
		viewCommandsMap.put("generate 3d maze", new Generate3DMazeCommand(this.view, this.model));
		viewCommandsMap.put("display", new DisplayMazeCommand(this.view,this.model));
		viewCommandsMap.put("display cross section by y", new DisplayCrossSectionByYCommand(this.view, this.model));
		viewCommandsMap.put("display cross section by x", new DisplayCrossSectionByXCommand(this.view, this.model));
		viewCommandsMap.put("display cross section by z", new DisplayCrossSectionByZCommand(this.view, this.model));
		viewCommandsMap.put("save maze", new SaveMazeToFileCommand(this.view, this.model));
		viewCommandsMap.put("load maze", new LoadMazeFromFileCommand(this.view, this.model));
		viewCommandsMap.put("maze size", new DisplayMazeSizeCommand(this.view, this.model));
		viewCommandsMap.put("file size", new DisplayFileSizeCommand(this.view, this.model));
		viewCommandsMap.put("solve", new SolveMazeCommand(this.view,this.model));
		viewCommandsMap.put("display solution", new DisplaySolutionCommand(this.view, this.model));
		viewCommandsMap.put("exit", new ExitCommand(this.view,this.model));
		
		this.modelCommandsMap = new HashMap<String, Command>();
		modelCommandsMap.put("display_message", new DisplayMessageCommand(this.view,this.model));
		
	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	/**
	 * @return the modelCommandsMap
	 */
	public HashMap<String, Command> getModelCommandsMap() {
		return modelCommandsMap;
	}

	/**
	 * @param modelCommandsMap the modelCommandsMap to set
	 */
	public void setModelCommandsMap(HashMap<String, Command> modelCommandsMap) {
		this.modelCommandsMap = modelCommandsMap;
	}

	/**
	 * @return the viewCommandsMap
	 */
	public HashMap<String, Command> getViewCommandsMap() {
		return viewCommandsMap;
	}

	/**
	 * @param viewCommandsMap the viewCommandsMap to set
	 */
	public void setViewCommandsMap(HashMap<String, Command> viewCommandsMap) {
		this.viewCommandsMap = viewCommandsMap;
	}

}