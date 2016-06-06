package MVP.boot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import MVP.model.Maze3DModel;
import MVP.presenter.Presenter;
import MVP.view.Maze3DCLIView;

public class Run {

	public static void main(String[] args) {
		// MVP Demo
		Maze3DModel model = new Maze3DModel();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter writer = new PrintWriter(System.out);
		Maze3DCLIView view = new Maze3DCLIView(reader, writer);
		
		Presenter presenter = new Presenter(view,model);
		view.addObserver(presenter);
		model.addObserver(presenter);
		
		view.start();	
	}

}
