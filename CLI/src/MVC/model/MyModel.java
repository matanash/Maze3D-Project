package MVC.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.HashMap;

import MVC.controller.Controller;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import algorithms.search.BFSSearcher;
import algorithms.search.BreadthFirstSearcher;
import algorithms.search.DFSSearcher;
import algorithms.search.Solution;
import algorithms.search.demo.Maze3dAdapter;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;
import model.maze3d.Maze3d;
import model.maze3d.Position;

/**
 *  
 * @author MatanA
 *
 */
public class MyModel implements Model 
{

	Controller ctrl;
	HashMap<String,Maze3d> mazesMap;
	HashMap<String,Solution> solutionMap;

	public MyModel(Controller ctrl){
		this.ctrl = ctrl;
		this.mazesMap = new HashMap<String,Maze3d>();
		this.solutionMap = new HashMap<String,Solution>();
	}

	public Controller getCtrl() {
		return this.ctrl;
	}
	
	public void setCtrl(Controller ctrl) {
		this.ctrl = ctrl;
	}
	
	@Override
	public String dir(String path) 
	{
		 try{
			 ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "dir",path);
		        builder.redirectErrorStream(true);
		        Process p = builder.start();
		        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
		        String line;
		        while (true) {
		            line = r.readLine();
		            if (line == null) { break; }
		            System.out.println(line);
		        }
		 }catch(IOException e)
		 {
			 e.getStackTrace();
		 }
		return null;
	}
	
	@Override
	public Maze3d generate3dMaze(String name,int height, int length, int width) throws Exception
	{
		Maze3d currentMaze3d = new MyMaze3dGenerator().generate(height, length, width);
		mazesMap.put(name, currentMaze3d);
		return currentMaze3d;
	}
	
	@Override
	public Maze3d display(String name)
	{
		if (mazesMap.containsKey(name)) 
			return mazesMap.get(name);
		else
			System.out.println("Couldn't find maze!");
		return null;
	}
	
	@Override
	public int [][] displayCrossSectionByY(int yLayer,String name) throws Exception
	{
		if (mazesMap.containsKey(name) && mazesMap.get(name).isInMaze(new Position(yLayer,0,0))) 
			return mazesMap.get(name).getCrossSectionByY(yLayer);
		throw new Exception("Maze " +name+ " doesn't exist or yLayer invalid");
		
	}
	
	@Override
	public int [][] displayCrossSectionByX(int xLayer,String name) throws Exception
	{
		if (mazesMap.containsKey(name) && mazesMap.get(name).isInMaze(new Position(0,xLayer,0)))
			return mazesMap.get(name).getCrossSectionByX(xLayer);
		throw new Exception("Maze " +name+ " doesn't exist or xLayer invalid");
	}
	
	@Override
	public int [][] displayCrossSectionByZ(int zLayer,String name) throws Exception
	{
		if (mazesMap.containsKey(name) && mazesMap.get(name).isInMaze(new Position(0,0,zLayer))) 
			return mazesMap.get(name).getCrossSectionByZ(zLayer);
		throw new Exception("Maze " +name+ " doesn't exist or zLayer invalid");
	}
	
	@Override
	public void saveMazeToFile(String name, String fileName)
	{
		try 
		{
			OutputStream out = new MyCompressorOutputStream(
					new FileOutputStream(fileName));
			out.write(mazesMap.get(name).toByteArray());
			out.flush();
			out.close();
			System.out.println("Maze "+name+" save to file "+fileName);
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void loadMazeFromFile(String fileName,String name) 
	{
		try 
		{
			//test
			byte b[] = new byte[3];
			InputStream in  = new FileInputStream(fileName);
			in.read(b, 0, b.length);
			b = new byte[((int)b[0]*(int)b[1]*(int)b[2])+9];
			in.close();
			in = new MyDecompressorInputStream(new FileInputStream(fileName));
			in.read(b);
			mazesMap.put(name,new Maze3d(b));
			in.close();
			System.out.println("Maze "+name+" loaded from file "+fileName);
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public int sizeInFile(String filename) 
	{
		File file;
		try 
		{
			file = new File(MyModel.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath() + "\\" + filename);
			return ((int) file.length());
		} catch (URISyntaxException e) 
		{
			e.printStackTrace();
		}
		return -1;
	}
	
	@Override
	public int sizeInMemory(String name) {
		if(mazesMap.containsKey(name))
			return this.mazesMap.get(name).toByteArray().length;
		else
			return -1;
	}	
	
	@Override
	public Solution solveMaze(String name, String algorithm) 
	{
		
		if (mazesMap.containsKey(name)) 
		{
			if (algorithm.equals("DFS")) 
			{
				solutionMap.put(name, new DFSSearcher().search(new Maze3dAdapter(mazesMap.get(name))));
				return solutionMap.get(name);
			}
			else if (algorithm.equals("Breadth First Search")) 
			{
				solutionMap.put(name, new BreadthFirstSearcher().search(new Maze3dAdapter(mazesMap.get(name))));
				return solutionMap.get(name);
			}
			else if (algorithm.equals("BFS"))
			{
				solutionMap.put(name, new BFSSearcher().search(new Maze3dAdapter(mazesMap.get(name))));
				return solutionMap.get(name);
			}
		}			
		return null;
	}
	
	@Override
	public Solution displaySolution(String name)
	{
		if (solutionMap.containsKey(name))
			return solutionMap.get(name);
		else
			return null;			
	}

	public boolean mazeExists(String name)
	{
		if(mazesMap.containsKey(name))
			return true;
		return false;
	}

}