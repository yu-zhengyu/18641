package util;

import model.Student;
/**
 * This class is a compute all statistic class, it can compute the highest, lowest and 
 * average scores in the class.
 * 
 * @version  1.0
 * @author YuZheng
 * @Date 9/11/2015
 */ 

public class Statistics {
	private int[] lowscores = new int[5];		// The lowest score record for each quiz
	private int[] highscores = new int[5];		// The highest score record for each quiz
	private float[] avgscores = new float[5];	// The average score record for each quiz

	public int[] getLowscores() {
		return lowscores;
	}

	public void setLowscores(int[] lowscores) {
		this.lowscores = lowscores;
	}

	public int[] getHighscores() {
		return highscores;
	}

	public void setHighscores(int[] highscores) {
		this.highscores = highscores;
	}

	public float[] getAvgscores() {
		return avgscores;
	}

	public void setAvgscores(float[] avgscores) {
		this.avgscores = avgscores;
	}

	public void findlow(Student[] a) {
		/*
		 * This method will find the lowest score and store it in an array names
		 * lowscores.
		 */

		for (int i = 0; i < 5; i++) {
			int lowest = 101;
			for (int j = 0; j < a.length; j++) {
				if (a[j] == null)
					continue;
				if (a[j].getScores()[i] < lowest) {
					lowest = a[j].getScores()[i];
				}
			}
			lowscores[i] = lowest;
		}
	}

	public void findhigh(Student[] a) {
		/*
		 * This method will find the highest score and store it in an array
		 * names highscores.
		 */
		for (int i = 0; i < 5; i++) {
			int thehighest = 0;
			for (int j = 0; j < a.length; j++) {
				if (a[j] == null)
					continue;
				if (a[j].getScores()[i] > thehighest) {
					thehighest = a[j].getScores()[i];
				}
			}
			highscores[i] = thehighest;
		}
	}

	public void findavg(Student[] a) {
		/*
		 * This method will find avg score for each quiz and store it in an
		 * array names avgscores.
		 */
		for (int i = 0; i < 5; i++) {
			int sum = 0;
			int count = 0;
			for (int j = 0; j < a.length; j++) {
				if (a[j] == null)
					continue;
				sum += a[j].getScores()[i];
				count++;
			}
			try {
				avgscores[i] = sum / count;
			} catch (ArithmeticException e) {
				avgscores[i] = 0;
			}
		}
	}
}
