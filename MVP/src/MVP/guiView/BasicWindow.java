package MVP.guiView;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

// TODO: Auto-generated Javadoc
/**
 * BasicWindow represents what every window with event driven GUI should implement.
 **/

public abstract class BasicWindow implements Runnable {
	
	/** The display. */
	protected Display display;
	
	/** The shell. */
	protected Shell shell;
	
	/**
	 * Instantiates a new basic window.
	 *
	 * @param title the title
	 * @param width the width
	 * @param height the height
	 */
	public BasicWindow(String title, int width,int height) {
		display =new Display();
		shell = new Shell(display);
 		shell.setSize(width, height);
 		shell.setText(title);
	}
	
	/**
	 * Inits the widgets.
	 */
	protected abstract void initWidgets();
	
	/**
 	 * Initializing each window's widgets.
 	 */
	public void run() {
 		
 		initWidgets();	
		shell.open();
		
		// main event loop
		while(!shell.isDisposed()){ // while window isn't closed

		    // 1. read events, put then in a queue.
		    // 2. dispatch the assigned listener
		    if(!display.readAndDispatch()){ 	// if the queue is empty
		       display.sleep(); 			// sleep until an event occurs 
		    }

		 } // shell is disposed

		 display.dispose(); // dispose OS components
	}	
	
	/**
	 * Used to dispose the window manually. 
	 */
	
	public void exit()
	{
		shell.dispose(); 
		System.exit(0);
	}

	/**
	 * Gets the display.
	 *
	 * @return the display
	 */
	public Display getDisplay() {
		return this.display;
	}

	/**
	 * Sets the display.
	 *
	 * @param display the new display
	 */
	public void setDisplay(Display display) {
		this.display = display;
	}

	/**
	 * Gets the shell.
	 *
	 * @return the shell
	 */
	public Shell getShell() {
		return this.shell;
	}

	/**
	 * Sets the shell.
	 *
	 * @param shell the new shell
	 */
	public void setShell(Shell shell) {
		this.shell = shell;
	}
	
}
