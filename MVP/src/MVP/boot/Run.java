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

/* ********
 * MVP Demo
 * ********/

public class Run {

	public static void main(String[] args) {

		String xmlPath = "Properties.xml";
		
		//Load properties configuration file
		Properties properties = null;
		try 
		{
			XMLDecoder decoder = new XMLDecoder(new FileInputStream(xmlPath));	//try to read the configuration XML file
			properties = (Properties)decoder.readObject();						//decoding the XML file and UpCast to Properties Object
			decoder.close();
		} 
		catch (FileNotFoundException e) 
		{		//if no properties.xml was found in directory, generating default properties.
			System.out.println("file not found, default properties will be loaded");
			properties = new Properties();
		} 
		catch (IllegalArgumentException e)
		{
			properties = new Properties();
		}
		
		CommonMaze3DView view = null ;
		switch (properties.getUi())
		{
			case "CLI":
			{	
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
				PrintWriter printerWriter = new PrintWriter(System.out);
				view = new Maze3DCLIView(bufferedReader,printerWriter);
				break;
			}
			case "GUI":
			{
				//view = new GUI();
				break;
			}
			default:
			{
				System.err.println("Missing view type");
				//System.err.println("Run Setup.bat to create a defualt properties file");
				//System.out.println("Press return to exit...");
				try 
				{
					new BufferedReader(new InputStreamReader(System.in)).readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
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
