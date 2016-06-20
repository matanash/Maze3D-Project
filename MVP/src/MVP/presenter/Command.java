package MVP.presenter;

// TODO: Auto-generated Javadoc
/**
 * The Interface Command.
 */
public interface Command{

	/**
	 * Do command.
	 *
	 * @param args the args
	 * @throws Exception the exception
	 */
	void doCommand(String[] args) throws Exception;
	
	/**
	 * Help.
	 */
	void help();
	
}
