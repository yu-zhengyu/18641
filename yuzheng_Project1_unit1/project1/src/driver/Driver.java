package driver;

import exception.AutoException;
import adapter.BuildAuto;
import adapter.CreateAuto;
import model.Automobile;
import util.FileIO;

/**
 * @version 1.0
 * @author YuZheng
 * @Date 9/14/2015
 * 
 *       This class is a test file, would read the file to do the configuration
 *       of Automotive And print it out. And then, Serialize the object,
 *       deseriablize the object, print it out again.
 */

public class Driver {

	public static void main(String[] args) throws AutoException {

		// test case 1

		BuildAuto a2 = new BuildAuto();
		a2.buildAuto("FordZTW.txt");
		a2.updateEditOptionName("Focus Wagon ZTW", "Color",
				"Infra-Red Clearcoat", "zzzzz");
		a2.updateEditOptionName("Focus Wagon ZTW", "Color", "zzzzz",
				"gggggggggg");
		a2.updateEditOptionName("Focus Wagon ZTW", "Color",
				"French Blue Clearcoat Metallic", "zzzzz");

		a2.updateEditOptionPrice("Focus Wagon ZTW", "Transmission", "automatic",
				1000);
		a2.updateEditOptionPrice("Focus Wagon ZTW", "Transmission", "automatic",
				1000);
		a2.updateEditOptionPrice("Focus Wagon ZTW", "Transmission", "automatic",
				1000);

		// wait for all thread finish
		try {
			Thread.sleep(3000);
		} catch (InterruptedException ie) {
			
		}
		
		a2.printAuto("Focus Wagon ZTW");

	}

}
