package MVP.model;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
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
 * @author Matan Ashkenazi & Noee Cohen
 *
 */
public class MyModel extends Observable implements Model {

	HashMap<String, Maze3d> mazesMap;
	HashMap<String, Solution> solutionMap;
	private ArrayList<Thread> threads;
	private String message;

	public MyModel() {
		this.mazesMap = new HashMap<String, Maze3d>();
		this.solutionMap = new HashMap<String, Solution>();
		this.threads = new ArrayList<Thread>();
	}
	public HashMap<String, Maze3d> getMazeMap() {
		return mazesMap;
	}

	public void setMazeMap(HashMap<String, Maze3d> mazeMap) {
		this.mazesMap = mazeMap;
	}
	public HashMap<String, Solution> getSolutionMap() {
		return solutionMap;
	}

	public void setSolutionMap(HashMap<String, Solution> solutionMap) {
		this.solutionMap = solutionMap;
	}
	public String getMessage() {
		return message;
	}
	
	@Override
	public String dir(String path) 
	{
		try {
			ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "dir", path);
			builder.redirectErrorStream(true);
			Process p = builder.start();
			BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while (true) {
				line = r.readLine();
				if (line == null) {
					break;
				}
				System.out.println(line);
			}
		} catch (IOException e) {
			e.getStackTrace();
		}
		return null;
	}
	
	@Override
	public void generate3dMaze(String name, int height, int length, int width) throws Exception {

		Thread t = new Thread(new Runnable() {
			Maze3d currentMaze3d;

			@Override
			public void run() {
				try {
					currentMaze3d = new MyMaze3dGenerator().generate(height, length, width);
				} catch (Exception e) {
					e.printStackTrace();
				}
				mazesMap.put(name, currentMaze3d);
				message = "Maze " + name + " is ready to use.";
				setChanged();
				notifyObservers("display_message");
			}
		}, "generateMazeThread");
		t.start();
		this.threads.add(t);

	}
	
	@Override
	public Maze3d display(String name) 
	{
		if (mazeExists(name)){
			message = "Maze3D " + name + ": ";
			setChanged();
			notifyObservers("display");
			return mazesMap.get(name);
		}
		else
			//System.out.println("Couldn't find maze!");
		return null;
	}
	public Maze3d getMaze3d (String name)
	{
		return mazesMap.get(name);
	}
	@Override
	public int[][] displayCrossSectionByY(int yLayer, String name) throws Exception {
		if (mazeExists(name) && mazesMap.get(name).isInMaze(new Position(yLayer, 0, 0)))
		{
			setChanged();
			return mazesMap.get(name).getCrossSectionByY(yLayer);
		}
		throw new Exception("Maze " + name + " doesn't exist or yLayer invalid");
	}
	@Override
	public int[][] displayCrossSectionByX(int xLayer, String name) throws Exception {
		if (mazeExists(name) && mazesMap.get(name).isInMaze(new Position(0, xLayer, 0)))
		{
			setChanged();
			return mazesMap.get(name).getCrossSectionByX(xLayer);
		}	
		throw new Exception("Maze " + name + " doesn't exist or xLayer invalid");
	}
	@Override
	public int[][] displayCrossSectionByZ(int zLayer, String name) throws Exception 
	{
		if (mazeExists(name) && mazesMap.get(name).isInMaze(new Position(0, 0, zLayer)))
		{
			setChanged();
			return mazesMap.get(name).getCrossSectionByZ(zLayer);
		}
		throw new Exception("Maze " + name + " doesn't exist or zLayer invalid");
	}
	@Override
	public void saveMazeToFile(String name, String fileName) {
		OutputStream out = null ;
		try {
			out = new MyCompressorOutputStream(new FileOutputStream(fileName));
			out.write(mazesMap.get(name).toByteArray());
			out.flush();
			System.out.println("Maze " + name + " save to file " + fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	@Override
	public void loadMazeFromFile(String name, String fileName) {
		InputStream in = null;
		try {
			byte b[] = new byte[3];
			in = new FileInputStream(fileName);
			in.read(b, 0, b.length);
			b = new byte[((int) b[0] * (int) b[1] * (int) b[2]) + 9];
			in.close();
			in = new MyDecompressorInputStream(new FileInputStream(fileName));
			in.read(b);
			mazesMap.put(name, new Maze3d(b));
			System.out.println("Maze " + name + " loaded from file: " + fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
	}
	@Override
	public int sizeInFile(String filename) {
		File file;
		try {
			file = new File(MyModel.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath() + "\\"
					+ filename);
			return ((int) file.length());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		return -1;
	}
	@Override
	public int sizeInMemory(String name) {
		if (mazeExists(name))
			return this.mazesMap.get(name).toByteArray().length;
		else
			return -1;
	}
	@Override
	public void solveMaze(String name, String algorithm) 
	{
		if (!mazeExists(name))
			System.out.println("There isn't such maze called " + name + " try solve another one.");
		else if (!(algorithm.toUpperCase().equals("BFS") || algorithm.toUpperCase().equals("BREADTHFIRSTSEARCH")
				|| algorithm.toUpperCase().equals("DFS")))
			System.out.println("There isn't such algorithm called " + algorithm + ", try another one");
		else 
		{
			Thread t = new Thread(new Runnable() {

				@Override
				public void run() {
					Solution sol = null;
					if (mazeExists(name)) {
						if (algorithm.toUpperCase().equals("DFS")) {
							sol = new DFSSearcher().search(new Maze3dAdapter(mazesMap.get(name)));
							if (sol != null) {
								solutionMap.put(name, sol);
								System.out.println("The maze3d DFS based Solution is ready");
							}
						} else if (algorithm.toUpperCase().equals("BREADTHFIRSTSEARCH")) {
							sol = new BreadthFirstSearcher().search(new Maze3dAdapter(mazesMap.get(name)));
							if (sol != null) {
								solutionMap.put(name, sol);
								System.out.println("The maze3d BreadthFirstSearch Solution based is ready");
							}
						} else if (algorithm.toUpperCase().equals("BFS")) {
							sol = new BFSSearcher().search(new Maze3dAdapter(mazesMap.get(name)));
							if (sol != null) {
								solutionMap.put(name, sol);
								System.out.println("The maze3d BFS Solution based is ready");
							}
						}
					}
				}
			}, "solveMazeThread");
			t.start();
			this.threads.add(t);
		}
		
	}
	@Override
	public Solution displaySolution(String name) {
		if (solutionMap.containsKey(name))
			return solutionMap.get(name);
		else
			return null;
	}

	public boolean mazeExists(String name) {
		if (mazesMap.containsKey(name))
			return true;
		return false;
	}
	@Override
	public void exitModel() {
		for (Thread t : threads)
			t.interrupt();
		System.exit(0);
		
	}
}