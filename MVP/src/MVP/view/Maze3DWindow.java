package MVP.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;

import model.maze3d.Maze3d;
import model.maze3d.Position;

public class Maze3DWindow extends BasicWindow {

	Button newGameButton;
	Maze3DDisplayerWidget maze3dDisplayer;
	Combo viewCrossSectionCombo;
	Text positionText;
	Button solveButton;
	Button exitButton;
	MenuItem newGameMenuItem;
	MenuItem loadMazeMenuItem;
	MenuItem saveMazeMenuItem;
	MenuItem editPropertiesMenuItem;
	MenuItem importPropertiesMenuItem;
	MenuItem exportPropertiesMenuItem;
	MenuItem exitMenuItem;
	
	/**
	 * Constructor of the Maze3D Window
	 * @param title - Window title
	 * @param width - Window width
	 * @param height - Window height
	 */
	
	public Maze3DWindow(String title, int width, int height) {
		super(title, width, height);
	}
	
	@Override
	void initWidgets() {
		initMenu();
		
		shell.setLayout(new GridLayout(3, false));
		
		newGameButton = new Button(shell, SWT.PUSH);
		newGameButton.setText("Start New Game");
		newGameButton.setLayoutData(new GridData(SWT.FILL, SWT.NONE, false, false, 2, 1));
		
		this.maze3dDisplayer = new Maze3DDisplayerWidget(shell, SWT.BORDER);
		this.maze3dDisplayer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 5));
		
		Label viewCrossSectionLabel = new Label(shell, SWT.NONE);
		viewCrossSectionLabel.setLayoutData(new GridData(SWT.NONE, SWT.CENTER, false, false));
		viewCrossSectionLabel.setText("View Cross Section ");
		
		//Combo box to select witch layer of Maze3D to View
		this.viewCrossSectionCombo = new Combo(shell, SWT.READ_ONLY);
		this.viewCrossSectionCombo.setLayoutData(new GridData(SWT.NONE, SWT.NONE, false, false));
		this.viewCrossSectionCombo.setItems(new String[] { "XZ", "XY", "ZY" });
		this.viewCrossSectionCombo.select(0);
		
		Label positionLabel = new Label(shell, SWT.NONE);
		positionLabel.setLayoutData(new GridData(SWT.NONE, SWT.CENTER, false, false));
		positionLabel.setText("Position:");
		
		this.positionText = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		this.positionText.setLayoutData(new GridData(SWT.FILL, SWT.NONE, false, false));
		
		this.solveButton = new Button(shell, SWT.PUSH);
		this.solveButton.setText("Solve");
		this.solveButton.setLayoutData(new GridData(SWT.FILL, SWT.NONE, false, false, 2, 1));
		this.solveButton.setEnabled(false);
		
		this.exitButton = new Button(shell, SWT.PUSH);
		this.exitButton.setText("Exit");
		this.exitButton.setLayoutData(new GridData(SWT.FILL, SWT.NONE, false, false, 2, 1));
	}
	
	// initialize the window menu bar
	private void initMenu() 
	{
		Menu menu = new Menu(shell, SWT.BAR);
		
		MenuItem fileMenuItem = new MenuItem(menu, SWT.CASCADE);
		fileMenuItem.setText("File");
		
		// The File Menu
		Menu fileMenu = new Menu(shell, SWT.DROP_DOWN);
		fileMenuItem.setMenu(fileMenu);
		
		this.newGameMenuItem = new MenuItem(fileMenu, SWT.PUSH);
		this.newGameMenuItem.setText("New Game");
		
		new MenuItem(fileMenu, SWT.SEPARATOR);
		
		this.loadMazeMenuItem = new MenuItem(fileMenu, SWT.PUSH);
		this.loadMazeMenuItem.setText("Load maze");
		
		this.saveMazeMenuItem = new MenuItem(fileMenu, SWT.PUSH);
		this.saveMazeMenuItem.setText("Save maze");
		
		new MenuItem(fileMenu, SWT.SEPARATOR);
		
		this.editPropertiesMenuItem = new MenuItem(fileMenu, SWT.PUSH);
		this.editPropertiesMenuItem.setText("Properties");
		
		this.importPropertiesMenuItem = new MenuItem(fileMenu, SWT.PUSH);
		this.importPropertiesMenuItem.setText("Import properties");
		
		this.exportPropertiesMenuItem = new MenuItem(fileMenu, SWT.PUSH);
		this.exportPropertiesMenuItem.setText("Export properties");
		
		new MenuItem(fileMenu, SWT.SEPARATOR);
		
		this.exitMenuItem = new MenuItem(fileMenu, SWT.PUSH);
		this.exitMenuItem.setText("Exit");
		
		shell.setMenuBar(menu);
	}
	
	
	/**
	 * Add a selection to handle user requests to start a new game
	 * @param listener - Listener
	 */
	public void addNewGameSelectionListener(SelectionListener listener) {
		this.newGameButton.addSelectionListener(listener);
		this.newGameMenuItem.addSelectionListener(listener);
	}
	
	/**
	 * Add a listener to handle user requests to change the view Cross Section
	 * @param listener - Listener
	 */
	public void addViewCrossSectionSelectionListener(SelectionListener listener) {
		this.viewCrossSectionCombo.addSelectionListener(listener);
	}
	
	/**
	 * Add a listener to handle user requests to solve the current maze from the current
	 * position
	 * @param listener Listener
	 */
	public void addSolveListener(SelectionListener listener) {
		this.solveButton.addSelectionListener(listener);
	}

	/**
	 * Add a listener to handle user requests to exit the game
	 * @param listener Listener
	 */
	public void addExitListener(Listener listener) {
		this.exitButton.addListener(SWT.Selection, listener);
		this.exitMenuItem.addListener(SWT.Selection, listener);
		this.shell.addListener(SWT.Close, listener);
	}
	
	/**
	 * Add a listener to handle user requests to load a maze
	 * @param listener Listener
	 */
	public void addLoadMazeListener(SelectionListener listener) {
		this.loadMazeMenuItem.addSelectionListener(listener);
	}
	
	/**
	 * Add a listener to handle user requests to save the current maze
	 * @param listener Listener
	 */
	public void addSaveMazeListener(SelectionListener listener) {
		this.saveMazeMenuItem.addSelectionListener(listener);
	}
	
	/**
	 * Add a listener to handle user requests to edit the game properties
	 * @param listener Listener
	 */
	public void addEditPropertiesListener(SelectionListener listener) {
		this.editPropertiesMenuItem.addSelectionListener(listener);
	}
	
	/**
	 * Add a listener to handle user requests to import game properties
	 * @param listener Listener
	 */
	public void addImportPropertiesListener(SelectionListener listener) {
		this.importPropertiesMenuItem.addSelectionListener(listener);
	}
	
	/**
	 * Add a listener to handle user requests to export the current game properties
	 * @param listener Listener
	 */
	public void addExportPropertiesListener(SelectionListener listener) {
		this.exportPropertiesMenuItem.addSelectionListener(listener);
	}
	
	/**
	 * Add a listener to handle keyboard input
	 * @param listener Listener
	 */
	public void addKeyListener(KeyListener listener) {
		maze3dDisplayer.addKeyListener(listener);
	}

	/**
	 * Set the current maze loaded by the game
	 * @param maze Maze
	 * @param characterPosition Game character initial position
	 */
	public void setMaze(Maze3d maze, Position characterPosition) {
		display.syncExec(new Runnable() {
			@Override
			public void run() {
				solveButton.setEnabled(maze != null);
				maze3dDisplayer.setMaze(maze, characterPosition);
				positionText.setText(characterPosition.toString());
			}
		});
	}

	/**
	 * Set the position of the game character
	 * @param position Position
	 */
	public void setCharacterPosition(Position position) {
		display.syncExec(new Runnable() {
			@Override
			public void run() {
				maze3dDisplayer.setCharacterPosition(position);
				positionText.setText(position.toString());
			}
		});
	}

	/**
	 * Set the view Cross Section displayed by the game
	 * @param viewCrossSection - View Cross Section
	 */
	public void setViewPlane(String viewCrossSection) {
		display.syncExec(new Runnable() {
			@Override
			public void run() {
				viewCrossSectionCombo.select(viewCrossSectionCombo.indexOf(viewCrossSection));
				maze3dDisplayer.setCrossSection(viewCrossSection);
			}
		});
	}

	/**
	 * Get the view played displayed by the game
	 * @return String - View Cross Section
	 */
	public String getSelectedViewCrossSection() {
		return viewCrossSectionCombo.getText();
	}

	/**
	 * Set whether the maze was solved
	 * @param solved Whether the maze was solved
	 */
	/*public void setSolved(boolean solved) {
		display.syncExec(new Runnable() {
			@Override
			public void run() {
				solveButton.setEnabled(!solved);
				maze3dDisplayer.setSolved(solved);
			}
		});
	}*/
}
