import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Zeit extends JLabel
{
	private static final long serialVersionUID = 1L;
	Timer timer;
	
	// timer_starten-Methode
	public void timer_starten(int x) { // x = Timer-Verögerung
		timer = new Timer(); // Timer erstellen
		timer.schedule(new explosion(), x); // Zeit bis zur Explosion um x Millisek. verzögern
	}

	public class explosion extends TimerTask {
		public void run() {
			System.out.print("Bombe explodiert"); // Konsolenausgabe zum Test
			setIcon(new ImageIcon("src/Pics/EXP.png")); // Bild laden
			Menue.getGame().repaint(); // Zeichnen
			Menue.getGame().setVisible(true); // Sichtbarkeit setzen
			timer.cancel(); // Timer terminieren
		}
		
	}

}