
public class BombHulk extends Thread{
	
	public BombHulk(){
	}
	
	public void run(){
		Menue.get_game().bombe_legen();
		Menue.get_game().removeAll(); // entferne alle bisherigen Komponenten vom Panel
		Menue.get_game().refresh(); // zeichne alle Komponenten des Panels neu
	}

}
