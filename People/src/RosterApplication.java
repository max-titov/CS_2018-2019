import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RosterApplication<E> {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		List<Student> rosterList = new ArrayList<>();
		
		try {
			System.out.print("Enter a file name (without the extension .txt): ");
			String filename = input.nextLine();
			input = new Scanner(new File("src/"+filename + ".txt"));
			//input = new Scanner(new File("roster.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Error: File not found.");
		}
		
		// Read data from file and store into ArrayList of Students
		while (input.hasNextLine()) {
			String[] studentData = input.nextLine().split(",");
			Student student = new Student(studentData[0], Integer.parseInt(studentData[1]), Integer.parseInt(studentData[2]), studentData[3]);
			rosterList.add(student);
		}
		System.out.println();
		
		input.close();
		
		// print roster
		System.out.println("Roster");
		for (Student student : rosterList) {
			System.out.println(student);
		}
		System.out.println();
		
		
		// Create a new Roster object
		Roster roster = new Roster(rosterList, "Computer Science");
		
		
		// Get the names of each student (just the first name from the Student class)
		System.out.println("Names of each Student");
		List<String> names = roster.getNames();
		System.out.println(names);
		System.out.println();
		
		
		// Get each unique major
		System.out.println("Majors");
		List<String> majors = roster.getMajors();
		for (String major : majors) {
			System.out.println(major);
		}
		System.out.println();
		
		
		// Search all names for containing an "a"
		System.out.println("Search names for anything containing an \"a\"");
		List<Student> students = roster.nameContains("a");
		for (Student student : students) {
			System.out.println(student);
		}
		System.out.println();
		
		
		// Create a sub roster for student before the name "ethan"
		// case is not sensitive
		Roster smallRoster = roster.getNamesBefore("ethan");
		System.out.println(smallRoster);
	}
	
	
	

}
