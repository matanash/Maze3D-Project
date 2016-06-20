package mvc.controller;
import mvc.model.Model;
import mvc.view.View;

/**
 * This method represent common command and used as place holder for command Interface
 * This method holds the view and the model references .
 * @author Matan Ashkenazi and Noee Cohen
 * @version - 1.0
 */
public abstract class CommonCommand implements Command 
{

	protected View view;
	protected Model model;
	public CommonCommand(View view, Model model) 
	{
		this.view = view;
		this.model= model;
	}
	/**
	 * This method execute the command
	 * @param args - the arguments required to execute the specific command
	 * @throws Exception - a Generic Exception
	 */
	@Override
	public abstract void doCommand(String[] args) throws Exception;
	/**
	 * This method explain what the command do and how the command and (display the syntax).
	 */
	@Override
	public abstract void help();

}
