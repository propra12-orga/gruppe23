import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Map extends JPanel {
	// Deklaration & Initialisierung:
	private static final long serialVersionUID = 1L;
	int n = 11; 							// Spielfeldgroesse
	static int[][] map; 					// Spielfeld
	Bombe bomb = new Bombe(); 				// Bombe erstellen
	JLabel[][] label = new JLabel[n][n]; 	// JLabel-Array erstellen

	// Konstruktor:
	public Map(int[][] map) {
		Map.map = map;
	}

	// move_Hulk-Methode:
	public void move_Hulk(int x, int y) {
		/* Grafische Fortbewegung der Spielfigur: */
		if (map[Menue.get_hulk().get_x()][Menue.get_hulk().get_y()] == 1) { 	// falls das Feld das Hulk-Icon (1) beinhaltet,... 
			map[Menue.get_hulk().get_x()][Menue.get_hulk().get_y()] = 2; 		// ...weise dem Feld das Weg-Icon (2) zu
		}

		map[Menue.get_hulk().get_x() + x][Menue.get_hulk().get_y() + y] = 1; 	// weise dem nächsten Feld das Hulk-Icon (1) zu

		/* Interne Fortbewegung der Spielfigur: */
		Menue.get_hulk().set_x(Menue.get_hulk().get_x() + x); 					// setze horizontale Hulk-Position weiter
		Menue.get_hulk().set_y(Menue.get_hulk().get_y() + y); 					// setze vertikale Hulk-Position weiter

		System.out.println("Hulks neue Position: " + Menue.get_hulk().get_x()
				+ "te Spalte, " + Menue.get_hulk().get_y() + "te Zeile"); 		// Test
		System.out.println();
	}

	// bombe_legen-Methode:
	public void bombe_legen() {
		add(bomb); 														// Bombe hinzufuegen
		bomb.aktivieren(); 												// Bombe aktivieren
		map[Menue.get_hulk().get_x()][Menue.get_hulk().get_y()] = 5; 	// Bombe darstellen
		bomb.set_x(Menue.get_hulk().get_x()); 							// setze horizontale Bomben-Position
		bomb.set_y(Menue.get_hulk().get_y()); 							// setze vertikale Bomben-Position
	}

	// bombe_detonieren-Methode:
	public void bombe_detonieren() {
		for (int x = -1, y = -1; x < 2; x++, y++) {					// Ausbreitung der Detonation um Radius 1
			// horizontale Ausbreitung der Detonation:
			if (map[bomb.get_x() + x][bomb.get_y()] == 2 			// falls das Zielfeld der Detonation ein Weg-Feld...
					|| map[bomb.get_x() + x][bomb.get_y()] == 5 	// ...oder Bombe-Feld ist,...
					|| map[bomb.get_x() + x][bomb.get_y()] == 3)	// ...oder Block-Feld ist...
				map[bomb.get_x() + x][bomb.get_y()] = 6; 			// ...dann wandele das Feld in ein Explosions-Feld um

			// vertikale Ausbreitung der Detonation:
			if (map[bomb.get_x()][bomb.get_y() + y] == 2 			// falls das Zielfeld der Detonation ein Weg-Feld...
					|| map[bomb.get_x()][bomb.get_y() + y] == 5 	// ...oder Bombe-Feld ist,...
					|| map[bomb.get_x()][bomb.get_y() + y] == 3)	// ...oder Block-Feld ist...
				map[bomb.get_x()][bomb.get_y() + y] = 6; 			// ...dann wandele das Feld in ein Explosions-Feld um

			// horizontales Treffen von Detonation & Hulk:
			if ((bomb.get_x() + x == Menue.get_hulk().get_x() + x 	// falls Explosion & Hulk die gleiche x-...
			&& bomb.get_y() == Menue.get_hulk().get_y()) 			// ...und y-Koordinate haben...
					|| map[bomb.get_x() + x][bomb.get_y()] == 1) { 	// ...oder das Explosions-Feld ein Hulk-Icon beinhaltet,...

				System.out.println("Verloren!"); 					// Test
				System.out.println();

				move_Hulk(-Menue.get_hulk().get_x() + 1, -Menue.get_hulk()
						.get_y() + 1); 	// ...dann bewege Hulk zum Startpunkt zurueck,...
				removeAll(); 			// ...entferne alle bisherigen Komponenten vom Panel,...
				refresh(); 				// ...und zeichne alle Komponenten des Panels neu.
			}

			// Vertikales Treffen von Detonation & Hulk:
			if ((bomb.get_x() == Menue.get_hulk().get_x() 					// falls Explosion & Hulk die gleiche x-...
					&& bomb.get_y() + y == Menue.get_hulk().get_y() + y) 	// ...und y-Koordinate haben...
					|| map[bomb.get_x()][bomb.get_y() + y] == 1) {			// ...oder das Explosions-Feld ein Hulk-Icon beinhaltet,...

				System.out.println("Verloren!"); // Test
				System.out.println();

				move_Hulk(-Menue.get_hulk().get_x() + 1, -Menue.get_hulk()
						.get_y() + 1);	// ...dann bewege Hulk zum Startpunkt zurueck,...
				removeAll();			// ...entferne alle bisherigen Komponenten vom Panel,...
				refresh();				// ...und zeichne alle Komponenten des Panels neu.
			}

		}

		Zeit ende_explosion = new Zeit(); 					// Timer fuer Dauer der Explosion erstellen
		Menue.get_game().add(ende_explosion); 				// Timer fuer Dauer der Explosion hinzufuegen
		ende_explosion.timer_starten(1000, "Detonation"); 	// Timer fuer Dauer der Explosion starten

		removeAll(); 										// entferne alle bisherigen Komponenten vom Panel
		refresh(); 											// zeichne alle Komponenten des Panels neu
	}

	// refresh-Methode:
	public void refresh() {
		GridBagConstraints[][] gbc_label = new GridBagConstraints[n][n];
		System.out.println("Spielfeld:"); 	// Test
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				label[i][j] = new JLabel(); // fuer jedes einzele Arrayelement wird ein neues Label erstellt
				label[i][j].setHorizontalAlignment(SwingConstants.CENTER);

				System.out.print(map[i][j] + ", "); // Test
				if (j == n - 1) {
					System.out.println();
				}

				label_laden(label[i][j], map[i][j]); 				// laedt in das Label das Bild (Map hat die Werte ob Hulk, etc..)
				gbc_label[i][j] = new GridBagConstraints();
				gbc_label[i][j].anchor = GridBagConstraints.CENTER;
				gbc_label[i][j].fill = GridBagConstraints.NONE;
				gbc_label[i][j].gridwidth = 1;
				gbc_label[i][j].gridheight = 1;
				gbc_label[i][j].insets = new Insets(0, 0, 0, 0);
				gbc_label[i][j].gridx = i;
				gbc_label[i][j].gridy = j;
				add(label[i][j], gbc_label[i][j]); 					// Label auf Panel laden
				label[i][j].repaint(); 								// Zeichnen
				label[i][j].setVisible(true); 						// Sichtbarkeit setzen
			}

		}

		System.out.println();

		/*
		 * System.out.println("Original Spielfeld:"); // Test for (int i = 0; i
		 * < n; i++) { for (int j = 0; j < n; j++) {
		 * System.out.print(Menue.map[i][j] + ", "); // Test if (j == n - 1) {
		 * System.out.println(); } } }
		 * 
		 * System.out.println();
		 */

		updateUI();
	}

	// init-Methode:
	public void init() {
		setBounds(100, 100, 550, 580); // Panel erstellen

		setBorder(new EmptyBorder(3, 3, 3, 3));
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 1, 1, 1, 1, 0 };
		gbl_contentPane.rowHeights = new int[] { 1, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0,
				Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gbl_contentPane);

		GridBagConstraints[][] gbc_label = new GridBagConstraints[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				label[i][j] = new JLabel(); 			// fuer jedes einzele Arrayelement wird ein neues Label erstellt
				label[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				label_laden(label[i][j], map[i][j]); 	// laedt in das Label das Bild (Map hat die Werte ob Hulk, etc..)
				gbc_label[i][j] = new GridBagConstraints();
				gbc_label[i][j].anchor = GridBagConstraints.CENTER;
				gbc_label[i][j].fill = GridBagConstraints.NONE;
				gbc_label[i][j].gridwidth = 1;
				gbc_label[i][j].gridheight = 1;
				gbc_label[i][j].insets = new Insets(0, 0, 0, 0);
				gbc_label[i][j].gridx = i;
				gbc_label[i][j].gridy = j;
				add(label[i][j], gbc_label[i][j]); 		// Label auf Panel laden
			}

		}

		updateUI();

	}

	// label_laden-Methode:
	public void label_laden(JLabel label, int i) {

		switch (i) {
		case 1: // Hulk
			label.setIcon(new ImageIcon(Map.class.getResource("/Pics/Hulk.png"))); 	// Grafik-Quelle:
																					// http://openclipart.org/detail/168616/hunk-by-bedpanner
			break;

		case 2: // Weg
			label.setIcon(new ImageIcon(Map.class.getResource("/Pics/Weg.png")));
			break;

		case 3: // Block
			label.setIcon(new ImageIcon(Map.class
					.getResource("/Pics/Block.png"))); 	// Grafik-Quelle:
														// http://openclipart.org/detail/151531/char-somua-s35-by-spadassin-151531
			break;

		case 4: // Mauer (nicht zerstoerbar, eventuell ueber Hulkmodus)
			label.setIcon(new ImageIcon(Map.class
					.getResource("/Pics/Mauer.png"))); 	// Grafik-Quelle:
														// http://openclipart.org/detail/3318/house-by-barretr
			break;

		case 5: // Bombe
			label.setIcon(new ImageIcon(Map.class
					.getResource("/Pics/Bombe.png")));	// Grafik-Quelle:
														// http://openclipart.org/detail/165734/dynamite-by-magnesus
			break;

		case 6: // Explosion
			label.setIcon(new ImageIcon(Map.class.getResource("/Pics/EXP.png"))); 	// Grafik-Quelle:
																					// http://openclipart.org/detail/122959/pow-by-viscious-speed
			break;

		case 7: // Ausgang
			label.setIcon(new ImageIcon(Map.class.getResource("/Pics/Exit.png")));	// Grafik-Quelle:
																					// http://openclipart.org/detail/126307/panneau-sortie--traffic-sign-exit-by-lmproulx
			break;
		}

	}

	// get_map-Methode:
	public static int[][] get_map() {
		return map;
	}

	// set_map-Methode:
	public void set_map(int[][] map) {
		Map.map = map;
	}

}
