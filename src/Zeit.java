import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

public class Zeit extends JLabel {
	private static final long serialVersionUID = 1L;
	Timer timer = new Timer(); // Timer erstellen
	int bomb_x, bomb_y;

	// timer_starten-Methode
	public void timer_starten(int x, String logo) { // x = Timer-Verögerung,
													// logo =
													// Bomben-/Detonations-Icon

		if (logo.equals("Bombe")
				&& Menue.get_game().bomb[bomb_x][bomb_y].liegt == true) {
			timer.schedule(new Bombe(), x); // Zeit bis zur Detonation um x
											// Millisek. verzögern
		}

		else if (logo.equals("Detonation")
				&& Menue.get_game().bomb[bomb_x][bomb_y].liegt == true) {
			timer.schedule(new Detonation(), x); 	// Zeit bis zum Ende der
													// Detonation um x Millisek.
													// verzögern
		}

	}

	public class Bombe extends TimerTask {
		public void run() {
			if (Menue.get_game().bomb[bomb_x][bomb_y].liegt == true) { // falls das Spiel nicht während des Timers neugestartet wurde
				System.out.println("Bombe explodiert"); // Test
				System.out.println("");

				Menue.get_game().bomb[bomb_x][bomb_y].bombe_detonieren(); // Detonation der Bombe
				timer.cancel(); // Timer terminieren
			}

		}

	}

	public class Detonation extends TimerTask {
		public void run() {
			if (Menue.get_game().bomb[bomb_x][bomb_y].liegt == true) { // falls das Spiel nicht während des Timers neugestartet wurde
				System.out.println("Detonation beendet"); // Test
				System.out.println("");

				timer.cancel(); // Timer terminieren	

				for (int x = -1, y = -1; x < 2; x++, y++) {
					if (Map.get_map()[bomb_x + x][bomb_y] == 6) {
						Map.get_map()[bomb_x + x][bomb_y] = 2; 	// Nach Ablauf des
					}											// Timers wieder das
					else if (Map.get_map()[bomb_x + x][bomb_y] == 9) {
						Map.get_map()[bomb_x + x][bomb_y] = 7;
					}
					// Weg-Icon
					// darstellen
					if (Map.get_map()[bomb_x][bomb_y + y] == 6) {
						Map.get_map()[bomb_x][bomb_y + y] = 2;
					} else if (Map.get_map()[bomb_x][bomb_y + y] == 9) {
						Map.get_map()[bomb_x][bomb_y + y] = 7;
					}
				}

			}

			Menue.get_game().removeAll();
			Menue.get_game().refresh();

			Menue.get_game().bomb[bomb_x][bomb_y].liegt = false;
			{
				timer.cancel(); // Timer terminieren
			}

		}
	}

	public void set_bomb_x(int bomb_x) {
		this.bomb_x = bomb_x;
	}

	public void set_bomb_y(int bomb_y) {
		this.bomb_y = bomb_y;
	}

}