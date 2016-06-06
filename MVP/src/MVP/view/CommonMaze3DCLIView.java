package MVP.view;

import java.io.BufferedReader;
import java.io.PrintWriter;
/**
 * This abstract class represents the CLI interfaces in the MVP.
 * @author Matan Ashkenazi and Noee Cohen
 */

public abstract class CommonMaze3DCLIView extends CommonMaze3DView {
	protected BufferedReader in;
	protected PrintWriter out;
	
	public CommonMaze3DCLIView(BufferedReader in , PrintWriter out) {
		this.in = in;
		this.out = out;
	}

	/**
	 * @return the in
	 */
	public BufferedReader getIn() {
		return in;
	}

	/**
	 * @param in the in to set
	 */
	public void setIn(BufferedReader in) {
		this.in = in;
	}

	/**
	 * @return the out
	 */
	public PrintWriter getOut() {
		return out;
	}

	/**
	 * @param out the out to set
	 */
	public void setOut(PrintWriter out) {
		this.out = out;
	}
	
}
