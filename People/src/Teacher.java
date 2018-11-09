import java.text.NumberFormat;

public class Teacher extends Person{
	
	private int salary;
	
	public final static int FEMALE = 1;
	public final static int MALE = 2;
	public final static int OTHER = 3;
	
	public Teacher(String name, int birthYear, int gender, int salary) {
		super(name, birthYear, gender);
		this.salary = salary;
	}

	public int getSalary() {
		return salary;
	}

	@Override
	public String getName() {
		int space = super.getName().indexOf(" ");
		String properName = "";
		if(getGender()==FEMALE) {
			properName +="Ms. ";
		}else if(getGender()==MALE) {
			properName +="Mr. ";
		}else {
			properName +="Teacher ";
		}
		properName+=super.getName().substring(space+1);
		return properName;
	}
	
	@Override
	public String toString() {
		NumberFormat salaryNumber = NumberFormat.getCurrencyInstance();
		return super.toString() + " " + salaryNumber.format(salary);
	}
	
}
