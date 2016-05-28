package model.maze3d;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class represents a 3D Maze
 * @author MatanA
 */
public class Maze3d 
{
	private int [][][] maze3d; // 3d array represent the maze
	private int height; // Y coordinate size
	private int length; // X coordinate size
	private int width; //  Z coordinate size
	
	private Position startPosition; // The Maze Entrance
	private Position goalPosition; // The Maze Exit
	
	public static final int WALL  =1;
	public static final int EMPTY =0;
	
	/**
	* Maze3d C'tor
	 * @param height - the height of the maze to set
	 * @param length - the length of the maze to set
	 * @param width  - the width of the  maze to set
	 * @throws Exception - invalid Dimensions Exception
	 */
	
	public Maze3d(int height, int length, int width) throws Exception 
	{
		if (height < 3 || length < 4 || width < 4)
			throw new Exception ("Invalid Dimensions");
		
		this.maze3d = new int [height][length][width];
		this.height = height;
		this.length = length;
		this.width = width;
	}
	/**
	 * Maze3d C'tor
	 * @param array - maze3d in byte array representation
	 * @throws Exception - invalid Dimensions Exception
	 */
	public Maze3d(byte[] array) throws Exception
	{
		ByteBuffer buf = ByteBuffer.wrap(array,0,array.length);
		
		this.height = buf.getInt();
		this.length = buf.getInt();
		this.width  = buf.getInt();
		
		if (this.height<3 || this.length <4 || this.width<4)
			throw new Exception ("Invalid Dimensions");
		
		this.startPosition = new Position(buf.getInt(),buf.getInt(),buf.getInt());
		this.goalPosition = new Position(buf.getInt(),buf.getInt(),buf.getInt());
		

		this.maze3d = new int[height][length][width];	
		
		for (int y=0;y<this.height;y++)
		{
			for (int x=0;x<this.length;x++)
			{
				for (int z=0;z<this.width;z++)
				{
					this.maze3d[y][x][z] = buf.getInt();
				}
			}
		}
		
	}
	
	/**
	 * This method get the maze height
	 * @return Maze height
	 */
	public int getHeight() {return height;}
	
	
	/**
	 * This method set maze height
	 * @param height- height of Maze to initialize
	 * @throws Exception - invalid height dimension exception
	 */
	public void setHeight(int height) throws Exception 
	{
		if (height<3)
			throw new Exception("Invalid height dimension");
		this.height = height;
	}
	
	
	/**
	 * This method get the maze length
	 * @return Maze Length
	 */
	public int getLength() {return length;}
	
	
	/**
	 * This method set the maze length
	 * @param length- length of Maze to initialize
	 * @throws Exception - invalid length dimension exception
	 */
	public void setLength(int length) throws Exception
	{
		if (length<4)
			throw new Exception("Invalid length dimension");
		this.length = length;
	}
	
	
	/**
	 * This method get the maze width
	 * @return Maze Width
	 */
	public int getWidth() {return width;}
	
	
	/**
	 * This method set the maze width
	 * @param width- width of Maze to initialize
	 * @throws Exception - invalid width dimension exception
	 */
	public void setWidth(int width) throws Exception
	{
		if (width<4)
			throw new Exception("Invalid width dimension");
		this.width = width;
	}
	
	/**
	 * @return the maze3d
	 */
	public int[][][] getMaze3d() {
		return this.maze3d;
	}
	/**
	 * @param maze3d the maze3d to set
	 */
	public void setMaze3d(int[][][] maze3d) {
		this.maze3d = maze3d;
	}
	/**
	 * This method get the Start Position of the Maze
	 * @return startPosition of the maze
	 */
	public Position getStartPosition() {return this.startPosition;}

