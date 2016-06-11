package MVP.boot;

import java.beans.XMLDecoder;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import MVP.model.CommonMaze3DModel;
import MVP.model.Maze3DModel;
import MVP.presenter.Presenter;
import MVP.presenter.Properties;
import MVP.view.CommonMaze3DView;
import MVP.view.Maze3DCLIView;

public class Run {

	public static void main(String[] args) {
		
		/* ********
		 * MVP Demo
		 * ********/
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		String xmlPath = "Properties.xml";
		
		//Load properties configuration file
		Properties properties = null;
		try {
			FileInputStream in = new FileInputStream(xmlPath);				//try to read the configuration XML file
			XMLDecoder decoder = new XMLDecoder(in);						//wrap FileInpoutStream in XNLDecoder Object
			properties = (Properties)decoder.readObject();					//decoding the XML file and UpCast to Properties Object
			decoder.close();
			in.close();
		} catch (FileNotFoundException e) {									//if no properties.xml was found in directory, generating default properties.
			System.out.println("file not found, default properties will be loaded");
			properties = new Properties();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (IllegalArgumentException e)
		{
			properties = new Properties();
		}
		CommonMaze3DView view = null ;
		System.out.println(properties.getSolveAlgorithm());
		switch (properties.getUi())
		{
			case "CLI":
			{	view = new Maze3DCLIView(br,pw);
				break;
			}
			default:
				break;
		}
		
		CommonMaze3DModel model = new Maze3DModel();
		Presenter presenter = new Presenter(view,model);
		presenter.setProperties(properties);
		
		//register view and model objects as observable objects of presenter
		view.addObserver(presenter);
		model.addObserver(presenter);
		
		//start the CLI View
		try {
			view.startView();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

}
