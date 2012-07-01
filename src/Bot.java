/**
 * Steuert die KI im Einzelspieler
 * 
 * @author sebi
 * 
 */
public class Bot extends Thread {
	private int[][] map;
	private int x, y, xNeu, yNeu, oben, unten, rechts, links, icon, start;
	public int[] startPos = new int[2];
	public int max_bomben = 1;
	private int bomben_radius = 2;
	private boolean loaded, used;

	/**
	 * @param xKoord
	 * aktuelle horizontale Position
	 * @param yKoord
	 * aktuelle vertikale Position
	 */
	public Bot() {
		x = 12;
		y = 12;
		rechts = 0;
		links = 0;
		oben = 0;
		unten = 0;
		icon = 10;
		xNeu = 0;
		yNeu = 0;

	}

	/**
	 * Initialisierung des Bot-Threads
	 */

	public void run() {
		start = 1;
		try {
			Bot.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while (true) {
			try {
				move();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Testet Bedingungen fuer Bewegung
	 * 
	 * @throws InterruptedException
	 */
	private void move() throws InterruptedException {
		xNeu = 0;
		yNeu = 0;
		used = false;
		loaded = Menue.get_mapLoaded();
		map = Menue.get_map();
		rechts = map[x + 1][y];
		links = map[x - 1][y];
		oben = map[x][y - 1];
		unten = map[x][y + 1];
		System.out.println("Rechts: " + rechts);
		System.out.println("Links: " + links);
		System.out.println("Oben: " + oben);
		System.out.println("Unten: " + unten);

		if (links == 2 && !used) {
			xNeu -= 1;
			used = true;
		} else if (oben == 2 && !used) {
			yNeu += 1;
			used = true;
		} else if (rechts == 2 && !used) {
			xNeu += 1;
			used = true;
		} else if (unten == 2 && !used) {
			yNeu -= 1;
			used = true;
		}

		System.out.println("Bot laueft!");
		if (loaded) {
			Menue.get_game().move_Bot(xNeu, yNeu, 1);
			System.out.println("Thread arbeitet");
			Menue.get_game().removeAll(); // ...entferne alle bisherigen Komponenten
											// vom Panel...
			Menue.get_game().refresh(); // ...und zeichne alle Komponenten des
										// Panels neu
		}
		System.out.println();

		try {
			Bot.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	//-------------------Setter & Getter-----------------------
	public void set_x(int X) {
		x = X;
	}

	public void set_y(int Y) {
		y = Y;
	}

	public int get_x() {
		return x;
	}

	public int get_y() {
		return y;
	}

	public void set_max_bomben(int anzahl) {
		max_bomben = anzahl;
	}

	public int get_max_bomben() {
		return max_bomben;
	}

	public void set_bomben_radius(int radius) {
		bomben_radius = radius;
	}

	public int get_bomben_radius() {
		return bomben_radius;
	}

	public int getStart() {
		return start;
	}

	public int get_icon() {
		return icon;
	}

	// get_startX-Methode:
	public int get_startX() {
		return startPos[0];
	}

	// get_startY-Methode:
	public int get_startY() {
		return startPos[1];
	}

}
