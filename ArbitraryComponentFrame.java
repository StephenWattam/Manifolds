import javax.swing.*; 
import javax.swing.JPanel;

import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.util.Vector;
import java.awt.font.*;
public class ArbitraryComponentFrame extends JFrame{
	private Component c;

	/**Creates a new visualisation with the given net and dimension.

	  	@param p_net The net to visualise
	 	@param p_width The width of the visualisation
		@param p_height The height of the visualisation
	 */
	public ArbitraryComponentFrame(int p_width, int p_height, String p_title, Component c){
		super(p_title);
		setSize(new Dimension(p_width,p_height));

		setComponent(c);

		repaint();
		setVisible(true);
	}

	private Component getComponent(){
		return this.c;
	}

	private void setComponent(Component c){
		this.c = c;
		
		c.setSize(this.getWidth(),this.getHeight());
		this.add(c);
	}

	public void Paint(Graphics g){
		System.out.println("Woo2");
	    
	}

	public void repaint(){
		System.out.println("Woo");
		c.repaint();
	}
}
