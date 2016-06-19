package MVP.presenter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Observable;

import MVP.model.Model;
import MVP.view.View;
import MVP.view.commands.DisplayMessageViewCommand;


public class MyPresenter extends  CommonPresenter {
	
	
	public MyPresenter(View view, Model model) {
		super(view, model);
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
									if (obs == view) 
									{
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
									else if (obs == model) 
									{
										if (modelCommandsMap.containsKey(connectedCommand.toString())) 
										{											
											modelCommandsMap.get(connectedCommand.toString()).doCommand(null);
											break;
										}
									}
									if (n < 0) 
									{
										view.display("Invalid command, you can use the \"help\" command to see the command-name list.", new DisplayMessageViewCommand(view));
									}
								}

						}
						else 
						{
							view.display("Invalid Object type. the type is: " + arg.getClass().getName()
									+ " and not String!", new DisplayMessageViewCommand(view));
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
	@Override
	public void initCommandsMaps() 
	{
		this.viewCommandsMap = new HashMap<String, Command>();
		this.viewCommandsMap.put("dir", new GetDirCommand(this));
		this.viewCommandsMap.put("generate 3d maze", new Generate3DMazeCommand(this));
		this.viewCommandsMap.put("display", new GetMazeCommand(this));
		this.viewCommandsMap.put("display start position", new GetMazeStartPositionCommand(this));
		this.viewCommandsMap.put("display goal position", new GetMazeGoalPositionCommand(this));
		this.viewCommandsMap.put("display cross section by y", new GetCrossSectionByYCommand(this));
		this.viewCommandsMap.put("display cross section by x", new GetCrossSectionByXCommand(this));
		this.viewCommandsMap.put("display cross section by z", new GetCrossSectionByZCommand(this));
		this.viewCommandsMap.put("save maze", new SaveMazeToFileCommand(this));
		this.viewCommandsMap.put("load maze", new LoadMazeFromFileCommand(this));
		this.viewCommandsMap.put("maze size", new GetMazeSizeInMemoryCommand(this));
		this.viewCommandsMap.put("file size", new GetMazeSizeInFileCommand(this));
		this.viewCommandsMap.put("solve", new SolveMazeCommand(this));
		this.viewCommandsMap.put("display solution", new GetSolutionCommand(this));
		this.viewCommandsMap.put("exit", new ExitCommand(this));
		this.viewCommandsMap.put("update properties",new UpdatePropertiesCommand(this));
		
		this.modelCommandsMap = new HashMap<String, Command>();
		this.modelCommandsMap.put("display_maze3d", new DisplayMazeCommand(this));
		this.modelCommandsMap.put("display_position", new DisplayMazeCommand(this));
		this.modelCommandsMap.put("display_solution", new DisplaySolutionCommand(this));
		this.modelCommandsMap.put("display_cross_section_by_y", new DisplayCrossSectionByCommand(this));
		this.modelCommandsMap.put("display_cross_section_by_x", new DisplayCrossSectionByCommand(this));
		this.modelCommandsMap.put("display_cross_section_by_z", new DisplayCrossSectionByCommand(this));
		this.modelCommandsMap.put("maze_size", new DisplayMazeSizeInMemoryCommand(this));
		this.modelCommandsMap.put("file_size", new DisplayMazeSizeInFileCommand(this));
		this.modelCommandsMap.put("display_message", new DisplayMessageCommand(this));
	}



}