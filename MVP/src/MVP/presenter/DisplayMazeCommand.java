package MVP.presenter;

public class DisplayMazeCommand extends CommonCommand {

	public DisplayMazeCommand(Presenter p) {
		super(p);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		
		if (args.length != 1) 
		{
			presenter.getView().displayMessage("Invalid arguments");
			this.help();
		} else 
		{
			presenter.getView().displayMaze(presenter.getModel().getMaze3d(args[0]));
		}
		
	}

	@Override
	public void help() {
		System.out.println("Displays the whole maze <maze name>. " + '\n' + '\t' + "--> Syntax: display <maze name>");

	}

}
