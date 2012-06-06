import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * 
 * @author Kolja Salewski
 *
 */
public class Menue implements KeyListener {

	// Deklaration & Initialisierung:
	/**
	 * Hauptframe des Programmes
	 */
	private JFrame frame;
	
	/**
	 * Button im Leistenmenue
	 * (Schliesst das Programm)
	 */
	private final Action_Beenden Action_Beenden = new Action_Beenden(); // Aktion
																		// zum
																		// Beenden
																		// des
																		// Spiels
																		// erstellen
	
	/**
	 * Button im Leistenmenue
	 * (Neustart des Spieles)
	 */
	private final Action_Neu Action_Neu = new Action_Neu(); // Aktion zum
															// Neustart des
															// Spiels erstellen
	final static int[][] map = MapLoader.laden(1); 	// Spielfeld initialisieren
	
	/**
	 * Objekt der Map()-Klasse; enthaelt die Daten des Spielfeldes;
	 */
	private static Map game = new Map(map); 		// Spielfeld erstellen
	
	/**
	 * enthalet die Informationen ueber die Spielerposition ((x,y)-Koordinate)
	 */
	private static Hulk hulk1 = new Hulk(1,1); 			// Hulk erstellen
	
	/**
	 * enthaelt Informationen ueber die 2. Spielerfigur (Position)
	 */
	private static Hulk hulk2 = new Hulk(10,10);
	public static boolean twoPlayer;
	
	// private Thread moveHulk;
	// private Thread bombHulk;
	private static boolean move;
	private static boolean bomb;
	private static int[] a;
	public boolean spiel_neugestartet = false;	

	// Konstruktor:
	/**
	 * {@code initialize()} legt die Panels in das JFrame {@code frame()} & erstellt die grafische Oberflaeche des Spieles
	 */
	public Menue() {
		
		initialize();
		a = new int[2];

		// moveHulk = new Thread(new MoveHulk()); //Threads initialisieren und
		// starten
		// moveHulk.start();

		// bombHulk = new Thread(new BombHulk());
		// bombHulk.start();

		bomb = false;
		move = false;
		twoPlayer = false;
	}

	// Methode zum Initialisieren des Spielfelds:
	
	/**
	 * Initialisiert das Spielfeld (Panels, frames etc.), 
	 */
	private void initialize() {
		frame = new JFrame(); // Fenster erstellen
		frame.setTitle("Bomberhulk"); // Fenstertitel setzen
		frame.setBounds(100, 100, 550, 605); 	// Fenstergroesse einstellen
												// (x-Position, y-Position,
												// Breite, Hoehe)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 	// Programm beim
																// Schliessen
																// des Fensters
																// beenden
		frame.setResizable(false); // Fenster soll nicht skalierbar sein

		JMenuBar menuBar = new JMenuBar(); 	// Menueleiste erstellen
		frame.setJMenuBar(menuBar); 		// Menueleiste hinzufuegen

		menuBar.isFocusable();

		JMenu mnSpiel = new JMenu("Spiel"); // Menuepunkt "Spiel" erstellen
		menuBar.add(mnSpiel); 				// Menuepunkt "Spiel" hinzufuegen

		JMenuItem mntmNeu = new JMenuItem("Neu"); 	// Untermenuepunkt "Neu"
													// erstellen
		mnSpiel.add(mntmNeu); 						// Untermenuepunkt "Neu" hinzufuegen
		mntmNeu.setAction(Action_Neu); 				// Aktion "Action_Neu" hinzufuegen

		JMenuItem mntmBeenden = new JMenuItem("Beenden"); 	// Untermenuepunkt
															// "Beenden"
															// erstellen
		mnSpiel.add(mntmBeenden); 							// Untermenuepunkt "Beenden" hinzufuegen
		mntmBeenden.setAction(Action_Beenden); 				// Aktion "Action_Beenden"
															// hinzufuegen

		frame.add(game); 	// Spielfeld hinzufuegen
		game.init(); 		// Spielfeld zeichnen

		game.addKeyListener(this); 	// Keylistener zum Spielfeld hinzufuegen
		game.setFocusable(true); 	// Spielfeld fokussierbar machen
		game.requestFocus(); 		// Fokus auf Spielfeld setzen
	}

