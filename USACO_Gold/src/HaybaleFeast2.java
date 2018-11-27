import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class HaybaleFeast2 {

	public static void main(String[] args) {
		Scanner file;
		try {
			String filename = "src/10.in";
			file = new Scanner(new File(filename));
			int N = file.nextInt();
			long M = file.nextLong();

			Haybale[] haybales = new Haybale[N];

			// add inputs to list
			for (int i = 0; i < N; i++) {
				int f = file.nextInt();
				int s = file.nextInt();
				haybales[i] = (new Haybale(f, s));

			}
			int minSpice = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				long totalFlavor = 0;
				int maxSpice = 0;
				for (int k = i; k < N; k++) {
					totalFlavor += haybales[k].getFlavor();
					maxSpice = Math.max(maxSpice, haybales[k].getSpice());
					if(totalFlavor >= M && maxSpice < minSpice) {
						minSpice = maxSpice;
					}
				}
			}
			System.out.println(minSpice);

		} catch (FileNotFoundException e) {
			System.out.println("fileNotFound");
		}
	}

}
