package MVP.guiView.Widgets;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;

import algorithms.search.Solution;
import model.maze3d.Maze3d;
import model.maze3d.Position;
import model.maze3d.domains.Maze3dState;

// TODO: Auto-generated Javadoc
/**
 * The Class Maze2DDisplayer.
 */
public class Maze2DDisplayer extends Maze3DDisplayer {

	/** The maze data. */
	int[][] mazeData;
	
	/** The timer. */
	private Timer timer;
	
	/** The task. */
	private TimerTask task;
	
	/**
	 * Instantiates a new maze 2 D displayer.
	 *
	 * @param parent the parent
	 * @param style the style
	 * @param m3d the m 3 d
	 */
	public Maze2DDisplayer(Composite parent, int style, Maze3d m3d) {
		super(parent, style);
		
		this.setBackground(new Color(null, 255,255,255)); // WHITE
		this.m3d = m3d;
		this.mazeData = m3d.getCrossSectionByY(m3d.getStartPosition().getY());
		this.character = new GameItem("resources/jerry_mouse.png", new Position2D(m3d.getStartPosition().getZ(), m3d.getStartPosition().getX()),m3d.getStartPosition());
		this.target = new GameItem("resources/cheese.png", new Position2D(this.m3d.getGoalPosition().getZ(),this.m3d.getGoalPosition().getX()), this.m3d.getGoalPosition());

	}

	/* (non-Javadoc)
	 * @see MVP.guiView.Widgets.Maze3DDisplayer#drawMaze(org.eclipse.swt.events.PaintEvent)
	 */
	@Override
	protected void drawMaze(PaintEvent e) {
		if (mazeData != null) {
			e.gc.setForeground(new Color(null, 0, 0, 0)); // BLACK
			e.gc.setBackground(new Color(null, 0, 0, 0)); // BLACK

			int canvasWidth = getSize().x;
			int canvasHeight = getSize().y;

			int middleWidth = canvasWidth / 2;

			double cellWidth = (double) canvasWidth / mazeData[0].length;
			double cellHeight = (double) canvasHeight / mazeData.length;
			for (int i = 0; i < mazeData.length; i++) {
				double w0 = 0.75 * cellWidth + 0.25 * cellWidth * i / mazeData.length;
				double w1 = 0.75 * cellWidth + 0.25 * cellWidth * (i + 1) / mazeData.length;
				double start = middleWidth - w0 * mazeData[i].length / 2;
				double start1 = middleWidth - w1 * mazeData[i].length / 2;
				for (int j = 0; j < mazeData[i].length; j++) {
					double[] dpoints = { start + j * w0, i * cellHeight, start + j * w0 + w0, i * cellHeight,
							start1 + j * w1 + w1, i * cellHeight + cellHeight, start1 + j * w1,
							i * cellHeight + cellHeight };
					double cheight = cellHeight / 2;
					if (mazeData[i][j] != 0) {
						paintCube(dpoints, cheight, e);
					}
					
					// draw the target image in the goal position 
					if (i == m3d.getGoalPosition().getX() && j == m3d.getGoalPosition().getZ()
							&& character.getPosition3d().getY()==m3d.getGoalPosition().getY()) {
						target.draw(e,(int) Math.round(dpoints[0] + 2)+10,
								(int) Math.round(dpoints[1] - cheight / 2 + 2), (int) Math.round((w0 + w1) / 3.5),
								(int) Math.round(cellHeight /2));
						
					}
					
					// draw the character image when he moving
					if (i == character.getPosition3d().getX() && j == character.getPosition3d().getZ()) {
						character.draw(e, (int) Math.round(dpoints[0] + 2)+10,
								(int) Math.round(dpoints[1] - cheight / 2 + 2), (int) Math.round((w0 + w1) / 2 / 1.5),
								(int) Math.round(cellHeight /1.5));
					}
				}
			}
			
		}
	}

