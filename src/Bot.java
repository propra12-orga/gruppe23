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
	private boolean firstMove, run;
	private String lastMove;

	/**
	 * @param xKoord
	 * aktuelle horizontale Position
	 * @param yKoord
	 * aktuelle vertikale Position
	 */
	public Bot() {
		x = 11;
		y = 11;
		startPos[0] = 11;
		startPos[1] = 11;
		rechts = 0;
		links = 0;
		oben = 0;
		unten = 0;
		icon = 10;
		xNeu = 0;
		yNeu = 0;
		firstMove = true;
		run = true;
		

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
		while (run) {
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
	
		map = Menue.get_map();
		
		rechts = map[x][y + 1];
		links = map[x][y - 1];
		oben = map[x - 1][y];
		unten = map[x + 1][y];
		
		System.out.println("Rechts: " + rechts);
		System.out.println("Links: " + links);
		System.out.println("Oben: " + oben);
		System.out.println("Unten: " + unten);

		if(firstMove){
			yNeu = -1;
			Menue.get_game().move_Bot(xNeu, yNeu, 1);
			Menue.get_game().removeAll(); // ...entferne alle bisherigen Komponenten
											// vom Panel...
			Menue.get_game().refresh(); // ...und zeichne alle Komponenten des
										// Panels neu
			lastMove = "links";
			firstMove = false;
		}

		else proofMove();
	
		try {
			Bot.sleep(750);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	private void proofMove(){
		if (lastMove.equals("links")){
			if (links == 4 || links == 3){
				if(oben == 4 || oben == 3){
					if (unten == 4 || unten == 3) moveDirection("rechts");
					else moveDirection("unten");
				}
				else moveDirection("oben");
			}
			else if (links == 1){
				interrupt();
			}
			else moveDirection("links");
		}		
		
		if (lastMove.equals("rechts")){
			if (rechts == 4 || rechts == 3){
				if(unten == 4 || unten == 3){
					if (oben == 4 || oben == 3) moveDirection("links");
					else moveDirection("oben");
				}
				else moveDirection("unten");
			}
			else if (links == 1){
				interrupt();
			}
			else moveDirection("rechts");
			
		}
				
		if (lastMove.equals("oben")){
			if (oben == 4 || oben == 3){
				if(rechts == 4 || rechts == 3){
					if (links == 4 || links == 3) moveDirection("unten");
					else moveDirection("links");
				}
				else moveDirection("rechts");
			}
			else if (oben == 1){
				interrupt();
			}
			else moveDirection("oben");					
		}
		
		if (lastMove.equals("unten")){
			if (unten == 4 || unten == 3){
				if (links == 4 || links == 3){
					if (rechts == 4 || rechts == 3) moveDirection("oben");
					else moveDirection("rechts");
				}
				else moveDirection("links");
			}
			if (unten == 1){
				interrupt();
			}
			else moveDirection("unten");
			
		}
	}
	
	public void moveDirection(String richtung){
		if (richtung.equals("links")){
			yNeu = -1;
			Menue.get_game().move_Bot(xNeu, yNeu, 1);
			Menue.get_game().removeAll(); // ...entferne alle bisherigen Komponenten
											// vom Panel...
			Menue.get_game().refresh(); // ...und zeichne alle Komponenten des
										// Panels neu
			lastMove = "links";
		}
		else if (richtung.equals("rechts")){
			yNeu = 1;
			Menue.get_game().move_Bot(xNeu, yNeu, 1);
			Menue.get_game().removeAll(); // ...entferne alle bisherigen Komponenten
											// vom Panel...
			Menue.get_game().refresh(); // ...und zeichne alle Komponenten des
										// Panels neu
			lastMove = "rechts";
		}
		else if (richtung.equals("oben")){
			xNeu = -1;
			Menue.get_game().move_Bot(xNeu, yNeu, 1);
			Menue.get_game().removeAll(); // ...entferne alle bisherigen Komponenten
											// vom Panel...
			Menue.get_game().refresh(); // ...und zeichne alle Komponenten des
										// Panels neu
			lastMove = "oben";
		}
		else { 
			xNeu = 1;
			Menue.get_game().move_Bot(xNeu, yNeu, 1);
			Menue.get_game().removeAll(); // ...entferne alle bisherigen Komponenten
											// vom Panel...
			Menue.get_game().refresh(); // ...und zeichne alle Komponenten des
										// Panels neu
			lastMove = "unten";
		}
		
	}
	
	public void botInterrupt(){
		run = false;
	}

	//---------------------------------------Setter & Getter-----------------------
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
