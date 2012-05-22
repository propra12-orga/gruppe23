
public class MoveHulk implements Runnable{
	int[] pos;
	boolean move;
	
	
	public MoveHulk(){
		super();
		pos = new int[2];
		move = false;
	}
	
	public void run(){
		while(true){
			move=Menue.get_move();
			
			if(move){
				pos = Menue.get_newPos();
				if (Menue.map[Menue.get_hulk().get_x()+pos[0]][Menue.get_hulk().get_y()+pos[1]] == 2){
					int hulk_x = Menue.get_hulk().get_x(); // horizontale Position des Hulks
					Menue.get_hulk().set_x(hulk_x+pos[0]); // setze horizontale Position des Hulks weiter
				
					int hulk_y = Menue.get_hulk().get_y(); // vertikale Position des Hulks
					Menue.get_hulk().set_y(hulk_y+pos[1]); // setze vertikale Position des Hulks weiter
				
					System.out.println("Hulks neue Position: " + Menue.get_hulk().get_x() + "te Spalte, " + Menue.get_hulk().get_y() + "te Zeile"); // Test
					System.out.println("");
					
					Menue.get_game().move_Hulk(pos[0],pos[1]); // bewege Hulk auf dem Spielfeld
					Menue.get_game().removeAll(); // entferne alle bisherigen Komponenten vom Panel
					Menue.get_game().refresh();	// zeichne alle Komponenten des Panels neu
				}
				else if (Menue.map[Menue.get_hulk().get_x()+pos[0]][Menue.get_hulk().get_y()+pos[1]] == 7) { // falls das naechste Feld das Ziel-Feld ist
					System.out.println("Gewonnen!"); // Test
					System.out.println("");
					
					Menue.get_game().move_Hulk(-Menue.map.length+3,-Menue.map.length+3); // bewege Hulk zum Startpunkt zurueck
					Menue.get_game().removeAll(); // entferne alle bisherigen Komponenten vom Panel
					Menue.get_game().refresh(); // zeichne alle Komponenten des Panels neu
				}
				Menue.set_move();
			}
		}
	}
}
