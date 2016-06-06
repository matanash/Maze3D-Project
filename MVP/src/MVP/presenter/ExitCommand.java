package MVP.presenter;

public class ExitCommand extends CommonCommand{

	
	public ExitCommand(Presenter p) {
		super(p);
		
	}

	@Override
	public void doCommand(String[] args) throws Exception
	{
		System.out.println("|----------------------------|");
		System.out.println("|   Thank you for playing!   |");
		System.out.println("|----------------------------|");
		presenter.getModel().exitModel();
	}

	@Override
	public void help() {
		System.out.println("Exit and close the maze game"+'\n'+'\t'+"--> exit");
		
	}

}