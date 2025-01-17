package adapter;

import java.util.LinkedHashMap;

import exception.AutoException;
import exception.Fix1to100;
import util.FileIO;
import model.*;

/**
 * @version  1.0
 * @author YuZheng
 * @Date 9/20/2015
 * 
 * The abstract class ProxyAutomobile just implements the method of 
 * interface: CreateAuto and UpdateAuto.   
 */ 

public abstract class ProxyAutomobile {
	private static LinkedHashMap<String, Automobile> a1list = new LinkedHashMap<String, Automobile>();
	
	public void buildAuto(String filename) {
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
	
	public void fix(int errno){
		Fix1to100 f1 = new Fix1to100();
		switch(errno)
		{
			/**
			 *  1.fileNotFound
			 *	2.noBasePrice
			 *	3.noOptionSetNmae
			 *	4.noOptionName
			 *	5.noOptionPrice
			 */
			case 1: f1.fixFileNoFind(); break;
			case 2: f1.fixFileNoBasePrice(); break;
			case 3: f1.fixNoOptionSetName(); break;
			case 4: f1.fixNoOptionName(); break;
			case 5: f1.fixNoOptionPrice(); break;
			default: break;
		}
	}
	
}
