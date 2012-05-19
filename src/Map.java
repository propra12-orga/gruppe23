import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Map extends JPanel {
	private static final long serialVersionUID = 1L;
	public boolean gefunden;
	public GameKeyListener kListener;
	public int n = 11;// Spielfeldgröße
	public int positionX, positionY;
	public int[] neuePosition;
	int[][] map = {	{4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
			{4, 1, 2, 2, 2, 2, 2, 2, 2, 2, 4},
			{4, 2, 4, 2, 4, 2, 4, 2, 4, 2, 4},
			{4, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4},
			{4, 2, 4, 2, 4, 2, 4, 2, 4, 2, 4},
			{4, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4},
			{4, 2, 4, 2, 4, 2, 4, 2, 4, 2, 4},
			{4, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4},
			{4, 2, 4, 2, 4, 2, 4, 2, 4, 2, 4},
			{4, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4},
			{4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4} };

	public Map() {
		gefunden = false;
		
		// int[][] map = new int[n][n];

		// for (int a = 0; a < n; a++) {
		// for (int b = 0; b < n; b++) { // Map initialisieren
		// map[a][b] = 4;
		// }
		// } für Map normaler Weise die Maploaderklasse verwenden.		
	}
	
	public void zeichnen() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] == 1) {
					gefunden = true;
					positionX = i; 	// Methode sucht Hulk im Map-Array
					positionY = j;	// Postion x und y sind dann die postionen vom
									// Hulk, für Keylistener wichtig!
					break;
				} 
				
				else {
					gefunden = false;				
				}
				
			}
			
		}		
		
		setBounds(100, 100, 550, 580); // Panel erstellen
		
		setBorder(new EmptyBorder(3, 3, 3, 3));
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 1, 1, 1, 1, 0 };
		gbl_contentPane.rowHeights = new int[] { 1, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0,
				Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gbl_contentPane);

		JLabel[][] label = new JLabel[n][n]; // erstellt ein JLabel-Array
		GridBagConstraints[][] gbc_label = new GridBagConstraints[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				label[i][j] = new JLabel(""); // für jedes einzele Arrayelement wird ein neues Label erstellt

				label[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				label_laden(label[i][j], map[i][j]); // lädt in das Label das Bild (Map hat die Werte ob Hulk, etc..)
				gbc_label[i][j] = new GridBagConstraints();
				gbc_label[i][j].anchor = GridBagConstraints.CENTER;
				gbc_label[i][j].fill = GridBagConstraints.NONE;
				gbc_label[i][j].gridwidth = 1;
				gbc_label[i][j].gridheight = 1;
				gbc_label[i][j].insets = new Insets(0, 0, 0, 0);
				gbc_label[i][j].gridx = i;
				gbc_label[i][j].gridy = j;
				add(label[i][j], gbc_label[i][j]); // Label auf Panel laden
			}
			
		}
		
	}
	
	public void label_laden(JLabel label, int i) {

		switch (i) {
			case 1: // Hulk
				label.setIcon(new ImageIcon(Map.class.getResource("/Pics/Hulk.png")));
				break;
	
			case 2: // Weg
				label.setIcon(new ImageIcon(Map.class.getResource("/Pics/Weg.png")));
				break;
	
			case 3: // Block
				label.setIcon(new ImageIcon(Map.class.getResource("/Pics/Block.png")));
				break;
	
			case 4: // Mauer nicht zerstörbar (eventuell über Hulkmodus)
				label.setIcon(new ImageIcon(Map.class.getResource("/Pics/Mauer.png")));
				break;
				
			case 5: // Bombe
				label.setIcon(new ImageIcon(Map.class.getResource("/Pics/Bombe.png")));
				break;
				
			case 6: // Explosion
				label.setIcon(new ImageIcon(Map.class.getResource("/Pics/EXP.png")));
				break;
				
			case 7: // Ausgang
				label.setIcon(new ImageIcon(Map.class.getResource("/Pics/Ausgang.png")));
				break;
		}
		
	}		

}
