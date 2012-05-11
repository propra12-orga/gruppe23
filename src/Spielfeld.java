import javax.swing.JFrame;
import javax.swing.JLabel;

public class Spielfeld extends JFrame{
	
//	ImageIcon Hulk = new ImageIcon("Hulk.jpg");
//  Image Hu1 = Hulk.getImage();
//  ImageIcon Block = new ImageIcon("Block.jpg");
//  Image Bl1 = Block.getImage();
//  ImageIcon Mauer = new ImageIcon("Mauer.jpg");
//  Image Ma1 = Mauer.getImage();
//  ImageIcon Weg = new ImageIcon("Weg.jpg");
//  Image Weg1 = Weg.getImage();	 	


	/**
	 * 
	 */
	private static final long serialVersionUID = -8277081151052108904L;
	public void malen(int i){
		
		switch(i){
			case	1: //male Hulk
				
			case 	2: //male Weg
				
			case	3: // male Mauer
				
			case	4: //male Block
				
		}
}
	public Spielfeld(){

	    int n = 16;//Spielfeldgr��e
		JLabel[][] Spiel = new JLabel[n][n];

		for(int i = 1; i <n;i++){
    	   for(int j = 1; j <n;j++){
    		   	// nicht die draw funktion benutzen, sondern f�r ein n x n array jeweils viele labels erstellen mit images einf�gen
    	   }
		}
	}
		public static void main(String args[]){
	
				Spielfeld first = new Spielfeld();
		


	int n = 16;//Spielfeldgr��e
	Feld[][] Spielfelder = new Feld[n][n];
//    	StdDraw.setXscale(0.0, 17.0);
//		StdDraw.setYscale(0.0,17.0);

//	
		for(double i = 1.0; i <n;i++){
    	   for(double j = 1.0; j <n;j++){
    		   StdDraw.square(i,j,1.5);


    	   }// nicht die draw funktion benutzen, 
    	   //sondern f�r ein n x n array jeweils 
    	   //viele labels erstellen mit images einf�gen
    	   

    	   }// nicht die draw funktion benutzen, sondern f�r ein n x n array jeweils viele labels erstellen mit images einf�gen

    	   }// nicht die draw funktion benutzen, sondern f�r ein n x n array jeweils viele labels erstellen mit images einf�gen

		}
		
		
	
