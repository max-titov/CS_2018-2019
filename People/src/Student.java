
public class Student extends Person{
	
	private String major;
	
	public Student(String name, int birthYear, int gender, String major) {
		super(name, birthYear, gender);
		this.major = major;
	}

	public String getMajor() {
		return major;
	}

	@Override
	public String getName() {
		int space = super.getName().indexOf(" ");
		if(space != -1) {
			return super.getName().substring(0, space);
		}
		return super.getName();
	}
	
	@Override
	public String toString() {
		return super.toString() + " Major: "+getMajor();
	}
	
}
