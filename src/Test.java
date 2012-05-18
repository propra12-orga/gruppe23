import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Test extends JFrame {

	private JPanel contentPane;
	boolean gefunden;
	GameKeyListener kListener;
	int n = 11;// Spielfeldgr��e
	int positionX, positionY;
	
	/*
	 * Das Spielfeld muss vom Typ Panel sein!!!
	 * D.h.: neue Klasse und ummodeln.
	 * 
	 * 
	 * 
	 * 
	 */

	 public static void main(String[] args) {
	 EventQueue.invokeLater(new Runnable() {
	 public void run() {
	 try {
	 Test frame = new Test();
	 frame.setVisible(true);
	 } catch (Exception e) {
	 e.printStackTrace();
	 }
	 }
	 });
	 }

	public Test() {
		n = 11;						// Spielfeldgroesse
		
		gefunden = false;
		kListener = new GameKeyListener();
		
		// int[][] map = new int[n][n];

		// for (int a = 0; a < n; a++) {
		// for (int b = 0; b < n; b++) { // map initialisieren
		// map[a][b] = 4;
		// }
		// } f�r map normaler weise die maploderklasse verwenden.
		
		int[][] map = { { 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, },

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
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {

				if (map[i][j] == 1) {
					gefunden = true;
					positionX = i; // methode sucht hulk im map - array
					positionY = j;// postion x und y sind dann die postionen vom
									// hulk, f�r keylistener wichtig!
					break;
				} else {
					gefunden = false;
					
				
			}
		}
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		setBounds(100, 100, 550, 580);// panel erstellen
		contentPane = new JPanel();
		contentPane.addKeyListener(kListener);							//KeyListener angefuegt
		contentPane.setBorder(new EmptyBorder(3, 3, 3, 3));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 1, 1, 1, 1, 0 };
		gbl_contentPane.rowHeights = new int[] { 1, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0,
				Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);
		setResizable(false);

		JLabel[][] label = new JLabel[n][n];// ERstellt ein JLabel array,
		GridBagConstraints[][] gbc_label = new GridBagConstraints[n][n];
		for (i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				label[i][j] = new JLabel("");// f�r jedes einzele arrayelement
												// wird ein neues label erstellt

				label[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				malen(label[i][j], map[i][j]);// l�dt in das label das
												// bild(map
												// hat die werte ob hulk,etc..
				gbc_label[i][j] = new GridBagConstraints();
				gbc_label[i][j].anchor = GridBagConstraints.CENTER;
				gbc_label[i][j].fill = GridBagConstraints.NONE;
				gbc_label[i][j].gridwidth = 1;
				gbc_label[i][j].gridheight = 1;
				gbc_label[i][j].insets = new Insets(0, 0, 0, 0);
				gbc_label[i][j].gridx = i;
				gbc_label[i][j].gridy = j;
				contentPane.add(label[i][j], gbc_label[i][j]);// label auf panel
																// laden
			}
		}}
		//pack();
	}

	public void malen(JLabel label, int i) {

		switch (i) {
		case 1:// Hulk
			label.setIcon(new ImageIcon(Test.class
					.getResource("/Pics/Hulk.png")));
			break;

		case 2:// Weg
			label.setIcon(new ImageIcon(Test.class.getResource("/Pics/Weg.png")));
			break;

		case 3:// Block
			label.setIcon(new ImageIcon(Test.class
					.getResource("/Pics/Block.png")));
			break;

		case 4:// Mauer nicht zerst�rbar(eventuell �ber hulkmodus)
			label.setIcon(new ImageIcon(Test.class
					.getResource("/Pics/Mauer.png")));
			break;
		case 5:// bombe
			label.setIcon(new ImageIcon(Test.class
					.getResource("/Pics/Bombe.png")));
			break;
		case 6:// explosion
			label.setIcon(new ImageIcon(Test.class.getResource("/Pics/EXP.png")));
			break;
		case 7:
			label.setIcon(new ImageIcon(Test.class.getResource("/Pics/Ausgang.png")));
			break;
		}
	}

}
