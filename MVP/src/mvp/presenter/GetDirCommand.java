package mvp.presenter;

import java.io.File;

import mvp.view.commands.DisplayMessageCLICommand;

public class GetDirCommand extends CommonCommand {

	public GetDirCommand(MyPresenter myPresenter) {
		super(myPresenter);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length != 1) {
			myPresenter.getView().display("Invalid arguments", new DisplayMessageCLICommand(this.myPresenter.getView()));
			this.help();
		} else {
			String path = args[0];
			File file = new File(path);
			if (file.isDirectory()){
				this.myPresenter.getModel().dir(path);
			}
			else
				myPresenter.getView().display("There is no such directory", new DisplayMessageCLICommand(this.myPresenter.getView()));
		}

	}

	@Override
	public void help() {
		this.myPresenter.getView().display("Displays a list of files and subdirectories in a directory. " + '\n' + '\t' + "--> Syntax: dir <path>", new DisplayMessageCLICommand(this.myPresenter.getView()));

	}

}
