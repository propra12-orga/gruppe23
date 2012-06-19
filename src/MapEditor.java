import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

// Autor T. K 
// überschreibt noch eine datei, falls sie schon vorhanden ist. muss noch ins menü eingebunden werden. ist wieder im jframe!
public class MapEditor {
	final static int n = MapLoader.get_n();
	private static int[][] map = new int[n][n];
	JFrame frame;
	protected static boolean saved = false;
	private static int power;
	ImageIcon pic;
	int iconSatz;

	public MapEditor() {
		edit();
	}

	public int edit() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = 2;
			}
		}
		
		int anzahlIcons = 9;

		String eingabe = JOptionPane.showInputDialog(null,
				"Geben Sie die Level-Nummer ein!", "Levelnummer",
				JOptionPane.PLAIN_MESSAGE);
		if (eingabe == null) {
			return 0;
		} else {
			MapLoader.set_level(Integer.parseInt(eingabe));
		}
		
		int iconAbfrage = JOptionPane.showConfirmDialog(null,
				"Möchten Sie den ersten Icon-Satz nutzen?",
				"Gespeichert?", JOptionPane.YES_NO_OPTION);
		switch (iconAbfrage) {
			case 0:
				MapLoader.set_iconSatz(1);
				iconSatz = 1;
				break;
			case 1:
				MapLoader.set_iconSatz(2);
				iconSatz = 2;
				break;
		}
		MapLoader.level_speichern(map, "Level-" + eingabe);
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setTitle("Map-Editor"); // Fenstertitel setzen
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		final String levelnummer = "Level-" + eingabe;
		JLabel levelname = new JLabel(levelnummer, JLabel.CENTER);

		frame.getContentPane().add(levelname, BorderLayout.BEFORE_FIRST_LINE);
		JButton save_button = new JButton("Level speichern");
		ActionListener save = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MapLoader.level_speichern(map, levelnummer);
				saved = true;
				Menue.spiel_neustarten();
			}
		};

		JPanel place = new JPanel();
		frame.setResizable(false); // Fenster soll nicht skalierbar sein
		
		JButton exit_button = new JButton("Editor Beenden");
		ActionListener exit = new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (saved) {
					frame.setVisible(false);
				}

				else {
					int eingabe = JOptionPane.showConfirmDialog(null,
							"Wollen Sie die neue Map speichern?",
							"Gespeichert?", JOptionPane.YES_NO_CANCEL_OPTION);

					switch (eingabe) {
					case 0:
						MapLoader.level_speichern(map, levelnummer);
						frame.setVisible(false);
						break;
					case 1:
						frame.setVisible(false);
						break;
					case 2: // abbrechen nichts machen
						break;
					}
				}
			}
		};
		exit_button.addActionListener(exit);
		save_button.addActionListener(save);
		place.add(exit_button);
		place.add(save_button);

		frame.getContentPane().add(place, BorderLayout.SOUTH);
		// Icons
		JRadioButton icon[] = new JRadioButton[anzahlIcons];
		final ButtonGroup buttonGroup = new ButtonGroup();
		icon[0] = new JRadioButton("Hulk", true);
		icon[1] = new JRadioButton("Weg");
		icon[2] = new JRadioButton("Block");
		icon[3] = new JRadioButton("Mauer");
		icon[4] = new JRadioButton("Ausgang");
		icon[5] = new JRadioButton("Block-Ausgang");
		icon[6] = new JRadioButton("Block/Flammen-Item");
		icon[7] = new JRadioButton("Block/Bomben-Item");
		icon[8] = new JRadioButton("2.Spieler");

		// eventuell noch mehrere
		JPanel radioPanel = new JPanel(new GridLayout(anzahlIcons, 1));
		for (int k = 0; k < anzahlIcons; k++) {
			buttonGroup.add(icon[k]);
			radioPanel.add(icon[k]);
		}

		frame.getContentPane().add(radioPanel, BorderLayout.LINE_START);
		frame.pack();
		JPanel buttonPanel = new JPanel(new GridLayout(n, n));
		final JButton feld[][] = new JButton[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				final int a = i;
				final int b = j;
				ActionListener list = new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						String name = getSelectedButton(buttonGroup).getText();
						if (name != null) {
							if (name == "Hulk") {
								power = 1;
								pic = new ImageIcon(
										Map.class.getResource("/Pics/" + iconSatz
												+ "/Hulk.png"));
							} else if (name == "Weg") {
								power = 2;
								pic = new ImageIcon(
										Map.class.getResource("/Pics/" + iconSatz
												+ "/Weg.png"));
							} else if (name == "Block") {
								power = 4;
								pic = new ImageIcon(
										Map.class.getResource("/Pics/" + iconSatz
												+ "/Block.png"));
							} else if (name == "Mauer") {
								power = 3;
								pic = new ImageIcon(
										Map.class.getResource("/Pics/" + iconSatz
												+ "/Mauer.png"));
							} else if (name == "Ausgang") {
								power = 7;
								pic = new ImageIcon(
										Map.class.getResource("/Pics/" + iconSatz
												+ "/Exit.png"));
							} else if (name == "Block-Ausgang") {
								power = 8;
								pic = new ImageIcon(
										Map.class.getResource("/Pics/" + iconSatz
												+ "/Exit.png"));
							} else if (name == "Block/Flammen-Item") {
								power = 9;
								pic = new ImageIcon(
										Map.class.getResource("/Pics/Flammen-Item.png"));
							} else if (name == "Block/Bomben-Item") {
								power = 9;
								pic = new ImageIcon(
										Map.class.getResource("/Pics/Bomben-Item.png"));
							} else if (name == "2.Spieler") {
								power = 10;
								pic = new ImageIcon(
										Map.class.getResource("/Pics/" + iconSatz
												+ "/Hulk2.png"));
							}

							map[a][b] = power;

							feld[a][b].setIcon(pic); // ...Grafik auf Button
							Menue.spiel_neustarten();

						}
					}
				};

				feld[i][j] = new JButton();

				feld[i][j].setPreferredSize(new Dimension(50, 50));// Button-Größe
				feld[i][j].addActionListener(list);
				buttonPanel.add(feld[i][j]);

			}
			frame.getContentPane().add(buttonPanel);
		}

		frame.pack();

		return 1;
	}

	public static JRadioButton getSelectedButton(ButtonGroup group) {
		Enumeration<AbstractButton> e = group.getElements();
		while (e.hasMoreElements()) {
			AbstractButton b = e.nextElement();
			if (b.isSelected())
				return (JRadioButton) b;
		}
		return null;
	}

}
