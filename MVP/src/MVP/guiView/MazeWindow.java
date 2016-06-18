package MVP.guiView;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;

import MVP.guiView.Widgets.Maze2DDisplayer;
import MVP.guiView.Widgets.Maze3DDisplayer;
import MVP.presenter.Properties;
import algorithms.search.Solution;
import model.maze3d.Maze3d;
import model.maze3d.Position;

/**
 * This the main window in the GUI view.
 *  @author Matan Ashkenazi and Noee Cohen
 */
public class MazeWindow extends BasicWindow{
	
	protected Maze3d maze;
	
	protected Position charPosition;
	
	protected Position goalPosition ;
	
	protected Solution solution;
	
	protected String selectedXMLpropertiesFilePath;
	
	protected DisposeListener exitListener;

	protected SelectionListener generateListener;
	
	protected SelectionListener displayMazeListener;
	
	protected KeyListener keyListener;

	protected SelectionListener solveListener;
	
	protected SelectionListener propertiesUpdateListener;
	
	protected SelectionListener saveListener;
	
	protected SelectionListener loadListener;
	
	protected SelectionListener viewCrossSectionListener;
	
	/** The maze file path. used in save or load maze scenario */
	protected String mazeFilePath;
	
	/** The maze properties. */
	protected Maze3DProperties maze3dProperties;
	
	/** The game properties. */
	protected Properties properties;

	protected Button generateButton;
	
	protected Button displayMazeButton;
	
	protected Button solveButton;

	protected Combo viewCrossSectionCombo;
	
	protected String currentCrossSection ;
	
	protected int currentLayer ;
	
	protected Text positionText;
	
	protected Text yGoalPositionTextBox ;
	
	protected Text xGoalPositionTextBox ;
	
