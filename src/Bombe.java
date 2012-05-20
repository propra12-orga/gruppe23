import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Bombe extends JLabel {

	private static final long serialVersionUID = 1L;
	
	// paint-Methode:
	public void paint() {
		System.out.println("Bombe gelegt"); // Konsolenausgabe zum Test
		setIcon(new ImageIcon("src/Pics/Bombe.png")); // Bild laden
		this.repaint(); // Zeichnen
		this.setVisible(true); // Sichtbarkeit setzen
		Zeit timer = new Zeit(); // Timer erstellen
		Menue.getGame().add(timer); // Timer hinzufügen
		timer.timer_starten(8000); // Timer starten
	}
	
}
