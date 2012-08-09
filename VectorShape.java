import java.awt.Color;

public class VectorShape{

    public int[] x;
    public int[] y;
    public int   num;
    public Color c;
    public boolean closed;
    public boolean filled;
    
    public VectorShape(int[] x, int[] y, Color c, boolean closed, boolean filled){
	this.x = x;
	this.y = y;
	this.num = Math.min(x.length, y.length);
	this.c = c;
	this.closed = closed;
	this.filled = filled;
    }

    public VectorShape(int[] x, int[] y, Color c, int num, boolean closed, boolean filled){
	this.x = x;
	this.y = y;
	this.c = c;
	this.num = num;
	this.closed = closed;
	this.filled = filled;
    }
}
