/**
	 * Objekt zur Verwaltung der Soundeffekte
	 * spielt alle Sounds ueber Methoden der StdAudio-Bibliothek ab
	 */

public class Sound {
	
	public Sound(){}	
	public void playItem(){ 
		StdAudio.play("src/media/Item.wav");
	}
	
	public void playExplosion(){
		StdAudio.play("src/media/explosion.wav");
	}
	
	public void playTheme(){
//		StdAudio.loop("src/media/theme.wav");
	}
	
	public void playZiel(){
		StdAudio.play("src/media/ziel.wav");
	}
	
	public void playTod(){
		StdAudio.play("src/media/tod.wav");
	}
}
