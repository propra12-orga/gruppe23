import java.awt.event.KeyEvent;


public class GameKeyListener {
	private int[] a = new int [2];

	public GameKeyListener(){}
	

	
	public void keyPressed(KeyEvent Key){ //Diese Funktion ist essentiel für die Umsetzung des Keylisteners (bzw. die einzig relevante)
		if(Key.getKeyCode() == Key.VK_UP){
			a[0] = 0;
			a[1] = 1;
		}
		
		if(Key.getKeyCode() == Key.VK_LEFT){
			a[0] = -1;
			a[1] = 0;
		}
		
		if(Key.getKeyCode() == Key.VK_RIGHT){
			a[0] = 1;
			a[1] = 0;
		}
		
		if(Key.getKeyCode() == Key.VK_DOWN){
			a[0] = 0;
			a[1] = -1;
		}
		//b = KeyListenerTest.zeichnen(b, a);			//An dieser Stelle muss die Print-Funktion mit Übergabe des a[] gesetzt werden.
	}											//Zudem muss der KeyListener für die Bombe-Taste noch eine Auswirkung bekommen.
	
	
	
	public void keyTyped(KeyEvent Key){}
	public void keyReleased(KeyEvent Key) {}

}
