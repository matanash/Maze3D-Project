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

/**
 * This the main window in the GUI view.
 *  @author Matan Ashkenazi and Noee Cohen
 */
public class MazeWindow extends BasicWindow{
	
	//protected Maze3d maze;
	//protected Position goalPosition ;
	//protected Solution solution;
	
	protected Maze3DDisplayer maze2dWidget ;

	protected String selectedXMLpropertiesFilePath;
	
	/** The maze file path. used in save or load maze scenario */
	protected String mazeFilePath;
	
	/** Maze properties. */
	protected Maze3DProperties maze3dProperties;
	
	/** Game properties. */
	protected Properties properties;

	protected Button generateButton;
	
	protected Button displayMazeButton;
	
	protected Button displaySolutionButton;
	
	protected Button solveButton;

	protected Text nameT;
	
	protected Text depthT;
	
	protected Text rowT;
	
	protected Text colsT;
	
	protected Text currentPositionT;
	
	protected Text goalPositionT;
	
	protected DisposeListener exitListener;

	protected SelectionListener generateListener;
	
	protected SelectionListener displayMazeListener;
	
	protected SelectionListener displayGoalPositionListener;
	
	protected SelectionListener displaySolutionListener;
	
	protected KeyListener keyListener;

	protected SelectionListener solveListener;
	
	protected SelectionListener propertiesUpdateListener;
	
	protected SelectionListener saveListener;
	
	protected SelectionListener loadListener;
	
