package model.maze3d;

import java.io.Serializable;

/**
 * This class Represents a 3D Position in the 3D maze
 * @author Matan Ashkenazi and Noee Cohen
 * @version - 1.0
 */

public class Position implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int y; // height coordinate
	private int x; // length coordinate
	private int z; // width coordinate	
	
	/**
	 * Position C'tor
	 * @param y - y Coordinate to set
	 * @param x - x Coordinate to set
	 * @param z - z Coordinate to set
	 */
	public Position(int y, int x, int z) 
	{
		this.y = y;
		this.x = x;
		this.z = z;
	}
	
	
	/**
	 * This method get Y Coordinate of the Position
	 * @return yCoordinate of the Position
	 */
	public int getY() {return this.y;}
	
	/**
	 * This method set the Y Coordinate of the Position
	 * @param y - y Coordinate to set
	 */
	public void setY(int y) {this.y = y;}
	
	/**
	 * This method get X Coordinate of the Position
	 * @return xCoordinate of the Position
	 */
	public int getX() {return this.x;}
	
	/**
	 * This method set the X Coordinate of the Position
	 * @param x - x Coordinate to set
	 */
	public void setX(int x) {this.x = x;}
	
	/**
	 * This method get Z Coordinate of the Position
	 * @return zCoordinate of the Position
	 */
	public int getZ() {return this.z;}
	
	/**
	 * This method set the Z Coordinate of the Position
	 * @param z - z Coordinate to set
	 */
	public void setZ(int z){this.z = z;}
	
	/**
	 * Add one position to another
	 * @param pos - Position to add
	 * @return result Position after adding
	 */
	public Position add(Position pos) { return new Position(this.y + pos.y, this.x + pos.x, this.z + pos.z);}
	
	/**
	 * Check if the positions are equals
	 * @param pos - Position to check with
	 * @return True or false
	 */
	public boolean equals(Position pos) { return (this.y == pos.y && this.x == pos.x && this.z == pos.z);}
	
	@Override
	public String toString(){ return ("{" + this.y + ", " + this.x + ", " + this.z + "}"); }

}
