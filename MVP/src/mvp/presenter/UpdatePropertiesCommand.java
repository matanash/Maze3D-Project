package mvp.presenter;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import mvp.view.commands.DisplayMessageCLICommand;

public class UpdatePropertiesCommand extends CommonCommand {

	public UpdatePropertiesCommand(MyPresenter p) {
		super(p);
	}

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
					this.myPresenter.getView().display("File not found, default properties will be loaded .", new DisplayMessageCLICommand(this.myPresenter.getView()));
				properties = new Properties();
				properties.setDefaults();
			}
			myPresenter.setProperties(properties);
			myPresenter.getView().display(properties.toString(), new DisplayMessageCLICommand(this.myPresenter.getView()));
		}
		else
		{
			myPresenter.getView().display("Invalid arguments", new DisplayMessageCLICommand(this.myPresenter.getView()));
		}

	}

	@Override
	public void help() {}

}
