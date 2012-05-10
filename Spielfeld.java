public class Spielfeld{

	public static void main(String[] args) {
		 	

	int n = 16;//Spielfeldgröße
	Feld[][] Spielfeld = new Feld[n][n];
    	StdDraw.setXscale(0.0, 17.0);
		StdDraw.setYscale(0.0,17.0);
	
		for(double i = 1.0; i <n;i++){
    	   for(double j = 1.0; j <n;j++){
    		   StdDraw.square(i,j,1.5);

    	   }
		}
	}
}