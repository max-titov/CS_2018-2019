import java.util.ArrayList;
import java.util.List;

public class Roster {
	
	private ArrayList<Student> roster;
	private String className;
	
	public Roster(List<Student> rosterList, String inClassName) {
		this.roster = (ArrayList<Student>) rosterList;
		this.className = inClassName;
	}
	
	public String getClassName() {
		return className;
	}
	
	public ArrayList<String> getNames(){
		ArrayList<String> names = new ArrayList<String>();
		for(int i = 0; i < roster.size(); i++) {
			names.add(roster.get(i).getName());
		}
		return names;
	}
	
	public ArrayList<String> getMajors(){
		ArrayList<String> majors = new ArrayList<String>();
		for(int i = 0; i < roster.size(); i++) {
			if(!majors.contains(roster.get(i).getMajor()))
				majors.add(roster.get(i).getMajor());
		}
		return majors;
	}
	
	public ArrayList<Student> nameContains(String str){
		ArrayList<Student> names = new ArrayList<Student>();
		for(int i = 0; i < roster.size(); i++) {
			if(roster.get(i).getName().contains(str))
				names.add(roster.get(i));
		}
		return names;
	}
	
	public Roster getNamesBefore(String str){
		List<Student> students = new ArrayList<Student>();
		for(int i = 0; i < roster.size(); i++) {
			if(roster.get(i).getName().toLowerCase().compareTo(str.toLowerCase()) < 0)
				students.add(roster.get(i));
		}
		return new Roster(students, getClassName());
	}
	
	public String toString() {
		String str = this.getClassName()+": \n";
		for(Student temp: roster) {
			str+=temp+"\n";
		}
		return str;
	}
	
}
