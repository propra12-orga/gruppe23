import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Bombe extends JFrame {
	public boolean gelegt;

	private static final long serialVersionUID = 1L;

	public Bombe(boolean taste) {
		super("Bombe");
		gelegt = taste;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setBounds(100, 100, 475, 309);
		// Gelegte Bombe nur zum testen

		if (gelegt == true) { // Bild zeichnen
			add(new JLabel(new ImageIcon("src/Pics/Bombe.jpg")));
			int Timer = -20000;
			// Timer der Bombe
			while (gelegt == true) {

				Timer++; // Timer runterzahlen lassen zum explodiren

				if (Timer == 0) { // explosion anzeigen
					add(new JLabel(new ImageIcon("src/Pics/EXP.jpg")));
					// gelegt = false;
				}

			}

		}
	}

	// private void delete(Bombe) {}

	public static void main(String[] args) {
		new Bombe(true);
	}
}