	/**
	 * Paint cube.
	 *
	 * @param a the a
	 * @param c the c
	 * @param pe the pe
	 */
	private void paintCube(double[] a, double c, PaintEvent pe) 
	{
		int[] f = new int[a.length];
		for (int k = 0; k < f.length; f[k] = (int) Math.round(a[k]), k++);

		pe.gc.drawPolygon(f);

		int[] r = f.clone();
		for (int k = 1; k < r.length; r[k] = f[k] - (int) (c), k += 2);

		int[] b = { r[0], r[1], r[2], r[3], f[2], f[3], f[0], f[1] };
		pe.gc.drawPolygon(b);
		int[] fr = { r[6], r[7], r[4], r[5], f[4], f[5], f[6], f[7] };
		pe.gc.drawPolygon(fr);

		pe.gc.fillPolygon(r);
	}

	/* (non-Javadoc)
	 * @see MVP.guiView.Widgets.Maze3DDisplayer#goLevelUp()
	 */
	@Override
	protected void goLevelUp() {
		Position pos = character.getPosition3d();
		Position desiredPosition = new Position(pos.getY() - 1,pos.getX() ,pos.getZ() );
		
		if (m3d.isInMaze(desiredPosition)
				&& m3d.isPositionEmpty(desiredPosition)) {
			// Switch Floor and redraw maze
			mazeData = m3d.getCrossSectionByY(desiredPosition.getY());
			character.setPosition3d(desiredPosition);
			this.redraw();
			isWin();
		}
		

	}

	/* (non-Javadoc)
	 * @see MVP.guiView.Widgets.Maze3DDisplayer#goLevelDown()
	 */
	@Override
	protected void goLevelDown() {

		Position pos = character.getPosition3d();
		Position desiredPosition = new Position(pos.getY() + 1,pos.getX() ,pos.getZ() );
		
		if (m3d.isInMaze(desiredPosition)
				&& m3d.isPositionEmpty(desiredPosition)) {
			mazeData = m3d.getCrossSectionByY(desiredPosition.getY());
			character.setPosition3d(desiredPosition);
			this.redraw();
			isWin ();
		}
		/*MessageBox errorBox =  new MessageBox(this.getShell(), SWT.ICON_INFORMATION); 
		errorBox.setMessage(m3d.toString()+m3d.getGoalPosition());
		errorBox.setText("info");
		errorBox.open();*/

	}

	/* (non-Javadoc)
	 * @see MVP.guiView.Widgets.Maze3DDisplayer#goLeft()
	 */
	@Override
	protected void goLeft() {
		Position2D pos = character.getPosition2d();
		Position2D desiredPosition = new Position2D(pos.getX()-1, pos.getY());
		if (isIn2DMaze(desiredPosition)
				&& isPosition2DEmpty(desiredPosition)) {
			character.setPosition2d(desiredPosition);
			character.setPosition3d(new Position(character.getPosition3d().getY(), character.getPosition3d().getX() ,
					character.getPosition3d().getZ()- 1));
			this.redraw();
			isWin ();
		}
	}

	/* (non-Javadoc)
	 * @see MVP.guiView.Widgets.Maze3DDisplayer#goRight()
	 */
	@Override
	protected void goRight() {
		Position2D pos = character.getPosition2d();
		Position2D desiredPosition = new Position2D(pos.getX()+1 , pos.getY());
		if (isIn2DMaze(desiredPosition)
				&& isPosition2DEmpty(desiredPosition)) {
			character.setPosition2d(desiredPosition);
			character.setPosition3d(new Position(character.getPosition3d().getY(), character.getPosition3d().getX() ,
					character.getPosition3d().getZ()+ 1));
			this.redraw();
			isWin ();
		}

	}

	/* (non-Javadoc)
	 * @see MVP.guiView.Widgets.Maze3DDisplayer#goUp()
	 */
	@Override
	protected void goUp() {
		Position2D pos = character.getPosition2d();
		Position2D desiredPosition =new Position2D(pos.getX(), pos.getY() - 1);
		if (isIn2DMaze(desiredPosition)
				&& isPosition2DEmpty(desiredPosition)) {
			character.setPosition2d(desiredPosition);
			character.setPosition3d(new Position(character.getPosition3d().getY(), character.getPosition3d().getX()- 1,
					character.getPosition3d().getZ() ));
			this.redraw();
			isWin ();
		}

	}

