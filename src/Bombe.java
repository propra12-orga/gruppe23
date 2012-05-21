import javax.swing.JLabel;

public class Bombe extends JLabel {

	// Deklaration & Initialisierung:
	private static final long serialVersionUID = 1L;	
	public int x = 1; // x-Koordinate (horizontale Position)
	public int y = 1; // y-Koordinate (vertikale Position)
	
	// setter & getter:
	public int get_x() {
		return x;
	}
	
	public void set_x(int x) {
		this.x = x;
	}
	
	public int get_y() {
		return y;
	}
	
	public void set_y(int y) {
		this.y = y;
	}
	
	// aktivieren-Methode:
	public void aktivieren() {
		System.out.println("Bombe gelegt"); // Test	
		Zeit timer = new Zeit(); // Timer erstellen
		Menue.get_game().add(timer); // Timer hinzufügen
		timer.timer_starten(3000, "Bombe"); // Timer für Bombe starten	
	}
	
}
