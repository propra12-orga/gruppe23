/**
 * Speichert die Position der Spielfigur im Spielfeld und Grafik
 * @author Tobias Korfmacher
 *
 */

public class Hulk {
	// Deklaration & Initialisierung:
	public int x; // x-Koordinate (horizontale Position)
	public int y; // y-Koordinate (vertikale Position)
	public int[] startPos = new int[2];
	public int icon;
	public int max_bomben = 1;
	
	/**
	 * 
	 * @param x	 Horizontal-Koordinate der Figur
	 * @param y Vertikal-Koordinate der Figur
	 * @param Icon Grafik der Spielfigur
	 */
	
	public Hulk(int x, int y, int Icon) {
		this.x = x;
		this.y = y;
		startPos[0] = x;
		startPos[1] = y;
		icon = Icon;
	}
	
	/**
	 * 
	 * @return aktuelle X-Koordinate im Map-Array
	 */
	public int get_x() {
		return x;
	}
	
	/**
	 * 
	 * @param x aktualisiert X-Koordinate
	 */
	public void set_x(int x) {
		this.x = x;
	}
	
	/**
	 * 
	 * @return aktuelle y-Koordinate im Map-Array
	 */
	public int get_y() {
		return y;
	}
	
	/**
	 * 
	 * @param y aktualisiert Y-Koordinate
	 */
	public void set_y(int y) {
		this.y = y;	
	}

	public int get_startX() {	
		return startPos[0];
	}
	
	public int get_startY() {
		return startPos[1];
	}
	
	/**
	 * 
	 * @return Icon-ID fuer Grafikarray
	 */
	public int get_Icon() {
		return icon;
	}
	
	public int get_max_bomben() {
		return max_bomben;
	}
	
	public void set_max_bomben(int max_bomben) {
		this.max_bomben = max_bomben;	
	}
	
}
