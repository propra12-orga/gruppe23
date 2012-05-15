public class Block extends Feld {
	/*
	 * statischer Block, wichtig für Kollisionsabfrage this.wert = 2 => kein
	 * durchkommen
	 */
	public Block() {
		this.wert = 2;
	}
}