	/**
	 * This method set the Start Position of the Maze
	 * @param startPos - the maze Entrance position to set
	 * @throws Exception - invalid entrance position exception
	 */
	public void setStartPosition (Position startPos) throws Exception
	{
		
		if (isOnEdge(startPos))
		{
			this.startPosition=startPos;
			this.setEmpty(this.startPosition);
		}
		else
			throw new Exception("Position isn't on maze's edges");
	}
	
	/**
	 * This method get the goal Position of the Maze
	 * @return goal Position of the maze
	 */
	public Position getGoalPosition() {return this.goalPosition;}
	
	/**
	 * This method set the goal Position of the Maze
	 * @param goalPos - the maze Exit position to set
	 * @throws Exception - invalid exit position exception
	 */

	public void setGoalPosition(Position goalPos) throws Exception
	{
		
		if (isOnEdge(goalPos))
		{
			this.goalPosition=goalPos;
			this.setEmpty(this.goalPosition);
		}
		else
			throw new Exception();
	}
	
	/**
	 * This method set Wall in a Position
	 * @param pos -  Position in maze to WALL
	 */
	public void setWall(Position pos)
	{
		if (!isInMaze(pos))
			return;
		this.maze3d[pos.getY()][pos.getX()][pos.getZ()] = WALL;
	}
	
	
	/**
	 * This method set Position to be Empty
	 * @param pos -  Position in maze to Empty
	 */
	public void setEmpty(Position pos)
	{
		if (!isInMaze(pos))
			return;
		this.maze3d[pos.getY()][pos.getX()][pos.getZ()] = EMPTY ;
	}
	
	
	/**
	 * This method get value of a Position in the Maze
	 * @param pos -  Position in maze to get value
	 * @return - value of Position pos
	 */
	public int getMazePositionValue(Position pos)
	{
		if (!isInMaze(pos))
			return -1;
		return this.maze3d[pos.getY()][pos.getX()][pos.getZ()];
	}

	
	
	/**
	 * This method Initialize all maze cells to be WALLS
	 */
	public void initializeMaze3dInWalls()
	{
		for (int y=0;y<this.height;y++)
		{
			for (int x=0;x<this.length;x++)
			{
				for (int z=0;z<this.width;z++)
				{
					this.maze3d[y][x][z] = WALL;
				}
			}
		}
	}
	
	
	/**
	 * This method Initialize all maze cells to be EMPTY
	 */
	public void initMaze3dInEmpty()
	{
		for (int y=0;y<this.height;y++)
		{
			for (int x=0;x<this.length;x++)
			{
				for (int z=0;z<this.width;z++)
				{
					this.maze3d[y][x][z] = EMPTY;
				}
			}
		}
	}
	
	/**
	 * This method check if a parameter Position is in the maze
	 * @param pos - Position to check
	 * @return - True or false
	 */
	public boolean isInMaze(Position pos)
	{
		if (pos==null)
			return false;
		return (pos.getY()>=0 && pos.getY()<this.getHeight() 
			 && pos.getX()>=0 && pos.getX()<this.getLength()
			 && pos.getZ()>=0 && pos.getZ()<this.getWidth());
	}
	
	/**
	 * Check if the Position is on the maze edges
	 * @param pos - Position to check
	 * @return - True or false
	 */
	public boolean isOnEdge(Position pos)
	{
		if (pos==null)
			return false;
		return (pos.getY()==0 || pos.getY()==(this.getHeight()-1) 
			 || pos.getX()==0 || pos.getX()==(this.getLength()-1)
			 || pos.getZ()==0 || pos.getZ()==(this.getWidth()-1));
	}
	
	/**
	 * This method check if a specific Position is EMPTY
	 * @param pos - Position to check
	 * @return - True or false
	 */
	public boolean isPositionEmpty(Position pos)
	{
		return this.getMazePositionValue(pos)==EMPTY;
	}
	
