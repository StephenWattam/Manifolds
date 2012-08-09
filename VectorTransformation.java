import java.util.*;

public class VectorTransformation{
    private Random rand = new Random();

    public VectorTransformation(){ };


    public int transformX(int x){
	return (int)(x*x * 0.01);

	//return (int)(Math.sqrt(x) * 20 );
    }

    public int transformY(int y){
	return (int)(y*y * 0.01);
	//return (int)(Math.sqrt(y) * 20); 
	//(rand.nextInt(4) - 2);
    }

    public VectorShape transformShape(VectorShape s){
	return s;	
    }
}
