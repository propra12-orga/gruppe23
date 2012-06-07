import javax.swing.JLabel;

/**
 * zustaendig fuer
 * @author Andrej Morlang
 *
 */

public class Bombe extends JLabel {

	// Deklaration & Initialisierung:
	private static final long serialVersionUID = 1L;
	public int x = 0; 			// x-Koordinate (horizontale Position)
	public int y = 0; 			// y-Koordinate (vertikale Position)
	Zeit timer = new Zeit(); 	// Timer erstellen
	boolean liegt = false;

	/**
	 * 
	 * @param x uebernimmt x-Koord. der Bombe
	 * @param y uebernimmt y-Koord. der Bombe
	 */
	public Bombe(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public Bombe(){}

	// setter & getter:
	/**
	 * 
	 * @return Bombenposition x-Koord.
	 */
	public int get_x() {
		return x;
	}

	/**
	 * 
	 * @param x Bombenposition x-Koord. setzen
	 */
	public void set_x(int x) {
		this.x = x;
	}

	/**
	 * 
	 * @return Bombenposition y-Koord.
	 */
	public int get_y() {
		return y;
	}

	/**
	 * 
	 * @param y Bombenposition y-Koord. setzen
	 */
	public void set_y(int y) {
		this.y = y;
	}

	// aktivieren-Methode:
	/**
	 * Aktiviert den Timer unfuegt ihn an die
	 * {@code Map Menue.game()}-Variable
	 */
	public void aktivieren(int Spieler) {
		liegt = true;
		System.out.println("Bombe gelegt"); // Test	
		System.out.println();
		timer.set_bomb_x(x);
		timer.set_bomb_y(y);
		Menue.get_game().add(timer); 		// Timer hinzufuegen
		timer.timer_starten(3000, "Bombe", Spieler); // Timer fuer Bombe starten	
	}

	// bombe_detonieren-Methode:
	/**
	 * Ueberpruefung der Legalitaet der Detonationsausbreitung; Umwandlung der Felder im Map-Array; Ueberpruefung ob Spieler von Explosion betroffen ist
	 */
	public void bombe_detonieren(int Spieler) {
		for (int x = -1, y = -1; x < 2; x++, y++) {	// Ausbreitung der Detonation um Radius 1
			Menue.get_game();
			// horizontale Ausbreitung der Detonation:
			if (Map.get_map()[this.x + x][this.y] == 2 			// falls das Zielfeld der Detonation ein Weg-Feld...
					|| Map.get_map()[this.x + x][this.y] == 3)	// ...oder Block-Feld ist...
				Map.get_map()[this.x + x][this.y] = 6; 			// ...dann wandele das Feld in ein Explosions-Feld um
			else if (Map.get_map()[this.x + x][this.y] == 8)
				Map.get_map()[this.x + x][this.y] = 9;
			// vertikale Ausbreitung der Detonation:
			if (Map.get_map()[this.x][this.y + y] == 2 			// falls das Zielfeld der Detonation ein Weg-Feld...
					|| Map.get_map()[this.x][this.y + y] == 3)	// ...oder Block-Feld ist...
				Map.get_map()[this.x][this.y + y] = 6; 			// ...dann wandele das Feld in ein Explosions-Feld um
			else if (Map.get_map()[this.x][this.y + y] == 8)
				Map.get_map()[this.x][this.y + y] = 9;
			
			if (x == 0 && y == 0) {								// falls das Zielfeld der Detonation die Bombe selbst ist...
				Map.get_map()[this.x][this.y] = 6;				// ...dann wandele das Feld in ein Explosions-Feld um
			}
			
			else {												// sonst (falls das Zielfeld der Detonation eine andere Bombe ist)...
				// horizontales Treffen von Explosion & Bombe:
				if (Map.get_map()[this.x + x][this.y] == 5) {		// falls das Zielfeld der Detonation ein Bombe-Feld ist...
					Menue.get_game().bomb[this.x + x][this.y].bombe_detonieren(Spieler);	// ...lass auch die andere Bombe detonieren
				}
				
				// vertikales Treffen von Explosion & Bombe:
				if (Map.get_map()[this.x][this.y + y] == 5) {		// falls das Zielfeld der Detonation ein Bombe-Feld ist
					Menue.get_game().bomb[this.x][this.y + y].bombe_detonieren(Spieler);	// ...lass auch die andere Bombe detonieren
				}
			}

			// Treffen von Detonation & Hulk:
			if (((this.x + x == Menue.get_hulk(Spieler).get_x() + x 		// falls Explosion & Hulk die gleiche x-...
			&& this.y == Menue.get_hulk(Spieler).get_y()) 					// ...und y-Koordinate haben...
					|| Map.get_map()[this.x + x][this.y] == 1)  	// ...oder das Explosions-Feld ein Hulk-Icon beinhaltet,...
					|| ((this.x == Menue.get_hulk(Spieler).get_x() 		// ...oder falls Explosion & Hulk die gleiche x-...
					&& this.y + y == Menue.get_hulk(Spieler).get_y() + y) 	// ...und y-Koordinate haben...
					|| Map.get_map()[this.x][this.y + y] == 1)) {	// ...oder das Explosions-Feld ein Hulk-Icon beinhaltet,...

				System.out.println("Verloren!"); 			// Test
				System.out.println();

				System.out.println("Spiel neugestartet"); 	// Test
				System.out.println();

				// Hulk zurueckpositionieren:
				Menue.get_hulk(Spieler).set_x(Menue.get_hulk(Spieler).get_startX());
				Menue.get_hulk(Spieler).set_y(Menue.get_hulk(Spieler).get_startY());

				// Bombe zurueckpositionieren:
				for (int x_neu = 0; x_neu < 11; x_neu++) {
					for (int y_neu = 0; y_neu < 11; y_neu++) {
						Menue.get_game().bomb[x_neu][y_neu].liegt = false;
					}
				}

				// Spielfeld intern reinitialisieren:
				Map.set_map(MapLoader.laden(1));
			}

		}

		Zeit ende_explosion = new Zeit();
		ende_explosion.set_bomb_x(x);
		ende_explosion.set_bomb_y(y);						// Timer fuer Dauer der Explosion erstellen
		Menue.get_game().add(ende_explosion); 				// Timer fuer Dauer der Explosion hinzufuegen
		ende_explosion.timer_starten(1000, "Detonation", Spieler); 	// Timer fuer Dauer der Explosion starten

		Menue.get_game().removeAll(); 						// entferne alle bisherigen Komponenten vom Panel
		Menue.get_game().refresh(); 						// zeichne alle Komponenten des Panels neu

	}

}
