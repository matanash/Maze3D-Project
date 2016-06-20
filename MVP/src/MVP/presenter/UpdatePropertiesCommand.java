package MVP.presenter;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import MVP.view.commands.DisplayMessageViewCommand;

// TODO: Auto-generated Javadoc
/**
 * The Class UpdatePropertiesCommand.
 */
public class UpdatePropertiesCommand extends CommonCommand {

	/**
	 * Instantiates a new update properties command.
	 *
	 * @param p the p
	 */
	public UpdatePropertiesCommand(MyPresenter p) {
		super(p);
	}

	/* (non-Javadoc)
	 * @see MVP.presenter.CommonCommand#doCommand(java.lang.String[])
	 */
	@Override
	public void doCommand(String[] args) throws Exception {
		if (args.length==1)
		{
			Properties properties;
			try {
				FileInputStream in = new FileInputStream(args[0]);
				XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(in));
				properties = (Properties)decoder.readObject();
				decoder.close();
		
			} catch (FileNotFoundException e) {
				if(myPresenter.getProperties().isDebugMode())
					this.myPresenter.getView().display("File not found, default properties will be loaded .", new DisplayMessageViewCommand(this.myPresenter.getView()));
				properties = new Properties();
				properties.setDefaults();
			}
			myPresenter.setProperties(properties);
			myPresenter.getView().display(properties.toString(), new DisplayMessageViewCommand(this.myPresenter.getView()));
		}
		else
		{
			myPresenter.getView().display("Invalid arguments", new DisplayMessageViewCommand(this.myPresenter.getView()));
		}

	}

	/* (non-Javadoc)
	 * @see MVP.presenter.CommonCommand#help()
	 */
	@Override
	public void help() {}

}
