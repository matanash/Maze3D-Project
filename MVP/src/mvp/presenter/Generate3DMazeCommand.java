package mvp.presenter;

import mvp.view.commands.DisplayMessageCLICommand;

public class Generate3DMazeCommand extends CommonCommand {

	public Generate3DMazeCommand(MyPresenter p) {
		super(p);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length != 4) 
		{
			myPresenter.getView().display("Invalid arguments", new DisplayMessageCLICommand(this.myPresenter.getView()));
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

	@Override
	public void help() {
		myPresenter.getView().display("Generates a new 3D Maze. " + '\n' + '\t'
				+ "--> Syntax: generate 3d maze <maze name> <height> <length> <width>", new DisplayMessageCLICommand(this.myPresenter.getView()));
		

	}

}
