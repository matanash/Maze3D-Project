package MVP.guiView;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Display;
import MVP.presenter.Properties;
import algorithms.search.Solution;
import model.maze3d.Maze3d;
import model.maze3d.Position;

public class Maze3DGUIView extends CommonMaze3DGUIView {

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
			
	}
	
	@Override
	public void startView() throws Exception {
		mainWindow.getShell().addMouseWheelListener(new MouseWheelListener() {
			
			@Override
			public void mouseScrolled(MouseEvent arg0) {
				if ((arg0.stateMask&SWT.CONTROL)==0)
					mainWindow.getMaze2dWidget().setSize(mainWindow.getMaze2dWidget().getSize().x+arg0.count,mainWindow.getMaze2dWidget().getSize().y+arg0.count);
				
			}
		});
		
		////////////////////////the selection listener that sets the behavior of - display maze request - in this specific MVP  ////////////
		mainWindow.setDisplayMazeListener(new SelectionListener() {
		
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
				setChanged();
				notifyObservers("display "+mainWindow.maze3dProperties.getName());
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		////////////////////////the selection listener that sets the behavior of - display solution request - in this specific MVP  ////////////
		mainWindow.setDisplaySolutionListener(new SelectionListener() {
		
		@Override
		public void widgetSelected(SelectionEvent arg0) {
		setChanged();
		notifyObservers("display solution "+mainWindow.maze3dProperties.getName());
		}
		
		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		////////////////////////the selection listener that sets the behavior of - display goal position request - in this specific MVP  ////////////
		mainWindow.setDisplayGoalPositionListener(new SelectionListener() {
		
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			setChanged();
			notifyObservers("display goal position "+mainWindow.maze3dProperties.getName());
		}
		
		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {}
		});

		////////////////////////  the selection listener that sets the behavior of - solve request - in this specific MVP  ////////////
		mainWindow.setSolveListener(new SelectionListener() {
		
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			String y =Integer.toString(mainWindow.getMaze2dWidget().getGameCharacter().getPosition3d().getY());
			String x =Integer.toString(mainWindow.getMaze2dWidget().getGameCharacter().getPosition3d().getY());
			String z =Integer.toString(mainWindow.getMaze2dWidget().getGameCharacter().getPosition3d().getY());
			setChanged();
			notifyObservers("solve "+mainWindow.maze3dProperties.getName()+ " " + y + " " + x + " " + z);
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
	
	////////////////////////the selection listener that sets the behavior of - exit request - in this specific MVP  ////////////
	mainWindow.setExitListener(new DisposeListener() {
		
		@Override
		public void widgetDisposed(DisposeEvent arg0) {
			setChanged();
			notifyObservers("exit");
		}
	});
	////////////////////////the selection listener that sets the behavior of - generate new maze request - in this specific MVP  ////////////		
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

	////////////////////////the selection listener that sets the behavior of - maze save request - in this specific MVP  ////////////
	mainWindow.setSaveListener(new SelectionListener() {
		
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			setChanged();
			notifyObservers("save maze "+ mainWindow.maze3dProperties.getName()+" " + mainWindow.getMazeFilePath());
							
		}
		
		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {}
	});
	////////////////////////the selection listener that sets the behavior of - maze load request - in this specific MVP  ////////////		
	mainWindow.setLoadListener(new SelectionListener() {
		
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			setChanged();
			notifyObservers("load maze " + mainWindow.maze3dProperties.getName()+ " " + mainWindow.getMazeFilePath());
			
			
		}
		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {}
	});
	
	mainWindow.run();

}
	@Override
	public void setProperties(Properties prop) {}
	
	@Override
	public void exitView() {
		mainWindow.exit();
	}


	@Override
	public void displayMessage(String message) {
		mainWindow.displayMessage(message);
	}
	@Override
	public void displayCrossSectionByCommand(int[][] matrix) {}

	@Override
	public void displaySolution(Solution solution) {

		mainWindow.displayWalkToGoalPosition(solution);
		
	}

	@Override
	public void displayMaze(Maze3d maze3d) {

		mainWindow.displayMaze(maze3d);
	}

	@Override
	public void displayPosition(Position goalPosition) {
		mainWindow.setGoalPositionText(goalPosition.toString());
	}

	
}
