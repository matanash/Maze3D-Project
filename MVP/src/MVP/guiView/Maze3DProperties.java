package MVP.guiView;
import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The  Maze Properties Class.
 * this class collecting all the data of the current maze.
 */
public class Maze3DProperties implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The name. */
	protected String name;
	
	/** The height. */
	protected int height;
	
	/** The length. */
	protected int length;
	
	/** The width. */
	protected int width;
	
	/**
	 * Instantiates a new maze properties.
	 * all the data will get default values.
	 */
	public Maze3DProperties() {
		
		this.name = "Maze";
		this.height = 11;
		this.length  = 11;
		this.width = 11;
		
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the height.
	 *
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Sets the height.
	 *
	 * @param height the new height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Gets the length.
	 *
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Sets the length.
	 *
	 * @param length the new length
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * Gets the width.
	 *
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Sets the width.
	 *
	 * @param width the new width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

}