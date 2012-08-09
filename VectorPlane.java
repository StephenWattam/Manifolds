import java.util.Vector;
import java.awt.Color;

public class VectorPlane{
    private Vector<VectorShape> shapes = new Vector<VectorShape>();

    private Color penColour = new Color(0, 0, 0);
    private int plotDensity;


    
    public VectorPlane(int plotDensity){ 
	this.plotDensity = plotDensity;	
    }

    public void setColor(Color c){
	this.penColour = c;
    }

    private float pythag(int x1, int y1, int x2, int y2){
	return pythag(x2-x1, y2-y1);
    }

    private float pythag(int x, int y){
	return (float)Math.sqrt( (x*x) + (y*y) );
    }

    public VectorShape plotLine(int x1, int y1, int x2, int y2){
	//ensure we always round up to segment shortish lines
	int number_of_segments = (int)(pythag(x1,y1,x2,y2) / (float)plotDensity) + 1;

	int[] x, y;
	if( number_of_segments <= 1 ){
	    x = new int[2];
	    y = new int[2];
	    x[0] = x1;
	    x[1] = x2;

	    y[0] = y1;
	    y[1] = y2;
	    number_of_segments = 2;
	}else{
	    float xinc = (float)(x2-x1)/number_of_segments;
	    float yinc = (float)(y2-y1)/number_of_segments;
	    x = new int[number_of_segments];
	    y = new int[number_of_segments];

	    for(int i=0; i<number_of_segments; i++){
		x[i] = x1 + (int)(i * xinc);
		y[i] = y1 + (int)(i * yinc);
	    }
	}
	/* appends to the end of the vector */
	VectorShape line = new VectorShape(x, y, penColour, number_of_segments, false, false);
	shapes.add(line);
	return line;
    }
    
    public void plotRect(int x1, int y1, int x2, int y2){
	plotRect(x1, y1, x2, y2, false);
    }

    public void plotRect(int x1, int y1, int x2, int y2, boolean filled){
	plotLine(x1, y1, x1, y2);
	plotLine(x1, y2, x2, y2);
	plotLine(x2, y2, x2, y1);
	plotLine(x2, y1, x1, y1);
    }

    public VectorShape plotCircle(int xmid, int ymid, int r){
	return plotCircle(xmid, ymid, r, false);
    }

    public VectorShape plotCircle(int xmid, int ymid, int r, boolean filled){
	Vector<Integer> xlist = new Vector<Integer>();
	Vector<Integer> ylist = new Vector<Integer>();

	
	int d = r*r;
	int y, x;

	for(x = -r; x<= r; x++){
	    y = (int)(Math.sqrt(d - x*x) + 0.5);

	    xlist.add(xmid + x);
	    ylist.add(ymid + y);
	}
	
	for(x = -r; x<= r; x++){
	    y = (int)(Math.sqrt(d - x*x) + 0.5);

	    xlist.add(xmid - x);
	    ylist.add(ymid - y);
	}
	

	int[] xarr = intVectorToArray(xlist);
	int[] yarr = intVectorToArray(ylist);

	VectorShape circle = new VectorShape(xarr, yarr, penColour, Math.min(xarr.length, yarr.length), true, filled);
	shapes.add(circle);
	return circle;
    }

    public void plotPolygon(int[] x, int[] y, int num, boolean closed, boolean filled){
	shapes.add(new VectorShape(x, y, penColour, num, closed, filled));
    }

    private int[] intVectorToArray(Vector<Integer> list){
	int[] result = new int[list.size()];
	int count = 0;
	for(Integer i: list){
	    result[count] = list.elementAt(count).intValue();
	    count++;
	}
	return result;
    }

    public Vector<VectorShape> getShapes(){
	return this.shapes;
    }

    public VectorPlane transform(VectorTransformation vt){
	VectorPlane vp = this.deepCopy();

	Vector<VectorShape> shapes = vp.getShapes();

	for(VectorShape s: shapes){

	    for(int i=0; i<s.x.length; i++)
		s.x[i] = vt.transformX(s.x[i])
		    ;
	    for(int i=0; i<s.y.length; i++)
		s.y[i] = vt.transformY(s.y[i]);

	    vt.transformShape(s);

	}

	return vp;
    }

    public VectorPlane deepCopy(){
	VectorPlane vpnew = new VectorPlane(this.plotDensity);
	for(VectorShape s: shapes){
	    vpnew.setColor(s.c);
	   
	    /* Array copy to ensure that changes aren't propagated back to the parent object */ 
	    int[] x = new int[s.x.length];
	    int[] y = new int[s.y.length];
	    System.arraycopy( s.x, 0, x, 0, s.x.length);
	    System.arraycopy( s.y, 0, y, 0, s.y.length);

	    /* plot polygons with the respective properties of all shapes within the current VP */
	    vpnew.plotPolygon(x, y, s.num, s.closed, s.filled);
	}
	vpnew.setColor(this.penColour);
	return vpnew;
    }

}
