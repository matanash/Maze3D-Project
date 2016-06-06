package MVP.presenter;

public class Generate3DMazeCommand extends CommonCommand {

	public Generate3DMazeCommand(Presenter p) {
		super(p);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length != 4) 
		{
			presenter.getView().displayMessage("Invalid arguments");
			help();
		} 
		else 
		{
			try {
				presenter.getModel().generate3dMaze(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]),
						Integer.parseInt(args[3]));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void help() {
		System.out.println("Generates a new 3D Maze. " + '\n' + '\t'
				+ "--> Syntax: generate 3d maze <maze name> <height> <length> <width>");

	}

}
