package adapter;

import java.util.LinkedHashMap;
import java.util.Properties;

import exception.AutoException;
import exception.Fix1to100;
import scale.EditOptions;
import util.FileIO;
import model.*;

/**
 * @version 2.0
 * @author YuZheng
 * @Date 10/2/2015
 * 
 *       The abstract class ProxyAutomobile just implements the method of
 *       interface: CreateAuto and UpdateAuto and AutoSever
 */

public abstract class ProxyAutomobile {
	private static LinkedHashMap<String, Automobile> a1list = new LinkedHashMap<String, Automobile>();
	private static int threadID = 1;
	
	// implement the AutoSever interface method.
	public void buildAutoByProperty(Properties carProperties) {
		Automobile a1 = null;
		FileIO io = new FileIO();
		a1 = io.buildAutoObjectProperty(carProperties);
		a1list.put(a1.getName(), a1);
	}

	// update by yuzheng, add a new parameter called file type, 
	// make it can handle different type of file
	public void buildAuto(String filename, String fileType) {
		
		if (fileType.equals("txt")) {
		FileIO io = new FileIO();
		Automobile a1 = null;
		try {
			a1 = io.buildAutoObject(filename, a1);
			a1list.put(a1.getName(), a1);
		} catch (AutoException e) {
			fix(1);
			try {
				a1 = io.buildAutoObject("default.txt", a1);
				a1list.put(a1.getName(), a1);
			} catch (AutoException e1) {
				e1.printStackTrace();
			}
		}
		}
		else if(fileType.equals("Properties")) {
			Automobile a1 = null;
			FileIO io = new FileIO();
			a1 = io.buildAutoObjectProperty(filename);
			a1list.put(a1.getName(), a1);
		}
	}

	public void printAuto(String Modelname) {

		a1list.get(Modelname).printBasicInfomation();
		a1list.get(Modelname).printoption();

	}

	public void updateOptionSetName(String Modelname, String OptionSetname,
			String newName) {

		a1list.get(Modelname).updateOptionSetNmae(OptionSetname, newName);
	}

	public void updateOptionPrice(String Modelname, String optionname,
			String Option, float newprice) {

		a1list.get(Modelname).updateOptionPrice(optionname, Option, newprice);
	}

	public void fix(int errno) {
		Fix1to100 f1 = new Fix1to100();
		switch (errno) {
		/**
		 * 1.fileNotFound 2.noBasePrice 3.noOptionSetNmae 4.noOptionName
		 * 5.noOptionPrice
		 */
		case 1:
			f1.fixFileNoFind();
			break;
		case 2:
			f1.fixFileNoBasePrice();
			break;
		case 3:
			f1.fixNoOptionSetName();
			break;
		case 4:
			f1.fixNoOptionName();
			break;
		case 5:
			f1.fixNoOptionPrice();
			break;
		default:
			break;
		}
	}

	public synchronized void updateEditOptionName(String model, String optionset,
			String option, String optionNewName) {
		Automobile tempAuto = a1list.get(model);
		EditOptions ed = new EditOptions(tempAuto, optionset, option, 1,
				optionNewName, threadID++);
		ed.start();
	}

	public synchronized void updateEditOptionPrice(String model, String optionset,
			String option, float price) {
		Automobile tempAuto = a1list.get(model);
		EditOptions ed = new EditOptions(tempAuto, optionset, option, price, 2, threadID++); 
		ed.start();
	}

}
