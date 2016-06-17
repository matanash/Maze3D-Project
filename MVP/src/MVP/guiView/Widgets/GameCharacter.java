package MVP.guiView.Widgets;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;

import model.maze3d.Position;

public class GameCharacter {
	private static final String icon = "resources/jerry_mouse.png";
	private Position2D position2d;
	private Position Position3d;
	
	public Position2D getPosition2d() {
		return position2d;
	}
	
	public void setPosition2d(Position2D position) {
		this.position2d = position;
	}
	
	public Position getPosition3d() {
		return Position3d;
	}
	
	public void setPosition3d(Position position3d) {
		Position3d = position3d;
	}
	
	public void draw(PaintEvent e, int cellWidth, int cellHeight) {
		e.gc.setBackground(new Color(null, 255, 255, 255));
		Image image = new Image(null, icon);
		e.gc.drawImage(image, 0, 0, image.getBounds().width, image.getBounds().height, position2d.getX() * cellWidth, position2d.getY() * cellHeight, cellWidth, cellHeight);
	}
}
