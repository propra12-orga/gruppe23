import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

/**
 * Beinhaltet Methoden zum Starten & Warten des Clients
 * @author Kolja Salewski
 */
public class Client extends Thread {
	Socket socket = null;
	PrintWriter out;
	BufferedReader in;
	int x, y, level;
	String in_string, out_string, antwort = "leer", schwierigkeitsgrad = "";
	boolean anfrage_erhalten = false, verbunden = false;
	
	/* METHODEN: */
	
	// starten-Methode:
	/**
	 * Startet den Client, fragt Benutzer nach Server-IP, erstellt Socket sowie
	 * Writer fuer Aus- und Reader fuer Eingabe
	 */
	int starten() {		
		// Eingabe der IP-Adresse:
		String server_ip = JOptionPane.showInputDialog(null, "Bitte geben Sie " +
							"die IP-Adresse des Servers ein: ", "Server-IP",
							JOptionPane.PLAIN_MESSAGE);
		
		// Eingabeaufforderung schlieﬂen beim Klick auf "Abbrechen":
		if (server_ip == null) {
			return 0;
		}

		try {
			// Ausgabe:
			Menue.meldungen[4].setText("Es wird versucht sich mit Server zu verbinden...");
			
			// Socket erstellen:
			socket = new Socket(server_ip,4711);
			
			// Verbindungsstatus aktualisieren:
			verbunden = true;
			Menue.meldungen[4].setText("");
			Menue.twoPlayer = true;
			Menue.hotSeat = false;
			
			// Writer fuer Aus- & Read fuer Eingabe erstellen
			out = new PrintWriter(socket.getOutputStream(), true);
		    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		    // Spiel neustarten
			Menue.spiel_neustarten();
			
			// Beginnen auf Server-Nachrichten zu warten
			warte();
			
			// Aus Funktion zurueckkehren, wenn die warte-Funktion abgebrochen wurde:
			return 1;
		}
		
		catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, "Server unter der angegebenen IP-Adresse nicht erreichbar.");
			Menue.meldungen[4].setText("");
			return 0;
		}
		
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Server unter der angegebenen IP-Adresse nicht erreichbar.");
			Menue.meldungen[4].setText("");
			return 0;
		}			
		
	}
	
	// warte-Methode:
	/**
	 * Wartet dauerhaft auf Nachrichten vom Server, nimmt diese entgegen und
	 * fuehrt die entsprechenden Aktionen mit der ersten Spielfigur durch
	 */
	void warte() {
		try {
			while (System.in.available()==0 && !isInterrupted())
			{
				// Nehme Server-Nachrichten entgegen
				in_string = in.readLine();
				
				/*
				 * Fuehre je nach Nachrichteninhalt verschiedene Aktionen durch:
				 */
				
				// Mit 1. Spielfigur Bombe legen:
				if (in_string.equals("bomb")) {
					Menue.spieler1_bombe();
				}
				
				// Frage ausgeben:
				else if (in_string.contains("?")) {
					
					int frage = JOptionPane.showConfirmDialog(null,
							in_string,
							"Frage des Servers", JOptionPane.YES_NO_OPTION);
					switch (frage) {
						case 0:
							if (in_string.contains("Spieler")) {
								out.println("yes");	
							}
							
							else {
								out.println("Spieler 2 moechte das Spiel neustarten. Soll das Spiel neugestartet werden?");
								//Menue.antwort_erhalten = true;
								antwort = "rueckfrage";
//								Menue.createAndShowGui(
//								"Spieler 1 wurde eine Anfrage zum Neustart des Spiels geschickt. Warte ",
//								" auf Antwort...", 60, 600, 100, 0, "", "neustart");
							}

							//Menue.createAndShowGui("Das Spiel wird in ", " neugestartet...", 5, 300, 100, 0); // BITTE AUSKOMMENTIERT LASSEN & NICHT LOESCHEN
							break;
						case 1:
							out.println("no");
							//Menue.createAndShowGui("Das Spiel wird in ", " fortgesetzt...", 5, 300, 100, 0); // BITTE AUSKOMMENTIERT LASSEN & NICHT LOESCHEN
							break;
					}
					
				}
				
				// Antwort speichern:
				else if (in_string.equals("yes") || in_string.equals("no")) {
					antwort = in_string;
				}
				
//				// Abfrage zum Neustart des Spiels ausgeben:
//				else if (in_string.equals("abfrage_neustarten")) {
//					anfrage_erhalten = true;
//					Menue.abfrage_neustarten();
//					anfrage_erhalten = false;
//				}
				
				// Spiel neustarten:
				else if (in_string.equals("neustart")) {
					antwort = "leer";
					Menue.antwort_erhalten = true;
					
					//Menue.createAndShowGui("Das Spiel wird in ", " neugestartet...", 5, 300, 100, 0);
					Menue.spiel_neustarten();
				}
				
				// Level wechseln:
				else if (in_string.equals("level")) {
					level = Integer.parseInt(in.readLine());
					antwort = "leer";
					
					//Menue.createAndShowGui("Es wird in ", " zu Level " + level + " gewechselt...", 5, 400, 100, level); // BITTE AUSKOMMENTIERT LASSEN
					MapLoader.set_level(level);
					Menue.spiel_neustarten();
				}
				
				// Schwierigkeitsgrad wechseln:
				else if (in_string.equals("schwierigkeitsgrad")) {
					schwierigkeitsgrad = in.readLine();
					antwort = "leer";
					Menue.antwort_erhalten = true;
					
					Menue.schwierigkeitsgrad_aendern(schwierigkeitsgrad);
					Menue.spiel_neustarten();
				}
				
				// 1. Spielfigur bewegen:
				else {
					x = Integer.parseInt(in_string);						// ...interpretiere
																			// den int-Wert der
																			// Nachricht als x-
																			// Bewegung,...
					in_string = in.readLine();								// ...lese die
																			// naechste
																			// Nachricht,...
					
					y = Integer.parseInt(in_string);						// ...interpretiere
																			// den int-Wert der
																			// zweiten Nachricht
																			// als y-Bewegung und...
					
					Menue.spieler1_aktionen2(x, y);							// ...fuehre die Bewegungen
																			// mit der 1. Spielfigur
																			// durch
				}
				
//				try {
//					Thread.sleep(1);
//				}
//				
//				catch (InterruptedException e )
//			    {
//					Menue.set_clientThread(null);
//					JOptionPane.showMessageDialog(null, "Verbindung zum Server getrennt");
//					Menue.singleplayer_starten();
//					interrupt();
//			    }
				
			}
			
		}
		
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Verbindung zum Server getrennt");
			if (Menue.hotSeat == false) {
				Menue.singleplayer_starten();	
			}
			
		}
		
		catch (NullPointerException ex) {
			JOptionPane.showMessageDialog(null, "Verbindung zum Server getrennt");
			if (Menue.hotSeat == false) {
				Menue.singleplayer_starten();	
			}
			
		}
		
	}

	// run-Methode:
	/**
	 * Laesst den Client starten
	 */
	@Override
	public void run() {
		starten();
		
		/*
		 * Client-Thread beenden, wenn warte-Funktion abgebrochen & start-
		 * Funktion beendet wurde:
		 */
		Menue.set_clientThread(null);
		if (Menue.hotSeat) {
			Menue.mntmHotSeat.setSelected(true);			
		}
		
		else {
			Menue.mntmSingleplayer.setSelected(true);
		}
		
		interrupt();
	}
	
}
