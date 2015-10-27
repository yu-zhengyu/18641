package util;
import model.Student;

/**
 * This class is a basic abstract class call display, it has basic print function,
 * also has a abstract function to display student information
 * 
 * @version  1.0
 * @author YuZheng
 * @Date 9/11/2015
 */ 

public abstract class Display {
	private String title;
	

	public Display(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public void printtitle() {
		System.out.println(title);
	}
	
	public abstract void displayStudentInformation(Student students[]);
}
