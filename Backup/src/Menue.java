import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

// Panel f�r KeyListener ben�tigt!

public class Menue {

	// Deklaration & Initialisierung:
	private JFrame frame;
	private final Action_Beenden Action_Beenden = new Action_Beenden(); // Aktion zum Beenden des Spiels erstellen
	private final Action_Neu Action_Neu = new Action_Neu(); // Aktion zum Neustart des Spiels erstellen
	private MenueKeyListener MenueListener;
	private GameKeyListener GameListener;
	private static Map game = new Map(); // Spielfeld erstellen
	private Bombe bomb = new Bombe(); // Bombe erstellen

	// private Map Player = new Hulk(2,2);														//<<<<----- 
	
	// Es muss noch ein Frame hinzugef�gt werden, in dem das Spielfeld ist:
	// Wichtig ist hier, dass .addKeyListener(GameKeyListener) zu diesem Frame hinzugef�gt wird!
	
	// Konstruktor:
	public Menue() {
		initialize();		
	}
	
	// Methode zum Initialisieren des Spielfelds:
	private void initialize() {
		frame = new JFrame(); // Fenster erstellen
		frame.setBounds(100, 100, 550, 600); // Fenstergr��e einstellen (x-Position, y-Position, Breite, H�he)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Programm beim Schlie�en des Fensters beenden
		frame.setResizable(false); // Fenster soll nicht skalierbar sein
		
		JMenuBar menuBar = new JMenuBar(); // Men�leiste erstellen
		frame.setJMenuBar(menuBar); // Men�leiste hinzuf�gen
		
		menuBar.addKeyListener(MenueListener); // KeyListener wird f�r's Menue implementiert
		menuBar.isFocusable();
		

		JMenu mnSpiel = new JMenu("Spiel"); // Men�punkt "Spiel" erstellen
		menuBar.add(mnSpiel); // Men�punkt "Spiel" hinzuf�gen

		game.paint();
		game.addKeyListener(GameListener);

		
		JMenuItem mntmNeu = new JMenuItem("Neu");  //Untermen�punkt "Neu" erstellen
		mnSpiel.add(mntmNeu); // Untermen�punkt "Neu" hinzuf�gen
		mntmNeu.setAction(Action_Neu); // Aktion "Action_Neu" hinzuf�gen
		
		JMenuItem mntmBeenden = new JMenuItem("Beenden"); // Untermen�punkt "Beenden" erstellen
		mnSpiel.add(mntmBeenden); // Untermen�punkt "Beenden" hinzuf�gen
		mntmBeenden.setAction(Action_Beenden); // Aktion "Action_Beenden" hinzuf�gen
		
		frame.add(getGame()); // Spielfeld hinzuf�gen
		getGame().paint(); // Spielfeld zeichnen
		
		getGame().add(bomb); // Bombe hinzuf�gen
		bomb.paint(); // Bombe zeichnen	
	}

	// Beenden des Spiels beim Klick auf "Beenden":
	private class Action_Beenden extends AbstractAction {
		private static final long serialVersionUID = 1L;
		public Action_Beenden() {
			putValue(NAME, "Beenden");
			putValue(SHORT_DESCRIPTION, "Beenden des Spiels");
		}
		
		public void actionPerformed(ActionEvent e) {
			System.exit( 0 );
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
			initialize();
		}
		
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

	// getGame-Methode:
	public static Map getGame() {
		return game;
	}

	// setGame-Methode:
	public void setGame(Map game) {
		this.game = game;
	}
}
