//import javax.swing.JOptionPane;
//
///**
// * Steuert die KI im Einzelspieler
// * 
// * @author sebi
// * 
// */
//public class Bot extends Thread {
//	private int[][] map;
//	private int x, y, xNeu, yNeu, oben, unten, rechts, links, icon, start, steps;
//	public int[] startPos = new int[2];
//	public int max_bomben = 1;
//	private int bomben_radius = 2;
//	private boolean firstMove, run;
//	private String lastMove;
//
//	/**
//	 * @param xKoord aktuelle horizontale Position
//	 * @param yKoord aktuelle vertikale Position
//	 */
//	public Bot() {
//		steps = 0;
//		x = 11;
//		y = 11;
//		startPos[0] = 11;
//		startPos[1] = 11;
//		rechts = 0;
//		links = 0;
//		oben = 0;
//		unten = 0;
//		icon = 10;
//		xNeu = 0;
//		yNeu = 0;
//		firstMove = true;
//		run = true;
//	}
//
//	/**
//	 * Initialisierung des Bot-Threads. Start wird um eine Sekunde verzoegert, da Karte geladen werden muss.
//	 */
//
//	public void run() {
//		start = 1;
//		try {
//			Bot.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		while (true) {
//			if (!run) break;
//			try {
//				move();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	/**
//	 * Testet Bedingungen fuer Bewegung
//	 * 
//	 * @throws InterruptedException
//	 */
//	private void move() throws InterruptedException {
//		xNeu = 0;
//		yNeu = 0;
//	
//		map = Map.get_map();
//		
//		rechts = map[x][y + 1];
//		links = map[x][y - 1];
//		oben = map[x - 1][y];
//		unten = map[x + 1][y];
//		
//		System.out.println("Rechts: " + rechts);
//		System.out.println("Links: " + links);
//		System.out.println("Oben: " + oben);
//		System.out.println("Unten: " + unten);
//
//		if(firstMove){
//			yNeu = -1;
//			Menue.get_game().move_Bot(xNeu, yNeu, 1);
//			Menue.get_game().removeAll(); // ...entferne alle bisherigen Komponenten
//											// vom Panel...
//			Menue.get_game().refresh(); // ...und zeichne alle Komponenten des
//										// Panels neu
//			lastMove = "links";
//			firstMove = false;
//		}
//
//		else proofMove();
//	
//		try {
//			Bot.sleep(750);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//
//	}
//	
//	/**
//	 * Prueft auf moegliche Richtung oder bestimmt gegebenen Falles eine neue Richtung (dreht sich immer 90� nach rechts). Bewegung wird in Extramethoden durchgefuehrt um Uebersicht zu wahren.
//	 */
//	private void proofMove(){
//		
//		if (lastMove.equals("links")){
//			if (links == 4 || links == 3){
////				if (links == 3) Menue.get_game().bombe_Bot(1);
//				if(oben == 4 || oben == 3){
//					if (unten == 4 || unten == 3) moveDirection("rechts");
//					else moveDirection("unten");
//				}
//				else moveDirection("oben");
//				steps++;
//			}
//			else if (links == 1){
//				String meldung = Menue.get_hulk(1).get_Spielername();
//				JOptionPane.showMessageDialog(null, meldung + " hat verloren!");
//				System.out.println(meldung + " hat verloren."); 	// Test
//				System.out.println(); 							// Test
//				Menue.sound.playTod();
//				Menue.abfrage_neustarten();
//				botInterrupt();
//			}
//			else moveDirection("links");
//		}		
//		
//		else if (lastMove.equals("rechts")){
//			
//			if (rechts == 4 || rechts == 3){
////				if(rechts == 3) Menue.get_game().bombe_Bot(1);
//				if(unten == 4 || unten == 3){
//					if (oben == 4 || oben == 3) moveDirection("links");
//					else moveDirection("oben");
//				}
//				else moveDirection("unten");
//			}
//			else if (links == 1){
//				String meldung = Menue.get_hulk(1).get_Spielername();
//				JOptionPane.showMessageDialog(null, meldung + " hat verloren!");
//				System.out.println(meldung + " hat verloren."); 	// Test
//				System.out.println(); 							// Test
//				Menue.sound.playTod();
//				Menue.abfrage_neustarten();
//				botInterrupt();
//			}
//			else moveDirection("rechts");
//		}
//				
//		else if (lastMove.equals("oben")){
//			if (oben == 4 || oben == 3){
////				if (oben == 3) Menue.get_game().bombe_Bot(1);
//				if(rechts == 4 || rechts == 3){
//					if (links == 4 || links == 3) moveDirection("unten");
//					else moveDirection("links");
//				}
//				else moveDirection("rechts");
//			}
//			else if (oben == 1){
//				String meldung = Menue.get_hulk(1).get_Spielername();
//				JOptionPane.showMessageDialog(null, meldung + " hat verloren!");
//				System.out.println(meldung + " hat verloren."); 	// Test
//				System.out.println(); 							// Test
//				Menue.sound.playTod();
//				Menue.abfrage_neustarten();
//				botInterrupt();
//			}
//			else moveDirection("oben");					
//		}
//		
//		else if (lastMove.equals("unten")){
//			if (unten == 4 || unten == 3){
////				if (unten == 3) Menue.get_game().bombe_Bot(1);
//				if (links == 4 || links == 3){
//					if (rechts == 4 || rechts == 3) moveDirection("oben");
//					else moveDirection("rechts");
//				}
//				else{ 
//					moveDirection("links");
//					System.out.println(xNeu + " " + yNeu);
//				}
//			}
//			else if (unten == 1){
//				String meldung = Menue.get_hulk(1).get_Spielername();
//				JOptionPane.showMessageDialog(null, meldung + " hat verloren!");
//				System.out.println(meldung + " hat verloren."); 	// Test
//				System.out.println(); 							// Test
//				Menue.sound.playTod();
//				Menue.abfrage_neustarten();
//				botInterrupt();
//			}
//			else moveDirection("unten");
//		}
//	}
//	
//	/**
//	 * Fuehrt die gueltige Bewegung aus. Dient hauptsaechlich der Uebersichtlichkeit.
//	 * @param richtung ist die Richtung, in die sich der Bot bewegen soll
//	 */	
//	public void moveDirection(String richtung){
//		if (Menue.get_map()[x+xNeu][y+yNeu] == 1){
//			Menue.botStop();
//			Menue.sound.playTod();			
//		}
//		else if(Menue.get_map()[x+xNeu][y+yNeu] == 6){
//			Menue.botStop();
//			String meldung = Menue.get_hulk(1).get_Spielername();
//			JOptionPane.showMessageDialog(null, meldung + " hat gewonnen!");
//			Menue.abfrage_neustarten();
//			
//		}		
//		else if (richtung.equals("links")){
//			Menue.set_map(this.x, this.y, 2);
//			yNeu = -1;
//			lastMove = "links";
//		}
//		else if (richtung.equals("rechts")){
//			
//			Menue.set_map(this.x, this.y, 2);
//			yNeu = 1;
//			lastMove = "rechts";
//		}
//		else if (richtung.equals("oben")){
//			Menue.set_map(this.x, this.y, 2);
//			xNeu = -1;
//			lastMove = "oben";
//		}
//		else { 
//			Menue.set_map(this.x, this.y, 2);
//			xNeu = 1;
//			lastMove = "unten";
//		}
//		Menue.get_game().move_Bot(xNeu, yNeu, 1);
//		Menue.get_game().removeAll(); // ...entferne alle bisherigen Komponenten
//									// vom Panel...
//		Menue.get_game().refresh(); // ...und zeichne alle Komponenten des
//									// Panels neu
//		steps++;
//		
//	}
//	
//	public void botInterrupt(){
//		run = false;
//	}
//
//	//---------------------------------------Setter & Getter-----------------------
//	public void set_x(int X) {
//		x = X;
//	}
//
//	public void set_y(int Y) {
//		y = Y;
//	}
//
//	public int get_x() {
//		return x;
//	}
//
//	public int get_y() {
//		return y;
//	}
//
//	public void set_max_bomben(int anzahl) {
//		max_bomben = anzahl;
//	}
//
//	public int get_max_bomben() {
//		return max_bomben;
//	}
//
//	public void set_bomben_radius(int radius) {
//		bomben_radius = radius;
//	}
//
//	public int get_bomben_radius() {
//		return bomben_radius;
//	}
//
//	public int getStart() {
//		return start;
//	}
//
//	public int get_icon() {
//		return icon;
//	}
//
//	// get_startX-Methode:
//	public int get_startX() {
//		return startPos[0];
//	}
//
//	// get_startY-Methode:
//	public int get_startY() {
//		return startPos[1];
//	}
//}
