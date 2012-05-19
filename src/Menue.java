import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

// Panel für KeyListener benötigt!

public class Menue {

	private JFrame frame;
	private final Action_Beenden Action_Beenden = new Action_Beenden();
	private final Action_Neu Action_Neu = new Action_Neu();
	private MenueKeyListener MenueListener;
	private GameKeyListener GameListener;
	private Map Game = new Map();

	// private Map Player = new Hulk(2,2);														//<<<<----- 
	
	// Es muss noch ein Frame hinzugefügt werden, in dem das Spielfeld ist:
	// Wichtig ist hier, dass .addKeyListener(GameKeyListener) zu diesem Frame hinzugefügt wird!
	
	public Menue() {
		initialize();		
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.add(Game);
		frame.setBounds(100, 100, 570, 616);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnSpiel = new JMenu("Spiel");
		
		Game.zeichnen();
		
		menuBar.add(mnSpiel);
		menuBar.addKeyListener(MenueListener);		//KeyListener wird fuer's Menue implementiert
		menuBar.isFocusable();
		
		JMenuItem mntmNeu = new JMenuItem("Neu");
		mntmNeu.setAction(Action_Neu);
		mnSpiel.add(mntmNeu);
		
		JMenuItem mntmBeenden = new JMenuItem("Beenden");
		mntmBeenden.setAction(Action_Beenden);
		mnSpiel.add(mntmBeenden);		
	}

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
