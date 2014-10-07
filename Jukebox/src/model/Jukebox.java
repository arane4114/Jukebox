package model;

public class Jukebox {
	private Students studentList;
	
	
	public Jukebox(){
		studentList = new Students();
		studentList.addStudent(new Student("Ali", 1111));
		studentList.addStudent(new Student("Chris", 2222));
		studentList.addStudent(new Student("River", 3333));
		studentList.addStudent(new Student("Ryan", 4444));
		
	}
}
