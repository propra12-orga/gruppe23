/*Dieses Programm muss an den Panel geknüpft werden, in welchem sich das Spielfeld befindet.
 *Das Panel muss Panel.addKeyListener(this) bekommen. Als Ausgabe bekommt man in a[] die
 *Koordinaten, welche aus der gedrückten Taste folgen (z.B.: für VK_UP wird (0,1) für (x,y) geliefert).*/






import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Spielfigur implements KeyListener{
	private Frame Fenster; //Zum Testen der Klasse benötigte Variablen
	private TextField tf;	//(müssen sonst ignoriert werden)
	
	private int[] a = new int[2];	//Schnittstelle für KeyListener (hier werden die Koordinaten gespeichert)
	
	public Spielfigur(){//von hier ...
		Fenster = new  Frame("KeyListenerTest");
		tf = new TextField("Label");
	}
	
	public void KeyStarter(){
		Label label = new Label("Pfeiltaste drücken");
		
        Fenster.add(label, BorderLayout.NORTH);
        Fenster.add(tf, BorderLayout.SOUTH);
        
        // Hinzufügen des Listeners
        tf.addKeyListener(this);
        tf.requestFocus();
        
        Fenster.setSize(300, 200);
        Fenster.setVisible(true);
	}										//bis hier dienen die Variablen und Methoden lediglich zum Test
	
	

	
	@Override
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
		tf.setText(a[0] +" , " + a[1]);			//An dieser Stelle muss die Print-Funktion mit Übergabe des a[] gesetzt werden.
	}											//Zudem muss der KeyListener für die Bombe-Taste noch eine Auswirkung bekommen.
	
	
	@Override
	public void keyTyped(KeyEvent Key){}
	public void keyReleased(KeyEvent Key) {}
	
	public static void main (String[]args){
		Spielfigur keyTest = new Spielfigur();
        keyTest.KeyStarter();
	}
	
}
