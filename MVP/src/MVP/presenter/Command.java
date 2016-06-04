package MVP.presenter;

public interface Command{

	void doCommand(String[] args) throws Exception;
	void help();
	
}
