
/**
 * Steuert die KI im Einzelspieler
 * @author sebi
 *
 */
public class Bot extends Thread{
	private int[][] map;
	private int x, y, oben, unten, rechts, links, icon, start;
	public int[] startPos = new int[2];
	public int max_bomben = 1;
	private int bomben_radius = 2;
	
	/** 
	 * @param xKoord aktuelle horizontale Position
	 * @param yKoord aktuelle vertikale Position
	 */
	public Bot(int xKoord, int yKoord){
		x = xKoord; y = yKoord;
		rechts = 0; links = 0; oben = 0; unten = 0;
		icon = 10;
		
	}
	
	/**
	 * Initialisierung des Bot-Threads
	 */
	public void run(){
		start = 1;
		while(true){
			move();
		}
	}
	
	
	/**
	 * Testet Bedingungen fuer Bewegung
	 */
	private void move(){
		map = Menue.get_map();
		rechts = map[x+1][y];
		links = map[x-1][y];
		oben = map[x][y+1];
		unten = map[x][y-1];
		
		if (links == 2){
			x -= 1;
		}
		else if (oben == 2){
			y += 1;
		}
		else if (rechts == 2){
			x += 1;
		}
		else if (unten == 2){
			y -= 1;
		}
		
		Menue.get_game().move_Hulk(x, y, 2);
		
		Bot.sleep(500);
		
	}
	
	public void set_x(int X){
		x = X;
	}
	
	public void set_y(int Y){
		y = Y;
	}
	
	public int getStart(){
		return start;
	}
}
