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
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import algorithms.mazeGenerators.MyMaze3dGenerator;
import algorithms.mazeGenerators.SimpleMaze3dGenerator;
import algorithms.search.BFSSearcher;
import algorithms.search.BreadthFirstSearcher;
import algorithms.search.DFSSearcher;
import algorithms.search.Solution;
import algorithms.search.demo.Maze3dAdapter;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;
import model.maze3d.Maze3d;
import model.maze3d.Position;

// TODO: Auto-generated Javadoc
/**
 * The Class Maze3DModel.
 *
 * @author Matan Ashkenazi & Noee Cohen
 */
public class Maze3DModel extends CommonMaze3DModel {

	/** The descriptor. */
	private Object descriptor;

	/* (non-Javadoc)
	 * @see MVP.model.Model#dir(java.lang.String)
	 */
	@Override
	public void dir(String path) {
		try {
			ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "dir", path);
			builder.redirectErrorStream(true);
			Process p = builder.start();
			BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String bufferLine = "";
			this.descriptor = "";
			while (true) {
				bufferLine = r.readLine();
				if (bufferLine == null) {
					break;
				}
				this.descriptor += '\n' + bufferLine;
			}
			setChanged();
			notifyObservers("display_message");
		} catch (IOException e) {
			e.getStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see MVP.model.Model#generate3dMaze(java.lang.String, int, int, int)
	 */
	@Override
	public void generate3dMaze(String name, int height, int length, int width) throws Exception {

		threadPool.submit(new Callable<Maze3d>() {
			Maze3d currentMaze3d;

			@Override
			public Maze3d call() throws Exception {
				if (height > properties.getMazeMaxHeight() || length > properties.getMazeMaxLength()
						|| width > properties.getMazeMaxWidth())
					descriptor = "Maze3D Dimensions is above the threshold as possible .";
				if (!mazeExists(name)) {
					try {
						if (properties.getGenerateAlgorithm().toLowerCase().equals("mymaze3dgenerator"))
							currentMaze3d = new MyMaze3dGenerator().generate(height, length, width);
						else if (properties.getGenerateAlgorithm().toLowerCase().equals("simplemaze3dgenerator"))
							currentMaze3d = new SimpleMaze3dGenerator().generate(height, length, width);
						descriptor = "Maze " + name + " is ready to use.";
						mazesMap.put(name, currentMaze3d);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					descriptor = "Maze3D " + name + " is already exists .";
				}

				setChanged();
				notifyObservers("display_message");
				return currentMaze3d;
			}
		});
	}

	/* (non-Javadoc)
	 * @see MVP.model.Model#getMaze3D(java.lang.String)
	 */
	@Override
	public void getMaze3D(String name) {
		if (mazeExists(name)) {
			this.descriptor = mazesMap.get(name);
			setChanged();
			notifyObservers("display_maze3d");
		} else {
			this.descriptor = "There isn't such maze called " + name;
			setChanged();
			notifyObservers("display_message");
		}

	}
	
	/* (non-Javadoc)
	 * @see MVP.model.Model#getStartPosition(java.lang.String)
	 */
	@Override
	public void getStartPosition (String name)
	{
		if (mazeExists(name))
		{
			this.descriptor = mazesMap.get(name).getStartPosition();
			setChanged();
			notifyObservers("display_position");
		}
		else
		{
			this.descriptor = "There isn't such maze called " + name;
			setChanged();
			notifyObservers("display_message");
		}
	}
	
	/* (non-Javadoc)
	 * @see MVP.model.Model#getGoalPosition(java.lang.String)
	 */
	@Override
	public void getGoalPosition(String name)
	{
		if (mazeExists(name))
		{
			this.descriptor = mazesMap.get(name).getGoalPosition();
			setChanged();
			notifyObservers("display_position");
		}
		else
		{
			this.descriptor = "There isn't such maze called " + name;
			setChanged();
			notifyObservers("display_message");
		}
	}
	
	/* (non-Javadoc)
	 * @see MVP.model.Model#displayCrossSectionByY(int, java.lang.String)
	 */
	@Override
	public void displayCrossSectionByY(int yLayer, String name) {
		if (mazeExists(name) && mazesMap.get(name).isInMaze(new Position(yLayer, 0, 0))) {
			this.descriptor = mazesMap.get(name).getCrossSectionByY(yLayer);
			setChanged();
			notifyObservers("display_cross_section_by_y");
		} else
			this.descriptor = "Maze " + name + " doesn't exist or yLayer invalid";
	}

	/* (non-Javadoc)
	 * @see MVP.model.Model#displayCrossSectionByX(int, java.lang.String)
	 */
	@Override
	public void displayCrossSectionByX(int xLayer, String name) {
		if (mazeExists(name) && mazesMap.get(name).isInMaze(new Position(0, xLayer, 0))) {
			this.descriptor = mazesMap.get(name).getCrossSectionByX(xLayer);
			setChanged();
			notifyObservers("display_cross_section_by_x");
		} else
			this.descriptor = "Maze " + name + " doesn't exist or xLayer invalid";
	}

	/* (non-Javadoc)
	 * @see MVP.model.Model#displayCrossSectionByZ(int, java.lang.String)
	 */
	@Override
	public void displayCrossSectionByZ(int zLayer, String name) {
		if (mazeExists(name) && mazesMap.get(name).isInMaze(new Position(0, 0, zLayer))) {
			this.descriptor = mazesMap.get(name).getCrossSectionByZ(zLayer);
			setChanged();
			notifyObservers("display_cross_section_by_z");
		} else
			this.descriptor = "Maze " + name + " doesn't exist or zLayer invalid";
	}

	/* (non-Javadoc)
	 * @see MVP.model.Model#saveMazeToFile(java.lang.String, java.lang.String)
	 */
	@Override
	public void saveMazeToFile(String name, String fileName) {
		OutputStream out = null;
		try {
			out = new MyCompressorOutputStream(new FileOutputStream(fileName));
			out.write(mazesMap.get(name).toByteArray());
			out.flush();
			descriptor = "Maze " + name + " saved to file " + fileName;
			setChanged();
			notifyObservers("display_message");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/* (non-Javadoc)
	 * @see MVP.model.Model#loadMazeFromFile(java.lang.String, java.lang.String)
	 */
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

			this.descriptor = "Maze " + name + " loaded from file: " + fileName;
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

	/* (non-Javadoc)
	 * @see MVP.model.Model#sizeInFile(java.lang.String)
	 */
	@Override
	public void sizeInFile(String filename) {
		File file;
		file = new File(filename);
		this.descriptor = ((int) file.length());
		setChanged();
		notifyObservers("file_size");
	}

	/* (non-Javadoc)
	 * @see MVP.model.Model#sizeInMemory(java.lang.String)
	 */
	@Override
	public void sizeInMemory(String name) {
		if (mazeExists(name)) {
			this.descriptor = this.mazesMap.get(name).toByteArray().length;
		} 
		else
			this.descriptor = "There is no such maze !";
		setChanged();
		notifyObservers("maze_size");

	}
	
	/* (non-Javadoc)
	 * @see MVP.model.Model#solveMaze(java.lang.String, java.lang.String, model.maze3d.Position)
	 */
	@Override
	public void solveMaze(String name, String algorithm,Position characterPosition) {
		if (!mazeExists(name)) {
			this.descriptor = "There isn't such maze called " + name + " try solve another one .";
			setChanged();
			notifyObservers("display_message");
		} else if (!(algorithm.toLowerCase().equals("bfs") || algorithm.toLowerCase().equals("breadthfirstsearch")
				|| algorithm.toLowerCase().equals("dfs"))) {
			this.descriptor = "There isn't such algorithm called " + algorithm + ", try another one .";
			setChanged();
			notifyObservers("display_message");
		} 
		else {

			threadPool.submit(new Callable<Solution>() {
				@Override
				public Solution call() throws Exception {
					mazesMap.get(name).setStartPosition(characterPosition);
					Solution sol = null;
					switch (algorithm.toLowerCase()) {
					case "dfs": {
						sol = new DFSSearcher().search(new Maze3dAdapter(mazesMap.get(name)));
						descriptor = "The maze3d DFS based Solution is ready";
						break;
					}
					case "breadthfirstsearch": {
						sol = new BreadthFirstSearcher().search(new Maze3dAdapter(mazesMap.get(name)));
						descriptor = "The maze3d BreadthFirstSearch Solution based is ready";
						break;
					}
					case "bfs": {
						sol = new BFSSearcher().search(new Maze3dAdapter(mazesMap.get(name)));
						descriptor = "The maze3d BFS Solution based is ready";
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
					return sol;
				}

			});
		}

	}
	
	/* (non-Javadoc)
	 * @see MVP.model.Model#solveMaze(java.lang.String, java.lang.String)
	 */
	@Override
	public void solveMaze(String name, String algorithm) {
		if (!mazeExists(name)) {
			this.descriptor = "There isn't such maze called " + name + " try solve another one .";
			setChanged();
			notifyObservers("display_message");
		} else if (!(algorithm.toLowerCase().equals("bfs") || algorithm.toLowerCase().equals("breadthfirstsearch")
				|| algorithm.toLowerCase().equals("dfs"))) {
			this.descriptor = "There isn't such algorithm called " + algorithm + ", try another one .";
			setChanged();
			notifyObservers("display_message");
		} else if (maze2sol.containsKey(mazesMap.get(name))) {
			this.descriptor = "Maze3D " + name + " solution is already exists .";
			setChanged();
			notifyObservers("display_message");
		} else {

			threadPool.submit(new Callable<Solution>() {
				@Override
				public Solution call() throws Exception {
					Solution sol = null;
					switch (algorithm.toLowerCase()) {
					case "dfs": {
						sol = new DFSSearcher().search(new Maze3dAdapter(mazesMap.get(name)));
						descriptor = "The maze3d DFS based Solution is ready";
						break;
					}
					case "breadthfirstsearch": {
						sol = new BreadthFirstSearcher().search(new Maze3dAdapter(mazesMap.get(name)));
						descriptor = "The maze3d BreadthFirstSearch Solution based is ready";
						break;
					}
					case "bfs": {
						sol = new BFSSearcher().search(new Maze3dAdapter(mazesMap.get(name)));
						descriptor = "The maze3d BFS Solution based is ready";
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
					return sol;
				}

			});
		}

	}

	/* (non-Javadoc)
	 * @see MVP.model.Model#getSolution(java.lang.String)
	 */
	@Override
	public void getSolution(String name) {
		if (solutionExists(name)) {
			this.descriptor = solutionMap.get(name);
			setChanged();
			notifyObservers("display_solution");
		} else if (solutionExists(this.mazesMap.get(name))) {
			this.descriptor = maze2sol.get(this.mazesMap.get(name));
			setChanged();
			notifyObservers("display_solution");
		} else {
			this.descriptor = "There isn't such solution called " + name;
			setChanged();
			notifyObservers("display_message");
		}
	}

	/* (non-Javadoc)
	 * @see MVP.model.Model#mazeExists(java.lang.String)
	 */
	@Override
	public boolean mazeExists(String name) {
		if (mazesMap.containsKey(name))
			return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see MVP.model.Model#solutionExists(model.maze3d.Maze3d)
	 */
	@Override
	public boolean solutionExists(Maze3d m3d) {
		if (maze2sol.containsKey(m3d))
			return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see MVP.model.Model#solutionExists(java.lang.String)
	 */
	@Override
	public boolean solutionExists(String name) {
		if (solutionMap.containsKey(name))
			return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see MVP.model.Model#saveGZipMaps()
	 */
	public void saveGZipMaps() {

		FileOutputStream fos = null;
		GZIPOutputStream gos = null;
		ObjectOutputStream oos = null;

		try {
			fos = new FileOutputStream("cache_Maps");
			gos = new GZIPOutputStream(fos);
			oos = new ObjectOutputStream(gos);

			oos.writeObject(mazesMap);
			oos.writeObject(solutionMap);
			oos.writeObject(maze2sol);

			oos.flush();
			oos.close();
			gos.close();
			fos.close();

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see MVP.model.Model#loadGZipMaps()
	 */
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

			mazesMap = (HashMap<String, Maze3d>) ois.readObject();
			solutionMap = (HashMap<String, Solution>) ois.readObject();
			maze2sol = (HashMap<Maze3d, Solution>) ois.readObject();

			ois.close();
			gis.close();
			fis.close();

		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see MVP.model.Model#exitModel()
	 */
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

	/* (non-Javadoc)
	 * @see MVP.model.Model#getDescriptor()
	 */
	public Object getDescriptor() {
		return this.descriptor;
	}

}