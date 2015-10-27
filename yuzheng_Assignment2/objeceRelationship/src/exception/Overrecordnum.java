package exception;

/**
 * This class is a Overrecordnum class, it's a exception class while the number of records
 * is over, the exception would throw.
 * 
 * @version  1.0
 * @author YuZheng
 * @Date 9/11/2015
 */ 

public class Overrecordnum extends Exception {
	private static final long serialVersionUID = -8303213550926146667L;
	
	public Overrecordnum(){
		super();
		printmyproblem();
	}
	public Overrecordnum(String message) {
		super(message);
		
	}
	public void printmyproblem() {
		System.out.println("The number of records is over"); 
	}
	public void fixproblem() {
		System.out.println("We would only take first 40 student records");
	}
}