	/* (non-Javadoc)
	 * @see MVP.guiView.Widgets.Maze3DDisplayer#goDown()
	 */
	@Override
	protected void goDown() {
		Position2D pos = character.getPosition2d();
		Position2D desiredPosition = new Position2D(pos.getX() , pos.getY()+1);
		if (isIn2DMaze(desiredPosition)
				&& isPosition2DEmpty(desiredPosition)) {
			character.setPosition2d(desiredPosition);
			character.setPosition3d(new Position(character.getPosition3d().getY(), character.getPosition3d().getX()+ 1,
					character.getPosition3d().getZ()));
			this.redraw();
			isWin ();
		}

	}

	/**
	 * Checks if is in 2 D maze.
	 *
	 * @param pos the pos
	 * @return true, if is in 2 D maze
	 */
	private boolean isIn2DMaze(Position2D pos) {
		if (pos == null)
			return false;
		return (pos.getX() >= 0 && pos.getX() < this.mazeData[0].length && pos.getY() >= 0
				&& pos.getY() < this.mazeData.length);
	}

	/**
	 * Checks if is position 2 D empty.
	 *
	 * @param pos the pos
	 * @return true, if is position 2 D empty
	 */
	private boolean isPosition2DEmpty(Position2D pos) {
		if (!isIn2DMaze(pos))
			return false;

		int x = pos.getX();
		int y = pos.getY();
		return mazeData[y][x] == 0;
	}

	/* (non-Javadoc)
	 * @see MVP.guiView.Widgets.Maze3DDisplayer#walkToGoalPosition(algorithms.search.Solution, org.eclipse.swt.widgets.Display)
	 */
	@Override
	public void walkToGoalPosition(Solution solution,Display display) {

		this.timer = new Timer();
		this.task = new TimerTask() {
			int i = 0;

			@Override
			public void run() {
				if (i < solution.getStates().size()-1)
				{
					Position pos = new Position(((Maze3dState) solution.getStates().get(i)).getCurrPosition().getY(),
							((Maze3dState) solution.getStates().get(i)).getCurrPosition().getX(),
							((Maze3dState) solution.getStates().get(i)).getCurrPosition().getZ());
					display.syncExec(new Runnable() {
						@Override
					    public void run() 
						{	
							if (pos.getY()<((Maze3dState)solution.getStates().get(i+1)).getCurrPosition().getY())
								goLevelDown();
							else if (pos.getY()>((Maze3dState)solution.getStates().get(i+1)).getCurrPosition().getY())
								goLevelUp();
							else
								setCharacterPosition3D(((Maze3dState)solution.getStates().get(i+1)).getCurrPosition());
							redraw();
						}
					});
					i++;
				} else {
					timer.cancel();
					timer.purge();
				}
			}
		};
		timer.scheduleAtFixedRate(task, 0, 500);
		timer.purge();

	}

	/**
	 * Sets the maze 2 D displayer.
	 *
	 * @param m3d the new maze 2 D displayer
	 */
	public void setMaze2DDisplayer(Maze3d m3d) {
		this.m3d = m3d;
		this.mazeData = m3d.getCrossSectionByY(m3d.getStartPosition().getX());
		this.setCharacterPosition2D(new Position2D(m3d.getStartPosition().getX(), m3d.getStartPosition().getZ()));
		this.setCharacterPosition3D(m3d.getStartPosition());
	}
	
	/**
	 * Checks if is win.
	 */
	private void isWin()
	{
		if (character.getPosition3d().equals(target.getPosition3d())){
			MessageBox info =  new MessageBox(this.getShell(), SWT.ICON_INFORMATION); 
			info.setMessage("You Win !!!");
			info.setText("Information");
			info.open();
		}
	}
}
