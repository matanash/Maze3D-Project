package mvp.guiView;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Display;

import mvp.presenter.Properties;
import mvp.view.commands.DisplayCLICommand;

public class Maze3DGUIView extends CommonMaze3DGUIView {
	
	/** The main window of the view. */
	protected MazeWindow mainWindow;
	
	
	/**
	 * Instantiates a new observable GUI view.
	 * @param title the window title
	 * @param width the window width
	 * @param height the window height
	 */
	public Maze3DGUIView(String title, int width, int height,String xmlFilePath) {
			super(new Properties());
			properties.setDefaults();
			mainWindow = new MazeWindow(title, width, height , properties,xmlFilePath);
		
	////////////////////////  the selection listener that sets the behavior of - solve request - in this specific MVP  ////////////
			mainWindow.setSolveListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				setChanged();
				notifyObservers("solve "+mainWindow.maze3dProperties.getName()+" "+properties.getSolveAlgorithm());
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
			
	////////////////////////  the selection listener that sets the behavior of - properties update request - in this specific MVP  ////////////
			mainWindow.setPropertiesUpdateListener(new SelectionListener() {
	
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					
					setChanged();
					notifyObservers("update properties "+mainWindow.getSelectedXMLpropertiesFilePath());
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {}
			});
////////////////////////  the key listener that sets the behavior of - movements requests - in this specific MVP  ////////////
		mainWindow.setKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent arg0) {}
			
			@Override
			public void keyPressed(KeyEvent key) {
				switch(key.keyCode)
				{
				case SWT.ARROW_UP:
					if (properties.isDebugMode() == true)
						System.out.println("up key pressed");
					setChanged();
					notifyObservers("movementRequest UP " + mainWindow.maze3dProperties.getName());
					break;
				case SWT.ARROW_DOWN:
					if (properties.isDebugMode() == true)
						System.out.println("down key pressed");
					setChanged();
					notifyObservers("movementRequest DOWN "+ mainWindow.maze3dProperties.getName());
					break;
				case SWT.ARROW_LEFT:
					if (properties.isDebugMode() == true)
						System.out.println("left key pressed");
					setChanged();
					notifyObservers("movementRequest LEFT "+ mainWindow.maze3dProperties.getName());
					break;
				case SWT.ARROW_RIGHT:
					if (properties.isDebugMode() == true)
						System.out.println("right key pressed");
					setChanged();
					notifyObservers("movementRequest RIGHT "+ mainWindow.maze3dProperties.getName());
					break;
				case SWT.PAGE_UP:
					if (properties.isDebugMode() == true)
						System.out.println("lvl up key pressed");
					setChanged();
					notifyObservers("movementRequest LVLUP "+ mainWindow.maze3dProperties.getName());
					break;
				case SWT.PAGE_DOWN:
					if (properties.isDebugMode() == true)
						System.out.println("lvl down key pressed");
					setChanged();
					notifyObservers("movementRequest LVLDOWN "+ mainWindow.maze3dProperties.getName());
					break;
				}
				
			}
		});
		
////////////////////////  the selection listener that sets the behavior of - exit request - in this specific MVP  ////////////
		mainWindow.setExitListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent arg0) {
				exitRequest();
				
			}
		});
////////////////////////  the selection listener that sets the behavior of - generate new maze request - in this specific MVP  ////////////		
		mainWindow.setGenerateListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Display.getDefault().syncExec(new Runnable() {
				    public void run() {
				    	setChanged();
				    	notifyObservers("generate 3d maze "+ mainWindow.maze3dProperties.getName()+" "+ mainWindow.maze3dProperties.getHeight()+" "+mainWindow.maze3dProperties.getLength() +" "+ mainWindow.maze3dProperties.getWidth());
				    }
				});
				    
			 }
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});

////////////////////////  the selection listener that sets the behavior of - maze save request - in this specific MVP  ////////////
		mainWindow.setSaveListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				setChanged();
				notifyObservers("save maze "+ mainWindow.maze3dProperties.getName()+" " + mainWindow.getMazePath());
								
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
////////////////////////  the selection listener that sets the behavior of - maze load request - in this specific MVP  ////////////		
		mainWindow.setLoadListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				setChanged();
				notifyObservers("load maze " + mainWindow.getMazePath()+ " " +mainWindow.maze3dProperties.getName());
				
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		mainWindow.run();
	}
	
	@Override
	public void startView() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitView() {
		mainWindow.exit();

	}

	@Override
	public void display(Object obj, DisplayCLICommand dc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setProperties(Properties prop) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void exitRequest() {
		setChanged();
		notifyObservers("exit");
		
	}
	
	
}
