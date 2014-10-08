package model;

import java.util.ArrayList;
import java.util.List;

public class Students {

	private List<Student> students = new ArrayList<Student>();
	
	public Students(){
		students = new ArrayList<Student>();
	}

	public void addStudent(Student student){
		students.add(student);
	}
	
	public boolean contains(long numberID){
		for(Student student : students){
			if(student.getNumberID() == numberID){
				return true;
			}
		}
		return false;
	}
	
	public Student getStudent(long numberID){
		for(Student student : students){
			if(student.getNumberID() == numberID){
				return student;
			}
		}
		return null;
	}
}
