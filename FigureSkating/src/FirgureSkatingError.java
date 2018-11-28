import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FirgureSkatingError {

	public static void main(String[] args) {

		List<Double> scores = new ArrayList<Double>();
		Scanner input = new Scanner(System.in);
		double value = 0;
		double total = 0;
		double average = 0.0;

		// Prompts user for a number between 0.5 and 10
		// Only accepts valid scores
		do {
			System.out.print("Enter a skating score (0.5 - 10) or 0 to quit: ");
			value = input.nextDouble();

			if (value != 0 && (value < 0.5 || value > 10))
				System.out.print("Invalid Score.  ");
			else if (value != 0)
				scores.add(value);
		} while (value != 0);

		input.close();

		/*
		 * 
		 * ERROR IS SOMEWHERE BELOW!!!!
		 * 
		 */

		// Prints the list of all the scores
		System.out.println(scores);

		// Eliminate the highest and lowest score
		double highScore = scores.get(0);
		double lowScore = scores.get(0);
		int indexHigh = 0;
		int indexLow = 0;

		for (int i = 0; i < scores.size(); i++) {
			if (scores.get(i) > highScore) {
				highScore = scores.get(i);
				indexHigh = i;
			}

			if (scores.get(i) < lowScore) {
				lowScore = scores.get(i);
				indexLow = i;
			}
		}

		System.out.println("Max: " + highScore + "  Min: " + lowScore);
		if (indexHigh > indexLow) {
			scores.remove(indexHigh);
			scores.remove(indexLow);
		} else {
			scores.remove(indexLow);
			scores.remove(indexHigh);
		}

		// Print out new list with highest and lowest scores removed
		System.out.println(scores);

		// Find the average of the remaining scores
		for (double score : scores)
			total += score;
		average = (double) total / scores.size();

		System.out.println("Score average: " + average);

	}

}
