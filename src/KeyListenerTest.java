
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyListenerTest implements KeyListener {
    private Frame f;
    private TextField tf;
    
    public KeyListenerTest() {
        f = new Frame("Key-Listener-Test");
        tf = new TextField("Label");
    }
    
    public void launchFrame() {
        Label label = new Label("Drücken Sie eine Taste "
                                + "der Tastatur");
        
        // Hinzufügen der Komponenten zum Frame
        f.add(label, BorderLayout.NORTH);
        f.add(tf, BorderLayout.SOUTH);
        
        // Hinzufügen des Listeners
        tf.addKeyListener(this);
        tf.requestFocus();
        
        f.setSize(300, 200);
        f.setVisible(true);
    }
    
    // Implementieren der Methoden des Interfaces KeyListener
    public void keyTyped(KeyEvent e) {}
    
    public void keyPressed(KeyEvent e) {
        String s = "Taste gedrückt: Key = " + e.getKeyChar()
            + " Code = " + e.getKeyCode();
        
        tf.setText(s);
    }

    public void keyReleased(KeyEvent e) {}

    public static void main(String args[]) {
        KeyListenerTest keyTest = new KeyListenerTest();
        keyTest.launchFrame();
    }
}