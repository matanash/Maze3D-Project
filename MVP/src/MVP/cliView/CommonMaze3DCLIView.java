package MVP.cliView;

import java.io.BufferedReader;
import java.io.PrintWriter;

import MVP.view.CommonMaze3DView;

// TODO: Auto-generated Javadoc
/**
 * This abstract class represents the CLI interfaces in the MVP.
 * @author Matan Ashkenazi and Noee Cohen
 */

public abstract class CommonMaze3DCLIView extends CommonMaze3DView {
	
	/** The in. */
	protected BufferedReader in;
	
	/** The out. */
	protected PrintWriter out;
	
	/**
	 * Instantiates a new common maze 3 DCLI view.
	 *
	 * @param in the in
	 * @param out the out
	 */
	public CommonMaze3DCLIView(BufferedReader in , PrintWriter out) {
		this.in = in;
		this.out = out;
	}

	/**
	 * Gets the out.
	 *
	 * @return the out
	 */
	public PrintWriter getOut() {
		return this.out;
	}
}
