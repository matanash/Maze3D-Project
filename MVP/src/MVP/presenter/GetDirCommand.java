package MVP.presenter;

import java.io.File;

import MVP.view.commands.DisplayMessageViewCommand;

public class GetDirCommand extends CommonCommand {

	public GetDirCommand(MyPresenter myPresenter) {
		super(myPresenter);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length != 1) {
			myPresenter.getView().display("Invalid arguments", new DisplayMessageViewCommand(this.myPresenter.getView()));
			this.help();
		} else {
			String path = args[0];
			File file = new File(path);
			if (file.isDirectory()){
				this.myPresenter.getModel().dir(path);
			}
			else
				myPresenter.getView().display("There is no such directory", new DisplayMessageViewCommand(this.myPresenter.getView()));
		}

	}

	@Override
	public void help() {
		this.myPresenter.getView().display("Displays a list of files and subdirectories in a directory. " + '\n' + '\t' + "--> Syntax: dir <path>", new DisplayMessageViewCommand(this.myPresenter.getView()));

	}

}
