package driver;
import exception.AutoException;
import adapter.BuildAuto;
import adapter.CreateAuto;
import model.Automobile;
import util.FileIO;

/**
 * @version  1.0
 * @author YuZheng
 * @Date 9/14/2015
 * 
 * This class is a test file, would read the file to do the configuration of Automotive
 * And print it out. 
 * And then, Serialize the object, deseriablize the object, print it out again.
 */ 

public class Driver {

	public static void main(String[] args) throws AutoException{
		
		// test case 1
		/**
		BuildAuto a2 = new BuildAuto();
		a2.buildAuto("FordZTW.txt");
		a2.buildAuto("GTR.txt");
		a2.printAuto("Focus Wagon ZTW");
		System.out.println("----------------------------------");
		a2.printAuto("Nissan GTR");
		*/

		// test case 2
		//Build Automobile Object from a file.
		FileIO io = new FileIO();
		Automobile FordZTW = null;
		FordZTW = io.buildAutoObject("FordZTW.txt", FordZTW);
		
		//Print attributes before serialization
		FordZTW.printBasicInfomation();
		FordZTW.printoption();
		
		//Set Choice
		FordZTW.setOptionChoice("Color", "Fort Knox Gold Clearcoat Metallic");
		FordZTW.setOptionChoice("Transmission", "standard");
		FordZTW.setOptionChoice("Brakes/Traction Control", "ABS");
		FordZTW.setOptionChoice("Side Impact Air Bags", "selected");
		FordZTW.setOptionChoice("Power Moonroof", "selected");
		System.out.println("----------------------------------");
		System.out.println("The original basic price is: " + FordZTW.getBaseprice());
		FordZTW.printChoice();
		FordZTW.printTotalPrice();
		//Serialize the object
		System.out.println("----------------------------------");
		System.out.println("Serialize the object and Deserialize and then read it");
		io.serializeAuto(FordZTW);
		//Deserialize the object and read it into memory.
		Automobile newFordZTW = io.DeserializeAuto("auto.ser"); //Print new attributes.
		System.out.println("----------------------------------");
		newFordZTW.printBasicInfomation();
		newFordZTW.printoption();
	
		
	}

}
