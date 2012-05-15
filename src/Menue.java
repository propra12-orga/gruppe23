import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Choice;
import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;


//Panel für KeyListener benötigt!


public class Menue implements KeyListener{

	private JFrame frame;
	private final Action_Beenden Action_Beenden = new Action_Beenden();
	private final Action_Neu Action_Neu = new Action_Neu();
	private int[] a = new int[2];
	private int[] b = new int[2];

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
	//Wichtig ist hier, dass .addKeyListener(Menue) zu diesem Frame hinzugefügt wird!
	//
	//
	public void keyPressed(KeyEvent Key){ //Diese Funktion ist essentiel für die Umsetzung des Keylisteners (bzw. die einzig relevante)
		if(Key.getKeyCode() == Key.VK_UP){
			a[0] = 0;
			a[1] = 1;
		}
		
		if(Key.getKeyCode() == Key.VK_LEFT){
			a[0] = -1;
			a[1] = 0;
		}
		
		if(Key.getKeyCode() == Key.VK_RIGHT){
			a[0] = 1;
			a[1] = 0;
		}
		
		if(Key.getKeyCode() == Key.VK_DOWN){
			a[0] = 0;
			a[1] = -1;
		}
		b = KeyListenerTest.zeichnen(b, a);		//An dieser Stelle muss die Print-Funktion mit Übergabe des a[] gesetzt werden.
	}											//Zudem muss der KeyListener für die Bombe-Taste noch eine Auswirkung bekommen.
	
	
	@Override
	public void keyTyped(KeyEvent Key){}
	public void keyReleased(KeyEvent Key) {}

	
	public Menue() {
		initialize();
		b[0]=0; b[1]=0;
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
		
		JMenuItem mntmNeu = new JMenuItem("Neu");
		mntmNeu.setAction(Action_Neu);
		mnSpiel.add(mntmNeu);
		
		JMenuItem mntmBeenden = new JMenuItem("Beenden");
		mntmBeenden.setAction(Action_Beenden);
		mnSpiel.add(mntmBeenden);
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
