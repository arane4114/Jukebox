/*
 * @ - Author: Abhishek Rane
 * @ - Author: Bryce Hammond
 */
package model;

import java.util.ArrayList;
import java.util.List;

/*
 * This is the collection class that holds all students.
 * It isnt used much in this iteration due to the spec changes regarding logins.
 */
public class Students {

	private List<Student> students = new ArrayList<Student>();

	/*
	 * Constructor for students.
	 * A list of Student.
	 */
	public Students() {
		students = new ArrayList<Student>();
	}

	/*
	 * Add Student to Students.
	 */
	public void addStudent(Student student) {
		students.add(student);
	}
	
	private boolean studentExists(String name){
		for(Student student: students){
			if(student.getName().equals(name)){
				return true;
			}
		}
		return false;
	}
	
	public Student getStudentByName(String name){
		for (Student student : students) {
			if (student.getName().endsWith(name)) {
				return student;
			}
		}
		return null;
	}
	
	public boolean login(String name, char[] passkey){
		boolean studentExists = studentExists(name);
		if(!studentExists){
			return false;
		}
		Student currentStudent = getStudentByName(name);
		String passKeyNumericalVersion = "";
		for(int i = 0; i < passkey.length ; i++){
			passKeyNumericalVersion += passkey[i];
		}
		if(currentStudent.getPassword().equals(passKeyNumericalVersion)){
			return true;
		}else{
			return false;
		} 
	}
}
