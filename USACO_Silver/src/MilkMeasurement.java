import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class MilkMeasurement {

	public static void main(String[] args) {
		Scanner file;
		try {
			String filename = "src/5.in";
			file = new Scanner(new File(filename));
			int logNum = file.nextInt();
			int G = file.nextInt();

			ArrayList<Log> logs = new ArrayList<Log>(logNum);
			for (int i = 0; i < logNum; i++) {
				int day = file.nextInt();
				int cow = file.nextInt();
				String changeString = file.next();
				int change = 0;
				if (changeString.charAt(0) == '+') {
					change = Integer.parseInt(changeString.substring(1));
				} else {
					change = -1 * Integer.parseInt(changeString.substring(1));
				}
//				System.out.println(day+" "+cow+ " "+change);
				logs.add(new Log(day, cow, change));
			}
			Collections.sort(logs, new Comparator<Log>() {
				@Override
				public int compare(Log o1, Log o2) {
					if (o1.getDay() < o2.getDay())
						return -1;
					else if (o1.getDay() > o2.getDay())
						return 1;
					else
						return 0;
				}
			});
			for (int i = 0; i < logs.size(); i++) {
				System.out.println(i + " " + logs.get(i));
			}
			System.out.println();

			ArrayList<Cow> below = new ArrayList<Cow>(logNum);
			ArrayList<Cow> above = new ArrayList<Cow>(logNum);

			ArrayList<Cow> prevTop = new ArrayList<Cow>(logNum);
			ArrayList<Cow> top = new ArrayList<Cow>(logNum);
			int count = 0;

			for (int i = 0; i < logNum; i++) {

				int cow = logs.get(i).getCow();
				int change = logs.get(i).getChange();

				if (containsCow(below, cow)) {
					int index = indexOfCow(below, cow);
					int newVal = below.get(index).getValue() + change;
					if (newVal < G) {
						below.get(index).setValue(newVal);
						System.out.println(i + " below to below");
					} else if (newVal > G) {
						below.remove(index);

						above.add(new Cow(cow, newVal));

						System.out.println(i + " below to above");
					} else {
						below.remove(index);

						System.out.println(i + " below to G");
					}
				} else if (containsCow(above, cow)) {
					int index = indexOfCow(above, cow);
					int newVal = above.get(index).getValue() + change;
					if (newVal < G) {
						above.remove(index);

						below.add(new Cow(cow, newVal));
						// change max

						System.out.println(i + " above to below");
					} else if (newVal > G) {
						above.get(index).setValue(newVal);
						System.out.println(i + " above to above");
					} else {
						above.remove(index);

						System.out.println(i + " above to G");
					}
				} else {
					int newVal = G + change;
					if (change > 0) {
						above.add(new Cow(cow, newVal));

						System.out.println(i + " G to above");
					} else {
						below.add(new Cow(cow, newVal));

						System.out.println(i + " G to below");
					}
				}

				boolean topChanged = true;

				int max = G;
				for (Cow temp : above) {
					if (temp.getValue() > max)
						max = temp.getValue();
				}
				for (Cow temp : above) {
					if (temp.getValue() == max) {
						top.add(temp);
					}
				}

				if (prevTop.size() == top.size()) {
					int sameCount = 0;
					for (Cow temp : prevTop) {
						if (containsCow(top, temp.getNum())) {
							sameCount++;
						}
					}
					if (sameCount == prevTop.size()) {
						topChanged = false;
					} else {
						System.out.println("  SAME SIZE  ");
						System.out.print("   ");
						for (Cow temp : prevTop) {
							System.out.print(temp + " ");
						}
						System.out.print("\n   ");
						for (Cow temp : top) {
							System.out.print(temp + " ");
						}
						System.out.println();
					}
				} else {
					topChanged = true;
					System.out.println("  DIF SIZE  ");
					System.out.print("   ");
					for (Cow temp : prevTop) {
						System.out.print(temp + " ");
					}
					System.out.print("\n   ");
					for (Cow temp : top) {
						System.out.print(temp + " ");
					}
					System.out.println();
				}
				prevTop.clear();
				for (Cow temp : top) {
					prevTop.add(temp);
				}
				top.clear();
				if (topChanged) {
					System.out.println(i + " *****TOP CHANGED*****");
					count++;
				}
			}
			System.out.println("ANSWER: " + count);

		} catch (FileNotFoundException e) {
			System.out.println("fileNotFound");
		}

	}

	public static boolean containsCow(ArrayList<Cow> cows, int num) {
		for (Cow cow : cows) {
			if (cow.getNum() == num)
				return true;
		}
		return false;
	}

	public static int indexOfCow(ArrayList<Cow> cows, int num) {
		for (int i = 0; i < cows.size(); i++) {
			if (cows.get(i).getNum() == num)
				return i;
		}
		return -1;
	}

}
