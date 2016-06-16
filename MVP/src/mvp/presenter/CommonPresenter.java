package mvp.presenter;

import java.util.HashMap;

import mvp.model.Model;
import mvp.view.View;

public abstract class CommonPresenter implements Presenter {
	protected View view;
	protected Model model;
	
	protected HashMap<String, Command> modelCommandsMap;
	protected HashMap<String, Command> viewCommandsMap;
	protected Properties properties ;
	
	public CommonPresenter(View view, Model model) {
		
		this.view = view;
		this.model = model;
		this.model.loadGZipMaps();
		initCommandsMaps();
	}
	
	public abstract void initCommandsMaps();
	

	public View getView() {
		return this.view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public Model getModel() {
		return this.model;
	}

	public void setModel(Model model) {
		this.model = model;
	}
	
	public Properties getProperties() {
		return this.properties;
	}

	public void setProperties(Properties prop) {
		this.properties = prop;
		if (model != null)
			this.model.setProperties(prop);
		if (view!=null)
			this.view.setProperties(prop);
	}
}
