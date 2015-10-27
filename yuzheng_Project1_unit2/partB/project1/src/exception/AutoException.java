package exception;
import java.io.*;
import java.io.BufferedWriter;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @version  1.0
 * @author YuZheng
 * @Date 9/20/2015
 * 
 * This class is a customer exception class
 */ 

public class AutoException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3353081391162535534L;
	private String msg;
	private ExceptionEnumerator errno;
	
	public AutoException(String msg, ExceptionEnumerator errno) {
		this.msg = msg;
		this.errno = errno;
		logException();
	}
	
	public void logException() {
		Date date = new Date();
		Timestamp time = new Timestamp(date.getTime());
		String information = time.toString() + " " + msg + "\n"; 
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream("log.txt", true)));
			bw.write(information);
			bw.flush();
			bw.close();
		} catch (IOException e) {
			System.out.println("Error -- " + e.toString());
		}
	}

	public void printmas() {
		System.out.println("The error number is: " + errno);
		System.out.println("The error is: " + msg);
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public ExceptionEnumerator getErrno() {
		return errno;
	}
	public void setErrno(ExceptionEnumerator errno) {
		this.errno = errno;
	}
	
	

}
