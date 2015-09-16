package util;
import model.Student;

/**
 * This class is a Display student class, which extends display class, it can display
 * all student information and print the highest, lowest and average score in the class.
 * 
 * @version  1.0
 * @author YuZheng
 * @Date 9/11/2015
 */ 

public class DisplayStudent extends Display implements Printstatis{
	
	public DisplayStudent(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}
	
	// print all student'ID and 5 quiz records.
	@Override
	public void displayStudentInformation(Student students[]) {
		// TODO Auto-generated method stub
		System.out.println("Here is the whole information of students");
		System.out.println("Stud\tQu1\tQu2\tQu3\tQu4\tQu5\t");
		for (int i = 0; i < students.length; i++) {
			if (students[i] == null)
				break;
			System.out.print(students[i].getSID() + "\t");
			for (int j = 0; j < students[i].getScores().length; j++)
				System.out.print(students[i].getScores()[j] + "\t");
			System.out.println();
		}
	}

	// Print the lowest scores for each quiz
	@Override
	public void printlow(Statistics sta) {
		// TODO Auto-generated method stub
		System.out.println("Here are low score about five times quizs");
		for (int i = 0; i < sta.getLowscores().length; i++)
			System.out.println("Qu" + (i + 1) + ": " + sta.getLowscores()[i]);
	}

	// Print the highest scores for each quiz
	@Override
	public void printhigh(Statistics sta) {
		// TODO Auto-generated method stub
		System.out.println("Here are highest score about five times quizs");
		for (int i = 0; i < sta.getHighscores().length; i++)
			System.out.println("Qu" + (i + 1) + ": " + sta.getHighscores()[i]);
	}

	// Print the average scores for each quiz
	@Override
	public void printave(Statistics sta) {
		// TODO Auto-generated method stub
		System.out.println("Here are Average score about five times quizs");
		for (int i = 0; i < sta.getAvgscores().length; i++)
			System.out.println("Qu" + (i + 1) + ": " + sta.getAvgscores()[i]);
	}

}
