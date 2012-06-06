/**
 * Speichert die Position der Spielfigur im Spielfeld, nuetzlich fuer parallele Programmierung
 * @author Tobias Korfmacher
 *
 */

public class Hulk {
	// Deklaration & Initialisierung:
	public int x; // x-Koordinate (horizontale Position)
	public int y; // y-Koordinate (vertikale Position)
	public static int[] startPos = new int[2];
	
	/**
	 * 
	 * @param x	 Horizontal-Koordinate der Figur
	 * @param y Vertikal-Koordinate der Figur
	 */
	
	public Hulk(int x, int y){
		this.x = x; this.y = y;
		startPos[0]=this.x;
		startPos[1]=this.y;
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
		// TODO Auto-generated method stub
		return startPos[0];
	}
	
	public int get_startY(){
		return startPos[1];
	}
	
}
