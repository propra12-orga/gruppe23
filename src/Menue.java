import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menue implements KeyListener {

	// Deklaration & Initialisierung:
	private JFrame frame;
	private final Action_Beenden Action_Beenden = new Action_Beenden(); // Aktion
																		// zum
																		// Beenden
																		// des
																		// Spiels
																		// erstellen
	private final Action_Neu Action_Neu = new Action_Neu(); // Aktion zum
															// Neustart des
															// Spiels erstellen
	static int[][] map = {
			{ 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 }, // Zeile ist in der Map die Spalte
			{ 4, 1, 2, 2, 2, 2, 2, 2, 2, 2, 4 },
			{ 4, 2, 4, 2, 4, 2, 4, 2, 4, 2, 4 },
			{ 4, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4 },
			{ 4, 2, 4, 2, 4, 2, 4, 2, 4, 2, 4 },
			{ 4, 2, 2, 2, 3, 3, 2, 2, 2, 2, 4 },
			{ 4, 2, 4, 2, 4, 2, 4, 2, 4, 2, 4 },
			{ 4, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4 },
			{ 4, 2, 4, 2, 4, 2, 4, 2, 4, 3, 4 },
			{ 4, 2, 3, 2, 2, 2, 2, 2, 3, 2, 4 },
			{ 4, 4, 4, 4, 4, 4, 4, 4, 4, 7, 4 } };
	private static Map game = new Map(map); // Spielfeld erstellen
	private static Hulk hulk = new Hulk(); // Hulk erstellen
	//private Thread moveHulk;
	//private Thread bombHulk;
	private static boolean move;

	private static boolean bomb;

	private static int[] a;

	// Konstruktor:
	public Menue() {
		initialize();
		a = new int[2];

		//	moveHulk = new Thread(new MoveHulk());			//Threads initialisieren und starten
		//	moveHulk.start();

		//	bombHulk = new Thread(new BombHulk());
		//	bombHulk.start();

		bomb = false;
		move = false;
	}

	// Methode zum Initialisieren des Spielfelds:
	private void initialize() {
		frame = new JFrame(); 									// Fenster erstellen
		frame.setTitle("Bomberhulk");							// Fenstertitel setzen
		frame.setBounds(100, 100, 550, 610); 					// Fenstergroesse einstellen (x-Position, y-Position, Breite, Hoehe)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 	// Programm beim Schliessen des Fensters beenden
		frame.setResizable(false); 								// Fenster soll nicht skalierbar sein

		JMenuBar menuBar = new JMenuBar(); 	// Menueleiste erstellen
		frame.setJMenuBar(menuBar); 		// Menueleiste hinzufuegen

		menuBar.isFocusable();

		JMenu mnSpiel = new JMenu("Spiel"); // Menuepunkt "Spiel" erstellen
		menuBar.add(mnSpiel); 				// Menuepunkt "Spiel" hinzufuegen

		JMenuItem mntmNeu = new JMenuItem("Neu"); 	// Untermenuepunkt "Neu" erstellen
		mnSpiel.add(mntmNeu); 						// Untermenuepunkt "Neu" hinzufuegen
		mntmNeu.setAction(Action_Neu); 				// Aktion "Action_Neu" hinzufuegen

		JMenuItem mntmBeenden = new JMenuItem("Beenden"); 	// Untermenuepunkt "Beenden" erstellen
		mnSpiel.add(mntmBeenden); 							// Untermenuepunkt "Beenden" hinzufuegen
		mntmBeenden.setAction(Action_Beenden); 				// Aktion "Action_Beenden" hinzufuegen

		frame.add(game); 	// Spielfeld hinzufuegen
		game.init(); 		// Spielfeld zeichnen

		game.addKeyListener(this); 	// Keylistener zum Spielfeld hinzufuegen
		game.setFocusable(true);	// Spielfeld fokussierbar machen
		game.requestFocus();		// Fokus auf Spielfeld setzen
	}

	// keyPressed-Methode:
	public void keyPressed(KeyEvent Key) {
		// oben:
		if (Key.getKeyCode() == KeyEvent.VK_UP) {
			System.out.println("oben"); 			// Test
			System.out.println("");

			a[0] = 0;
			a[1] = -1;

			move = true;
		}

		// Pfeiltaste links:
		if (Key.getKeyCode() == KeyEvent.VK_LEFT) {
			System.out.println("links"); 			// Test
			System.out.println("");

			a[0] = -1;
			a[1] = 0;

			move = true;
		}

		// Pfeiltaste rechts:
		if (Key.getKeyCode() == KeyEvent.VK_RIGHT) {
			System.out.println("rechts"); 			// Test
			System.out.println("");

			a[0] = 1;
			a[1] = 0;

			move = true;
		}

		// Pfeiltaste unten:
		if (Key.getKeyCode() == KeyEvent.VK_DOWN) {
			System.out.println("unten"); 			// Test
			System.out.println("");

			a[0] = 0;
			a[1] = 1;

			move = true;
		}

		// Leertaste:
		if (Key.getKeyCode() == KeyEvent.VK_SPACE) {
			System.out.println("Space"); 			// Test
			System.out.println("");

			bomb = true;

			game.bombe_legen();
			game.removeAll(); 	// entferne alle bisherigen Komponenten vom Panel
			game.refresh(); 	// zeichne alle Komponenten des Panels neu
		}

		if (Key.getKeyCode() != KeyEvent.VK_SPACE) {
			if (game.map[hulk.get_x() + a[0]][hulk.get_y() + a[1]] == 2) { // falls
																			// das
																			// naechste
																			// Feld
																			// ein
																			// Weg-Feld
																			// ist
				int hulk_x = hulk.get_x(); 	// horizontale Position des Hulks
				hulk.set_x(hulk_x + a[0]); 	// setze horizontale Position des
											// Hulks weiter

				int hulk_y = hulk.get_y(); 	// vertikale Position des Hulks
				hulk.set_y(hulk_y + a[1]); 	// setze vertikale Position des Hulks
											// weiter

				System.out.println("Hulks neue Position: " + hulk.get_x()
						+ "te Spalte, " + hulk.get_y() + "te Zeile"); // Test
				System.out.println("");

				game.move_Hulk(a[0], a[1]); // bewege Hulk auf dem Spielfeld
				game.removeAll(); 			// entferne alle bisherigen Komponenten vom
									// Panel
				game.refresh(); 			// zeichne alle Komponenten des Panels neu
			}

			else if (game.map[hulk.get_x() + a[0]][hulk.get_y() + a[1]] == 7) { // falls
																				// das
																				// naechste
																				// Feld
																				// das
																				// Ziel-Feld
																				// ist
				System.out.println("Gewonnen!"); // Test
				System.out.println("");

				game.move_Hulk(-hulk.get_x() + 1, -hulk.get_y() + 1); 	// bewege
																		// Hulk
																		// zum
																		// Startpunkt
																		// zurueck
				game.removeAll(); 	// entferne alle bisherigen Komponenten vom
									// Panel
				game.refresh(); 	// zeichne alle Komponenten des Panels neu
			}

			else if (game.map[hulk.get_x()][hulk.get_y() + a[1]] == 6) { 	// falls
																			// das
																			// naechste
																			// Feld
																			// ein
																			// Explosions-Feld
																			// ist
				System.out.println("Verloren!"); // Test
				System.out.println("");

				game.move_Hulk(-hulk.get_x() + 1, -hulk.get_y() + 1); 	// bewege
																		// Hulk
																		// zum
																		// Startpunkt
																		// zurueck
				game.removeAll(); 	// entferne alle bisherigen Komponenten vom
									// Panel
				game.refresh(); 	// zeichne alle Komponenten des Panels neu
			}
		}
	}

	public void keyTyped(KeyEvent Key) {
	}

	public void keyReleased(KeyEvent Key) {
	}

	public static int[] get_newPos() {
		return a;
	}

	// Beenden des Spiels beim Klick auf "Beenden":
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

	// Neustart des Spiels beim Klick auf "Neu"
	private class Action_Neu extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public Action_Neu() {
			putValue(NAME, "Neu");
			putValue(SHORT_DESCRIPTION, "Neustart des Spiels");
		}

		public void actionPerformed(ActionEvent e) {
			game.move_Hulk(-hulk.get_x() + 1, -hulk.get_y() + 1); 	// bewege Hulk
																	// zum
																	// Startpunkt
																	// zurueck
			game.removeAll(); 	// entferne alle bisherigen Komponenten vom Panel
			game.refresh(); 	// zeichne alle Komponenten des Panels neu
		}

	}

	public static boolean get_bomb() {
		return bomb;
	}

	public static void set_bomb() {
		bomb = false;
	}

	public static boolean get_move() {
		return move;
	}

	public static void set_move() {
		move = false;
	}

	// getGame-Methode:
	public static Map get_game() {
		return game;
	}

	// setGame-Methode:
	public void set_game(Map game) {
		Menue.game = game;
	}

	// getGame-Methode:
	public static Hulk get_hulk() {
		return hulk;
	}

	// setGame-Methode:
	public void set_hulk(Hulk hulk) {
		Menue.hulk = hulk;
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
