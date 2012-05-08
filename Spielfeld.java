import javax.swing.*;

public class Spielfeld extends JFrame {
	
	
	public Spielfeld(){
		
		super("BomberHulk 1.0");
	int n = 16;//Spielfeldgröße
	Feld[][] Spielfeld = new Feld[n][n];

			/* Zufallsbelegung möglich? oder nur feste maps ;  StdDraw.picture(rx, ry, "");
			
              setSize(300,300);
              setLocation(300,300);
              setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              setVisible(true);*/
	
         StdDraw.setXscale(0.0, 16.0);
         StdDraw.setYscale(0.0,16.0);
       for(int i = 0; i <n;i++){
    	   for(int j = 0; j <n;j++){
    		  // Spielfeld[i][j]= dra
    		   
    				   StdDraw.Square(8.0,8.0,8.0);
    	   }
       }
              
              

	}
	public static void main(String[] args) {
		 
        Spielfeld g = new Spielfeld();


}
}