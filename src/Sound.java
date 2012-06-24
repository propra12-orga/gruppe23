import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


public class Sound {
	public static URL exp = null;
	public String filename;
	
	public Sound(String file){filename = file;}
	
	public void play(){
		try{
			File expF = new File(filename);
			if (expF.canRead()) exp = expF.toURI().toURL();
			System.out.println("Soundfile im Arsch");
		}
		catch (MalformedURLException e) { e.printStackTrace(); }
        // URL url = StdAudio.class.getResource(filename);
        if (exp == null) throw new RuntimeException("Es konnte explosion.wav nicht gefunden werden!");
        AudioClip explosion = Applet.newAudioClip(exp);
        explosion.play();
	}
}
