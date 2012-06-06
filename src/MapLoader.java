import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * @author Tobias Korfmacher
 *
 */
public class MapLoader {
	public static boolean twoPlayerSet;
	/**
	 * 
	 * @param i legt das zu ladene Level fest
	 * @return map-Objekt
	 */
	public static int[][] laden(int i) {
		twoPlayerSet = Menue.getMultiplayer();
		
		int c = 0;
		
		int n = 11;
		int k = 0, l = 0;
		int[][] map = new int[n][n];
		String filename = "src/Maps/Level-" + i + ".txt";
		
		try {			
			FileReader f = new FileReader(filename);
			
			System.out.println("Spielfeld eingelesen:");	// Test
			
			while ((c = f.read()) != -1) {
				if (Character.getNumericValue(c) != -1) {
					map[k][l] = Character.getNumericValue(c);
					
					System.out.print(map[k][l] + ", ");	// Test
					
					if (k < 10) {
						k++;
					}
					
					else if (l < 10) {
						System.out.println();	// Test
						k = 0;
						l++;
					}
					
				}
				
			}
			if(twoPlayerSet){
				map[9][9] = 10;
				System.out.println();
				System.out.println();
				System.out.println("Multiplayer on");
			}
			
			System.out.println();	// Test
			System.out.println();	// Test
			
			f.close();

		} 
		
		catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
		
		return map;

	}
}