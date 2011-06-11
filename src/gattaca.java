import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 
 * Gattaca
 * 
 * Reads in the file to be tested. The DNA sequence provided is irrelevant.
 * Simply look for the predictions using a regular expression.
 * Once we have a list of predictions then test which ones don't
 * overlap and which groups of predictions give the best score
 * 
 * @author Dean Williams
 */
public class gattaca {
	
	// Store the read in predictions
	private LinkedList<Prediction> predictions = new LinkedList<Prediction>();
	
	/**
	 * Main entry point
	 * 
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
			while (scanner.hasNext()) {
				String line = scanner.nextLine();
				if (line.matches("\\d+\\s+\\d+\\s+\\d+\\s*")) {
					// We have a prediction
					Scanner s2 = new Scanner(line);
					int col = 0;
					int start = 0;
					int end = 0;
					int score = 0;
					while (s2.hasNext()) {
						if (col == 0)
							start = s2.nextInt();
						else if (col == 1)
							end = s2.nextInt();
						else if (col == 2)
							score = s2.nextInt();
						col++;
						if (col == 3) {
							col = 0;
							Prediction i = new Prediction(start, end, score);
							predictions.add(i);
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Could not find file");
		}
	}
	
	/**
	 * Do the intervals of a & b overlap?
	 * 
	 * @param a
	 * @param b
	 * @return boolean
	 */
	private boolean intervalsOverlap(Prediction a, Prediction b) {
		if (a.end < b.start)
	        return false;
		else if (b.end < a.start)
	        return false;
	    else
	        return true;
	}
	
	/**
	 * Get Best DNA Sequence Score
	 * 
	 * @return int
	 */
	public int getBestDNAScore() {
		int bestScore = 0;
		int score = 0;
		// Loop through the predictions we have found starting with a different one each time
		for (Prediction start : predictions) {
			score = findScore(start, predictions);
			if (score > bestScore)
				bestScore = score;
		}
		return bestScore;
	}
	
	/**
	 * Calculate the score
	 * 
	 * @param start
	 * @param allowed
	 * @return
	 */
	private int findScore(Prediction start, LinkedList<Prediction> allowed) {
		// Create a list to store predictions whose ranges don't overlap
		LinkedList<Prediction> nonOverlappingIntervals = new LinkedList<Prediction>();
		// ... and add the prediction we are starting with
		nonOverlappingIntervals.add(start);
		// Now loop through all the predictions
		for (Prediction i : allowed) {
			// ... and if the one we are testing does not overlap with the starting prediction...
			if (!intervalsOverlap(start, i)) {
				// ... then check if it overlaps with any intervals we have already tested
				boolean exists = false;
				for (Prediction n : nonOverlappingIntervals) {
					if (intervalsOverlap(i, n)) {
						exists = true;
						break;
					}
				}
				// ... and if it doesn't then add it to the list
				if (!exists) {
					nonOverlappingIntervals.add(i);
				}
			}
		}
		// Now score the intervals we have
		int score = 0;
		for (Prediction i : nonOverlappingIntervals) {
			score += i.score;
		}
		return score;
	}
	
	/**
	 * Struct like class to store the predictions indexes and score
	 * 
	 * @author Dean Williams
	 *
	 */
	private class Prediction {
		
		public int start;
		public int end;
		public int score;
		
		public Prediction(int start, int end, int score) {
			this.start = start;
			this.end = end;
			this.score = score;
		}
		
	}
}
