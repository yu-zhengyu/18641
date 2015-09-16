package model;

/**
 * This class is student model class, it includes all information about a student
 * 
 * @version  1.0
 * @author YuZheng
 * @Date 9/11/2015
 */ 

public class Student {
	private int SID;					// The student ID
	private int scores[] = new int[5];	// Five scores record of the student
	
	public Student() {}
	
	public Student(int sID, int[] scores) {
		SID = sID;
		this.scores = scores;
	}
	
	public int getSID() {
		return SID;
	}
	public void setSID(int sID) {
		SID = sID;
	}
	public int[] getScores() {
		return scores;
	}
	public void setScores(int[] scores) {
		this.scores = scores;
	}
	
	public void printScores() {
		for(int i = 0; i < 5; i++) {
			System.out.print(scores[i] + "\t");
		}
		System.out.println();
	}
	
	public void printSID() {
		System.out.println(SID);
	}
}
