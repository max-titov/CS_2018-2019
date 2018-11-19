import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class MilkMeasurement {

	public static void main(String[] args) {
		Scanner file;
		try
		{
			String filename = "src/2.in";
			file = new Scanner (new File(filename));
			int logNum = file.nextInt();
			int G = file.nextInt();
			
			ArrayList<Log> logs = new ArrayList<Log>(logNum);
			for(int i = 0; i < logNum; i++) {
				int day = file.nextInt();
				int cow = file.nextInt();
				String changeString = file.next();
				int change = 0;
				if(changeString.charAt(0) == '+') {
					change = Integer.parseInt(changeString.substring(1));
				}
				else {
					change = -1 * Integer.parseInt(changeString.substring(1));
				}
//				System.out.println(day+" "+cow+ " "+change);
				logs.add(new Log(day, cow, change));
			}
			Collections.sort(logs, new Comparator<Log>() {
			    @Override
			    public int compare(Log o1, Log o2) {
					if(o1.getDay() < o2.getDay()) return -1;
					else if(o1.getDay() > o2.getDay()) return 1;
					else return 0;
			    }
			});
			for(Log log: logs) {
				System.out.println(log);
			}
			System.out.println();
			
			ArrayList<Integer> belowNum = new ArrayList<Integer>(logNum);
			ArrayList<Integer> belowVal = new ArrayList<Integer>(logNum);
			int max = G;
			
			ArrayList<Integer> aboveNum = new ArrayList<Integer>(logNum);
			ArrayList<Integer> aboveVal = new ArrayList<Integer>(logNum);
			
			int count = 0;

			for(int i = 0; i < logNum; i++) {
				boolean topChanged = false;
				
				int cow = logs.get(i).getCow();
				int change = logs.get(i).getChange();
				if(belowNum.contains(cow)) {
					int index = belowNum.indexOf(cow);
					int newVal = belowVal.get(index) + change;
					if(newVal < G) { //keep in below
						belowVal.set(index, newVal);
					}
					else if (newVal > G) { //move to above
						belowNum.remove(index);
						belowVal.remove(index);
						
						aboveNum.add(cow);
						aboveVal.add(newVal);
						
						if(newVal > max) {
							max = newVal;
							topChanged = true;
						}
					}
					else { //remove since it is equal to G
						belowNum.remove(index);
						belowVal.remove(index);
						
						if(aboveNum.size() == 0) {
							topChanged = true;
						}
					}
				}
				else if(aboveNum.contains(cow)) {
					int index = aboveNum.indexOf(cow);
					int newVal = aboveVal.get(index) + change;
					int oldVal = aboveVal.get(index);
					if(newVal > G) { //keep in above
						aboveVal.set(index, newVal);
						if(newVal > max) {
							max = newVal;
							topChanged = true;
						}
					}
					else if (newVal < G) { //move to below
						aboveNum.remove(index);
						aboveVal.remove(index);
						
						belowNum.add(cow);
						belowVal.add(newVal);
						
						if(oldVal == max) {
							topChanged = true;
							if(aboveNum.size() == 0) {
								max = G;
							}else {
								int tempMax = G;
								for(int val: aboveVal) {
									if(val>tempMax) tempMax = val;
								}
								max = tempMax;
							}
							
						}
					}
					else { //remove since it is equal to G
						aboveNum.remove(index);
						aboveVal.remove(index);
						
						if(oldVal == max) {
							topChanged = true;
							if(aboveNum.size() == 0) {
								max = G;
							}else {
								int tempMax = G;
								for(int val: aboveVal) {
									if(val>tempMax) tempMax = val;
								}
								max = tempMax;
							}
							
						}
					}
				}
				else {
					int newVal = G + change;
					if(newVal < G) {
						belowNum.add(cow);
						belowVal.add(newVal);
						
						if(aboveNum.size() == 0) {
							topChanged = true;
						}
					}else if(newVal > G) {
						aboveNum.add(cow);
						aboveVal.add(newVal);
						
						if(newVal > max) {
							max = newVal;
							topChanged = true;
						}
					}
				}
				if(topChanged) count++;
			}
			System.out.println(count);
			
		} 
		catch (FileNotFoundException e)
		{
			System.out.println("fileNotFound");
		}

	}

}
