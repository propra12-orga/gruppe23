/**
 * Speichert die Position der Spielfigur im Spielfeld, nuetzlich fuer parallele Programmierung
 * @author Tobias Korfmacher
 *
 */

public class Hulk {
	// Deklaration & Initialisierung:
	public int x = 1; // x-Koordinate (horizontale Position)
	public int y = 1; // y-Koordinate (vertikale Position)
	
	public Hulk(int x, int y){
		this.x = x; this.y = y;
	}
	
	/**
	 * legt Spielerposition in der Map fest (beim Spielstart)
	 */
	public void setPlayerPosition(){
		
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
	
}
