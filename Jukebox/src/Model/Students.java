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
}
