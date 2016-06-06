package MVP.presenter;


/**
 * Implementing what every Command must have.
 */

public abstract class CommonCommand implements Command 
{
	protected Presenter presenter;
	public CommonCommand(Presenter p) 
	{
		this.presenter =p;
	}
	
	//Remains abstract
	@Override
	public abstract void doCommand(String[] args) throws Exception;

	@Override
	public abstract void help();

}
