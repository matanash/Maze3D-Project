package MVP.guiView.Widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import algorithms.search.Solution;
import model.maze3d.Maze3d;
import model.maze3d.Position;

public abstract class Maze3DDisplayer extends Composite{
	
	protected GameItem character;
	
	protected GameItem target ;
	
	protected Maze3d m3d;
	
	protected abstract void drawMaze(PaintEvent e);

	protected abstract void goLevelDown();

	protected abstract void goLevelUp();
	
	protected abstract void goLeft();

	protected abstract void goRight();
	
	protected abstract void goDown();
	
	protected abstract void goUp();

	public Maze3DDisplayer(Composite parent, int style) 
	{
		super(parent, style);

		this.addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent e) {
				drawMaze(e);
			}
		});

		this.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent arg0) {}

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.keyCode) {
				case SWT.PAGE_UP:
					goLevelUp();
					break;
				case SWT.PAGE_DOWN:
					goLevelDown();
					break;
				case SWT.ARROW_LEFT:
					goLeft();
					break;
				case SWT.ARROW_RIGHT:
					goRight();
					break;
				case SWT.ARROW_UP:
					goUp();
					break;
				case SWT.ARROW_DOWN:
					goDown();
					break;
				}
			}
		});
	}
	
	public abstract void walkToGoalPosition(Solution solution,Display display);
	
	public Maze3d getMaze3d() {
		return this.m3d;
	}
	
	public void setMaze3d(Maze3d m3d) {
		this.m3d = m3d;
	}
	
	public GameItem getGameCharacter() {
		return this.character;
	}
	
	public void setGameItem(GameItem character) {
		this.character = character;
	}
	
	public void setCharacterPosition2D(Position2D position2d) {
		character.setPosition2d(position2d);
	}
	public void setCharacterPosition3D(Position position3d) {
		character.setPosition3d(position3d);
	}
	
	
}
