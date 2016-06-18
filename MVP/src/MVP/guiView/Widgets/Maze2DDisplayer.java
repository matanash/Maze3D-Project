package MVP.guiView.Widgets;

import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import algorithms.search.Solution;
import model.maze3d.Maze3d;
import model.maze3d.Position;
import model.maze3d.domains.Maze3dState;

public class Maze2DDisplayer extends Maze3DDisplayer {

	int[][] mazeData;
	
	private Timer timer;
	
	private TimerTask task;
	
	public Maze2DDisplayer(Composite parent, int style, Maze3d m3d) {
		super(parent, style);
		this.setBackground(new Color(null, 255, 255, 255)); // WHITE
		
		this.m3d = m3d;
		mazeData = m3d.getCrossSectionByY(m3d.getStartPosition().getX());
		this.setCharacterPosition2D(new Position2D(m3d.getStartPosition().getX(), m3d.getStartPosition().getZ()));
		this.setCharacterPosition3D(m3d.getStartPosition());
		MessageBox messageBox =  new MessageBox((Shell) parent, SWT.ICON_INFORMATION); 
    	messageBox.setMessage(m3d.getStartPosition().toString());
    	messageBox.setText("information message");
    	messageBox.open();
	}

	@Override
	protected void drawMaze(PaintEvent e) {
		if(mazeData != null)
		{
			e.gc.setForeground(new Color(null, 0, 0, 0)); // BLACK
			e.gc.setBackground(new Color(null, 0, 0, 0)); //BLACK
			
			int canvasWidth = getSize().x;
			int canvasHeight = getSize().y;
			
			int middleWidth = canvasWidth / 2;
			
			double cellWidth = (double) canvasWidth / mazeData[0].length;
			double cellHeight = (double) canvasHeight/ mazeData.length;
			for(int  i = 0; i < mazeData.length ; i++)
			{
			   double w0 = 0.75 * cellWidth + 0.25 * cellWidth * i /mazeData.length;
			   double w1 = 0.75 * cellWidth + 0.25 * cellWidth * (i+1) /mazeData.length;
			   double start = middleWidth -w0 * mazeData[i].length / 2;
			   double start1 = middleWidth -w1 * mazeData[i].length / 2;
			   for(int j=0; j<mazeData[i].length; j++)
			   {
				   double[] dpoints = {start+j*w0,i*cellHeight,start+j*w0+w0,i*cellHeight,start1+j*w1+w1,i*cellHeight+cellHeight,start1+j*w1,i*cellHeight+cellHeight};
				   double cheight = cellHeight / 2;
				   if(mazeData[i][j] != 0){
					   paintCube(dpoints, cheight, e);
				   }
				   
				 //draw the character image when he moving
		          if(i==character.getPosition3d().getZ() && j==character.getPosition3d().getX()){
		        	  character.draw(e,(int)Math.round(dpoints[0]+2), (int)Math.round(dpoints[1]-cheight/2+2), (int)Math.round((w0+w1)/2/1.5), (int)Math.round(cellHeight/1.5));
		          }
			          
		        //draw the goal position image when we arrive it
		         if(i == m3d.getGoalPosition().getZ() && j==m3d.getGoalPosition().getX() && m3d.getCrossSectionByY(m3d.getGoalPosition().getY()).equals(mazeData)){
		        	  character.draw(e,(int)Math.round(dpoints[0]+2), (int)Math.round(dpoints[1]-cheight/2+2), (int)Math.round((w0+w1)/2/1.5), (int)Math.round(cellHeight/1.5) );
		         }
			   }
			}
		}
		
		
	}
	private void paintCube(double[] a,double c, PaintEvent pe){
		
        int[] f=new int[a.length];
        for(int k=0;k<f.length;f[k]=(int)Math.round(a[k]),k++);
        
        pe.gc.drawPolygon(f);
        
        int[] r = f.clone();
        for(int k = 1; k < r.length; r[k]=f[k]-(int)(c), k+=2);
        

        int[] b={r[0],r[1],r[2],r[3],f[2],f[3],f[0],f[1]};
        pe.gc.drawPolygon(b);
        int[] fr={r[6],r[7],r[4],r[5],f[4],f[5],f[6],f[7]};
        pe.gc.drawPolygon(fr);
        
        pe.gc.fillPolygon(r);
	}

