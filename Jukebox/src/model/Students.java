package model;

import java.util.ArrayList;
import java.util.List;

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

	/*
	 * Return Student that has a passed in ID.
	 */
	public Student getStudent(long numberID) {
		for (Student student : students) {
			if (student.getNumberID() == numberID) {
				return student;
			}
		}
		return null;
	}
}
