package mvp.guiView;

import mvp.presenter.Properties;
import mvp.view.CommonMaze3DView;

public abstract class CommonMaze3DGUIView extends CommonMaze3DView {
	
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
}