	@Override
	protected void goLevelUp() {
		Position pos = character.getPosition3d();
		if (m3d.isInMaze(new Position(pos.getY() - 1, pos.getX(), pos.getZ()))
				&& m3d.isPositionEmpty(new Position(pos.getY() - 1, pos.getX(), pos.getZ()))) {
			//Switch Floor and redraw maze
			mazeData = m3d.getCrossSectionByY(pos.getY() - 1);
			character.setPosition3d(new Position(character.getPosition3d().getY() - 1, character.getPosition3d().getX(),
					character.getPosition3d().getZ()));

			this.redraw();
		}

	}

	@Override
	protected void goLevelDown() {

		Position pos = character.getPosition3d();

		if (m3d.isInMaze(new Position(pos.getY() + 1, pos.getX(), pos.getZ()))
				&& m3d.isPositionEmpty(new Position(pos.getY() + 1, pos.getX(), pos.getZ()))) {
			mazeData = m3d.getCrossSectionByY(pos.getY() + 1);
			character.setPosition3d(new Position(character.getPosition3d().getY() + 1, character.getPosition3d().getX(),
					character.getPosition3d().getZ()));

			this.redraw();
		}

	}

	@Override
	protected void goLeft() {
		Position2D pos = character.getPosition2d();
		if (isIn2DMaze(new Position2D(pos.getX() - 1, pos.getY()))
				&& isPosition2DEmpty(new Position2D(pos.getX() - 1, pos.getY()))) {
			character.setPosition2d(new Position2D(pos.getX() - 1, pos.getY()));
			character.setPosition3d(new Position(character.getPosition3d().getY(), character.getPosition3d().getX() - 1,
					character.getPosition3d().getZ()));
			this.redraw();
		}
	}

	@Override
	protected void goRight() {
		Position2D pos = character.getPosition2d();
		if (isIn2DMaze(new Position2D(pos.getX() + 1, pos.getY()))
				&& isPosition2DEmpty(new Position2D(pos.getX() + 1, pos.getY()))) {
			character.setPosition2d(new Position2D(pos.getX() + 1, pos.getY()));
			character.setPosition3d(new Position(character.getPosition3d().getY(), character.getPosition3d().getX() + 1,
					character.getPosition3d().getZ()));
			this.redraw();
		}

	}

	@Override
	protected void goUp() {
		Position2D pos = character.getPosition2d();
		if (isIn2DMaze(new Position2D(pos.getX(), pos.getY() - 1))
				&& isPosition2DEmpty(new Position2D(pos.getX(), pos.getY() - 1))) {
			character.setPosition2d(new Position2D(pos.getX(), pos.getY() - 1));
			character.setPosition3d(new Position(character.getPosition3d().getY(), character.getPosition3d().getX(),
					character.getPosition3d().getZ() - 1));
			this.redraw();
		}

	}

	@Override
	protected void goDown() {
		Position2D pos = character.getPosition2d();
		if (isIn2DMaze(new Position2D(pos.getX(), pos.getY() + 1))
				&& isPosition2DEmpty(new Position2D(pos.getX(), pos.getY() + 1))) {
			character.setPosition2d(new Position2D(pos.getX(), pos.getY() + 1));
			character.setPosition3d(new Position(character.getPosition3d().getY(), character.getPosition3d().getX(),
					character.getPosition3d().getZ() + 1));
			this.redraw();
		}

	}

	public boolean isIn2DMaze(Position2D pos) {
		if (pos == null)
			return false;
		return (pos.getX() >= 0 && pos.getX() < this.mazeData[0].length && pos.getY() >= 0
				&& pos.getY() < this.mazeData.length);
	}

	public boolean isPosition2DEmpty(Position2D pos) {
		if (!isIn2DMaze(pos))
			return false;

		int x = pos.getX();
		int y = pos.getY();
		return mazeData[y][x] == 0;
	}
	@Override
	public void walkToGoalPosition(Solution solution)
	{
		//Collections.reverse(solution.getPath());
		this.timer = new Timer();
		this.task = new TimerTask() {
			int i = solution.getStates().size()-1;
			
			@Override
			public void run() {

				if(i >= 0){
					setCharacterPosition3D(new Position(((Maze3dState)solution.getStates().get(i)).getCurrPosition().getY(),((Maze3dState)solution.getStates().get(i)).getCurrPosition().getX(),
							((Maze3dState)solution.getStates().get(i)).getCurrPosition().getZ()));
					i--;
				}
				else{
					timer.cancel();
					timer.purge();
				}
			}
		};
		timer.scheduleAtFixedRate(task, 0, 100);
		timer.purge();
	}

}
