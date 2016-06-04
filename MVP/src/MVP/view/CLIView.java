package MVP.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.util.Observable;

public class CLIView extends Observable
{
	private BufferedReader in;
	private Writer out;	
	
	public CLIView(BufferedReader in, Writer out) {
		this.in = in;
		this.out = out;
	}

	public void start() {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run()  {
				 
				try {					
					String line = null;
					do {
						out.write("Enter command: ");
						out.flush();
						line = in.readLine().toLowerCase();
						setChanged();
						notifyObservers(line);
						out.append('\n');
					} while (!(line.equals("exit")));
					setChanged();
					notifyObservers("exit");
					out.close();
					
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}						
			}
			
		});
		t.start();
	}


	
	

}