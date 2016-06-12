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
	Combo viewCrossCombo;
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
	
	// initialize the window menu bar
	private void initMenu() 
	{
		Menu menu = new Menu(shell, SWT.BAR);
		
		MenuItem fileMenuItem = new MenuItem(menu, SWT.CASCADE);
		fileMenuItem.setText("File");
		
		Menu fileMenu = new Menu(shell, SWT.DROP_DOWN);
		fileMenuItem.setMenu(fileMenu);
		
		newGameMenuItem = new MenuItem(fileMenu, SWT.PUSH);
		newGameMenuItem.setText("New game");
		
		new MenuItem(fileMenu, SWT.SEPARATOR);
		
		loadMazeMenuItem = new MenuItem(fileMenu, SWT.PUSH);
		loadMazeMenuItem.setText("Load maze");
		
		saveMazeMenuItem = new MenuItem(fileMenu, SWT.PUSH);
		saveMazeMenuItem.setText("Save maze");
		
		new MenuItem(fileMenu, SWT.SEPARATOR);
		
		editPropertiesMenuItem = new MenuItem(fileMenu, SWT.PUSH);
		editPropertiesMenuItem.setText("Properties");
		
		importPropertiesMenuItem = new MenuItem(fileMenu, SWT.PUSH);
		importPropertiesMenuItem.setText("Import properties");
		
		exportPropertiesMenuItem = new MenuItem(fileMenu, SWT.PUSH);
		exportPropertiesMenuItem.setText("Export properties");
		
		new MenuItem(fileMenu, SWT.SEPARATOR);
		
		exitMenuItem = new MenuItem(fileMenu, SWT.PUSH);
		exitMenuItem.setText("Exit");
		
		shell.setMenuBar(menu);
	}
	
	
	@Override
	void initWidgets() {
		/*initMenu();
		
		shell.setLayout(new GridLayout(3, false));
		
		newGameButton = new Button(shell, SWT.PUSH);
		newGameButton.setText("Start new game");
		newGameButton.setLayoutData(new GridData(SWT.FILL, SWT.NONE, false, false, 2, 1));
		
		Maze3DDisplayerWidget = new Maze3DDisplayerWidget(shell, SWT.BORDER);
		Maze3DDisplayerWidget.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 5));
		
		Label viewPlaneLabel = new Label(shell, SWT.NONE);
		viewPlaneLabel.setLayoutData(new GridData(SWT.NONE, SWT.CENTER, false, false));
		viewPlaneLabel.setText("View Plane");
		
		viewPlaneCombo = new Combo(shell, SWT.READ_ONLY);
		viewPlaneCombo.setLayoutData(new GridData(SWT.NONE, SWT.NONE, false, false));
		viewPlaneCombo.setItems(new String[] { "XZ", "XY", "ZY" });
		viewPlaneCombo.select(0);
		
		Label positionLabel = new Label(shell, SWT.NONE);
		viewPlaneLabel.setLayoutData(new GridData(SWT.NONE, SWT.CENTER, false, false));
		positionLabel.setText("Position:");
		
		positionText = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		positionText.setLayoutData(new GridData(SWT.FILL, SWT.NONE, false, false));
		
		solveButton = new Button(shell, SWT.PUSH);
		solveButton.setText("Solve");
		solveButton.setLayoutData(new GridData(SWT.FILL, SWT.NONE, false, false, 2, 1));
		solveButton.setEnabled(false);
		
		exitButton = new Button(shell, SWT.PUSH);
		exitButton.setText("Exit");
		exitButton.setLayoutData(new GridData(SWT.FILL, SWT.NONE, false, false, 2, 1));*/
	}

}
