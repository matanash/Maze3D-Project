package MVP.guiView.Widgets;

// TODO: Auto-generated Javadoc
/**
 * The Class Position2D.
 */
public class Position2D {
	
	/** The x. */
	private int x;
	
	/** The y. */
	private  int y;
	
	/**
	 * Instantiates a new position 2 D.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public Position2D(int x, int y) {		
		this.x = x;
		this.y = y;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		Position2D p = (Position2D)obj;
		return (x == p.x && y == p.y);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "{" + x + "," + y + "}";
	}

	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * Sets the x.
	 *
	 * @param x the new x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * Sets the y.
	 *
	 * @param y the new y
	 */
	public void setY(int y) {
		this.y = y;
	}
	
}
