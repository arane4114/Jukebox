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

	/*
	 * Does the list contain a unique ID.
	 */
	public boolean contains(long numberID) {
		for (Student student : students) {
			if (student.getNumberID() == numberID) {
				return true;
			}
		}
		return false;
	}
	
	private boolean studentExists(String name){
		for(Student student: students){
			if(student.getName().equals(name)){
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Return Student that has a passed in ID.
	 */
	public Student getStudentById(long numberID) {
		for (Student student : students) {
			if (student.getNumberID() == numberID) {
				return student;
			}
		}
		return null;
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
		long passKeyNumericalVersion = 0;
		for(int i = 0; i < 4; i++){
			passKeyNumericalVersion += Long.parseLong(""+ passkey[i]) * Math.pow(10, 3-i);
		}
		if(currentStudent.getNumberID() == passKeyNumericalVersion){
			return true;
		}else{
			return false;
		} 
	}
}
