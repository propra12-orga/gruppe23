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
			Zeit timer = new Zeit(8000);// Timer der Bombe

			//
			// if (new Zeit(0) == null) { // wie z‰hlt denn der bakackte timer
			// // denn???????? bei != null funzt
			// // ist auch logisch // explosion
			// // beim ablauf der zeit
			// add(new JLabel(new ImageIcon("src/Pics/EXP.png")));
			// System.out.println("Bombe explodiert"); // testen
			//
			// }

		}

	}

	// private void delete(Bombe) {}

	public static void main(String[] args) {
		new Bombe(true); // false da die bombe erst vom spieler gelegt werden
							// muss
	}
}
