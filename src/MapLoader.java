import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * @author Tobias Korfmacher
 * 
 */
public class MapLoader {
	public static boolean twoPlayerSet;
	static int n = 13;
	static int level = 1;

	/**
	 * 
	 * @param i
	 *            legt das zu ladene Level fest
	 * @return map-Objekt
	 */
	public static int[][] laden(int i) {
		twoPlayerSet = Menue.getMultiplayer();

		int c = 0;

		int k = 0, l = 0;
		int[][] map = new int[n][n];
		String filename = "src/Maps/Level-" + i + ".txt";

		try {
			FileReader f = new FileReader(filename);

			System.out.println("Spielfeld eingelesen:"); // Test

			while ((c = f.read()) != -1) {
				if (Character.getNumericValue(c) != -1) {
					map[k][l] = Character.getNumericValue(c);

					System.out.print(map[k][l] + ", "); // Test

					if (k < n - 1) {
						k++;
					}

					else if (l < n - 1) {
						System.out.println(); // Test
						k = 0;
						l++;
					}

				}

			}

			System.out.println(); // Test

			if (twoPlayerSet) {
				map[n - 2][n - 2] = 10;
				map[1][n - 2] = 2;
			}

			System.out.println(); // Test

			f.close();

		}

		catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}

		return map;

	}

	// get_n-Methode:
	public static int get_n() {
		return n;
	}

	// get_level-Methode:
	public static int get_level() {
		return level;
	}

	// set_level-Methode:
	public static void set_level(int level) {
		MapLoader.level = level;
	} // level speichern

	public static void level_speichern(int[][] map, String levelnummer) {

		String[] line = new String[n];
		String path = "src/Maps/" + levelnummer + ".txt";
		for (int i = 0; i < n; i++) {
			line[i] = "";
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {

				line[i] += map[i][j];
			}
		}
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(path));
			for (int i = 0; i < n; i++) {
				out.write(line[i]);
				out.newLine();
			}

		} catch (IOException e) {
			System.out.println(e);
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (Exception ex) {
			}
		}
	}
}