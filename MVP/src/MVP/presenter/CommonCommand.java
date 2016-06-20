package MVP.presenter;


// TODO: Auto-generated Javadoc
/**
 * Implementing what every Command must have.
 */

public abstract class CommonCommand implements Command 
{
	
	/** The my presenter. */
	protected MyPresenter myPresenter;
	
	/**
	 * Instantiates a new common command.
	 *
	 * @param presenter the presenter
	 */
	public CommonCommand(MyPresenter presenter) 
	{
		this.myPresenter =presenter;
	}
	
	/* (non-Javadoc)
	 * @see MVP.presenter.Command#doCommand(java.lang.String[])
	 */
	//Remains abstract
	@Override
	public abstract void doCommand(String[] args) throws Exception;

	/* (non-Javadoc)
	 * @see MVP.presenter.Command#help()
	 */
	@Override
	public abstract void help();

}
