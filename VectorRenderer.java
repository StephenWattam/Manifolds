import java.awt.*;
import java.util.Vector;

public class VectorRenderer{

    public void renderToGraphics(Graphics g, VectorPlane vp, ViewPort view, 
	    int x1, int y1, int x2, int y2){
	Vector<VectorShape> shapes = vp.getShapes();
	for(VectorShape s: shapes){

	    g.setColor(s.c);

	    if(s.closed){
		if(s.filled){
		    g.fillPolygon(view.transformX(s.x, x1, x2), view.transformY(s.y, y1, y2), s.num);
		}else{
		    g.drawPolygon(view.transformX(s.x, x1, x2), view.transformY(s.y, y1, y2), s.num);
		}
	    }else{
		g.drawPolyline(view.transformX(s.x, x1, x2), view.transformY(s.y, y1, y2), s.num);
	    }
	}
    }

    /* can contain settings such as resolution */
    public VectorRenderer(){ };
}
