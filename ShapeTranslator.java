public abstract class ShapeTranslator{
    public static void translate(VectorShape s, int x, int y){
	for(int i=0; i<s.x.length; i++)
	    s.x[i] += x;

	for(int i=0; i<s.y.length; i++)
	    s.y[i] += y;
	
    }
}
