package MVC.controller;

public interface Command{

	void doCommand(String[] args) throws Exception;
	void help();
	
}
