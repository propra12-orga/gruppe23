import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * 
 * @author Tobias Korfmacher
 * 
 */

public class Map extends JPanel {
	// Deklaration & Initialisierung:
	private static final long serialVersionUID = 1L;
	int n = MapLoader.get_n(); 					// Spielfeldgroesse
	static int[][] map; 						// Spielfeld
	Bombe bomb[][] = new Bombe[n][n]; 			// Bomben erstellen
	int bomb_x, bomb_y;
	JLabel[][] label = new JLabel[n][n]; 		// JLabel-Array erstellen
	public static boolean twoPlayerset;			// Multiplayerabfrage
	int hoehe = 50, breite = 50;
	// Icons:
	Icon block_icon, hulk_icon, hulk2_icon, weg_icon, exit_icon, mauer_icon,
			bomb_icon, exp_icon, bomben_item_icon, flammen_item_icon;
	// Dateipfade zu Grafiken:
	String exit_pic, block_pic, weg_pic, mauer_pic, hulk_pic, hulk2_pic,
			bomb_pic, exp_pic;

	/* Konstruktor: */
	public Map(int[][] map) {
		Map.map = map;
		twoPlayerset = Menue.getMultiplayer();
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < n; y++) {
				bomb[x][y] = new Bombe();
			}

		}

	}

	/* METHODEN: */

	// move_Hulk-Methode:

	/**
	 * Aehnliche Methode zu move_hulk(), prueft label der Bot-Bewegung und veraendert diese entsprechend
	 * 
	 * @param x
	 *            Horizontale Koordinate
	 * @param y
	 *            vertikale Koordinate
	 * @param Botnummer
	 *            1 entspricht Bot fuer 2. Spieler, 2 fuer 3. Spieler etc.
	 */
	public void move_Bot(int x, int y, int Botnummer) {
		/* Grafische Fortbewegung der Spielfigur: */
		map[Menue.get_bot(Botnummer).get_x()][Menue.get_bot(Botnummer).get_y()] = 2;
		map[Menue.get_bot(Botnummer).get_x() + x][Menue.get_bot(Botnummer).get_y() + y] = 10;

		//		if (map[Menue.get_bot(Botnummer).get_x()][Menue.get_bot(Botnummer).get_y()] == 1
		//				|| map[Menue.get_bot(Botnummer).get_x()][Menue.get_bot(Botnummer).get_y()] == 10) { 	// falls das Feld das Hulk-Icon (1 oder 10) beinhaltet,... 
		//			map[Menue.get_bot(Botnummer).get_x()][Menue.get_bot(Botnummer).get_y()] = 2; 			// ...weise dem Feld das Weg-Icon (2) zu
		//		}
		//
		//		if (map[Menue.get_bot(Botnummer).get_x() + x][Menue.get_bot(Botnummer).get_y() + y] == 12		// falls das n�chste Feld das Bomben-Item-Icon enth�lt...
		//				&& Menue.get_bot(Botnummer).get_max_bomben() < 8) {								// ...und der Spieler noch nicht bei max. 8 Bomben angelangt ist...
		//			Menue.get_bot(Botnummer).set_max_bomben(
		//					Menue.get_bot(Botnummer).get_max_bomben() + 1);	// ...dann erhoehe die maximale Anzahl an Bomben um 1
		//		}
		//
		//		if (map[Menue.get_bot(Botnummer).get_x() + x][Menue.get_bot(Botnummer).get_y() + y] == 15) {		// falls das n�chste Feld das Bomben-Item-Icon enth�lt...
		//			Menue.get_bot(Botnummer).set_bomben_radius(Menue.get_bot(Botnummer).get_bomben_radius() + 1);	// ...dann erhoehe die maximale Anzahl an Bomben um 1
		//		}
		//
		//		if (Botnummer == 1) {
		//			map[Menue.get_bot(Botnummer).get_x() + x][Menue.get_bot(Botnummer).get_y() + y] = 1; 	// weise dem naechsten Feld das Hulk-Icon zu
		//		}
		//
		//		else if (Botnummer == 2) {
		//			map[Menue.get_bot(Botnummer).get_x() + x][Menue.get_bot(Botnummer).get_y() + y] = 10; // weise dem naechsten Feld das Hulk-Icon zu
		//		}

		/* Logische Fortbewegung der Spielfigur: */
		Menue.get_bot(Botnummer).set_x(Menue.get_bot(Botnummer).get_x() + x); 					// setze horizontale Hulk-Position weiter
		Menue.get_bot(Botnummer).set_y(Menue.get_bot(Botnummer).get_y() + y); 					// setze vertikale Hulk-Position weiter

		System.out
				.println("Neue Position des " + Botnummer
						+ "ten Bots: "
						+ Menue.get_bot(Botnummer).get_x()					// Test
						+ "te Spalte, " + Menue.get_bot(Botnummer).get_y()
						+ "te Zeile"); 										// Test
		System.out.println();												// Test		
	}

	void bilder_skalieren() {
		int iconSatz = MapLoader.get_iconSatz();
		String exit_pic = "/Pics/" + iconSatz + "/Exit.png",	// Grafik-Quelle:
																// http://openclipart.org/detail/126307/panneau-sortie--traffic-sign-exit-by-lmproulx
		block_pic = "/Pics/" + iconSatz + "/Block.png",	// Grafik-Quelle:
														// http://openclipart.org/detail/151531/char-somua-s35-by-spadassin-151531
		weg_pic = "/Pics/" + iconSatz + "/Weg.png", mauer_pic = "/Pics/"
				+ iconSatz + "/Mauer.png",	// Grafik-Quelle:
											// http://openclipart.org/detail/3318/house-by-barretr
		hulk_pic = "/Pics/" + iconSatz + "/Hulk.png",	// Grafik-Quelle:
														// http://openclipart.org/detail/168616/hunk-by-bedpanner
		hulk2_pic = "/Pics/" + iconSatz + "/Hulk2.png",	// Grafik-Quelle:
														// http://openclipart.org/detail/168616/hunk-by-bedpanner
		bomb_pic = "/Pics/" + iconSatz + "/Bombe.gif",	// Grafik-Quelle:
														// http://openclipart.org/detail/165734/dynamite-by-magnesus
		exp_pic = "/Pics/" + iconSatz + "/EXP.png";		// Grafik-Quelle:
		// http://openclipart.org/detail/122959/pow-by-viscious-speed

		Image source, scaledImage;

		source = new ImageIcon(Map.class.getResource(block_pic)).getImage();
		scaledImage = source.getScaledInstance(breite, hoehe,
				Image.SCALE_DEFAULT);
		block_icon = new ImageIcon(scaledImage);

		source = new ImageIcon(Map.class.getResource(hulk_pic)).getImage();
		scaledImage = source.getScaledInstance(breite, hoehe,
				Image.SCALE_DEFAULT);
		hulk_icon = new ImageIcon(scaledImage);

		source = new ImageIcon(Map.class.getResource(weg_pic)).getImage();
		scaledImage = source.getScaledInstance(breite, hoehe,
				Image.SCALE_DEFAULT);
		weg_icon = new ImageIcon(scaledImage);

		source = new ImageIcon(Map.class.getResource(mauer_pic)).getImage();
		scaledImage = source.getScaledInstance(breite, hoehe,
				Image.SCALE_DEFAULT);
		mauer_icon = new ImageIcon(scaledImage);

		source = new ImageIcon(Map.class.getResource(bomb_pic)).getImage();
		scaledImage = source.getScaledInstance(breite, hoehe,
				Image.SCALE_DEFAULT);
		bomb_icon = new ImageIcon(scaledImage);

		source = new ImageIcon(Map.class.getResource(exp_pic)).getImage();
		scaledImage = source.getScaledInstance(breite, hoehe,
				Image.SCALE_DEFAULT);
		exp_icon = new ImageIcon(scaledImage);

		source = new ImageIcon(Map.class.getResource(exit_pic)).getImage();
		scaledImage = source.getScaledInstance(breite, hoehe,
				Image.SCALE_DEFAULT);
		exit_icon = new ImageIcon(scaledImage);

		source = new ImageIcon(Map.class.getResource(hulk2_pic)).getImage();
		scaledImage = source.getScaledInstance(breite, hoehe,
				Image.SCALE_DEFAULT);
		hulk2_icon = new ImageIcon(scaledImage);

		source = new ImageIcon(Map.class.getResource("/Pics/Bomben-Item.png"))
				.getImage();
		scaledImage = source.getScaledInstance(breite, hoehe,
				Image.SCALE_DEFAULT);
		bomben_item_icon = new ImageIcon(scaledImage);

		source = new ImageIcon(Map.class.getResource("/Pics/Flammen-Item.png"))
				.getImage();
		scaledImage = source.getScaledInstance(breite, hoehe,
				Image.SCALE_DEFAULT);
		flammen_item_icon = new ImageIcon(scaledImage);
	}

	/**
	 * Setzt die Icons "Hulk" und "Weg" , die bei einer Bewegung verändert werden
	 * 
	 * @param * X - Koordinate im Map - Array , um welche die Spielfigur bewegt werden soll
	 * @param y
	 *            Y - Koordinate im Map - Array , um welche die Spielfigur bewegt werden soll
	 * @param z
	 *            zu bewegende Spielfigur ( 1 : = Spieler 1 , 2 : = Spieler 2 ... )
	 */
	public void move_Hulk(int x, int y, int z) {
		/* Grafische Fortbewegung der Spielfigur: */
		if (map[Menue.get_hulk(z).get_x()][Menue.get_hulk(z).get_y()] == 1
				|| map[Menue.get_hulk(z).get_x()][Menue.get_hulk(z).get_y()] == 10) { 	// falls das Feld das Hulk-Icon (1 oder 10) beinhaltet,... 
			map[Menue.get_hulk(z).get_x()][Menue.get_hulk(z).get_y()] = 2; 				// ...weise dem Feld das Weg-Icon (2) zu
		}

		if (map[Menue.get_hulk(z).get_x() + x][Menue.get_hulk(z).get_y() + y] == 12		// falls das naechste Feld das Bomben-Item-Icon enthaelt...
				&& Menue.get_hulk(z).get_max_bomben() < 8) {							// ...und der Spieler noch nicht bei max. 8 Bomben angelangt ist...
			Menue.get_hulk(z).set_max_bomben(
					Menue.get_hulk(z).get_max_bomben() + 1);							// ...dann erhoehe die maximale Anzahl an Bomben um 1
			if (z == 1 && Menue.clientThread == null) {
				Menue.max_bomben_S1.setText("Max. Anzahl Bomben Spieler 1: "
						+ Menue.get_hulk(z).get_max_bomben());
			}

			else if (z == 2 && (Menue.hotSeat || Menue.clientThread != null)) {
				Menue.max_bomben_S2.setText("Max. Anzahl Bomben Spieler 2: "
						+ Menue.get_hulk(z).get_max_bomben());
			}
			Menue.sound.playItem();
		}

		if (map[Menue.get_hulk(z).get_x() + x][Menue.get_hulk(z).get_y() + y] == 15) {	// falls das naechste Feld das Flammen-Item-Icon enthaelt...
			Menue.get_hulk(z).set_bomben_radius(
					Menue.get_hulk(z).get_bomben_radius() + 1);							// ...dann erhoehe die maximale Anzahl an Bomben um 1
			if (z == 1 && Menue.clientThread == null) {
				Menue.bomben_radius_S1.setText("Bomben-Radius Spieler 1: "
						+ Menue.get_hulk(z).get_bomben_radius());
			}

			else if (z == 2 && (Menue.hotSeat || Menue.clientThread != null)) {
				Menue.bomben_radius_S2.setText("Bomben-Radius Spieler 2: "
						+ Menue.get_hulk(z).get_bomben_radius());
			}

			Menue.sound.playItem();
		}

		if (z == 1) {
			map[Menue.get_hulk(z).get_x() + x][Menue.get_hulk(z).get_y() + y] = 1; 	// weise dem naechsten Feld das Hulk-Icon zu
		}

		else if (z == 2) {
			map[Menue.get_hulk(z).get_x() + x][Menue.get_hulk(z).get_y() + y] = 10; // weise dem naechsten Feld das Hulk-Icon zu
		}

		/* Logische Fortbewegung der Spielfigur: */
		Menue.get_hulk(z).set_x(Menue.get_hulk(z).get_x() + x); // setze horizontale Hulk-Position weiter
		Menue.get_hulk(z).set_y(Menue.get_hulk(z).get_y() + y); // setze vertikale Hulk-Position weiter
	}

	// bombe_legen-Methode:
	public void bombe_legen(int Spieler) {
		bomb_x = Menue.get_hulk(Spieler).get_x();
		bomb_y = Menue.get_hulk(Spieler).get_y();
		bomb[bomb_x][bomb_y] = new Bombe(bomb_x, bomb_y);

		add(bomb[bomb_x][bomb_y]); 					// Bombe hinzufuegen
		bomb[bomb_x][bomb_y].aktivieren(Spieler, Menue.get_hulk(Spieler)
				.get_bomben_radius()); 				// Bombe aktivieren
		map[bomb_x][bomb_y] = 5; 					// Bombe darstellen
	}

	public void bombe_legenBot(int Spieler) {
		bomb_x = Menue.get_bot(Spieler).get_x();
		bomb_y = Menue.get_bot(Spieler).get_y();
		bomb[bomb_x][bomb_y] = new Bombe(bomb_x, bomb_y);

		add(bomb[bomb_x][bomb_y]); 					// Bombe hinzufuegen
		bomb[bomb_x][bomb_y].aktivieren(Spieler, Menue.get_bot(Spieler)
				.get_bomben_radius()); 				// Bombe aktivieren
		map[bomb_x][bomb_y] = 5; 					// Bombe darstellen
	}

	// refresh-Methode:
	public void refresh() {
		GridBagConstraints[][] gbc_label = new GridBagConstraints[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				label[i][j] = new JLabel(); 						// fuer jedes einzele Arrayelement wird ein neues Label erstellt
				label[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				label_laden(label[i][j], map[i][j]); 				// laedt in das Label das Bild (Map hat die Werte ob Hulk, etc..)
				gbc_label[i][j] = new GridBagConstraints();
				gbc_label[i][j].anchor = GridBagConstraints.CENTER;
				gbc_label[i][j].fill = GridBagConstraints.BOTH;
				gbc_label[i][j].insets = new Insets(0, 0, 0, 0);
				gbc_label[i][j].gridx = j;
				gbc_label[i][j].gridy = i;
				gbc_label[i][j].weightx = 1.0;
				gbc_label[i][j].weighty = 1.0;
				add(label[i][j], gbc_label[i][j]); 					// Label auf Panel laden
				label[i][j].repaint(); 								// Zeichnen
				label[i][j].setVisible(true); 						// Sichtbarkeit setzen
			}

		}
		updateUI();
	}

	// init-Methode:
	public void init() {
		int n = MapLoader.get_n();
		GridBagLayout gbl_contentPane = new GridBagLayout();
		setLayout(gbl_contentPane);

		GridBagConstraints[][] gbc_label = new GridBagConstraints[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				label[i][j] = new JLabel(); 			// fuer jedes einzele Arrayelement wird ein neues Label erstellt
				label_laden(label[i][j], map[i][j]); 	// laedt in das Label das Bild (Map hat die Werte ob Hulk, etc..)
				gbc_label[i][j] = new GridBagConstraints();
				gbc_label[i][j].anchor = GridBagConstraints.CENTER;
				gbc_label[i][j].fill = GridBagConstraints.BOTH;
				gbc_label[i][j].gridx = j;
				gbc_label[i][j].gridy = i;
				gbc_label[i][j].weightx = 1.0;
				gbc_label[i][j].weighty = 1.0;
				add(label[i][j], gbc_label[i][j]); 		// Label auf Panel laden
			}

		}

	}

	// label_laden-Methode:
	public void label_laden(JLabel label, int i) {
		switch (i) {
		case 0: // Block/Bomben-Item
			label.setIcon(block_icon);
			break;

		case 1: // Hulk
			label.setIcon(hulk_icon);
			break;

		case 2: // Weg
			label.setIcon(weg_icon);
			break;

		case 3: // Block
			label.setIcon(block_icon);
			break;

		case 4: // Mauer (nicht zerstoerbar)
			label.setIcon(mauer_icon);
			break;

		case 5: // Bombe
			label.setIcon(bomb_icon);
			break;

		case 6: // Explosion
			label.setIcon(exp_icon);
			break;

		case 7: // Ausgang
			label.setIcon(exit_icon);
			break;

		case 8: // Block/Ausgang	
			label.setIcon(block_icon);
			break;

		case 9: // Block/Flammen-Item 	
			label.setIcon(block_icon);
			break;

		case 10: // 2. Spieler
			label.setIcon(hulk2_icon);
			break;

		case 11: // Explosion/Bomben-Item 	
			label.setIcon(exp_icon);
			break;

		case 12: // Bomben-Item
			label.setIcon(bomben_item_icon);
			break;

		case 13: // Explosion/Ausgang
			label.setIcon(exp_icon);
			break;

		case 14: // Explosion/Flammen-Item
			label.setIcon(exp_icon);
			break;

		case 15: // Flammen-Item
			label.setIcon(flammen_item_icon);
			break;
		}

	}

	/* setter & getter: */

	// get_map-Methode:
	public static int[][] get_map() {
		return map;
	}

	// set_map-Methode:
	public static void set_map(int[][] map) {
		Map.map = map;
	}

}
