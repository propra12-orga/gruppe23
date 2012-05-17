import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameKeyListener implements KeyListener{
	private int[] a = new int [2];
	private int[] b = new int [2];

	public GameKeyListener(){
		for(int i = 0; i < 2; i++){
			a[i] = 0;
			b[i] = 0;
		}
	}
	
	public void zeichnen(int[] Position, int[] neuePosition){
			//noch keine Funktion, da nicht mit Programm verknüpft	
	}
	
	public void keyPressed(KeyEvent Key){ //Diese Funktion ist essentiel für die Umsetzung des Keylisteners (bzw. die einzig relevante)
		if(Key.getKeyCode() == Key.VK_UP){			//Oben
			a[0] = 0;
			a[1] = 1;
		}
		
		if(Key.getKeyCode() == Key.VK_LEFT){		//Links
			a[0] = -1;
			a[1] = 0;
		}
		
		if(Key.getKeyCode() == Key.VK_RIGHT){		//Rechts
			a[0] = 1;
			a[1] = 0;
		}
		
		if(Key.getKeyCode() == Key.VK_DOWN){		//Unten
			a[0] = 0;
			a[1] = -1;
		}
		if(Key.getKeyCode() == Key.VK_SPACE){		//Bombe
			
		}
		zeichnen(b, a);			//An dieser Stelle muss die Print-Funktion mit Übergabe des a[] gesetzt werden.
	}												//Zudem muss der KeyListener für die Bombe-Taste noch eine Auswirkung bekommen.
	
	
	
	public void keyTyped(KeyEvent Key){}
	public void keyReleased(KeyEvent Key) {}

}
