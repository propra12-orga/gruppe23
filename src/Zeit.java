import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

public class Zeit extends JLabel {
	private static final long serialVersionUID = 1L;
	Timer timer;

	// timer_starten-Methode
	public void timer_starten(int x, String logo) { // x = Timer-Verögerung,
													// logo =
													// Bomben-/Detonations-Icon
		timer = new Timer(); // Timer erstellen

		if (logo.equals("Bombe")) {
			timer.schedule(new Bombe(), x); // Zeit bis zur Detonation um x
											// Millisek. verzögern
		}

		else if (logo.equals("Detonation")) {
			timer.schedule(new Detonation(), x); // Zeit bis zum Ende der
													// Detonation um x Millisek.
													// verzögern
		}

	}

	public class Bombe extends TimerTask {
		public void run() {
			System.out.println("Bombe explodiert"); // Test
			System.out.println("");

			Menue.get_game().bombe_detonieren(); // Detonation der Bombe
			timer.cancel(); // Timer terminieren
		}

	}

	public class Detonation extends TimerTask {
		public void run() {
			System.out.println("Detonation beendet"); // Test
			System.out.println("");

			timer.cancel(); // Timer terminieren

			for (int x = -1, y = -1; x < 2; x++, y++) {
				if (Menue.get_map()[Menue.get_game().bomb.get_x() + x][Menue
						.get_game().bomb.get_y()] == 6)
					Menue.get_map()[Menue.get_game().bomb.get_x() + x][Menue
							.get_game().bomb.get_y()] = 2; // Nach Ablauf des
															// Timers wieder das
															// Weg-Icon
															// darstellen
				if (Menue.get_map()[Menue.get_game().bomb.get_x()][Menue
						.get_game().bomb.get_y() + y] == 6)
					Menue.get_map()[Menue.get_game().bomb.get_x()][Menue
							.get_game().bomb.get_y() + y] = 2;
			}

			Menue.get_game().removeAll();
			Menue.get_game().refresh();
		}

	}

}