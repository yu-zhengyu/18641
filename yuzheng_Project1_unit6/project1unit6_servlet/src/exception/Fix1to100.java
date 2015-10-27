package exception;

import java.util.Scanner;

/**
 * @version  1.0
 * @author YuZheng
 * @Date 9/20/2015
 * 
 * This class support specific method to fix error 
 */ 

public class Fix1to100 {
	
	public void fixFileNoFind() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Since the file name is not exist. So we would load "
				+ "a default file.");
	}
	
	// 
	public String fixFileNoBasePrice(){
		Scanner sc = new Scanner(System.in);
		System.out.println("The base price is not exist,"
				+ " please input a new price: ");
		String opprice = sc.nextLine();
		return opprice;
	}
	
	public String fixNoOptionSetName(){
		Scanner sc = new Scanner(System.in);
		System.out.println("The option set name is not exist,"
				+ " please input a new option set name: ");
		String opsetname = sc.nextLine();
		return opsetname;
		
	}
	
	public String fixNoOptionName() {
		Scanner sc = new Scanner(System.in);
		System.out.println("The option set name is not exist,"
				+ " please input a new option set name: ");
		String opname = sc.nextLine();
		return opname;
		
	}
	
	public String fixNoOptionPrice(){
		Scanner sc = new Scanner(System.in);
		System.out.println("The option prince is not exist,"
				+ " please input a new option set name: ");
		String opprice = sc.nextLine();
		return opprice;
	}
}
