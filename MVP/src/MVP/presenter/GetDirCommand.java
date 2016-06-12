package MVP.presenter;

import java.io.File;

import MVP.view.commands.DisplayMessageCLICommand;

public class GetDirCommand extends CommonCommand {

	public GetDirCommand(Presenter presenter) {
		super(presenter);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length != 1) {
			presenter.getView().display("Invalid arguments", new DisplayMessageCLICommand(this.presenter.getView()));
			this.help();
		} else {
			String path = args[0];
			File file = new File(path);
			if (file.isDirectory()){
				this.presenter.getModel().dir(path);
			}
			else
				presenter.getView().display("There is no such directory", new DisplayMessageCLICommand(this.presenter.getView()));
		}

	}

	@Override
	public void help() {
		this.presenter.getView().display("Displays a list of files and subdirectories in a directory. " + '\n' + '\t' + "--> Syntax: dir <path>", new DisplayMessageCLICommand(this.presenter.getView()));

	}

}
