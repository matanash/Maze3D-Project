package MVP.guiView.Widgets;

public class Position2D {
	private int x;
	private  int y;
	
	public Position2D(int x, int y) {		
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object obj) {
		Position2D p = (Position2D)obj;
		return (x == p.x && y == p.y);
	}

	@Override
	public String toString() {
		return "{" + x + "," + y + "}";
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
