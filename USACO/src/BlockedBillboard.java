import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BlockedBillboard {

	public static void main(String[] args) {
		Scanner file;
		Rectangle board1 = new Rectangle();
		Rectangle board2 = new Rectangle();
		Rectangle truck = new Rectangle();
		
		try
		{
			String filename = "src/1.in";
			file = new Scanner (new File(filename));
			String line1 = file.nextLine();
			String line2 = file.nextLine();
			String line3 = file.nextLine();
			
			String[] num1 = line1.split(" ");
			String[] num2 = line2.split(" ");
			String[] num3 = line3.split(" ");
			
			board1 = new Rectangle(Integer.parseInt(num1[0]),Integer.parseInt(num1[1]),Integer.parseInt(num1[2]),Integer.parseInt(num1[3]));
			board2 = new Rectangle(Integer.parseInt(num2[0]),Integer.parseInt(num2[1]),Integer.parseInt(num2[2]),Integer.parseInt(num2[3]));
			truck = new Rectangle(Integer.parseInt(num3[0]),Integer.parseInt(num3[1]),Integer.parseInt(num3[2]),Integer.parseInt(num3[3]));
			System.out.println(board1+"\n"+board2+"\n"+truck);
		} 
		catch (FileNotFoundException e)
		{
			System.out.println("fileNotFound");
		}
		
		int overlapTB1 = truck.overlap(board1);
		System.out.println(overlapTB1);
		int overlapB1T = board1.overlap(truck);
		System.out.println(overlapB1T);
		int overlapTB2 = truck.overlap(board2);
		System.out.println(overlapTB2);
		int overlapB2T = board2.overlap(truck);
		System.out.println(overlapB2T);
		
		if(overlapTB1 < overlapB1T) overlapTB1 = overlapB1T;
		
		if(overlapTB2 < overlapB2T) overlapTB2 = overlapB2T;
		
		System.out.println(board1.area() - overlapTB1);
		System.out.println(board2.area() - overlapTB2);
		

	}

}
