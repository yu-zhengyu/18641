package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import exception.Overrecordnum;
import model.Student;
/**
 * This class is a Util class, it can read student information through a .txt file, also
 * it extends the displaystudent class, so it has the function to print all information 
 * about students
 * 
 * @version  1.0
 * @author YuZheng
 * @Date 9/11/2015
 */ 

public class Util extends DisplayStudent {
	
	public Util(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}
	
	// Read the student information from a .txt file
	public static Student[] readFile(String filename, Student[] stu) {
		int linecount = 0;
		int studentnum = 0;
		try {
			FileReader file = new FileReader(filename);
			@SuppressWarnings("resource")
			BufferedReader buff = new BufferedReader(file);
			boolean eof = false;
			while (!eof) {
				String line = buff.readLine();
				if (line == null)
					eof = true;
				else {
					linecount++;
					if (linecount == 1)
						continue;
					// throw the exception when the records is over 40
					if (linecount > 41)
						throw new Overrecordnum();
					StringTokenizer st = new StringTokenizer(line);
					Student s = new Student();
					boolean checkSID = true;
					int quiznum = 0;
					int[] scores = new int[5];
					while (st.hasMoreTokens()) {
						if(checkSID) {
							int id = Integer.parseInt(st.nextToken());
							s.setSID(id);
							checkSID = false;
						}
						else {
							scores[quiznum++] = Integer.parseInt(st.nextToken());
						}
					}
					s.setScores(scores);
					stu[studentnum++] = s;
					if(stu[0] == null) {
						System.out.println("There is no record");
						int[] zeroscore = new int[]{0,0,0,0,0};
						stu[0].setSID(0);
						stu[0].setScores(zeroscore);
						
					}
				}
			}
			buff.close();
		} catch (Overrecordnum e){
			e.fixproblem();
			
		} catch (IOException e) {
			System.out.println("Error ­­ " + e.toString());
		}
		
		return stu;
	}
}
