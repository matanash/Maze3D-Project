package MVP.presenter;

import java.io.File;

public class GetDirCommand extends CommonCommand {

	public GetDirCommand(Presenter p) {
		super(p);
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length != 1) {
			System.out.println("Invalid arguments");
			this.help();
		} else {
			String path = args[0];
			File file = new File(path);
			if (this.presenter.getView() == null)
				System.out.println("123");
			if (file.isDirectory())
				this.presenter.getModel().dir(path);
			else
				System.out.println("There is no such directory");
		}

	}

	@Override
	public void help() {
		System.out.println(
				"Displays a list of files and subdirectories in a directory. " + '\n' + '\t' + "--> Syntax: dir <path>");

	}

}
