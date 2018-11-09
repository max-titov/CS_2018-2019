
public class PersonTester {


	public static void main(String[] args) {

		Person person = new Person("Donald Duck", 1934, Person.MALE);
		System.out.println("Name: " + person.getName());
		System.out.println("Birth Year: " + person.getBirthYear());
		System.out.println("Gender: " + person.getGender() + " "+ person.getGenderAsString());
		System.out.println(person);
		System.out.println();
		
		Student student = new Student("Minnie Mouse", 1928, Person.FEMALE, "Graphics & Animation");
		System.out.println("Name: " + student.getName());
		System.out.println("Birth Year: " + student.getBirthYear());
		System.out.println("Gender: " + student.getGender() + " "+ student.getGenderAsString());
		System.out.println("Major: " + student.getMajor());
		System.out.println(student);
		System.out.println();
		
		Teacher teacher = new Teacher("Goofy Goof", 1932, Person.OTHER, 5000);
		System.out.println("Name: " + teacher.getName());
		System.out.println("Salary: " + teacher.getSalary());
		System.out.println(teacher);
		System.out.println();
		
		Teacher teacher1 = new Teacher("Huey Duck", 1938, Person.MALE, 0);
		System.out.println("Name: " + teacher1.getName());
		System.out.println("Salary: " + teacher1.getSalary());
		System.out.println(teacher1);
		System.out.println();
		
		Teacher teacher2 = new Teacher("Daisy Duck", 1937, Person.FEMALE, 100_000);
		System.out.println("Name: " + teacher2.getName());
		System.out.println(teacher2);
		System.out.println();
		
		Teacher[] teachers = new Teacher[10];
		for(int i = 0; i < 10; i++) {
			teachers[i] = new Teacher("Teacher "+(i+1), 1965+i, (int)(Math.random()*3)+1, (int)(Math.random()*25001)+50000);
		}
		for(Teacher tempTeach: teachers)
			System.out.println(tempTeach);
		
		
	}

}
