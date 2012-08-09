public class ViewPort{

    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private int w;
    private int h;



    public void setWidth(int width){
	w = width;
	x2 = x1 + w;
    }

    public void setHeight(int height){
	h = height;
	y2 = y1 + h;
    }

    public void setBounds(int x1, int y1, int x2, int y2){
	this.x1 = x1;
	this.y1 = y1;
	this.x2 = x2;
	this.y2 = y2;
	this.w = x2 - x1;
	this.h = y2 - y1;
    }

    public ViewPort(int x1, int y1, int x2, int y2){
	setBounds(x1, y1, x2, y2);
    }

    public int getWidth(){
	return w;
    }

    public int getHeight(){
	return h;
    }

    public int getX1(){
	return x1;
    }

    public int getX2(){
	return x2;
    }

    public int getY1(){
	return y1;
    }

    public int getY2(){
	return y2;
    }
    

    public int[] transformX(int[] x, int xmin, int xmax){
	int[] transx = new int[x.length];
	int outwidth = xmax - xmin;


	for(int i=0; i<x.length; i++){
	    //System.out.println("x[" + i + "] = " + x[i] + ", xmin = " + xmin + ", xmax = " + xmax);
	    transx[i] = (int)((float)(x[i] - x1) * ((float)outwidth / (float)w)) + xmin;
	    //System.out.println("transx[" + i + "] = " + transx[i]);
	}
	return transx;
    }

    public int[] transformY(int[] y, int ymin, int ymax){
	int[] transy = new int[y.length];
	int outheight = ymax - ymin;

	for(int i=0; i<y.length; i++){
	    //System.out.println("y1=" + y1);
	    transy[i] = (int)((float)(y[i] - y1) * ((float)outheight / (float)h)) + ymin;
	}
	return transy;	
    }

}
