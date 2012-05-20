/*Dieses Programm muss an den Panel geknüpft werden, in welchem sich das Spielfeld befindet.
 *Das Panel muss Panel.addKeyListener(this) bekommen. Als Ausgabe bekommt man in a[] die
 *Koordinaten, welche aus der gedrückten Taste folgen (z.B.: für VK_UP wird (0,1) für (x,y) geliefert).*/






import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MenueKeyListener implements KeyListener{
		
	public MenueKeyListener(){
		
	}
	
	@Override
	public void keyPressed(KeyEvent Key){}
	
	
	@Override
	public void keyTyped(KeyEvent Key){
		
	}
	public void keyReleased(KeyEvent Key) {}
	
	/*public static void main (String[]args){
		Spielfigur keyTest = new Spielfigur();
        keyTest.KeyStarter();
	}*/
	
}
