import javax.swing.JLabel;

/**
 * zustaendig fuer
 * @author Andrej Morlang
 *
 */

public class Bombe extends JLabel {

	/* Deklaration & Initialisierung: */
	private static final long serialVersionUID = 1L;
	public int x = 0; 			// x-Koordinate (horizontale Position)
	public int y = 0; 			// y-Koordinate (vertikale Position)
	Zeit timer = new Zeit(); 	// Timer erstellen
	boolean liegt = false;
	int radius = 2;
	
	// Pro Richtung kann die Bombe maximal einen Treffer verursachen:
	boolean treffer_oben 	= false,
			treffer_rechts 	= false,	
			treffer_unten 	= false,	
			treffer_links 	= false;
	
	int n = MapLoader.get_n();

	/* Konstruktor: */
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

	/* METHODEN: */
	/* setter & getter: */
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
		System.out.println("Bombe gelegt");	// Test	
		System.out.println();				// Test
		
		timer.set_bomb_x(x);
		timer.set_bomb_y(y);
		Menue.get_game().add(timer); 							// Timer hinzufuegen
		timer.timer_starten(3000, "Bombe", Spieler, radius);	// Timer fuer Bombe starten	
	}

	// bombe_detonieren-Methode:
	/**
	 * Ueberpruefung der Legalitaet der Detonationsausbreitung; Umwandlung der Felder im Map-Array; Ueberpruefung ob Spieler von Explosion betroffen ist
	 */
	public void bombe_detonieren(int Spieler) {
		Menue.get_hulk(Spieler).set_max_bomben(Menue.get_hulk(Spieler).get_max_bomben() + 1);	// Spieler darf wieder eine Bombe mehr legen
		
		/* Ausbreitung der Detonation nach rechts und unten: */
		for (int x = 0, y = 0; x <= radius; x++, y++) {
			// horizontal:
			if (this.x + x > 0 && this.x + x < n) {		// falls die Detonation nicht ueber den Spielfeldrand hinaus geht
				if (Map.get_map()[this.x + x][this.y] == 4) {		// falls das Zielfeld der Detonation ein Mauer-Feld ist...
					if (x > 0 && treffer_rechts == false) {
						treffer_rechts = true;
					}
				}
				
				if (Map.get_map()[this.x + x][this.y] == 3) {		// falls das Zielfeld der Detonation ein Block-Feld ist...
					if (x > 0 && treffer_rechts == false) {
						treffer_rechts = true;
						Map.get_map()[this.x + x][this.y] = 6; 			// ...dann wandele das Feld in ein Explosions-Feld um
					}
					
				}
				
				else if (Map.get_map()[this.x + x][this.y] == 8) {	// oder falls das Zielfeld der Detonation ein Block-/Ausgangs-Feld ist...
					if (x > 0 && treffer_rechts == false) {
						treffer_rechts = true;
						Map.get_map()[this.x + x][this.y] = 9;			// ...dann wandele das Feld in ein Explosions-/Ausgangs-Feld um
					}
					
				}
				
				else if (Map.get_map()[this.x + x][this.y] == 0) {	// oder falls das Zielfeld der Detonation ein Block-/Bomben-Item-Feld ist...
					if (x > 0 && treffer_rechts == false) {
						treffer_rechts = true;
						Map.get_map()[this.x + x][this.y] = 11;			// ...dann wandele das Feld in ein Explosions-/Bomben-Item-Feld um
					}
					
				}
				
				if (Map.get_map()[this.x + x][this.y] == 2) { 		// falls das Zielfeld der Detonation ein Weg-Feld...
					if (x > 0 && treffer_rechts == false) {
						Map.get_map()[this.x + x][this.y] = 6;			// ...dann wandele das Feld in ein Explosions-Feld um
					}
					
				}
				
			}
			
			// vertikal:
			if (this.y + y > 0 && this.y + y < n) {		// falls die Detonation nicht ueber den Spielfeldrand hinaus geht
				if (Map.get_map()[this.x][this.y + y] == 4) {	// falls das Zielfeld der Detonation ein Mauer-Feld ist...
					if (y > 0 && treffer_unten == false) {
						treffer_unten = true;
					}
				}
				
				if (Map.get_map()[this.x][this.y + y] == 3) {	// falls das Zielfeld der Detonation ein Block-Feld ist...
					if (y > 0 && treffer_unten == false) {
						treffer_unten = true;
						Map.get_map()[this.x][this.y + y] = 6; 		// ...dann wandele das Feld in ein Explosions-Feld um
					}
					
				}
				
				else if (Map.get_map()[this.x][this.y + y] == 8) {	// oder falls das Zielfeld der Detonation ein Block-/Ausgangs-Feld ist...
					if (y > 0 && treffer_unten == false) {
						treffer_unten = true;
						Map.get_map()[this.x][this.y + y] = 9;			// ...dann wandele das Feld in ein Explosions-/Ausgangs-Feld um
					}
					
				}
				
				else if (Map.get_map()[this.x][this.y + y] == 0) {	// oder falls das Zielfeld der Detonation ein Block-/Bomben-Item-Feld ist...
					if (y > 0 && treffer_unten == false) {
						treffer_unten = true;
						Map.get_map()[this.x][this.y + y] = 11;			// ...dann wandele das Feld in ein Explosions-/Bomben-Item-Feld um
					}
					
				}
				
				if (Map.get_map()[this.x][this.y + y] == 2) { 		// falls das Zielfeld der Detonation ein Weg-Feld...
					if (y > 0 && treffer_unten == false) {
						Map.get_map()[this.x][this.y + y] = 6;			// ...dann wandele das Feld in ein Explosions-Feld um
					}
				
				}
				
			}
			
			/* Treffen von Detonation & Bombe: */
			// dieselbe Bombe:
			if (x == 0 && y == 0) {								// falls das Zielfeld der Detonation die Bombe selbst ist...
				Map.get_map()[this.x][this.y] = 6;				// ...dann wandele das Feld in ein Explosions-Feld um
			}
			
			// andere Bombe:
			else {												// sonst (falls das Zielfeld der Detonation eine andere Bombe ist)...
				// horizontales Treffen von Explosion & Bombe:
				if (this.x + x > 0 && this.x + x < n) {		// falls die Detonation nicht ueber den Spielfeldrand hinaus geht
					if (Map.get_map()[this.x + x][this.y] == 5) {		// falls das Zielfeld der Detonation ein Bombe-Feld ist...
						Menue.get_game().bomb[this.x + x][this.y].bombe_detonieren(Spieler);	// ...lass auch die andere Bombe detonieren
					}
					
				}
				
				if (this.y + y > 0 && this.y + y < n) {		// falls die Detonation nicht ueber den Spielfeldrand hinaus geht
					// vertikales Treffen von Explosion & Bombe:
					if (Map.get_map()[this.x][this.y + y] == 5) {		// falls das Zielfeld der Detonation ein Bombe-Feld ist
						Menue.get_game().bomb[this.x][this.y + y].bombe_detonieren(Spieler);	// ...lass auch die andere Bombe detonieren
					}
					
				}
				
			}

			/* Treffen von Detonation & Hulk: */
			// horizontal:
			if (this.x + x > 0 && this.x + x < n) {	// falls die Detonation nicht ueber den Spielfeldrand hinaus geht
				if (Map.get_map()[this.x + x][this.y] == 1		// falls die Detonation eine Spielfigur trifft...
					|| Map.get_map()[this.x + x][this.y] == 10) {					
					if (x > 0 && treffer_rechts == false) {
						System.out.println("Game over"); 			// Test
						System.out.println();						// Test
						
						Menue.spiel_neustarten();
					}					

				}
				
			}
			
			// vertikal:
			if (this.y + y > 0 && this.y + y < n) {	// falls die Detonation nicht ueber den Spielfeldrand hinaus geht
				if (Map.get_map()[this.x][this.y + y] == 1	// falls die Detonation die erste...
						|| Map.get_map()[this.x][this.y + y] == 10) {	// ...oder zweite Spielfigur trifft					
					if (y > 0 && treffer_unten == false) {
						System.out.println("Game over"); 			// Test
						System.out.println();						// Test
						
						Menue.spiel_neustarten();
					}

				}
				
			}
			
			// Spielfeld grafisch reinitialisieren:
			Menue.get_game().removeAll();
			Menue.get_game().refresh();
		}
		
		/* Ausbreitung der Detonation nach links und oben: */
		for (int x = 0, y = 0; x >= -radius; x--, y--) {
			// horizontal:
			if (this.x + x > 0 && this.x + x < n) {	// falls die Detonation nicht ueber den Spielfeldrand hinaus geht
				if (Map.get_map()[this.x + x][this.y] == 4) {	// falls das Zielfeld der Detonation ein Mauer-Feld ist...
					if (x < 0 && treffer_links == false) {
						treffer_links = true;
					}

				}
				
				if (Map.get_map()[this.x + x][this.y] == 3) {	// falls das Zielfeld der Detonation ein Block-Feld ist...
					if (x < 0 && treffer_links == false) {
						treffer_links = true;
						Map.get_map()[this.x + x][this.y] = 6; 		// ...dann wandele das Feld in ein Explosions-Feld um
					}

				}
				
				else if (Map.get_map()[this.x + x][this.y] == 8) {	// oder falls das Zielfeld der Detonation ein Block-/Ausgangs-Feld ist...
					if (x < 0 && treffer_links == false) {
						treffer_links = true;
						Map.get_map()[this.x + x][this.y] = 9;			// ...dann wandele das Feld in ein Explosions-/Ausgangs-Feld um
					}

				}
				
				else if (Map.get_map()[this.x + x][this.y] == 0) {	// oder falls das Zielfeld der Detonation ein Block-/Bomben-Item-Feld ist...
					if (x < 0 && treffer_links == false) {
						treffer_links = true;
						Map.get_map()[this.x + x][this.y] = 11;			// ...dann wandele das Feld in ein Explosions-/Bomben-Item-Feld um
					}

				}
				
				if (Map.get_map()[this.x + x][this.y] == 2) { 		// falls das Zielfeld der Detonation ein Weg-Feld...
					if (x < 0 && treffer_links == false) {
						Map.get_map()[this.x + x][this.y] = 6;			// ...dann wandele das Feld in ein Explosions-Feld um
					}

				}
				
			}
			
			// vertikal:
			if (this.y + y > 0 && this.y + y < n) {	// falls die Detonation nicht ueber den Spielfeldrand hinaus geht
				if (Map.get_map()[this.x][this.y + y] == 4) {	// falls das Zielfeld der Detonation ein Mauer-Feld ist...
					if (y < 0 && treffer_oben == false) {
						treffer_oben = true;
					}

				}
				
				if (Map.get_map()[this.x][this.y + y] == 3) {	// falls das Zielfeld der Detonation ein Block-Feld ist...
					if (y < 0 && treffer_oben == false) {
						treffer_oben = true;
						Map.get_map()[this.x][this.y + y] = 6; 		// ...dann wandele das Feld in ein Explosions-Feld um
					}

				}
				
				else if (Map.get_map()[this.x][this.y + y] == 8) {	// oder falls das Zielfeld der Detonation ein Block-/Ausgangs-Feld ist...
					if (y < 0 && treffer_oben == false) {
						treffer_oben = true;
						Map.get_map()[this.x][this.y + y] = 9;			// ...dann wandele das Feld in ein Explosions-/Ausgangs-Feld um
					}

				}
				
				else if (Map.get_map()[this.x][this.y + y] == 0) {	// oder falls das Zielfeld der Detonation ein Block-/Bomben-Item-Feld ist...					
					if (y < 0 && treffer_oben == false) {
						treffer_oben = true;
						Map.get_map()[this.x][this.y + y] = 11;			// ...dann wandele das Feld in ein Explosions-/Bomben-Item-Feld um
					}

				}
				
				if (Map.get_map()[this.x][this.y + y] == 2) { 		// falls das Zielfeld der Detonation ein Weg-Feld...
					if (y < 0 && treffer_oben == false) {
						Map.get_map()[this.x][this.y + y] = 6;			// ...dann wandele das Feld in ein Explosions-Feld um
					}

				}
				
			}
			
			/* Treffen von Detonation & Bombe: */
			// dieselbe Bombe:
			if (x == 0 && y == 0) {								// falls das Zielfeld der Detonation die Bombe selbst ist...
				Map.get_map()[this.x][this.y] = 6;				// ...dann wandele das Feld in ein Explosions-Feld um
			}
			
			// andere Bombe:
			else {												// sonst (falls das Zielfeld der Detonation eine andere Bombe ist)...
				// horizontales Treffen von Explosion & Bombe:
				if (this.x + x > 0 && this.x + x < n) {	// falls die Detonation nicht ueber den Spielfeldrand hinaus geht
					if (Map.get_map()[this.x + x][this.y] == 5) {		// falls das Zielfeld der Detonation ein Bombe-Feld ist...
						Menue.get_game().bomb[this.x + x][this.y].bombe_detonieren(Spieler);	// ...lass auch die andere Bombe detonieren
					}
				}
				
				// vertikales Treffen von Explosion & Bombe:
				if (this.y + y > 0 && this.y + y < n) {	// falls die Detonation nicht ueber den Spielfeldrand hinaus geht
					if (Map.get_map()[this.x][this.y + y] == 5) {		// falls das Zielfeld der Detonation ein Bombe-Feld ist
						Menue.get_game().bomb[this.x][this.y + y].bombe_detonieren(Spieler);	// ...lass auch die andere Bombe detonieren
					}
				}
			}

			/* Treffen von Detonation & Hulk: */
			// horizontal:
			if (this.x + x > 0 && this.x + x < n) {	// falls die Detonation nicht ueber den Spielfeldrand hinaus geht
				if (Map.get_map()[this.x + x][this.y] == 1		// falls die Detonation die erste...
					|| Map.get_map()[this.x + x][this.y] == 10) {	// ...oder zweite Spielfigur trifft
					
					if (x < 0 && treffer_links == false) {
						System.out.println("Game over"); 			// Test
						System.out.println();						// Test
						
						Menue.spiel_neustarten();
					}
					
				}
				
			}
			
			// vertikal:
			if (this.y + y > 0 && this.y + y < n) {	// falls die Detonation nicht ueber den Spielfeldrand hinaus geht
				if (Map.get_map()[this.x][this.y + y] == 1	// falls die Detonation die erste...
					|| Map.get_map()[this.x][this.y + y] == 10) {	// ...oder zweite Spielfigur trifft
					
					if (y < 0 && treffer_oben == false) {
						System.out.println("Game over"); 			// Test
						System.out.println();						// Test
						
						Menue.spiel_neustarten();
					}

				}
				
			}
			
			// Spielfeld grafisch reinitialisieren:
			Menue.get_game().removeAll();
			Menue.get_game().refresh();
		}
		
		// Treffer zurücksetzen:
		treffer_oben 	= false;
		treffer_rechts 	= false;
		treffer_unten 	= false;
		treffer_links 	= false;		

		Zeit ende_explosion = new Zeit();
		ende_explosion.set_bomb_x(x);
		ende_explosion.set_bomb_y(y);						// Timer fuer Dauer der Explosion erstellen
		Menue.get_game().add(ende_explosion); 				// Timer fuer Dauer der Explosion hinzufuegen
		
		if (liegt == true) { 	// falls das Spiel nicht waehrend des Timers neugestartet wurde
			ende_explosion.timer_starten(1000, "Detonation", Spieler, radius); 	// Timer fuer Dauer der Explosion starten
		}
		
		else {					// sonst
			Map.set_map(MapLoader.laden(1));									// Spielfeld neu einlesen
		}
		
		Menue.get_game().removeAll();
		Menue.get_game().refresh();

	}

}
