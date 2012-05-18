import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;


//Panel für KeyListener benötigt!


public class Menue{

	private JFrame frame;
	private final Action_Beenden Action_Beenden = new Action_Beenden();
	private final Action_Neu Action_Neu = new Action_Neu();
	private MenueKeyListener MenueListener;
	private GameKeyListener GameListener;
	
	public int gameMap[][] = { {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, },
			{ 4, 1, 2, 2, 2, 2, 2, 2, 2, 2, 4, },
			{ 4, 2, 4, 2, 4, 2, 4, 2, 4, 2, 4, },
			{ 4, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4, },
			{ 4, 2, 4, 2, 4, 2, 4, 2, 4, 2, 4, },
			{ 4, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4, },
			{ 4, 2, 4, 2, 4, 2, 4, 2, 4, 2, 4, },
			{ 4, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4, },
			{ 4, 2, 4, 2, 4, 2, 4, 2, 4, 2, 4, },
			{ 4, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4, },
			{ 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, }, };
	
	//private Map Player = new Hulk(2,2);
	
	
	//private Map Game = new Map(gameMap);															//<<<<----- 
	
	//
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
		frame.setBounds(100, 100, 475, 309);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnSpiel = new JMenu("Spiel");//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//		public void run() {
//			try {
//				Map frame = new Map();
//					frame.setVisible(true);
//				}
//			catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//			});
//		}
		menuBar.add(mnSpiel);
		menuBar.addKeyListener(MenueListener);		//KeyListener wird fuer's Menue implementiert
		menuBar.isFocusable();
		
		JMenuItem mntmNeu = new JMenuItem("Neu");
		mntmNeu.setAction(Action_Neu);
		mnSpiel.add(mntmNeu);
		
		JMenuItem mntmBeenden = new JMenuItem("Beenden");
		mntmBeenden.setAction(Action_Beenden);
		mnSpiel.add(mntmBeenden);
		
		
	
		
		/*JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		*/
		
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
		/*Beenden-Funktion wird implementiert 
		 */

		public Action_Beenden() {
			putValue(NAME, "Beenden");
			putValue(SHORT_DESCRIPTION, "Beenden des Spiels");
		}
		public void actionPerformed(ActionEvent e) {
			System.exit( 0 );
		}
	}
	
	/*
	 * Neustarten-Funktion wird implementiert
	 */
	
	private class Action_Neu extends AbstractAction {
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
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
