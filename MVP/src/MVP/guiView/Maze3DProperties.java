package MVP.guiView;
import java.io.Serializable;

/**
 * The  Maze Properties Class.
 * this class collecting all the data of the current maze.
 */
public class Maze3DProperties implements Serializable {

	private static final long serialVersionUID = 1L;
	protected String name;
	protected int height;
	protected int length;
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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

}