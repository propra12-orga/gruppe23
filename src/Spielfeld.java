import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Spielfeld extends JFrame{
	 	

//	private static final long serialVersionUID = -8277081151052108904L;
	public void malen(int i){
		
		switch(i){
			case	1: //male Hulk
				
			case 	2: //male Weg
				
			case	3: // male Mauer
				
			case	4: //male Block

	int n = 16;//Spielfeldgr��e
	Feld[][] Spielfelder = new Feld[n][n];
//    	StdDraw.setXscale(0.0, 17.0);
//		StdDraw.setYscale(0.0,17.0);
//	
		/*for(double i = 1.0; i <n;i++){
    	   for(double j = 1.0; j <n;j++){
//    		   StdDraw.square(i,j,1.5);

    	   }// nicht die draw funktion benutzen, sondern f�r ein n x n array jeweils viele labels erstellen mit images einf�gen

		*/
	}
}
	public Spielfeld(){ 
		final Image imageHulk  = Toolkit.getDefaultToolkit().getImage("Hulk.jpg");
		final Image imageBlock = Toolkit.getDefaultToolkit().getImage("Block.jpg");
		final Image imageMauer = Toolkit.getDefaultToolkit().getImage("Mauer.jpg");
		final Image imageWeg   = Toolkit.getDefaultToolkit().getImage("Weg.jpg");

	    int n = 16;//Spielfeldgdgroesse
		JLabel[][] Spiel = new JLabel[n][n];
		Container cp = this.getContentPane();

		final JPanel panel = new JPanel() {

			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {

				super.paintComponent(g);
				if (true)
					g.drawLine(0, 0, 200, 100);

				// Bild zeichnen
				g.drawImage(imageHulk, 200, 100, 150, 100, this);

			}
		};


	}
	
	public static void main(String args[]){
		
		
	}
}

		
		
	
