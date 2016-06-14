package MVP.view;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;

public class Maze2DDisplayerWidget extends Maze3DDisplayerWidget {

	public Maze2DDisplayerWidget(Composite parent, int style) {
		super(parent, style);
		setBackground(new Color(null, 255, 255, 255));
	}

}
