import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class HaybaleFeast {

	public static void main(String[] args) {
		Scanner file;
		try {
			String filename = "src/1.in";
			file = new Scanner(new File(filename));
			int N = file.nextInt();
			int M = file.nextInt();

			int treeSize = 1;
			int tempNum = N;
			while (tempNum > 1) {
				tempNum /= 2;
				treeSize++;
			}
			System.out.println("treeSize: "+treeSize);
			ArrayList<ArrayList<Haybale>> tree = new ArrayList<ArrayList<Haybale>>(treeSize);

			// initialize ArrayLists
			tree.add(new ArrayList<Haybale>(N));
			for (int i = 1; i < treeSize; i++) {
				tree.add(new ArrayList<Haybale>(N / (i * 2)));
			}

			// add inputs to first layer of tree
			for (int i = 0; i < N; i++) {
				int f = file.nextInt();
				int s = file.nextInt();
				tree.get(0).add(new Haybale(f, s));

			}

			for (int i = 1; i < treeSize; i++) {
				int numOfNodes = (int) (N / (Math.pow(2, i)));
				System.out.println(numOfNodes);
				for (int k = 0; k < numOfNodes; k++) {
					// get 2 corresponding Haybales in the previous layer
					Haybale temp1 = tree.get(i - 1).get(k * 2);
					Haybale temp2 = tree.get(i - 1).get(k * 2 + 1);
					// get total flavor
					int totalFlavor = temp1.getFlavor() + temp2.getFlavor();
					// get max spice
					int maxSpice = Math.max(temp1.getSpice(), temp2.getSpice());

					tree.get(i).add(new Haybale(totalFlavor, maxSpice));
					//System.out.print(tree.get(i).get(tree.get(i).size() - 1));
				}
				//System.out.println();
			}
			for (int i = 0; i < treeSize; i++) {
				for (int k = 0; k < tree.get(i).size(); k++) {
					System.out.print(tree.get(i).get(k));
				}
				System.out.println();
			}

			for (int i = 0; i < N; i++) {
				int totalFlavor = 0;
				int maxSpice = 0;
				for (int k = i; k < N; k++) {
					
					
					if (k % 2 == 1) { // if odd
						Haybale temp = tree.get(0).get(k);
						totalFlavor += temp.getFlavor();
						maxSpice = Math.max(maxSpice, temp.getSpice());
						
						System.out.println(i+" "+k+" 0");
					} else { // even
						int level = 1; // what level to get the Haybale from
						int nodeSize = 2;
						//find what level to get the Haybale from
						for (int j = 2; j < treeSize; j++) {
							nodeSize = (int) Math.pow(j, 2);
							// if k is at the node size and the nodeSize + k does not extend past N
							if (k % nodeSize == 0 && k + nodeSize < N) { 
								level = j;
							} else {
								break;
							}
						}
						System.out.println(i+" "+k+" "+level);
						
					}
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("fileNotFound");
		}
	}

}
