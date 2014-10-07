package Model;

import java.util.ArrayList;
import java.util.List;

public class Students {

	List<Student> students = new ArrayList<Student>();
	
	public Students(){
		students = new ArrayList<Student>();
	}

	public void addStudent(Student student){
		students.add(student);
	}
	
	public boolean contains(int numberID){
		for(Student student : students){
			if(student.getNumberID() == numberID){
				return true;
			}
		}
		return false;
	}
	
	public Student getStudent(int numberID){
		for(Student student : students){
			if(student.getNumberID() == numberID){
				return student;
			}
		}
		return null;
	}
}
