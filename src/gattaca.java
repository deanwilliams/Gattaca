import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class gattaca {

	private final int width = 80;
	private int predictions[][];
	private String dnaSequence;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Missing argument");
			System.exit(1);
		}
		gattaca g = new gattaca(args[0]);
		int bestScore = g.getBestDNAScore();
		System.out.println(bestScore);
	}

	/**
	 * Constructor
	 * 
	 * @param fileName
	 */
	public gattaca(String fileName) {
		try {
			File file = new File(fileName);
			Scanner scanner = new Scanner(file);
			int i = 0;
			int lines = 0;
			int length = 0;
			int predictionLineCounter = 0;
			int predictionColCounter = 0;
			StringBuilder sb = new StringBuilder();
			while (scanner.hasNext()) {
				if (i == 0) {
					// First line = length of DNA sequence
					length = scanner.nextInt();
					if (length <= width)
						lines = 0;
					else if (length % width == 0)
						lines = length / width;
					else
						lines = (length / width) + 1;
				} else if (i <= lines) { 
					sb.append(scanner.next());
				} else if (i == (lines + 1)) {
					predictions = new int[scanner.nextInt()][3];
				} else {
					int z = scanner.nextInt();
					predictions[predictionLineCounter][predictionColCounter] = z;
					predictionColCounter++;
					if (predictionColCounter == 3) {
						predictionColCounter = 0;
						predictionLineCounter++;
					}
				}
				i++;
			}
			dnaSequence = sb.toString();
		} catch (FileNotFoundException e) {
			System.out.println("Could not find file");
		}
	}
	
	/**
	 * Get Best DNA Sequence Score
	 * 
	 * @return int
	 */
	public int getBestDNAScore() {
		return 0;
	}
}
