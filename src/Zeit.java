import java.util.Timer;
import java.util.TimerTask;

public class Zeit {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Timer leuft;

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
			// explosion
			leuft.cancel(); // timer wird terminiert
		}
	}

}