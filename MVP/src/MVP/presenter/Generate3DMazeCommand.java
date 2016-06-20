package MVP.presenter;

import MVP.view.commands.DisplayMessageViewCommand;

// TODO: Auto-generated Javadoc
/**
 * The Class Generate3DMazeCommand.
 */
public class Generate3DMazeCommand extends CommonCommand {

	/**
	 * Instantiates a new generate 3 D maze command.
	 *
	 * @param p the p
	 */
	public Generate3DMazeCommand(MyPresenter p) {
		super(p);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see MVP.presenter.CommonCommand#doCommand(java.lang.String[])
	 */
	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length != 4) 
		{
			myPresenter.getView().display("Invalid arguments", new DisplayMessageViewCommand(this.myPresenter.getView()));
			help();
		} 
		else 
		{
			try {
				myPresenter.getModel().generate3dMaze(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]),
						Integer.parseInt(args[3]));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/* (non-Javadoc)
	 * @see MVP.presenter.CommonCommand#help()
	 */
	@Override
	public void help() {
		myPresenter.getView().display("Generates a new 3D Maze. " + '\n' + '\t'
				+ "--> Syntax: generate 3d maze <maze name> <height> <length> <width>", new DisplayMessageViewCommand(this.myPresenter.getView()));
		

	}

}
