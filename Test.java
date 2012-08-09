import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;

public class Test extends Canvas implements ActionListener{

    private VectorPlane vp = new VectorPlane(10);
    private VectorRenderer vr = new VectorRenderer();
    private ViewPort view = new ViewPort(-20, -20, 390, 290);


    private VectorTransformation vt = new VectorTransformation();
   

    
    private VectorShape ballshape;
    private int ballposx = 1;
    private int ballposy = 1;
    private int dx = 1;
    private int dy = 1;

    private JFrame frame;

    public Test(){

	this.setPreferredSize(new Dimension(410, 310));


	JFrame frame = new JFrame();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().add(this);
	frame.pack();
	frame.setVisible(true);

	javax.swing.Timer timer = new javax.swing.Timer(100, this);
	timer.start();




	//vp.setColor(Color.RED);
	//for(int i=0; i<view.getWidth(); i+= 5)
	    //vp.plotLine(i, view.getY1(), i, view.getY2());
	//for(int i=0; i<view.getHeight(); i+= 5)
	    //vp.plotLine(view.getX1(), i, view.getX2(), i);
	
	
	//vp.setColor(Color.BLUE);
	//vp.plotRect(50,50, 100,100);
	//vp.plotRect(60,60, 110,110, true);

	vp.setColor(Color.BLACK);
	ballshape = vp.plotCircle(ballposx, ballposy, 20, false);
	//vp.plotCircle(150, 50, 25, true);
	//vp.plotCircle(150, 150, 30);
	

    }

    public void actionPerformed(ActionEvent e){
	if(ballposx >= view.getX2() || ballposx <= view.getX1())
	    dx *= -1;
	if(ballposy >= view.getY2() || ballposy <= view.getY1())
	    dy *= -1;

	ShapeTranslator.translate(ballshape, dx, dy);
	ballposx += dx;
	ballposy += dy;

	//System.out.println("x: " + ballposx + ", y: " + ballposy);
	this.repaint();
    }

    public void paint(Graphics g){

	vr.renderToGraphics(g, vp.transform(vt), view, 0, 0, this.getWidth(), this.getHeight());

	//vr.renderToGraphics(g, vp, view, 0, 0, this.getWidth(), this.getHeight());
    }

    
    //public void update(Graphics g){
	//this.paint(g);
    //}


    public static void main(String[] args){
	new Test();
    }
}
