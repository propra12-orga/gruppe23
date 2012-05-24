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

	final static int[][] map = Map.init_map(); // Spielfeld initialisieren

	private static Map game = new Map(map); // Spielfeld erstellen
	private static Hulk hulk = new Hulk(); // Hulk erstellen
	// private Thread moveHulk;
	// private Thread bombHulk;
	private static boolean move;

	private static boolean bomb;

	private static int[] a;

	// Konstruktor:
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
	}

	// Methode zum Initialisieren des Spielfelds:
	private void initialize() {
		frame = new JFrame(); // Fenster erstellen
		frame.setTitle("Bomberhulk"); // Fenstertitel setzen
		frame.setBounds(100, 100, 550, 605); // Fenstergroesse einstellen
												// (x-Position, y-Position,
												// Breite, Hoehe)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Programm beim
																// Schliessen
																// des Fensters
																// beenden
		frame.setResizable(false); // Fenster soll nicht skalierbar sein

		JMenuBar menuBar = new JMenuBar(); // Menueleiste erstellen
		frame.setJMenuBar(menuBar); // Menueleiste hinzufuegen

		menuBar.isFocusable();

		JMenu mnSpiel = new JMenu("Spiel"); // Menuepunkt "Spiel" erstellen
		menuBar.add(mnSpiel); // Menuepunkt "Spiel" hinzufuegen

		JMenuItem mntmNeu = new JMenuItem("Neu"); // Untermenuepunkt "Neu"
													// erstellen
		mnSpiel.add(mntmNeu); // Untermenuepunkt "Neu" hinzufuegen
		mntmNeu.setAction(Action_Neu); // Aktion "Action_Neu" hinzufuegen

		JMenuItem mntmBeenden = new JMenuItem("Beenden"); // Untermenuepunkt
															// "Beenden"
															// erstellen
		mnSpiel.add(mntmBeenden); // Untermenuepunkt "Beenden" hinzufuegen
		mntmBeenden.setAction(Action_Beenden); // Aktion "Action_Beenden"
												// hinzufuegen

		frame.add(game); // Spielfeld hinzufuegen
		game.init(); // Spielfeld zeichnen

		game.addKeyListener(this); // Keylistener zum Spielfeld hinzufuegen
		game.setFocusable(true); // Spielfeld fokussierbar machen
		game.requestFocus(); // Fokus auf Spielfeld setzen
	}

	// keyPressed-Methode:
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

			if (Map.get_bomb().get_x() == 0 && Map.get_bomb().get_y() == 0) { // falls
																				// nicht
																				// schon
																				// eine
																				// andere
																				// Bombe
																				// liegt
				bomb = true;

				game.bombe_legen();
				game.removeAll(); // entferne alle bisherigen Komponenten vom
									// Panel
				game.refresh(); // zeichne alle Komponenten des Panels neu
			}
		}

		if (Key.getKeyCode() != KeyEvent.VK_SPACE) {
			if (Map.map[hulk.get_x() + a[0]][hulk.get_y() + a[1]] == 2) { // falls
																			// das
																			// naechste
																			// Feld
																			// ein
																			// Weg-Feld
																			// ist

				game.move_Hulk(a[0], a[1]); // bewege Hulk auf dem Spielfeld
				game.removeAll(); // entferne alle bisherigen Komponenten vom
									// Panel
				game.refresh(); // zeichne alle Komponenten des Panels neu
			}

			else if (Map.map[hulk.get_x() + a[0]][hulk.get_y() + a[1]] == 7) { // falls
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
				hulk.set_x(1);
				hulk.set_y(1);

				// Bombe zurueckpositionieren:
				Map.get_bomb().set_x(0);
				Map.get_bomb().set_y(0);

				// Spielfeld intern reinitialisieren:
				game.set_map(Map.init_map());

				// Spielfeld grafisch reinitialisieren:
				game.removeAll();
				game.refresh();
			}

			else if (Map.map[hulk.get_x() + a[0]][hulk.get_y() + a[1]] == 6) { // falls
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
				hulk.set_x(1);
				hulk.set_y(1);

				// Bombe zurueckpositionieren:
				Map.get_bomb().set_x(0);
				Map.get_bomb().set_y(0);

				// Spielfeld intern reinitialisieren:
				game.set_map(Map.init_map());
				game.set_map(Map.init_map());

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
			System.out.println("Spiel neugestartet"); // Test
			System.out.println();

			// Hulk zurueckpositionieren:
			hulk.set_x(1);
			hulk.set_y(1);

			// Bombe zurueckpositionieren:
			Map.get_bomb().set_x(0);
			Map.get_bomb().set_y(0);

			// Spielfeld intern reinitialisieren:
			game.set_map(Map.init_map());

			// Spielfeld grafisch reinitialisieren:
			game.removeAll();
			game.refresh();
		}

	}

	// get_bomb-Methode:
	public static boolean get_bomb() {
		return bomb;
	}

	// set_bomb-Methode:
	public static void set_bomb() {
		bomb = false;
	}

	// get_move-Methode:
	public static boolean get_move() {
		return move;
	}

	// set_move-Methode:
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

	// get_hulk-Methode:
	public static Hulk get_hulk() {
		return hulk;
	}

	// set_hulk-Methode:
	public void set_hulk(Hulk hulk) {
		Menue.hulk = hulk;
	}

	// get_map-Methode:
	public static int[][] get_map() {
		return map;
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
