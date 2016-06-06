package MVP.boot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import MVP.model.Maze3DModel;
import MVP.presenter.Presenter;
import MVP.view.Maze3DCLIView;

public class Run {

	public static void main(String[] args) {
		
		/* ********
		 * MVP Demo
		 * ********/
		
		Maze3DModel model = new Maze3DModel();
		Maze3DCLIView view = new Maze3DCLIView(new BufferedReader(new InputStreamReader(System.in)),new PrintWriter(System.out));
		Presenter presenter = new Presenter(view,model);
		
		//register view and model objects as observable of presenter
		view.addObserver(presenter);
		model.addObserver(presenter);
		
		//start the CLI View
		view.startView();	
	}

}
