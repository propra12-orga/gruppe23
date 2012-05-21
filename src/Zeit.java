import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

public class Zeit extends JLabel
{
	private static final long serialVersionUID = 1L;
	Timer timer;
	
	// timer_starten-Methode
	public void timer_starten(int x, String logo) { // x = Timer-Verögerung, logo = Bomben-/Detonations-Icon
		timer = new Timer(); // Timer erstellen
		
		if (logo.equals("Bombe")) {
			timer.schedule(new bombe(), x); // Zeit bis zur Detonation um x Millisek. verzögern
		}
		
		else if (logo.equals("Detonation")) {
			timer.schedule(new detonation(), x); // Zeit bis zum Ende der Detonation um x Millisek. verzögern
		}

	}

	public class bombe extends TimerTask {
		public void run() {
			System.out.println("Bombe explodiert"); // Test
			System.out.println("");
			
			Menue.get_game().bombe_detonieren(); // Detonation der Bombe
			timer.cancel(); // Timer terminieren				
		}
		
	}
	
	public class detonation extends TimerTask {
		public void run() {
			System.out.println("Detonation beendet"); // Test
			System.out.println("");
			
			timer.cancel(); // Timer terminieren
			Menue.map[Menue.get_game().bomb.get_x()][Menue.get_game().bomb.get_y()] = 2; // Nach Ablauf des Timers wieder das Weg-Icon darstellen
		}
		
	}

}