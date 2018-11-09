
public class Person {
	
	private String name;
	private int birthYear;
	private int gender;
	
	public final static int FEMALE = 1;
	public final static int MALE = 2;
	public final static int OTHER = 3;
	
	public Person(String name, int birthYear, int gender) {
		this.name = name;
		this.birthYear = birthYear;
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public int getGender() {
		return gender;
	}

	public String getGenderAsString() {
		if(gender == 1)
			return "Female";
		else if(gender == 2)
			return "Male";
		else
			return "Other";
	}
	
	@Override
	public String toString() {
		return "Name: "+name+" Birth year: "+birthYear+" Gender: "+getGenderAsString();
	}
	
}
