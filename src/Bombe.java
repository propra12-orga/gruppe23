import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Bombe extends JFrame {
	public boolean gelegt;

	private static final long serialVersionUID = 1L;

	public Bombe(boolean taste) {
		super("Bombe");
		gelegt = taste;
		setDefaultCloseOperation(EXIT_ON_CLOSE); // wenn man das fenster
													// schliesst wird das
													// programm beendet
		pack(); // wird zum zeichnen benotigt
		setVisible(true);
		setBounds(100, 100, 475, 309); // groﬂes des fensters

		if (gelegt == true) {
			System.out.println("Bombe gelegt"); // testen
			add(new JLabel(new ImageIcon("src/Pics/Bombe.png")));// Bild
			// zeichnen
			Zeit timer = new Zeit(2000);// Timer der Bombe

			// if (x == 0) { // explosion beim ablauf der zeit
			// add(new JLabel(new ImageIcon("src/Pics/EXP.png")));
			// }

			class Zeit {
				Timer leuft;

				//
				// {
				// new Zeit(3000); // zeit bis zur explosion
				// System.out.println("Bombe gelegt"); // testen
				// add(new JLabel(new ImageIcon("src/Pics/Bombe.png")));
				// }
				//
				public Zeit(int x) { // construktor erstellen
					leuft = new Timer();
					leuft.schedule(new explosion(), x); // verzogert diezeit bis
														// zur explosion
				}

				class explosion extends TimerTask {
					public void run() {
						System.out.print("Bombe explodiert"); // wenn die zeit
																// ableuft

						add(new JLabel(new ImageIcon("src/Pics/EXP.png"))); // explosion
						leuft.cancel(); // timer wird terminiert

					}

				}

			}
		}

	}

	// private void delete(Bombe) {}

	public static void main(String[] args) {
		new Bombe(true); // false da die bombe erst vom spieler gelegt werden
							// muss
	}
}