	/**
	 * Get cross section by yLayer
	 * @param yLayer - y layer to get
	 * @return yLayer Level
	 */
	public int [][] getCrossSectionByY(int yLayer) throws IndexOutOfBoundsException
	{
		if (yLayer < 0 || yLayer >= this.getHeight())
			throw new IndexOutOfBoundsException();
		int [][] cross = new int [this.getLength()][this.getWidth()];
		for (int x=0;x<this.getLength();x++)
		{
			for (int z=0;z<this.getWidth();z++)
			{
				cross[x][z] = this.maze3d[yLayer][x][z];
			}
		}
		return cross;
	}
	
	/**
	 * Get cross section by xLayer
	 * @param xLayer - x layer to get
	 * @return  xLayer Level
	 */
	public int [][] getCrossSectionByX(int xLayer) throws IndexOutOfBoundsException
	{
		if (xLayer < 0 || xLayer >= this.getLength())
			throw new IndexOutOfBoundsException();
		int [][] cross = new int [this.getHeight()][this.getWidth()];
		for (int y=0;y<this.getHeight();y++)
		{
			for (int z=0;z<this.getWidth();z++)
			{
				cross[y][z] = this.maze3d[y][xLayer][z];
			}
		}
		return cross;
	}
	
	/**
	 * Get cross section by zLayer
	 * @param zLayer - z layer to get
	 * @return  zLayer Level
	 */
	public int [][] getCrossSectionByZ(int zLayer) throws IndexOutOfBoundsException
	{
		if (zLayer < 0 || zLayer >= this.getWidth())
			throw new IndexOutOfBoundsException();
		int [][] cross = new int [this.getHeight()][this.getLength()];
		for (int y=0;y<this.getHeight();y++)
		{
			for (int x=0;x<this.getLength();x++)
			{
				cross[y][x] = this.maze3d[y][x][zLayer];
			}
		}
		return cross;
	}
	
	/**
	 * Get possible moves (Empty Positions) from a specific Position
	 * @param pos - Position to check
	 * @return Directions Array of Possible moves
	 */
	public Direction[] getPossibleMoves(Position pos)
	{
		ArrayList<Direction> posMoves = new ArrayList<Direction>();
		
		Position tempPos = pos.add(new Position(-1,0,0)); // up
		if (this.isInMaze(tempPos) && this.getMazePositionValue(tempPos) == EMPTY)
			posMoves.add(Direction.Up);
		
		
		tempPos = pos.add(new Position(1,0,0)); // down
		if (this.isInMaze(tempPos) && this.getMazePositionValue(tempPos) == EMPTY)
			posMoves.add(Direction.Down);
	
		
		tempPos = pos.add(new Position(0,-1,0)); // left
		if (this.isInMaze(tempPos) && this.getMazePositionValue(tempPos) == EMPTY)
			posMoves.add(Direction.Left);
		
		
		tempPos = pos.add(new Position(0,1,0)); // right
		if (this.isInMaze(tempPos) && this.getMazePositionValue(tempPos) == EMPTY)
			posMoves.add(Direction.Right);
		
		
		tempPos = pos.add(new Position(0,0,-1)); // backward
		if (this.isInMaze(tempPos) && this.getMazePositionValue(tempPos) == EMPTY)
			posMoves.add(Direction.Backward);
		
		
		tempPos = pos.add(new Position(0,0,1)); // forward
		if (this.isInMaze(tempPos) && this.getMazePositionValue(tempPos) == EMPTY)
			posMoves.add(Direction.Forward);
		
		Collections.shuffle(posMoves);
		
		Direction[] dirs = new Direction[posMoves.size()]; 
		posMoves.toArray(dirs);
		return dirs;	
	}
	