	// keyPressed-Methode:
	/**
	 * Horcht, ob eine Taste gedrueckt wurde und wertet die Aktion gegebenfalls aus.
	 * Gueltige Aktionen sind: Hoch-, Links-, Rechts-, Runtertaste (Bewegung) und Leertaste (Bombe)
	 * 
	 */
	public void keyPressed(KeyEvent Key) {
		// oben:
		if (Key.getKeyCode() == KeyEvent.VK_UP) {
			System.out.println("oben"); // Test
			System.out.println();

			a[0] = 0;
			a[1] = -1;

			move = true;
		}

		// Pfeiltaste links:
		if (Key.getKeyCode() == KeyEvent.VK_LEFT) {
			System.out.println("links"); // Test
			System.out.println();

			a[0] = -1;
			a[1] = 0;

			move = true;
		}

		// Pfeiltaste rechts:
		if (Key.getKeyCode() == KeyEvent.VK_RIGHT) {
			System.out.println("rechts"); // Test
			System.out.println();

			a[0] = 1;
			a[1] = 0;

			move = true;
		}

		// Pfeiltaste unten:
		if (Key.getKeyCode() == KeyEvent.VK_DOWN) {
			System.out.println("unten"); // Test
			System.out.println();

			a[0] = 0;
			a[1] = 1;

			move = true;
		}

		// Leertaste:
		if (Key.getKeyCode() == KeyEvent.VK_SPACE) {
			System.out.println("Space"); // Test
			System.out.println();

			bomb = true;

			game.bombe_legen();
			game.removeAll(); 	// entferne alle bisherigen Komponenten vom Panel
			game.refresh();		// zeichne alle Komponenten des Panels neu

		}

		if (Key.getKeyCode() != KeyEvent.VK_SPACE) {
			if (Map.map[hulk1.get_x() + a[0]][hulk1.get_y() + a[1]] == 2) { 	// falls
																			// das
																			// naechste
																			// Feld
																			// ein
																			// Weg-Feld
																			// ist

				game.move_Hulk(a[0], a[1]); // bewege Hulk auf dem Spielfeld
				game.removeAll(); 			// entferne alle bisherigen Komponenten vom
											// Panel
				game.refresh(); 			// zeichne alle Komponenten des Panels neu
			}

			else if (Map.map[hulk1.get_x() + a[0]][hulk1.get_y() + a[1]] == 7) { 	// falls
																				// das
																				// naechste
																				// Feld
																				// das
																				// Ziel-Feld
																				// ist
				System.out.println("Gewonnen!"); // Test
				System.out.println();

				System.out.println("Spiel neugestartet"); // Test
				System.out.println();
		
				// Hulk zurueckpositionieren:
				hulk1.set_x(1);
				hulk1.set_y(1);

				
				// Gelegte Bomben entfernen:
				for (int x=0; x<11; x++) {
					for (int y=0; y<11; y++) {
						game.bomb[x][y].liegt = false;
					}
				}				
				
				// Spielfeld intern reinitialisieren:
				Map.set_map(MapLoader.laden(1));

				// Spielfeld grafisch reinitialisieren:
				game.removeAll();
				game.refresh();
			}

			else if (Map.map[hulk1.get_x() + a[0]][hulk1.get_y() + a[1]] == 6) { // falls
																				// das
																				// naechste
																				// Feld
																				// ein
																				// Explosions-Feld
																				// ist
				System.out.println("Verloren!"); // Test
				System.out.println();

				System.out.println("Spiel neugestartet"); // Test
				System.out.println();
				
				// Hulk zurueckpositionieren:
				hulk1.set_x(1);
				hulk1.set_y(1);

				// Gelegte Bomben entfernen:
				for (int x=0; x<11; x++) {
					for (int y=0; y<11; y++) {
						game.bomb[x][y].liegt = false;
					}
				}			
				
				// Spielfeld intern reinitialisieren:
				Map.set_map(MapLoader.laden(1));

				// Spielfeld grafisch reinitialisieren:
				game.removeAll();
				game.refresh();
			}
		}
	}

