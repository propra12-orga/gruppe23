/*Siehe MoveHulk.java
 * 
 */
/**
 * 
 * @author Sebastian Dittmann
 *
 */


public class BombHulk implements Runnable{
	boolean bomb;
	
	
	public BombHulk(){
		bomb = false;
	}
	
	public void run(){
		while(true){
			bomb = Menue.get_bomb();
			if(bomb){
				Menue.get_game().bombe_legen();
				Menue.get_game().removeAll(); // entferne alle bisherigen Komponenten vom Panel
				Menue.get_game().refresh(); // zeichne alle Komponenten des Panels neu
				bomb = false;
				Menue.set_bomb();
				System.out.println("bomb-Thread");
			}
		}
		
	}

}
