package MVP.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

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
public class Maze3DModel extends CommonMaze3DModel {

	private String message;

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
	public void dir(String path) {
		try {
			ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "dir", path);
			builder.redirectErrorStream(true);
			Process p = builder.start();
			BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String bufferLine = "";
			message = "";
			while (true) {
				bufferLine = r.readLine();
				if (bufferLine == null) {
					break;
				}
				message += '\n' + bufferLine;
			}
			setChanged();
			notifyObservers("display_message");
		} catch (IOException e) {
			e.getStackTrace();
		}

	}

	@Override
	public void generate3dMaze(String name, int height, int length, int width) throws Exception {

		threadPool.execute(new Runnable() {
			Maze3d currentMaze3d;

			@Override
			public void run() {
				if(!mazeExists(name)){
					try {
						currentMaze3d = new MyMaze3dGenerator().generate(height, length, width);
					} catch (Exception e) {
						e.printStackTrace();
					}
					mazesMap.put(name, currentMaze3d);
				}
				message = "Maze " + name + " is ready to use.";
				setChanged();
				notifyObservers("display_message");

			}

		});
	}

	@Override
	public Maze3d getMaze(String name) {
		if (mazeExists(name)) {
			message = "Maze3D " + name + ": ";
			setChanged();
			notifyObservers("display");
			return mazesMap.get(name);
		} else {
			message = "There isn't such maze called " + name;
			setChanged();
			notifyObservers("display_message");
		}
		return null;
	}

	public Maze3d getMaze3d(String name) {
		return mazesMap.get(name);
	}

	@Override
	public int[][] displayCrossSectionByY(int yLayer, String name) throws Exception {
		if (mazeExists(name) && mazesMap.get(name).isInMaze(new Position(yLayer, 0, 0))) {
			setChanged();
			return mazesMap.get(name).getCrossSectionByY(yLayer);
		}
		throw new Exception("Maze " + name + " doesn't exist or yLayer invalid");
	}

	@Override
	public int[][] displayCrossSectionByX(int xLayer, String name) throws Exception {
		if (mazeExists(name) && mazesMap.get(name).isInMaze(new Position(0, xLayer, 0))) {
			setChanged();
			return mazesMap.get(name).getCrossSectionByX(xLayer);
		}
		throw new Exception("Maze " + name + " doesn't exist or xLayer invalid");
	}

	@Override
	public int[][] displayCrossSectionByZ(int zLayer, String name) throws Exception {
		if (mazeExists(name) && mazesMap.get(name).isInMaze(new Position(0, 0, zLayer))) {
			setChanged();
			return mazesMap.get(name).getCrossSectionByZ(zLayer);
		}
		throw new Exception("Maze " + name + " doesn't exist or zLayer invalid");
	}

	@Override
	public void saveMazeToFile(String name, String fileName) {
		OutputStream out = null;
		try {
			out = new MyCompressorOutputStream(new FileOutputStream(fileName));
			out.write(mazesMap.get(name).toByteArray());
			out.flush();

			message = "Maze " + name + " saved to file " + fileName;
			setChanged();
			notifyObservers("display_message");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
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
			
			message = "Maze " + name + " loaded from file: " + fileName;
			setChanged();
			notifyObservers("display_message");
			
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
			file = new File(Maze3DModel.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()
					+ "\\" + filename);
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
		if (!mazeExists(name)){
			message = "There isn't such maze called " + name + " try solve another one .";
			setChanged();
			notifyObservers("display_message");
		}	
		else if (!(algorithm.toUpperCase().equals("BFS") || algorithm.toUpperCase().equals("BREADTHFIRSTSEARCH")
				|| algorithm.toUpperCase().equals("DFS"))){
				message = "There isn't such algorithm called " + algorithm + ", try another one .";
				setChanged();
				notifyObservers("display_message");
			}
		else if(maze2sol.containsKey(mazesMap.get(name))){
			message = "Maze3D " + name + " solution is already exists .";
			setChanged();
			notifyObservers("display_message");
		}
		else {

			threadPool.submit(new Runnable() {

				@Override
				public void run() {
					Solution sol = null;
					switch(algorithm.toLowerCase()){
						case "dfs":
						{
							sol = new DFSSearcher().search(new Maze3dAdapter(mazesMap.get(name)));
							message = "The maze3d DFS based Solution is ready";
							break;
						}
						case "breadthfirstsearch":
						{
							sol = new BreadthFirstSearcher().search(new Maze3dAdapter(mazesMap.get(name)));
							message = "The maze3d BreadthFirstSearch Solution based is ready";
							break;
						}
						case "bfs":
						{
							sol = new BFSSearcher().search(new Maze3dAdapter(mazesMap.get(name)));
							message = "The maze3d BFS Solution based is ready";
							break;
						}
						default:
							break;
					}
					if (sol != null) {
						solutionMap.put(name, sol);
						maze2sol.put(mazesMap.get(name), sol);
						setChanged();
						notifyObservers("display_message");
					}
				}
			});
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
	
	public boolean solutionExists(Maze3d m3d) {
		if (mazesMap.containsKey(m3d))
			return true;
		return false;
	}
	public void saveGZipMaps(){
		
		FileOutputStream fos = null;
		GZIPOutputStream gos = null;
		ObjectOutputStream oos = null;
		
		try {
			fos = new FileOutputStream("cache_Maps");
			gos = new GZIPOutputStream(fos);
			oos = new ObjectOutputStream(gos);
			
			/*oos.writeObject(mazesMap);
			oos.writeObject(solutionMap);*/
			oos.writeObject(maze2sol);
			
			oos.flush();
			oos.close();
			gos.close();
			fos.close();
			
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	public void loadGZipMaps() {
		
		FileInputStream fis = null;
		GZIPInputStream gis = null;
		ObjectInputStream ois = null;
		
		File f = new File("cache_Maps");
		if (!f.exists()) 
			return;
		
		try {
			
			fis = new FileInputStream("cache_Maps");
			gis = new GZIPInputStream(fis);
			ois = new ObjectInputStream(gis);
			
			/*mazesMap = (HashMap<String,Maze3d>)ois.readObject();
			solutionMap= (HashMap<String,Solution>)ois.readObject();*/
			maze2sol = (HashMap<Maze3d,Solution>)ois.readObject();
			
			ois.close();
			gis.close();
			fis.close();
			
		} catch(IOException ioe) {
			ioe.printStackTrace();
		} catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}
	@Override
	public void exitModel() {
		threadPool.shutdown();
		try {
			if (!threadPool.awaitTermination(3, TimeUnit.SECONDS)) {
				threadPool.shutdownNow();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}