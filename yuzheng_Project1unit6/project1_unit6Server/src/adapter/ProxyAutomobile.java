package adapter;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Properties;

import javax.swing.GroupLayout.Alignment;
import javax.swing.text.html.Option;

import exception.AutoException;
import exception.Fix1to100;
import scale.EditOptions;
import util.DatabaseIO;
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
	
	
	// ID for the database 
	private static int AutoID = 0; // The ID of car
	private static int OptionSetID = 0; // The ID of option set
	private static int OptionID = 0; // The ID of option
	
	// All implement with DataBaseAuto interface
	public void creatDBAuto(String filename) {
		Automobile auto = null; 
		try {
			auto = new FileIO().buildAutoObject(filename, auto);
		} catch (AutoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		a1list.put(auto.getName(), auto);
		DatabaseIO database = new DatabaseIO();
		int[] updatenumb = database.addAutoMobileToDB(auto, AutoID, OptionSetID, OptionID);
		AutoID++;
		OptionSetID = updatenumb[0];
		OptionID = updatenumb[1];
	}

	public void updateDBAutoBasicPrice(String autoName, float price) {
		if(a1list.get(autoName) != null) {
			a1list.get(autoName).setBaseprice(price);
			DatabaseIO database = new DatabaseIO();
			database.updateAutoDB(autoName, price);
		}
		else {
			System.out.println("No Such AutoMobile in Database");
		}
		
	}

	public void deleteDBAuto(String autoname) {
		if(a1list.get(autoname) != null) {
			a1list.remove(autoname);
			DatabaseIO database = new DatabaseIO();
			database.deleteAutoFromDB(autoname);
		}
		else {
			System.out.println("No Such AutoMobile in Database");
		}
		
	}
	
	// implement the AutoSever interface method.
	public void buildAutoByProperty(Properties carProperties) {
		Automobile a1 = null;
		FileIO io = new FileIO();
		a1 = io.buildAutoObjectProperty(carProperties);
		a1list.put(a1.getName(), a1);
	}
	
	public void buildAutoByfilename(String filename) {
		buildAuto(filename, "txt");
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
	
	/**
	 * implement the AutoServer interface
	 * @return
	 */
	public ArrayList<String> getModelList() {
		ArrayList<String> modelNameList = new ArrayList<String>();
		for (String key : a1list.keySet()) {
			modelNameList.add(key);
		}
		return modelNameList;
		
	}
	
	/**
	 * implement the AutoServer interface
	 * @param obOutputStream
	 * @param modelName
	 */
	public void sendObject(ObjectOutputStream obOutputStream, String modelName) {
		Automobile a = a1list.get(modelName);
		try {
			obOutputStream.writeObject(a);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
