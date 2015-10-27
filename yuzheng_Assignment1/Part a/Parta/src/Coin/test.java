package Coin;

/**
 * This is a test file, run 2 times the simulation class.
 * @version  1.0
 * @author YuZheng
 * @Date 9/5/2015
 */ 

public class test {

	public static void main(String[] args) {

		Coin coinTest1 = new Coin();		// case1 coin instance
		Coin coinTest2 = new Coin();		// case2 coin instance 
		Simulation s = new Simulation();	// simulate the case, toss coin 20 times
		
		// test case 1, toss 20 times, display the result
		System.out.println("Test case 1: ");
		s.run(coinTest1);
		System.out.print(s.gettrack());
		System.out.println("The number of heads is:" + s.getHeads_num());
		System.out.println("The number of tails is:" + s.getTails_num());		
		System.out.println("\n");
		
		// test 2
		// test case 2, toss 20 times, display the result		
		System.out.println("Test case 2: ");
		s.run(coinTest2);
		System.out.print(s.gettrack());
		System.out.println("The number of heads is:" + s.getHeads_num());
		System.out.println("The number of tails is:" + s.getTails_num());
	}

}
