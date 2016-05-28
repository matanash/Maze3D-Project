package io.demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;
import model.maze3d.Maze3d;

public class Demo 
{
	public static void main(String[] args) throws Exception 
	{
		testIO(new MyMaze3dGenerator());	
		
	}
	private static void testIO(Maze3dGenerator mg) throws Exception
	{
		try{
			
			Maze3d maze = mg.generate(3, 4, 4); //... generate it

			// save it to a file
			OutputStream out=new MyCompressorOutputStream(new FileOutputStream("1.maz"));
			out.write(maze.toByteArray());
			out.flush();
			out.close();
			
			// read it from a file
			InputStream in=new MyDecompressorInputStream(new FileInputStream("1.maz"));
			byte b[]=new byte[maze.toByteArray().length];
			in.read(b);
			in.close();
			
			Maze3d loaded=new Maze3d(b);
			System.out.println(loaded);
			System.out.println(maze.equals(loaded));
			
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
