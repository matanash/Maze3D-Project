package MVP.presenter;

import java.util.HashMap;

import MVP.model.Model;
import MVP.view.View;

// TODO: Auto-generated Javadoc
/**
 * The Class CommonPresenter.
 */
public abstract class CommonPresenter implements Presenter {
	
	/** The view. */
	protected View view;
	
	/** The model. */
	protected Model model;
	
	/** The model commands map. */
	protected HashMap<String, Command> modelCommandsMap;
	
	/** The view commands map. */
	protected HashMap<String, Command> viewCommandsMap;
	
	/** The properties. */
	protected Properties properties ;
	
	/**
	 * Instantiates a new common presenter.
	 *
	 * @param view the view
	 * @param model the model
	 */
	public CommonPresenter(View view, Model model) {
		
		this.view = view;
		this.model = model;
		this.model.loadGZipMaps();
		initCommandsMaps();
	}
	
	/**
	 * Inits the commands maps.
	 */
	public abstract void initCommandsMaps();
	

	/**
	 * Gets the view.
	 *
	 * @return the view
	 */
	public View getView() {
		return this.view;
	}

	/**
	 * Sets the view.
	 *
	 * @param view the new view
	 */
	public void setView(View view) {
		this.view = view;
	}

	/**
	 * Gets the model.
	 *
	 * @return the model
	 */
	public Model getModel() {
		return this.model;
	}

	/**
	 * Sets the model.
	 *
	 * @param model the new model
	 */
	public void setModel(Model model) {
		this.model = model;
	}
	
	/**
	 * Gets the properties.
	 *
	 * @return the properties
	 */
	public Properties getProperties() {
		return this.properties;
	}

	/**
	 * Sets the properties.
	 *
	 * @param prop the new properties
	 */
	public void setProperties(Properties prop) {
		this.properties = prop;
		if (model != null)
			this.model.setProperties(prop);
		if (view!=null)
			this.view.setProperties(prop);
	}
}
