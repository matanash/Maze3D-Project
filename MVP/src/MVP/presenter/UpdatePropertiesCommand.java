package MVP.presenter;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class UpdatePropertiesCommand extends CommonCommand {

	public UpdatePropertiesCommand(Presenter p) {
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
				if(presenter.getProperties().isDebugMode())
					System.out.println("File not found, default properties will be loaded .");
				properties = new Properties();
				properties.setDefaults();
			}
			presenter.setProperties(properties);
		}
		else
		{
			presenter.getView().displayMessage("Invalid arguments .");
		}

	}

	@Override
	public void help() {
		// TODO Auto-generated method stub

	}

}
