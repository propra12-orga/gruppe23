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

//		for(int i = 1; i <n;i++){
//    	   for(int j = 1; j <n;j++){
////    		   male(1);
//    	   }
//		}
	}
	
}

		
		
	
