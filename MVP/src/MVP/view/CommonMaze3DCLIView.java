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
	
	@Override
	public PrintWriter getOut() {
		return this.out;
	}
}
