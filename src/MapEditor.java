import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

//  Autor T. K 
// überschreibt noch eine datei, falls sie schon vorhanden ist. muss noch ins menü eingebunden werden. ist wieder im jframe!
public class MapEditor extends JFrame {
	final static int n = MapLoader.get_n();
	private static int[][] map = new int[n][n];
	static JFrame frame;
	protected static boolean saved = false;
	private static int power;

	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { MapEditor window = new
	 * MapEditor(); window.frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */

	public MapEditor() {
		edit();
	}

	public static void edit() {

		int anzahlIcons = 8;

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		String eingabe = JOptionPane.showInputDialog(null,
				"Geben Sie die Level-Nummer ein!", "Levelnummer",
				JOptionPane.PLAIN_MESSAGE);
		MapLoader.set_level(Integer.parseInt(eingabe));

		final String levelnummer = "Level-" + eingabe;
		JLabel levelname = new JLabel(levelnummer, JLabel.CENTER);

		frame.getContentPane().add(levelname, BorderLayout.BEFORE_FIRST_LINE);
		JButton save_button = new JButton("Level speichern");
		ActionListener save = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MapLoader.level_speichern(map, levelnummer);
				saved = true;
			}
		};

		JPanel place = new JPanel();
		JButton exit_button = new JButton("Editor Beenden");
		ActionListener exit = new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (saved) {
					System.exit(0);
				}

				else {
					int eingabe = JOptionPane.showConfirmDialog(null,
							"Wollen Sie die neue Map speichern?",
							"Gespeichert?", JOptionPane.YES_NO_CANCEL_OPTION);

					switch (eingabe) {
					case 0:
						MapLoader.level_speichern(map, levelnummer);
						System.exit(0);
						break;
					case 1:
						System.exit(0);
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
		icon[6] = new JRadioButton("Block-Item");
		icon[7] = new JRadioButton("2.Spieler");

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
							} else if (name == "Weg") {
								power = 2;
							} else if (name == "Block") {
								power = 4;
							} else if (name == "Mauer") {
								power = 3;
							} else if (name == "Ausgang") {
								power = 7;
							} else if (name == "Block-Ausgang") {
								power = 8;
							} else if (name == "Block-Item") {
								power = 9;
							} else if (name == "2.Spieler") {
								power = 10;
							}

							map[a][b] = power;
							String place = "";
							place += power;
							feld[a][b].setText(place);
							Menue.spiel_neustarten();

						}
					}
				};

				feld[i][j] = new JButton();

				feld[i][j].setPreferredSize(new Dimension(45, 30));// Button-Größe
				feld[i][j].addActionListener(list);
				buttonPanel.add(feld[i][j]);

			}
			frame.getContentPane().add(buttonPanel);
		}

		frame.pack();

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
