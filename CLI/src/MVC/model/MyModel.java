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
import java.util.ArrayList;
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
 * @author Matan Ashkenazi & Noee Cohen
 *
 */
public class MyModel implements Model {

	Controller ctrl;
	HashMap<String, Maze3d> mazesMap;
	HashMap<String, Solution> solutionMap;
	private ArrayList<Thread> threads;

	public MyModel(Controller ctrl) {
		this.ctrl = ctrl;
		this.mazesMap = new HashMap<String, Maze3d>();
		this.solutionMap = new HashMap<String, Solution>();
		this.threads = new ArrayList<Thread>();
	}

	public Controller getCtrl() {
		return this.ctrl;
	}

	public void setCtrl(Controller ctrl) {
		this.ctrl = ctrl;
	}

	@Override
	public String dir(String path) {
		try {
			ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "dir", path);
			builder.redirectErrorStream(true);
			Process p = builder.start();
			BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			do {
				line = r.readLine();
				if (line!=null)
					System.out.println(line);
			}while (line!=null);
		} catch (IOException e) {
			e.getStackTrace();
		}
		return null;
	}

	/**
	 * @author MatanA
	 * @version - 1.0
	 * @param -
	 *            name of maze , height of maze, length of maze , width of maze
	 * @return - generated maze3d
	 * @throws -
	 *             Exception
	 */
	@Override
	public void generate3dMaze(String name, int height, int length, int width) throws Exception {

		Thread t = new Thread(new Runnable() {
			Maze3d currentMaze3d;

			@Override
			public void run() {
				try {
					currentMaze3d = new MyMaze3dGenerator().generate(height, length, width);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mazesMap.put(name, currentMaze3d);
			}
		}, "generateMazeThread");
		t.start();
		this.threads.add(t);

	}

	@Override
	public Maze3d display(String name) {
		if (mazeExists(name))
			return mazesMap.get(name);
		else
			System.out.println("Couldn't find maze!");
		return null;
	}

	@Override
	public int[][] displayCrossSectionByY(int yLayer, String name) throws Exception {
		if (mazeExists(name) && mazesMap.get(name).isInMaze(new Position(yLayer, 0, 0)))
			return mazesMap.get(name).getCrossSectionByY(yLayer);
		throw new Exception("Maze " + name + " doesn't exist or yLayer invalid");

	}

	@Override
	public int[][] displayCrossSectionByX(int xLayer, String name) throws Exception {
		if (mazeExists(name) && mazesMap.get(name).isInMaze(new Position(0, xLayer, 0)))
			return mazesMap.get(name).getCrossSectionByX(xLayer);
		throw new Exception("Maze " + name + " doesn't exist or xLayer invalid");
	}

	@Override
	public int[][] displayCrossSectionByZ(int zLayer, String name) throws Exception {
		if (mazeExists(name) && mazesMap.get(name).isInMaze(new Position(0, 0, zLayer)))
			return mazesMap.get(name).getCrossSectionByZ(zLayer);
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
	public void loadMazeFromFile(String fileName, String name) {
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
	public void solveMaze(String name, String algorithm) {
		if (!mazeExists(name))
			System.out.println("There isn't such maze called " + name + " try solve another one.");
		else if (!(algorithm.toUpperCase().equals("BFS") || algorithm.toUpperCase().equals("BREADTHFIRSTSEARCH")
				|| algorithm.toUpperCase().equals("DFS")))
			System.out.println("There isn't such algorithm called " + algorithm + ", try another one");
		else {
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

	public void exitModel() {
		for (Thread t : threads)
			t.interrupt();
		System.exit(0);
	}

}