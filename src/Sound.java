/**
	 * Objekt zur Verwaltung der Soundeffekte
	 * spielt alle Sounds ueber Methoden der StdAudio-Bibliothek ab
	 */

public class Sound {
	
	public Sound(){}	
	public void playItem(){ 
		if(Menue.ton.isSelected())
			StdAudio.play("src/media/Item.wav");		//selfmade
	}
	
	public void playExplosion(){
		if(Menue.ton.isSelected())
		StdAudio.play("src/media/explosion.wav");	//
	}
	
//	public void playTheme(){
////		StdAudio.loop("src/media/theme.wav");
//	}
//	
	public void playZiel(){
		if(Menue.ton.isSelected())
		StdAudio.play("src/media/ziel.wav");	//selfmade
	}
	
	public void playTod(){
		if(Menue.ton.isSelected())
		StdAudio.play("src/media/tod.wav");		//selfmade
	}
}
