package MVP.guiView;

import MVP.presenter.Properties;
import MVP.view.CommonMaze3DView;

// TODO: Auto-generated Javadoc
/**
 * The Class CommonMaze3DGUIView.
 */
public abstract class CommonMaze3DGUIView extends CommonMaze3DView {
	
	/** The main window. */
	protected MazeWindow mainWindow;
	
	/** The properties. */
	protected Properties properties;
	
	/**
	 * Instantiates a new common maze 3 DGUI view.
	 *
	 * @param properties the properties
	 */
	public CommonMaze3DGUIView(Properties properties) {
		super();
		this.properties = properties;
	}
	
	/**
	 * Sets the debug mode.
	 *
	 * @param debug the new debug mode
	 */
	public void setDebugMode(boolean debug) {
		properties.setDebugMode(debug);
		
	}
	//abstract void exitRequest();
	
	
	/**
	 * Gets the main window.
	 *
	 * @return the main window
	 */
	public MazeWindow getMainWindow() {
		return this.mainWindow;
	}

	/**
	 * Sets the main window.
	 *
	 * @param mainWindow the new main window
	 */
	public void setMainWindow(MazeWindow mainWindow) {
		this.mainWindow = mainWindow;
	}
}
