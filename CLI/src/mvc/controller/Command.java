package mvc.controller;

public interface Command{

	void doCommand(String[] args) throws Exception;
	void help();
	
}
