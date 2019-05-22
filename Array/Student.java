
public class Student {
	
	private String name;
	private int score;
	
	public Student(String studentName, int studentScore) {
		name = studentName;
		score = studentScore;
	}
	
	@Override
	public String toString() {
		return String.format("Student(name: %s, score: %d)", name, score);
	}
	
	//使用泛型
	public static void main(String[] args) {
		ArrayUsingGeneric<Student> arr = new ArrayUsingGeneric<Student>();
		arr.addLast(new Student("Jack", 100));
		arr.addLast(new Student("Rose", 99));
		arr.addLast(new Student("William", 98));
		System.out.println(arr);
	}
}
