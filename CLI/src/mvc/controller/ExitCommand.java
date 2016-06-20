package mvc.controller;

import mvc.model.Model;
import mvc.view.View;

/**
 * This class represents Exit Command and resposible of orderly closure of the
 * Program
 * 
 * @author Matan Ashkenazi and Noee Cohen
 * @version - 1.0
 */
public class ExitCommand extends CommonCommand {

	public ExitCommand(View view, Model model) {
		super(view, model);

	}

	@Override
	public void doCommand(String[] args) throws Exception {
		this.view.getOut().println("|----------------------------|");
		this.view.getOut().flush();
		this.view.getOut().println("|   Thank you for playing!   |");
		this.view.getOut().flush();
		this.view.getOut().println("|----------------------------|");
		this.view.getOut().flush();
		model.exitModel();
	}

	@Override
	public void help() {
		this.view.getOut().println("Exit and close the maze game" + '\n' + '\t' + "--> exit");
		this.view.getOut().flush();

	}

}
