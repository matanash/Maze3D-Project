package MVP.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public abstract class BasicWindow implements Runnable {

	boolean ownDisplay = false;
	Display display;
	Shell shell;
	
	public BasicWindow(String title, int width,int height) 
	{
 		display = Display.getCurrent();
 		if(display == null) {
 			display = new Display();
 			ownDisplay = true;
 		}
		
		shell = new Shell(display);
 		shell.setSize(width, height);
 		shell.setText(title);
 		
 		initWidgets();
	}
	
	abstract void initWidgets();
	
	@Override
	public void run() {
		shell.open();
		
		// main event loop
		while(!shell.isDisposed())
		{
			// read events, put then in a queue and dispatch the assigned listener
		    
		    if(!display.readAndDispatch())  // if the queue is empty
		       display.sleep(); // sleep until an event occurs 
		}

		if(ownDisplay)
			display.dispose(); // dispose OS components
	}
	
	/*public void close() {
		shell.dispose();
	}
	
	
	
	public void displayMessage(int icon, String title, String message) {
		display.syncExec(new Runnable() {
			@Override
			public void run() {
				MessageBox messageBox = new MessageBox(shell, icon);
				messageBox.setText(title);
				messageBox.setMessage(message);
				messageBox.open();
			}
		});
	}
	
	public void displayInformation(String title, String message) {
		displayMessage(SWT.ICON_INFORMATION, title, message);
	}
	
	public void displayError(String title, String message) {
		displayMessage(SWT.ICON_ERROR, title, message);
	}
	
	public int displayQuestion(String title, String message) {
		Response<Integer> response = new Response<Integer>();
		display.syncExec(new Runnable() {
			@Override
			public void run() {
				MessageBox messageBox = new MessageBox(shell, SWT.ICON_QUESTION | SWT.YES | SWT.NO);
				messageBox.setText(title);
				messageBox.setMessage(message);
				response.set(messageBox.open());
			}
		});
		return response.get();
	}
	
	public String displayFileDialog(int style, String title, String[] filterExtensions) {
		Response<String> response = new Response<String>();
		display.syncExec(new Runnable() {
			@Override
			public void run() {
				FileDialog fileDialog = new FileDialog(shell, style);
				fileDialog.setText(title);
				fileDialog.setFilterExtensions(filterExtensions);
				response.set(fileDialog.open());
			}
		});
		return (String) response.get();
	}
	
	
	// inner static class 
	static private class Response<T> {
		private T value;
		public void set(T value) { this.value = value; }
		public T get() { return value; }
	}*/
}
