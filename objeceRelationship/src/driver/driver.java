package driver;
import util.DisplayStudent;
import util.Statistics;
import util.Util;
import model.Student;
/**
 * This class is a driver class, it just a general test class to use all other class
 * 
 * @version  1.0
 * @author YuZheng
 * @Date 9/11/2015
 */ 

public class driver {
	public static void main(String[] args){
		Student lab2[] = new Student[40]; 
		DisplayStudent display = new DisplayStudent("The student information");
		
		// Reading the record from the .txt file, you can modify the file name,
		// The "studentless40.txt" is the records number less than 40
		// The "student.txt" is the records number is 40
		// The "studentmore40.txt" is the number of record more than 40
		
		 lab2 = Util.readFile("studentless40.txt", lab2);
		// lab2 = Util.readFile("studentmore40.txt", lab2);
		// lab2 = Util.readFile("student.txt", lab2);
		Statistics statlab2 = new Statistics();
		
		// finding the highest, lowest and average scores in the records
		statlab2.findlow(lab2);
		statlab2.findhigh(lab2);
		statlab2.findavg(lab2);
		
		// display all information about the student information
		display.displayStudentInformation(lab2);
		display.printlow(statlab2);
		display.printhigh(statlab2);
		display.printave(statlab2);
	}
}