	protected SelectionListener viewCrossSectionListener;

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
		shell.setImage(new Image(display, "resources/jerry_cheese.jpg"));
	}

	@Override
	protected void initWidgets() 
	{
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
				FileDialog fd=new FileDialog(shell,SWT.OPEN);				//opening a new file dialog widget.
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
				if(mazeFilePath!=null){
					displayMazeButton.setEnabled(true);
					loadListener.widgetSelected(arg0);	
				}
								
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
		generateButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Maze3DPropertiesWindow propwindow = new Maze3DPropertiesWindow(shell,maze3dProperties,generateListener);
		    	propwindow.open();
		    	nameT.setText(propwindow.getProperties().getName());
		    	depthT.setText(Integer.toString(propwindow.getProperties().getHeight()));
		    	rowT.setText(Integer.toString(propwindow.getProperties().getLength()));
		    	colsT.setText(Integer.toString(propwindow.getProperties().getWidth()));
			}
				
			@Override				
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			
		});
		
		this.displayMazeButton=new Button(toolbar, SWT.PUSH);
		this.displayMazeButton.setText("       Display maze       ");
		this.displayMazeButton.setEnabled(false);
		this.displayMazeButton.addSelectionListener(displayMazeListener);
		
		this.solveButton=new Button(toolbar, SWT.PUSH);
		this.solveButton.setText("      Solve the maze     ");
		this.solveButton.setEnabled(false);
		this.solveButton.addSelectionListener(solveListener);

		this.displaySolutionButton=new Button(toolbar, SWT.PUSH);
		this.displaySolutionButton.setText("     Display Solution    ");
		this.displaySolutionButton.setEnabled(false);
		this.displaySolutionButton.addSelectionListener(displaySolutionListener);
		
		for (int i=0; i<50 ;i++)
			new Label(toolbar, SWT.NONE);

		Label nameL= new Label(toolbar, SWT.NONE);
		nameL.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, true, true, 2, 2));
		nameL.setText("Maze Name: ");
		this.nameT = new Text(toolbar, SWT.BORDER);
		this.nameT.setLayoutData(new GridData(SWT.NONE, SWT.FILL, true, true, 2, 2));
		
		Label depthL =new Label(toolbar, SWT.NONE);
		depthL.setLayoutData(new GridData(SWT.NONE, SWT.FILL, true, true, 2, 2));
		depthL.setText("Floors: ");
		this.depthT=new Text(toolbar, SWT.BORDER);
		this.depthT.setLayoutData(new GridData(SWT.NONE, SWT.FILL, true, true, 2, 2));
		
		Label rowL =new Label(toolbar, SWT.NONE);
		rowL.setLayoutData(new GridData(SWT.NONE, SWT.FILL, true, true, 2, 2));
		rowL.setText("Rows: ");
		this.rowT=new Text(toolbar, SWT.BORDER);
		this.rowT.setLayoutData(new GridData(SWT.NONE, SWT.FILL, true, true, 2, 2));

		Label colsL =new Label(toolbar, SWT.NONE);
		colsL.setLayoutData(new GridData(SWT.NONE, SWT.FILL, true, true, 2, 2));
		colsL.setText("Columns: ");
		this.colsT=new Text(toolbar, SWT.BORDER);
		this.colsT.setLayoutData(new GridData(SWT.NONE, SWT.FILL, true, true, 2, 2));
		
		Label currentPositionL =new Label(toolbar, SWT.NONE);
		currentPositionL.setLayoutData(new GridData(SWT.NONE, SWT.FILL, true, true, 2, 2));
		currentPositionL.setText("Current Position ");
		this.currentPositionT=new Text(toolbar, SWT.BORDER);
		this.currentPositionT.setLayoutData(new GridData(SWT.NONE, SWT.FILL, true, true, 2, 2));
		
		Label goalPositionL =new Label(toolbar, SWT.NONE);
		goalPositionL.setLayoutData(new GridData(SWT.NONE, SWT.FILL, true, true, 2, 2));
		goalPositionL.setText("Exit Position ");
		this.goalPositionT=new Text(toolbar, SWT.BORDER);
		this.goalPositionT.setLayoutData(new GridData(SWT.NONE, SWT.FILL, true, true, 2, 2));
		this.goalPositionT.addSelectionListener(displayGoalPositionListener);
	}
	
	public Maze3DDisplayer getMaze2dWidget() {
		return this.maze2dWidget;
	}

	public void setMaze2dWidget(Maze3DDisplayer maze2dWidget) {
		this.maze2dWidget = maze2dWidget;
	}

	/**
	 * the Exit request behavior.
	 */
	protected void exitRequest() {
		shell.dispose();
	}


	
	/**
	 * Display a Maze3d .
	 * @param m3d - the Maze3d to display
	 */
	public void displayMaze(Maze3d m3d) {
		
		Display.getDefault().syncExec(new Runnable() {
			
			@Override
		    public void run() {
				if (maze2dWidget==null)	
					maze2dWidget = new Maze2DDisplayer (shell, SWT.BORDER,m3d);
				else
					((Maze2DDisplayer)maze2dWidget).setMaze2DDisplayer(m3d);
				maze2dWidget.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));	
				shell.setMaximized(true);
				maze2dWidget.setFocus();
				solveButton.setEnabled(true);
		    	displaySolutionButton.setEnabled(true);
		    }
		});
		
	}
	
	/**
	 * Display error.
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
	 * @param string the string to display
	 */
	public void displayMessage(String message) {
		Display.getDefault().syncExec(new Runnable() {
			
			@Override
		    public void run() {
				if (message.toLowerCase().contains("ready to use"))
					displayMazeButton.setEnabled(true);
				MessageBox messageBox =  new MessageBox(shell, SWT.ICON_INFORMATION); 
		    	messageBox.setMessage(message);
		    	messageBox.setText("information message");
		    	messageBox.open();		
		    	
		    }
		});
	}

	public void displayWalkToGoalPosition(Solution solution) {
				maze2dWidget.walkToGoalPosition(solution,this.getDisplay());
		    
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
	 * @param displayMazeListener - the new display maze listener
	 */
	public void setDisplayMazeListener(SelectionListener displayMazeListener) {
		this.displayMazeListener = displayMazeListener;
	}
	
	public SelectionListener getDisplayGoalPositionListener() {
		return displayGoalPositionListener;
	}

	public void setDisplayGoalPositionListener(SelectionListener displayGoalPositionListener) {
		this.displayGoalPositionListener = displayGoalPositionListener;
	}

	/**
	 * Sets the selection listener that sets the behavior of - display solution request - in the MVP.
	 * @param displaySolutionListener - the new display solution listener
	 */
	public void setDisplaySolutionListener(SelectionListener displaySolutionListener) {
		this.displaySolutionListener = displaySolutionListener;
	}

	/**
	 * Sets the key listener that sets the behavior of - movements requests - in the MVP.
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

	public Text getCurrentPositionText() {
		return currentPositionT;
	}

	public void setCurrentPositionText(Text currentPositionText) {
		this.currentPositionT = currentPositionText;
	}

	public Text getGoalPositionText() {
		return goalPositionT;
	}

	public void setGoalPositionText(String goalPositionText) {
		this.goalPositionT.setText(goalPositionText);
		
	}
	
	
}