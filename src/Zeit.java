import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Zeit extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Timer leuft;
	{

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack(); // wird zum zeichnen benotigt
		setVisible(true);
		setBounds(100, 100, 475, 309);
	}

	public static void main(String args[]) {
		new Zeit(0); // zeit bis zur explosion
		System.out.println("Bombe gelegt"); // testen
	}

	public Zeit(int x) { // construktor erstellen
		leuft = new Timer();
		leuft.schedule(new explosion(), x); // verzogert diezeit bis zur
											// explosion
	}

	public class explosion extends TimerTask {
		public void run() {
			System.out.print("Bombe explodiert"); // wenn die zeit ableuft
			add(new JLabel(new ImageIcon("src/Pics/EXP.png")));// explosion
			leuft.cancel(); // timer wird terminiert
		}
	}

}