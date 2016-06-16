package MVP.guiView;

import MVP.presenter.Properties;
import MVP.view.CommonMaze3DView;

public abstract class CommonMaze3DGUIView extends CommonMaze3DView {
	
	protected MazeWindow mainWindow;
	protected Properties properties;
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
	abstract void exitRequest();
	
	
	public MazeWindow getMainWindow() {
		return this.mainWindow;
	}

	public void setMainWindow(MazeWindow mainWindow) {
		this.mainWindow = mainWindow;
	}
}
