package mvp.guiView;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;

import algorithms.search.Solution;
import model.maze3d.Maze3d;
import model.maze3d.Position;
import mvp.guiView.Widgets.Maze3DWidget;
import mvp.guiView.Widgets.MazeCubeDisplayer;
import mvp.guiView.Widgets.MazeDisplayer;
import mvp.guiView.Widgets.PossibleMovesWidget;
import mvp.presenter.Properties;


/**
 * This the main window in the GUI view.
 *  @author Matan Ashkenazi and Noee Cohen
 */
public class MazeWindow extends BasicWindow{
	
	protected Maze3d maze;
	
	protected Position charPosition;
	
	protected Solution solution;
	
	protected String selectedXMLpropertiesFilePath;
	
	protected DisposeListener exitListener;

	protected SelectionListener generateListener;

	protected KeyListener keyListener;

	protected SelectionListener solveListener;
	
	protected SelectionListener propertiesUpdateListener;
	
	protected SelectionListener saveListener;
	
	protected SelectionListener loadListener;
	
	/** The maze file path. used in save or load maze scenario */
	protected String mazePath;
	
	/** The widgets list. */
	protected ArrayList<MazeDisplayer> widgetsList;
	
	/** The maze properties. */
	protected Maze3DProperties maze3dProperties;
	
	/** The game properties. */
	protected Properties properties;

	protected Button solveButton;

	/**
	 * Instantiates a new maze window.
	 * @param title the window title
	 * @param width the window width
	 * @param height the window height
	 * @param properties the game properties
	 */
	public MazeWindow(String title, int width, int height , Properties properties,String xmlFilePath) {
		super(title, width, height);
		this.properties= properties;
		this.maze3dProperties=new Maze3DProperties();   					//default values
		selectedXMLpropertiesFilePath = xmlFilePath;
		widgetsList = new ArrayList<MazeDisplayer>();
		shell.setImage(new Image(display, "resources/pacman.png"));
	}

