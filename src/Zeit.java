import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;

/**
 * 
 * @author Andrej Morlang
 *
 */
public class Zeit extends JLabel {
	private static final long serialVersionUID = 1L;
	Timer timer = new Timer(); // Timer erstellen
	int bomb_x, bomb_y;
	
	

	// timer_starten-Methode
	/**
	 * Initialisiert den Bombentimer
	 * @param x	 Zeit bis zur Detonation der Bombe
	 * @param logo String der Bomben-Aktion ("Bombe" fuer Legen der Bombe, "Detonation")
	 */
	public void timer_starten(int x, String logo, int spieler) { // x = Timer-Ver�gerung,
													// logo =
													// Bomben-/Detonations-Icon

		if (logo.equals("Bombe")
				&& Menue.get_game().bomb[bomb_x][bomb_y].liegt == true) {
			timer.schedule(new Bombe(spieler), x); // Zeit bis zur Detonation um x
											// Millisek. verz�gern
		}

		else if (logo.equals("Detonation")
				&& Menue.get_game().bomb[bomb_x][bomb_y].liegt == true) {
			timer.schedule(new Detonation(), x); 	// Zeit bis zum Ende der
													// Detonation um x Millisek.
													// verz�gern
		}

	}

	/**
	 * 
	 * @author Andrej Morlang
	 *
	 */
	public class Bombe extends TimerTask {
		
		private int Spieler;
		public Bombe (int spieler){
			Spieler = spieler;
		}
		/**
		 * startet den TimerTask-Thread Bombe (Ueberprueft gleichzeitig, ob das Spiel waehrend des Countdown neugestartet wurde
		 */
		public void run() {
			if (Menue.get_game().bomb[bomb_x][bomb_y].liegt == true) { // falls das Spiel nicht w�hrend des Timers neugestartet wurde
				System.out.println("Bombe explodiert"); // Test
				System.out.println("");

				Menue.get_game().bomb[bomb_x][bomb_y].bombe_detonieren(Spieler); // Detonation der Bombe
				timer.cancel(); // Timer terminieren
			}

		}

	}

	/**
	 * 
	 * @author Andrej Morlang
	 *
	 */
	public class Detonation extends TimerTask {
		/**
		 * startet den TimerTask-Thread Detonation nach Ablauf der Zeit x (timer_starten-Methode), welcher die Detonationsschritte vollzieht
		 */
		public void run() {
			if (Menue.get_game().bomb[bomb_x][bomb_y].liegt == true) { // falls das Spiel nicht w�hrend des Timers neugestartet wurde
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

	/**
	 * 
	 * @param bomb_x setzt x-Koordinate der Bombe
	 */
	public void set_bomb_x(int bomb_x) {
		this.bomb_x = bomb_x;
	}

	/**
	 * 
	 * @param bomb_y setzt y-Koordinate der Bombe
	 */
	public void set_bomb_y(int bomb_y) {
		this.bomb_y = bomb_y;
	}

}