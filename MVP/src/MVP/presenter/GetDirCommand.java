package MVP.presenter;

import java.io.File;

public class GetDirCommand extends CommonCommand {

	public GetDirCommand(Presenter p) {
		super(p);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length != 1) {
			this.presenter.getView().displayMessage("Invalid arguments");
			this.help();
		} else {
			String path = args[0];
			File file = new File(path);
			if (file.isDirectory()){
				this.presenter.getModel().dir(path);
			}
				
			
			else
				this.presenter.getView().displayMessage("There is no such directory");
		}

	}

	@Override
	public void help() {
		this.presenter.getView().displayMessage(
				"Displays a list of files and subdirectories in a directory. " + '\n' + '\t' + "--> Syntax: dir <path>");

	}

}
