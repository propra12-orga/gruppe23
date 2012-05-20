import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.KeyListener;

// Panel f�r KeyListener ben�tigt!

public class Menue implements KeyListener{

	// Deklaration & Initialisierung:
	private JFrame frame;
	private final Action_Beenden Action_Beenden = new Action_Beenden(); // Aktion zum Beenden des Spiels erstellen
	private final Action_Neu Action_Neu = new Action_Neu(); // Aktion zum Neustart des Spiels erstellen
	private MenueKeyListener menueListener;
	private GameKeyListener gameListener;
	private static Map game = new Map(); // Spielfeld erstellen
	private Bombe bomb = new Bombe(); // Bombe erstellen

	private int[] a;
	
	// private Map Player = new Hulk(2,2);														//<<<<----- 
	
	// Es muss noch ein Frame hinzugef�gt werden, in dem das Spielfeld ist:
	// Wichtig ist hier, dass .addKeyListener(GameKeyListener) zu diesem Frame hinzugef�gt wird!
	
	// Konstruktor:
	public Menue() {
		initialize();	
		a = new int[2];
	}
	
	// Methode zum Initialisieren des Spielfelds:
	private void initialize() {
		frame = new JFrame(); // Fenster erstellen
		frame.setBounds(100, 100, 550, 600); // Fenstergr��e einstellen (x-Position, y-Position, Breite, H�he)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Programm beim Schlie�en des Fensters beenden
		frame.setResizable(false); // Fenster soll nicht skalierbar sein
		
		JMenuBar menuBar = new JMenuBar(); // Men�leiste erstellen
		frame.setJMenuBar(menuBar); // Men�leiste hinzuf�gen
		
		menuBar.addKeyListener(menueListener); // KeyListener wird f�r's Menue implementiert
		menuBar.isFocusable();
		
		JMenu mnSpiel = new JMenu("Spiel"); // Men�punkt "Spiel" erstellen
		menuBar.add(mnSpiel); // Men�punkt "Spiel" hinzuf�gen
		
		JMenuItem mntmNeu = new JMenuItem("Neu"); // Untermen�punkt "Neu" erstellen
		mnSpiel.add(mntmNeu); // Untermen�punkt "Neu" hinzuf�gen
		mntmNeu.setAction(Action_Neu); // Aktion "Action_Neu" hinzuf�gen
		
		JMenuItem mntmBeenden = new JMenuItem("Beenden"); // Untermen�punkt "Beenden" erstellen
		mnSpiel.add(mntmBeenden); // Untermen�punkt "Beenden" hinzuf�gen
		mntmBeenden.setAction(Action_Beenden); // Aktion "Action_Beenden" hinzuf�gen
		
		frame.add(getGame()); // Spielfeld hinzuf�gen
		getGame().paint(); // Spielfeld zeichnen
		
		getGame().add(bomb); // Bombe hinzuf�gen
		bomb.paint(); // Bombe zeichnen	
		game.addKeyListener(gameListener);
		game.setFocusable(true);
	}
	
	public void keyPressed(KeyEvent Key){ 			//Diese Funktion ist essentiel für die Umsetzung des Keylisteners (bzw. die einzig relevante)
		if(Key.getKeyCode() == Key.VK_UP){			//Oben
			a[0] = 0;
			a[1] = 1;
			Map.setNewPosition(a);
		}
		
		if(Key.getKeyCode() == Key.VK_LEFT){		//Links
			a[0] = -1;
			a[1] = 0;
		}
		
		if(Key.getKeyCode() == Key.VK_RIGHT){		//Rechts
			a[0] = 1;
			a[1] = 0;
		}
		
		if(Key.getKeyCode() == Key.VK_DOWN){		//Unten
			a[0] = 0;
			a[1] = -1;
		}
		if(Key.getKeyCode() == Key.VK_SPACE){		//Bombe
			Bombe bomb = new Bombe();
		}
										//An dieser Stelle muss die Print-Funktion mit Übergabe des a[] gesetzt werden.
	}												//Zudem muss der KeyListener für die Bombe-Taste noch eine Auswirkung bekommen.
	
	public void keyTyped(KeyEvent Key){}
	public void keyReleased(KeyEvent Key) {}


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
	
	// getGame-Methode:
	public static Map getGame() {
		return game;
	}

	// setGame-Methode:
	public void setGame(Map game) {
		this.game = game;
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
