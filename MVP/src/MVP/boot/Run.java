package MVP.boot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import MVP.model.MyModel;
import MVP.presenter.Presenter;
import MVP.view.MyView;

public class Run {

	public static void main(String[] args) {
		// MVP Demo
		MyModel model = new MyModel();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter writer = new PrintWriter(System.out);
		MyView view = new MyView(reader, writer);
		
		Presenter presenter = new Presenter(view,model);
		view.addObserver(presenter);
		model.addObserver(presenter);
		
		view.start();	
	}

}
