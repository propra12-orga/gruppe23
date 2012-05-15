import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Choice;
import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;


//Panel für KeyListener benötigt!


public class Menue{

	private JFrame frame;
	private final Action_Beenden Action_Beenden = new Action_Beenden();
	private final Action_Neu Action_Neu = new Action_Neu();
	private MenueKeyListener MenueListener;
	private GameKeyListener GameListener;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menue window = new Menue();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	//
	//Methoden zur Initialisierung des Keylisteners
	//Es muss noch ein Frame hinzugefügt werden, in dem das Spielfeld ist:
	//Wichtig ist hier, dass .addKeyListener(GameKeyListener) zu diesem Frame hinzugefügt wird!
	//
	//
	public Menue() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnSpiel = new JMenu("Spiel");
		menuBar.add(mnSpiel);
		menuBar.addKeyListener(MenueListener);		//KeyListener wird fuer's Menue implementiert
		menuBar.isFocusable();
		
		JMenuItem mntmNeu = new JMenuItem("Neu");
		mntmNeu.setAction(Action_Neu);
		mnSpiel.add(mntmNeu);
		
		JMenuItem mntmBeenden = new JMenuItem("Beenden");
		mntmBeenden.setAction(Action_Beenden);
		mnSpiel.add(mntmBeenden);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
		
	}
	private class Action_Beenden extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 2921437400818980224L;
		public Action_Beenden() {
			putValue(NAME, "Beenden");
			putValue(SHORT_DESCRIPTION, "Beenden des Spiels");
		}
		public void actionPerformed(ActionEvent e) {
			System.exit( 0 );
		}
	}
	private class Action_Neu extends AbstractAction {
		public Action_Neu() {
			putValue(NAME, "Neu");
			putValue(SHORT_DESCRIPTION, "Neustart des Spiels");
		}
		public void actionPerformed(ActionEvent e) {
			initialize();
		}
	}
}
