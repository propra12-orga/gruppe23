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

	
	public void malen(int i){
		
		switch(i){
			case	1: //male Hulk
				
			case 	2: //male Weg
				
			case	3: // male Mauer
				
			case	4: //male Block
				
		}
}
	public Spielfeld(){

	    int n = 16;//Spielfeldgröße
		JLabel[][] Spiel = new JLabel[n][n];

		for(int i = 1; i <n;i++){
    	   for(int j = 1; j <n;j++){
    		   	// nicht die draw funktion benutzen, sondern für ein n x n array jeweils viele labels erstellen mit images einfügen
    	   }
		}
	}
		public static void main(String args[]){
	
				Spielfeld first = new Spielfeld();
			
		}
		
		
	}