	/**
	 * Get possible moves (Wall Positions) to carve from a specific Position
	 * @param pos - Position to check
	 * @return Directions Array of Possible moves
	 */
	public Direction[] getPossibleDirections(Position pos)
	{
		ArrayList<Direction> posMoves = new ArrayList<Direction>();
		
		Position tempPos = pos.add(new Position(-1,0,0)); // Up
		if (this.isInMaze(tempPos) && this.getMazePositionValue(tempPos) == WALL)
			posMoves.add(Direction.Up);
		
		
		tempPos = pos.add(new Position(1,0,0)); // Down
		if (this.isInMaze(tempPos) && this.getMazePositionValue(tempPos) == WALL)
			posMoves.add(Direction.Down);
	
		
		tempPos = pos.add(new Position(0,-1,0)); // Left
		if (this.isInMaze(tempPos) && this.getMazePositionValue(tempPos) == WALL)
			posMoves.add(Direction.Left);
		
		
		tempPos = pos.add(new Position(0,1,0)); // Right
		if (this.isInMaze(tempPos) && this.getMazePositionValue(tempPos) == WALL)
			posMoves.add(Direction.Right);
		
		
		tempPos = pos.add(new Position(0,0,-1)); // Backward
		if (this.isInMaze(tempPos) && this.getMazePositionValue(tempPos) == WALL)
			posMoves.add(Direction.Backward);
		
		
		tempPos = pos.add(new Position(0,0,1)); // Forward
		if (this.isInMaze(tempPos) && this.getMazePositionValue(tempPos) == WALL)
			posMoves.add(Direction.Forward);
		
		Collections.shuffle(posMoves);
		
		Direction[] arr = new Direction[posMoves.size()]; 
		posMoves.toArray(arr);
		return arr;	
		
	}
	/**
	 * Check if the Mazes are equals
	 * @param maze3d - the Maze3d to compare with
	 * @return True or false
	 */
	public boolean equals(Maze3d maze3d) 
	{
		if (this.height == maze3d.height && this.length == maze3d.length && this.width == maze3d.width && this.startPosition.equals(maze3d.startPosition) && this.goalPosition.equals(maze3d.goalPosition))
		{
			for(int y=0 ; y<this.getHeight(); y++)
			{
				for(int x=0; x<this.getLength(); x++)
				{
					for(int z=0;z<this.getWidth(); z++)
					{
						if (this.maze3d[y][x][z]!=maze3d.maze3d[y][x][z])
							return false;
					}
				}
			}
			return true;
		}
		return false;
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		for( int y=0 ; y< this.getHeight();y++) // floors
		{
			sb.append("{\n");
			for(int x=0; x<this.getLength(); x++) // rows
			{
				for(int z=0;z<this.getWidth();z++) // cols
				{
					if (z==0)
						sb.append("\t");
					sb.append((this.maze3d[y][x][z]+" "));
				}
				sb.append("\n");
			}
			sb.append("}\n\n");
		}
		return sb.toString();
	}
	
	/**
	 * this method return the Maze3D object in byte array representation
	 * @return byte array representing the Maze3D
	 */
	public byte[] toByteArray()
	{
		ArrayList<Byte> al = new ArrayList<Byte>();
		
		al.add(new Byte((byte)this.getHeight()));
		al.add(new Byte((byte)this.getLength()));
		al.add(new Byte((byte)this.getWidth()));
		
		al.add(new Byte((byte)this.getStartPosition().getY()));
		al.add(new Byte((byte)this.getStartPosition().getX()));
		al.add(new Byte((byte)this.getStartPosition().getZ()));
		
		al.add(new Byte((byte)this.getGoalPosition().getY()));
		al.add(new Byte((byte)this.getGoalPosition().getX()));
		al.add(new Byte((byte)this.getGoalPosition().getZ()));
		
		for (int y=0;y<this.getHeight();y++)
		{
			for (int x=0;x<this.getLength();x++)
			{
				for (int z=0;z<this.getWidth();z++)
				{
					al.add(new Byte((byte) this.maze3d[y][x][z]));
				}
			}
		}
		
		byte [] arr = new byte[al.size()];
		for (int i=0;i<al.size();i++)
			arr[i]=al.get(i);
		
		return arr;
		
	}
}