	@Override
	protected void initWidgets() 
	{
		shell.addDisposeListener(exitListener);								//for X button and 'Exit' button
		shell.setLayout(new GridLayout(2,false));	
		/*Image image= new Image(display,"resources/background.jpg");
		shell.setBackgroundImage(image);*/
		shell.setBackgroundMode(SWT.INHERIT_FORCE);
		
		
		Menu menuBar = new Menu(shell, SWT.BAR);							//the main window menu bar.
		MenuItem fileMenuHeader = new MenuItem(menuBar, SWT.CASCADE);		//menu bar header button.
		fileMenuHeader.setText("&File");

		Menu fileMenu = new Menu(shell, SWT.DROP_DOWN);
		fileMenuHeader.setMenu(fileMenu);

		MenuItem fileOpenPropItem = new MenuItem(fileMenu, SWT.PUSH);   	//button used to load new properties during runtime.
		fileOpenPropItem.setText("Open properties file");
		fileOpenPropItem.addSelectionListener(new SelectionListener() {
				
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				FileDialog fd=new FileDialog(shell,SWT.OPEN);			//opening a new file dialog widget.
				fd.setText("open");
				String[] filterExt = { "*.xml" };
				fd.setFilterExtensions(filterExt);
				selectedXMLpropertiesFilePath = fd.open();
				if(selectedXMLpropertiesFilePath!=null)
					propertiesUpdateListener.widgetSelected(arg0);
				
			}
				
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
        //button for editing an existing properties.
		MenuItem fileEditPropItem = new MenuItem(fileMenu, SWT.PUSH);		
		fileEditPropItem.setText("Edit properties");
		fileEditPropItem.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Maze3DPropertiesWindow window = new Maze3DPropertiesWindow(shell, maze3dProperties, generateListener);
				window.open();
				if(selectedXMLpropertiesFilePath!=null)
					propertiesUpdateListener.widgetSelected(arg0);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		
		//button for safe exit.    
		MenuItem fileExitItem = new MenuItem(fileMenu, SWT.PUSH);
		fileExitItem.setText("Exit");										
		fileExitItem.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shell.dispose();		//activates the DisposeListener.						
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		//window menu Maze header.
		MenuItem MazeMenuHeader = new MenuItem(menuBar, SWT.CASCADE);		
		MazeMenuHeader.setText("Maze");

		Menu MazeMenu = new Menu(shell, SWT.DROP_DOWN);						
		MazeMenuHeader.setMenu(MazeMenu);

		
		// save maze button
		MenuItem mazeSave = new MenuItem(MazeMenu, SWT.PUSH);			
		mazeSave.setText("Save maze");
		mazeSave.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				FileDialog fd = new FileDialog(shell,SWT.SAVE);
				mazePath = fd.open();
				if(mazePath!=null)
					saveListener.widgetSelected(arg0);
				else
					displayError("Save canceled.");
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		//load maze button
		MenuItem mazeLoad= new MenuItem(MazeMenu, SWT.PUSH);			
		mazeLoad.setText("Load maze");
		
		mazeLoad.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				FileDialog fd = new FileDialog(shell,SWT.OPEN);
				mazePath = fd.open();
				if(mazePath!=null)
					loadListener.widgetSelected(arg0);				
				else
					displayError("Load canceled.");
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		    
		shell.setMenuBar(menuBar);
        
		Button generateButton=new Button(shell, SWT.PUSH);
		generateButton.setText("  Generate new maze  ");
		generateButton.setLayoutData(new GridData(SWT.NONE, SWT.None, false, false, 1, 1));
		generateButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Maze3DPropertiesWindow propwindow = new Maze3DPropertiesWindow(shell,maze3dProperties,generateListener);
		    	propwindow.open();
			}
				
			@Override				
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			
		});
		
		//Main Maze display widget.
		MazeDisplayer mazeWidget=new Maze3DWidget(shell, SWT.NULL);
		widgetsList.add(mazeWidget);
		mazeWidget.setFocus();
		mazeWidget.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,5));
		

		solveButton=new Button(shell, SWT.PUSH);
		solveButton.setText("     Solve the maze     ");
		solveButton.setLayoutData(new GridData(SWT.None, SWT.None, false, false, 1, 1));
		solveButton.setEnabled(false);
		solveButton.addSelectionListener(solveListener);
		
		// cube widget.
		MazeCubeDisplayer mazeCube = new MazeCubeDisplayer(shell, SWT.BORDER);
		mazeCube.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));
		widgetsList.add(mazeCube);
		
		// possibleMoves widget.
		MazeDisplayer possibleMoves=new PossibleMovesWidget(shell,SWT.BORDER);
		possibleMoves.setLayoutData(new GridData(SWT.CENTER, SWT.BOTTOM, false, true, 1, 1));
		widgetsList.add(possibleMoves);
		
		for (MazeDisplayer mazeDisplayer : widgetsList) {
			mazeDisplayer.addKeyListener(keyListener);
		}
		
	}
	
	/**
	 * the Exit request behavior.
	 */
	protected void exitRequest() {
		shell.dispose();

	}
	
	/**
	 * Widgets refresh all the relevant data and redraw.
	 */
	public void widgetsRefresh()
	{
		for (MazeDisplayer canvasWidget : widgetsList) {
			if(maze!=null)
				canvasWidget.setMazeData(maze);
			if(charPosition!=null)
				canvasWidget.setCharPosition(charPosition);
			
			canvasWidget.setSolution(solution);
		}
	}
	
	/**
	 * Sets the character position data.
	 *
	 * @param charPosition the new character position data
	 */
	public void setPositionData(Position charPosition) {
		this.charPosition = charPosition;
		widgetsRefresh();
		
	}
	
	/**
	 * Sets the maze data.
	 *
	 * @param maze the new maze data
	 */
	public void setMazeData(Maze3d maze){
		this.maze = maze;
		this.solution =new Solution(); 		//reset the solution map
		Display.getDefault().syncExec(new Runnable() {
		    public void run() {
		    	solveButton.setEnabled(true);
		    }
		});
		
		widgetsRefresh();
	
	}
	
	/**
	 * Sets the solution data.
	 *
	 * @param solution the new solution
	 */
	public void setSolution(Solution solution) {
		
		this.solution= solution;
		widgetsRefresh();
		
	}
	
	/**
	 * Display error.
	 *
	 * @param string the message
	 */
	public void displayError(String string) {
		Display.getDefault().syncExec(new Runnable() {
			
			@Override
			public void run() {
				MessageBox errorBox =  new MessageBox(shell, SWT.ICON_ERROR); 
				errorBox.setMessage(string);
				errorBox.setText("Error");
				errorBox.open();				
			}
		});
	}
	
	/**
	 * Display a string.
	 *
	 * @param string the string to display
	 */
	public void display(String string) {
		Display.getDefault().syncExec(new Runnable() {
			
			@Override
		    public void run() {
		    	MessageBox messageBox =  new MessageBox(shell, SWT.ICON_INFORMATION); 
		    	messageBox.setMessage(string);
		    	messageBox.setText("information message");
		    	messageBox.open();		
		    	
		    }
		});
	}
	

	/**
	 * Sets the selection listener that sets the behavior of - generate request - in the MVP.
	 *
	 * @param generateListener the new generate listener
	 */
	public void setGenerateListener(SelectionListener generateListener) {
		this.generateListener = generateListener;
	}
	
	/**
	 * Sets the key listener that sets the behavior of - movements requests - in the MVP.
	 *
	 * @param keyListener the new key listener
	 */
	public void setKeyListener(KeyListener keyListener) {
		this.keyListener = keyListener;
		
	}
	
	/**
	 * Sets the selection listener that sets the behavior of - solve request - in the MVP.
	 *
	 * @param selectionListener the new solve listener
	 */
	public void setSolveListener(SelectionListener selectionListener) {
		this.solveListener = selectionListener;
		
	}

	/**
	 * Sets the selection listener that sets the behavior of - exit request - in the MVP.
	 *
	 * @param exitListener the new exit listener
	 */
	public void setExitListener(DisposeListener exitListener) {
		this.exitListener = exitListener;
	}


	/**
	 * Sets the selection listener that sets the behavior of - update properties request - in the MVP.
	 *
	 * @param selectionListener the new properties update listener
	 */
	public void setPropertiesUpdateListener(SelectionListener selectionListener) {
		this.propertiesUpdateListener = selectionListener;
		
	}



	/**
	 * Gets the selected XML properties file.
	 *
	 * @return the selected XML properties file
	 */
	public String getSelectedXMLpropertiesFilePath() {
		return selectedXMLpropertiesFilePath;
		
	}



	/**
	 * Sets the selection listener that sets the behavior of - save maze request - in the MVP.
	 *
	 * @param selectionListener the new save listener
	 */
	public void setSaveListener(SelectionListener selectionListener) {
		this.saveListener= selectionListener;
		
	}



	/**
	 * Gets the maze file path.
	 *
	 * @return the maze path
	 */
	public String getMazePath() {
		return this.mazePath;
	}



	/**
	 * Sets the selection listener that sets the behavior of - load maze request - in the MVP.
	 *
	 * @param selectionListener the new load listener
	 */
	public void setLoadListener(SelectionListener selectionListener) {
		this.loadListener = selectionListener;
		
	}
}