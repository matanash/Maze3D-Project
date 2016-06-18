package MVP.presenter;


/**
 * Implementing what every Command must have.
 */

public abstract class CommonCommand implements Command 
{
	protected MyPresenter myPresenter;
	public CommonCommand(MyPresenter presenter) 
	{
		this.myPresenter =presenter;
	}
	
	//Remains abstract
	@Override
	public abstract void doCommand(String[] args) throws Exception;

	@Override
	public abstract void help();

}
