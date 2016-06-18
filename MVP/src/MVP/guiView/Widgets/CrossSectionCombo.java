/*package MVP.guiView.Widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

*//**
 * Used to control a Maze2D widget through a Combo widget.
 *//*
public class CrossSectionCombo extends Canvas{
	
	protected Maze2DWidget slave;		//the controlled Maze2D
	protected Combo combo;		
	
	*//**
	 * Regular SWT Ctor.
	 * @param parent - parenting Widget.
	 * @param style - SWT style.
	 * @param aslave - the controlled Maze2D.
	 *//*
	public CrossSectionCombo(Composite parent, int style, Maze2DWidget aslave) {
		super(parent, SWT.NULL);
		this.slave = aslave;
		Canvas canvas = this;
		
		setLayout(new GridLayout(1, true));
		combo = new Combo(canvas, style);		//Initializing the Combo.
		combo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		combo.setItems(new String[] { "x", "y", "z" });
		
		combo.setBackground(new Color(getDisplay(), 255, 255, 255));
		combo.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(slave !=null)
				switch(combo.getText())
				{
					case "y":
						slave.setCrossSection('y');
						break;	
					case "x":
						slave.setCrossSection('x');			//changes the displayed plain in respect to the combo's selection.
						break;
					case "z":
						slave.setCrossSection('z');
						break;
					default:
						slave.setCrossSection('y');	
				}
				
				Display.getDefault().syncExec(new Runnable() {		//redraws the widget.
				    public void run() {
				    	redraw();
				    }
			    });
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
	}
	
	*//**
	 * Regular get method for slave member.
	 * @return Maze2D slave.
	 *//*
	public Maze2DWidget getSlave() {
		return slave;
	}
	
	*//**
	 * Regular set method for slave member.
	 * @param slave - Maze2D new slave.
	 *//*
	public void setSlave(Maze2DWidget slave) {
		this.slave = slave;
		updateText();	//updates the displayed combo.
	}
	
	*//**
	 * As the CrossCombo receives new plain it updates the Combo's text.
	 *//*
	public void updateText()
	{
		if(slave!=null)
		{
			switch(slave.getCrossSection())
			{
				case 'y':
					combo.select(0);
					break;	
				case 'x':
					combo.select(1);
					break;
				case 'z':
					combo.select(3);
					break;
				default:
					combo.select(0);	
			}
		}
	}
}
*/