	public void keyTyped(KeyEvent Key) {
	}

	public void keyReleased(KeyEvent Key) {
	}

	/**
	 * 
	 * @return	Array mit neuer Spielfigurenposition
	 */
	public static int[] get_newPos() {
		return a;
	}

	/**
	 * Klasse fuer Menuebuttonorganisation "Beenden", beendet das Programm
	 * @author Kolja Salewski
	 *
	 */
	private class Action_Beenden extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public Action_Beenden() {
			putValue(NAME, "Beenden");
			putValue(SHORT_DESCRIPTION, "Beenden des Spiels");
		}

		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}

	}

	/**
	 * Klasse fuer Menuebuttonorganisation "Neu", setzt das Spielfeld auf Anfang
	 * @author Kolja Salewski
	 *
	 */
	private class Action_Neu extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public Action_Neu() {
			putValue(NAME, "Neu");
			putValue(SHORT_DESCRIPTION, "Neustart des Spiels");
		}

		public void actionPerformed(ActionEvent e) {
			System.out.println("Spiel neugestartet"); // Test
			System.out.println();

			// Hulk zurueckpositionieren:
			hulk1.set_x(1);
			hulk1.set_y(1);

			// Gelegte Bomben entfernen:
			for (int x=0; x<11; x++) {
				for (int y=0; y<11; y++) {
					game.bomb[x][y].liegt = false;
				}
			}		
			
			// Spielfeld intern reinitialisieren:
			Map.set_map(MapLoader.laden(1));

			// Spielfeld grafisch reinitialisieren:
			game.removeAll();
			game.refresh();
		}

	}

	/**
	 * Horchmethode fuer BombHulk-Thread
	 */
	public static boolean get_bomb() {
		return bomb;
	}

	/**
	 * Horchmethode fuer BombHulk-Thread
	 */
	public static void set_bomb() {
		bomb = false;
	}

	/**
	 * Horchmethode fuer MoveHulk-Thread
	 */
	public static boolean get_move() {
		return move;
	}

	/**
	 * Horchmethode fuer MoveHulk-Thread
	 */
	public static void set_move() {
		move = false;
	}

	/**
	 * Wird in der Map-Klasse verwendet
	 * @return Spielfeldobjekt game
	 */
	public static Map get_game() {
		return game;
	}

	/**
	 * 
	 * @param game Setzt das Spielfeldobjekt
	 */
	public void set_game(Map game) {
		Menue.game = game;
	}

	/**
	 * 
	 * @return Position der Spielfigur im Array (hulk)
	 */
	public static Hulk get_hulk() {
		return hulk1;
	}

	/**
	 * 
	 * @param hulk Aktualisiert die Position der Spielfigur in der Menue-Klasse
	 */
	public void set_hulk(Hulk hulk) {
		Menue.hulk1 = hulk;
	}
	
	/**
	 * Für Mehrspieler.
	 * @return Hulk-Objekt hulk2
	 */
	public static Hulk getHulk2() {
		return hulk2;
	}

	/**
	 * 
	 * @param hulk2	 Hulk-Objekt 2. Spieler
	 */
	public static void setHulk2(Hulk hulk2) {
		Menue.hulk2 = hulk2;
	}
	
	/**
	 * 
	 * @return Map-Array (Positionen der Icons im Spielfeld)
	 */
	public static int[][] get_map() {
		return map;
	}
	
	public static boolean getMultiplayer(){
		return twoPlayer;
	}

	// main-Methode:
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menue window = new Menue();
					window.frame.setVisible(true);
				}

				catch (Exception e) {
					e.printStackTrace();
				}

			}

		});
		
	}



}