	protected Text zGoalPositionTextBox ;
	
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
/*		widgetsList = new ArrayList<MazeDisplayer>();*/
		shell.setImage(new Image(display, "resources/jerry_cheese.jpg"));
	}

	@Override
	protected void initWidgets() 
	{
		//shell.addDisposeListener(exitListener);								//for X button and 'Exit' button
		shell.setLayout(new GridLayout(2,false));	
		Image image= new Image(display,"resources/bg.jpg");
		shell.setBackgroundImage(image);
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
				exit();		//activates the DisposeListener.						
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
				fd.setFilterExtensions(new String[] { "*.maz"});
				mazeFilePath = fd.open();
				if(mazeFilePath!=null)
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
				fd.setFilterExtensions(new String[] { "*.maz"});
				mazeFilePath = fd.open();
				if(mazeFilePath!=null)
					loadListener.widgetSelected(arg0);				
				else
					displayError("Load canceled.");
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		    
		shell.setMenuBar(menuBar);
		
		Composite toolbar = new Composite(shell, SWT.NONE);
		GridLayout gridLayout = new GridLayout(SWT.VERTICAL,true);
		gridLayout.numColumns = 2;
		toolbar.setLayout(gridLayout);
		
		this.generateButton=new Button(toolbar, SWT.PUSH);
		generateButton.setText("  Generate new maze  ");
		//generateButton.setLayoutData(new GridData(SWT.FILL, SWT.NONE, false, false, 2, 1));
		generateButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Maze3DPropertiesWindow propwindow = new Maze3DPropertiesWindow(shell,maze3dProperties,generateListener);
		    	propwindow.open();
			}
				
			@Override				
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			
		});
		
		this.displayMazeButton=new Button(toolbar, SWT.PUSH);
		this.displayMazeButton.setText("       Display maze       ");
		//this.displayMazeButton.setLayoutData(new GridData(SWT.NONE, SWT.None, false, true, 1, 1));
		this.displayMazeButton.addSelectionListener(displayMazeListener);
		
		this.solveButton=new Button(toolbar, SWT.PUSH);
		this.solveButton.setText("      Solve the maze     ");
		//this.solveButton.setLayoutData(new GridData(SWT.None, SWT.None, false, false, 2, 1));
		this.solveButton.setEnabled(false);
		this.solveButton.addSelectionListener(solveListener);
		
		for (int i=0; i<50 ;i++)
			new Label(toolbar, SWT.NONE);

		Label nameL= new Label(toolbar, SWT.NONE);
		nameL.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, true, true, 2, 2));
		nameL.setText("Maze Name: ");
		final Text nameT = new Text(toolbar, SWT.BORDER);
		nameT.setLayoutData(new GridData(SWT.NONE, SWT.FILL, true, true, 2, 2));
		
		Label depthL =new Label(toolbar, SWT.NONE);
		depthL.setLayoutData(new GridData(SWT.NONE, SWT.FILL, true, true, 2, 2));
		depthL.setText("Floors: ");
		final Text depthT=new Text(toolbar, SWT.BORDER);
		depthT.setLayoutData(new GridData(SWT.NONE, SWT.FILL, true, true, 2, 2));
		
		Label rowL =new Label(toolbar, SWT.NONE);
		rowL.setLayoutData(new GridData(SWT.NONE, SWT.FILL, true, true, 2, 2));
		rowL.setText("Rows: ");
		final Text rowT=new Text(toolbar, SWT.BORDER);
		rowT.setLayoutData(new GridData(SWT.NONE, SWT.FILL, true, true, 2, 2));

		Label colsL =new Label(toolbar, SWT.NONE);
		colsL.setLayoutData(new GridData(SWT.NONE, SWT.FILL, true, true, 2, 2));
		colsL.setText("Columns: ");
		final Text colsT=new Text(toolbar, SWT.BORDER);
		colsT.setLayoutData(new GridData(SWT.NONE, SWT.FILL, true, true, 2, 2));
		
		
		
		/*Label viewCrossSectionLabel = new Label(toolbar, SWT.NONE);
		//viewCrossSectionLabel.setLayoutData(new GridData(SWT.NONE, SWT.CENTER, false, false));
		viewCrossSectionLabel.setText("View Plane:");
		
		this.viewCrossSectionCombo = new Combo(toolbar, SWT.READ_ONLY);
		//this.viewCrossSectionCombo.setLayoutData(new GridData(SWT.NONE, SWT.NONE, false, false));
		this.viewCrossSectionCombo.setItems(new String[] { "XZ", "YZ", "XY" });
		this.viewCrossSectionCombo.select(0);
		this.viewCrossSectionCombo.addSelectionListener(viewCrossSectionListener);
		currentCrossSection = viewCrossSectionCombo.getItem(0);*/
		
		/*Label currentViewLayerLabel = new Label(shell, SWT.NONE);
		currentViewLayerLabel.setLayoutData(new GridData(SWT.NONE, SWT.CENTER, false, false));
		currentViewLayerLabel.setText("View Layer:");

		Label positionLabel = new Label(toolbar, SWT.NONE);
		//viewCrossSectionLabel.setLayoutData(new GridData(SWT.NONE, SWT.CENTER, false, false));
		positionLabel.setText("Position:");
		
		positionText = new Text(toolbar, SWT.BORDER | SWT.READ_ONLY);
		//positionText.setLayoutData(new GridData(SWT.FILL, SWT.NONE, false, false));
		
		shell.setLayout(new GridLayout(1, true));
		Label yTitle = new Label(shell, SWT.COLOR_WIDGET_DARK_SHADOW);
		yTitle.setText("Goal Floor: ");

		yGoalPositionTextBox = new Text(shell, SWT.BORDER);
		yGoalPositionTextBox.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));

		Label xTitle = new Label(shell, SWT.COLOR_WIDGET_DARK_SHADOW);
		xTitle.setText("Goal Row: ");

		xGoalPositionTextBox = new Text(shell, SWT.BORDER);
		//xTextBox.setText("" + this.goalPosition.getX());
		xGoalPositionTextBox .setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));

		Label zTitle = new Label(shell, SWT.COLOR_WIDGET_DARK_SHADOW);
		zTitle.setText("Goal Column: ");

		zGoalPositionTextBox  = new Text(shell, SWT.BORDER);
		//zTextBox.setText("" + this.goalPosition.getZ());
		xGoalPositionTextBox.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));*/
		
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
/*	public void widgetsRefresh()
	{
		for (MazeDisplayer canvasWidget : widgetsList) {
			if(maze!=null)
				canvasWidget.setMazeData(maze);
			if(charPosition!=null)
				canvasWidget.setCharPosition(charPosition);
			
			canvasWidget.setSolution(solution);
		}
	}*/
	
	/**
	 * Sets the character position data.
	 *
	 * @param charPosition the new character position data
	 */
	public void setPositionData(Position charPosition) {
		this.charPosition = charPosition;
		//widgetsRefresh();
		
	}
	
	/**
	 * Sets the maze data.
	 *
	 * @param maze the new maze data
	 */
	public void setMazeData(Maze3d m3d){
		this.maze = m3d;
		this.solution =new Solution(); 		//reset the solution map
		Display.getDefault().syncExec(new Runnable() {
		    public void run() {
		    	solveButton.setEnabled(true);
		    }
		});
		
		//widgetsRefresh();
	}
	
	/**
	 * Sets the solution data.
	 *
	 * @param solution the new solution
	 */
	public void setSolution(Solution solution) {
		
		this.solution= solution;
		//widgetsRefresh();
		
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
	public void displayMessage(String message) {
		Display.getDefault().syncExec(new Runnable() {
			
			@Override
		    public void run() {
		    	MessageBox messageBox =  new MessageBox(shell, SWT.ICON_INFORMATION); 
		    	messageBox.setMessage(message);
		    	messageBox.setText("information message");
		    	messageBox.open();		
		    	
		    }
		});
	}
	
	/**
	 * Display a Maze3d .
	 * @param m3d - the Maze3d to display
	 */
	public void displayMaze(Maze3d m3d) {
		Display.getDefault().syncExec(new Runnable() {
			
			@Override
		    public void run() {
				Maze3DDisplayer maze2dWidget = new Maze2DDisplayer (shell, SWT.BORDER,m3d);
				maze2dWidget.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));	
				maze2dWidget.setFocus();
		    }
		});
	}

	
	/**
	 * Display a Position.
	 *
	 * @param goalPosition - the the Goal Position to display
	 */
	public void setGoalPosition(Position goalPosition) {
		this.goalPosition = goalPosition;
	}
	
	public Position getGoalPosition() {
		return this.goalPosition;
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
	 * Sets the selection listener that sets the behavior of - display maze request - in the MVP.
	 *
	 * @param displayMazeListenerListener the new generate listener
	 */
	public void setDisplayMazeListener(SelectionListener displayMazeListener) {
		this.displayMazeListener = displayMazeListener;
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
	public String getMazeFilePath() {
		return this.mazeFilePath;
	}

	/**
	 * Sets the selection listener that sets the behavior of - load maze request - in the MVP.
	 *
	 * @param selectionListener the new load listener
	 */
	public void setLoadListener(SelectionListener selectionListener) {
		this.loadListener = selectionListener;
		
	}
	
	/**
	 * Sets the selection listener that sets the behavior of - view Cross Section request - in the MVP.
	 * @param selectionListener the new Cross Section listener
	 */
	public void setViewCrossSectionListener(SelectionAdapter selectionListener) {
		this.viewCrossSectionListener = selectionListener;
	}
	
	/**
	 * Add a listener to handle user requests to change the view plane
	 * @param listener Listener
	 */
	public void addViewCrossSectionSelectionListener(SelectionListener listener) {
		this.viewCrossSectionCombo.addSelectionListener(listener);
	}

	public Maze3d getMaze() {
		return this.maze;
	}

	public void setMaze(Maze3d maze) {
		this.maze = maze;
	}

	public Position getCharPosition() {
		return this.charPosition;
	}

	public void setCharPosition(Position charPosition) {
		this.charPosition = charPosition;
	}

	public Text getyGoalPositionTextBox() {
		return this.yGoalPositionTextBox;
	}

	public void setyGoalPositionTextBox(int y) {
		this.yGoalPositionTextBox.setText("" + this.goalPosition.getY());
	}

	public Text getxGoalPositionTextBox() {
		return this.xGoalPositionTextBox;
	}

	public void setxGoalPositionTextBox(Text xGoalPositionTextBox) {
		this.xGoalPositionTextBox.setText("" + this.goalPosition.getX());
	}

	public Text getzGoalPositionTextBox() {
		return this.zGoalPositionTextBox;
	}

	public void setzGoalPositionTextBox(Text zGoalPositionTextBox) {
		this.zGoalPositionTextBox.setText("" + this.goalPosition.getZ());
	}

	public Solution getSolution() {
		return this.solution;
	}

	public String getcurrentCrossSection() {
		return this.currentCrossSection;
	}

	public void setcurrentCrossSection(String currentCrossSection) {
		this.currentCrossSection = currentCrossSection;
	}

	public Combo getViewCrossSectionCombo() {
		return this.viewCrossSectionCombo;
	}

	public void setViewCrossSectionCombo(Combo viewCrossSectionCombo) {
		this.viewCrossSectionCombo = viewCrossSectionCombo;
	}

	public int getCurrentLayer() {
		return this.currentLayer;
	}

	public void setCurrentLayer(int currentLayer) {
		this.currentLayer = currentLayer;
	}
	
}