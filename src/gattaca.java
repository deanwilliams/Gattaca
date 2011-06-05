import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class gattaca {
	
	private LinkedList<Interval> predictions = new LinkedList<Interval>();
	
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
							Interval i = new Interval(start, end, score);
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
	 * Get Best DNA Sequence Score
	 * 
	 * @return int
	 */
	public int getBestDNAScore() {
		return 0;
	}
	
	private class Interval {
		
		public int start;
		public int end;
		public int score;
		
		public Interval(int start, int end, int score) {
			this.start = start;
			this.end = end;
			this.score = score;
		}
	}
}
