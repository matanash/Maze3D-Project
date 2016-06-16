package mvp.boot;

import java.beans.XMLDecoder;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import mvp.cliView.Maze3DCLIView;
import mvp.guiView.Maze3DGUIView;
import mvp.model.CommonMaze3DModel;
import mvp.model.Maze3DModel;
import mvp.presenter.MyPresenter;
import mvp.presenter.Properties;
import mvp.view.CommonMaze3DView;

/* ********
 * MVP Demo
 * ********/

public class Run {

	public static void main(String[] args) throws Exception {

		String xmlFilePath = "Properties.xml";
		
		//Load properties configuration file
		Properties properties = null;
		try 
		{
			XMLDecoder decoder = new XMLDecoder(new FileInputStream(xmlFilePath));	//try to read the configuration XML file
			properties = (Properties)decoder.readObject();						//decoding the XML file and UpCast to Properties Object
			decoder.close();
		} 
		catch (FileNotFoundException e) 
		{		//if no properties.xml file was found in directory, generating default properties.
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
				view = new Maze3DGUIView("maze game", 800, 500,xmlFilePath);
				break;
			}
			default:
			{
				System.err.println("Missing view type");
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
		
		MyPresenter myPresenter = new MyPresenter(view,model);
		myPresenter.setProperties(properties);
		
		//register view and model objects as observable objects of presenter
		view.addObserver(myPresenter);
		model.addObserver(myPresenter);
		
		//start the CLI View
		try {
			view.startView();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
