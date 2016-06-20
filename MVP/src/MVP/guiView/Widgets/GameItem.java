package MVP.guiView.Widgets;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Image;

import model.maze3d.Position;

// TODO: Auto-generated Javadoc
/**
 * The Class GameItem.
 */
public class GameItem {
	
	/** The icon. */
	private final String icon ;
	
	/** The position 2 d. */
	private Position2D position2d;
	
	/** The Position 3 d. */
	private Position Position3d;
	
	/**
	 * Instantiates a new game item.
	 *
	 * @param icon the icon
	 * @param position2d the position 2 d
	 * @param position3d the position 3 d
	 */
	public GameItem(final String icon,Position2D position2d,Position position3d) {
		this.icon = icon;
		this.position2d = position2d;
		this.Position3d = position3d;
	}
	
	/**
	 * Gets the position 2 d.
	 *
	 * @return the position 2 d
	 */
	public Position2D getPosition2d() {
		return position2d;
	}
	
	/**
	 * Sets the position 2 d.
	 *
	 * @param position the new position 2 d
	 */
	public void setPosition2d(Position2D position) {
		this.position2d = position;
	}
	
	/**
	 * Gets the position 3 d.
	 *
	 * @return the position 3 d
	 */
	public Position getPosition3d() {
		return this.Position3d;
	}
	
	/**
	 * Sets the position 3 d.
	 *
	 * @param position3d the new position 3 d
	 */
	public void setPosition3d(Position position3d) {
		Position3d = position3d;
	}
	
	/**
	 * Draw.
	 *
	 * @param e the e
	 * @param destX the dest X
	 * @param destY the dest Y
	 * @param destWidth the dest width
	 * @param destHeight the dest height
	 */
	public void draw(PaintEvent e, int destX, int destY, int destWidth, int destHeight)
	{
		Image image = new Image(null, icon);
		e.gc.drawImage(image, 0, 0, image.getBounds().width, image.getBounds().height,destX,destY,destWidth,destHeight);
	}
}
