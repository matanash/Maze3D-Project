package MVP.guiView.Widgets;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;

import model.maze3d.Maze3d;
import model.maze3d.Position;

public class Maze2DDisplayer extends Maze3DDisplayer {

	int[][] mazeData;

	public Maze2DDisplayer(Composite parent, int style, Maze3d m3d, String displayedCrossSection) {
		super(parent, style);
		this.setBackground(new Color(null, 255, 255, 255));

		this.m3d = m3d;
		this.displayedCrossSection = displayedCrossSection;

		switch (this.displayedCrossSection) {
		case "XZ":
		case "xz": {
			mazeData = m3d.getCrossSectionByY(0);
			this.setCharacterPosition2D(new Position2D(m3d.getStartPosition().getX(), m3d.getStartPosition().getZ()));
			// this.setCharacterPosition(m3d.getStartPosition());
		}
		case "YZ":
		case "yz": {
			mazeData = m3d.getCrossSectionByY(0);
			this.setCharacterPosition2D(new Position2D(m3d.getStartPosition().getY(), m3d.getStartPosition().getZ()));
			// this.setCharacterPosition(m3d.getStartPosition());
		}
		case "XY":
		case "xy": {
			mazeData = m3d.getCrossSectionByY(0);
			this.setCharacterPosition2D(new Position2D(m3d.getStartPosition().getX(), m3d.getStartPosition().getY()));
			// this.setCharacterPosition(m3d.getStartPosition());
		}
		}
	}

	@Override
	protected void drawMaze(PaintEvent e) {
		e.gc.setBackground(new Color(null, 0, 0, 0));

		// width and height of the canvas
		int canvasWidth = getSize().x;
		int canvasHeight = getSize().y;

		int cellWidth;
		int cellHeight;

		// width and height of each cell
		switch (this.displayedCrossSection) {
		case ("XZ"):
		case ("xz"): {
			cellWidth = canvasWidth / m3d.getWidth();
			cellHeight = canvasHeight / m3d.getLength();
		}
		case ("YZ"):
		case ("yz"): {
			cellWidth = canvasWidth / m3d.getWidth();
			cellHeight = canvasHeight / m3d.getHeight();
		}
		case ("XY"):
		case ("xy"): {
			cellWidth = canvasWidth / m3d.getLength();
			cellHeight = canvasHeight / m3d.getHeight();
		}
		default: // y Cross Section Displayed sizes
		{
			cellWidth = canvasWidth / m3d.getWidth();
			cellHeight = canvasHeight / m3d.getLength();
		}
		}

		for (int i = 0; i < mazeData.length; i++) {
			for (int j = 0; j < mazeData[0].length; j++) {
				if (mazeData[i][j] != 0) {
					e.gc.fillRectangle(j * cellWidth, i * cellHeight, cellWidth, cellHeight);
				}
			}
		}

		character.draw(e, cellWidth, cellHeight);
	}

	@Override
	protected void goLevelUp() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void goLevelDown() {
		// Position2D pos = character.getPosition2d();
		Position pos = character.getPosition3d();
		/*
		 * if (isIn2DMaze(new Position2D(pos.getX() + 1, pos.getY())) &&
		 * isPosition2DEmpty(new Position2D(pos.getX() + 1, pos.getY())))
		 */
		if (m3d.isInMaze(new Position(pos.getY() + 1, pos.getX(), pos.getZ()))
				&& m3d.isPositionEmpty(new Position(pos.getY() + 1, pos.getX(), pos.getZ()))) 
		{
			mazeData = m3d.getCrossSectionByY(pos.getY()+1);
			character.setPosition3d(new Position(character.getPosition3d().getY(), character.getPosition3d().getX() + 1,
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

}
