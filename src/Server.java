import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

/**
 * Beinhaltet Methoden zum Starten & Warten des Servers
 * @author Kolja Salewski
 */
public class Server extends Thread {
	Socket clientSocket = null;
	ServerSocket serverSocket = null;
	PrintWriter out;
	BufferedReader in;
	int x, y, level, meldungen_zaehler;
	String in_string, out_string, antwort = "leer", schwierigkeitsgrad = "";
	boolean verbunden = false, anfrage_erhalten = false;
	
	/* METHODEN: */
	
	// starten-Methode:
	/**
	 * Startet den Server, erstellt Sockets sowie Writer fuer Aus- und Reader
	 * fuer Eingabe. Falls sich ein Client verbunden hat, wird das Spiel neu-
	 * gestartet und begonnen auf Client-Nachrichten zu warten.
	 */
	void starten() {
		try {
			// Sockets erstellen:
			serverSocket = new ServerSocket(4711);
			
			// Ausgabe:
			Menue.meldungen[0].setText("Ihre IP-Adresse(n):");
	        String localHost = InetAddress.getLocalHost().getHostName();
	        meldungen_zaehler = 1;
	        for (InetAddress ia : InetAddress.getAllByName(localHost)) {
	        	if (ia.getHostAddress().contains(".")) {
	        		if (meldungen_zaehler == 4) {
	        			meldungen_zaehler = 1;
	        		}
	        		
        			Menue.meldungen[meldungen_zaehler].setText(ia.getHostAddress());
        			meldungen_zaehler++;	        		 
	        	}
	    	}
	        Menue.meldungen[4].setText("Es wird auf einen Client gewartet...");
			
			clientSocket = serverSocket.accept();
			
			// Verbindungsstatus aktualisieren:
			verbunden = true;
			Menue.twoPlayer = true;
			Menue.hotSeat = false;
			for (int nr = 0; nr < 5; nr++) {
				Menue.meldungen[nr].setText("");
			}

			// Writer fuer Aus- & Read fuer Eingabe erstellen
			out = new PrintWriter(clientSocket.getOutputStream(), true);
		    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		    
		    // Client die aktuelle Levelnr. mitteilen:
		    out.println("level");
		    out.println(MapLoader.get_level());
		    
		    // Client den aktuellen Schwierigkeitsgrad mitteilen:
		    out.println("schwierigkeitsgrad");
		    
			switch (Menue.zeit) {
				case 0: // Block/Bomben-Item
					out.println("Anf�nger");
					break;
	
				case 180: // Hulk
					out.println("Leicht");
					break;
	
				case 90: // Weg
					out.println("Mittel");
					break;
	
				case 45: // Block
					out.println("Schwer");
					break;
			}
		    
			// Spiel neustarten
			Menue.spiel_neustarten();
			
			// Beginnen auf Client-Nachrichten zu warten
			warte();
		}
		
		catch (IOException e) {
			for (int nr = 0; nr < 5; nr++) {
				Menue.meldungen[nr].setText("");
			}
		}

	}
	
	// warte-Methode:
	/**
	 * Wartet dauerhaft auf Nachrichten vom Client, nimmt diese entgegen und
	 * fuehrt die entsprechenden Aktionen mit der zweiten Spielfigur durch
	 */
	void warte() {
		try {
			while (System.in.available()==0 && !isInterrupted())
			{ 
				// Nehme Client-Nachrichten entgegen
				in_string = in.readLine();
				
				/*
				 * Fuehre je nach Nachrichteninhalt verschiedene Aktionen durch:
				 */

				// Mit 2. Spielfigur Bombe legen:
				if (in_string.equals("bomb")) {
					Menue.spieler2_bombe();
				}
				
				// Frage ausgeben:
				else if (in_string.contains("?")) {
					
					int frage = JOptionPane.showConfirmDialog(null,
							in_string,
							"Frage des Clients", JOptionPane.YES_NO_OPTION);
					
					switch (frage) {
						case 0:
							if (in_string.contains("Spieler")) {
								out.println("yes");	
							}
							
							else {
								out.println("Spieler 1 moechte das Spiel neustarten. Soll das Spiel neugestartet werden?");
								//Menue.antwort_erhalten = true;
								antwort = "rueckfrage";
//								Menue.createAndShowGui(
//								"Spieler 2 wurde eine Anfrage zum Neustart des Spiels geschickt. Warte ",
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
				
				// 2. Spielfigur bewegen:
				else {														// ...sonst...
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
					
					Menue.spieler2_aktionen(x, y);							// ...fuehre die Bewegungen
																			// mit der 2. Spielfigur
																			// durch
				}
				
			}
			
		}
		
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Verbindung zum Client getrennt");
			if (Menue.hotSeat == false) {
				Menue.singleplayer_starten();	
			}
			
		}
		
		catch (NullPointerException ex) {
			JOptionPane.showMessageDialog(null, "Verbindung zum Client getrennt");
			if (Menue.hotSeat == false) {
				Menue.singleplayer_starten();	
			}
			
		}
		
	}
	
	// run-Methode:
	/**
	 * Laesst den Server starten
	 */
	@Override
	public void run() {
		starten();
	}
	
}
