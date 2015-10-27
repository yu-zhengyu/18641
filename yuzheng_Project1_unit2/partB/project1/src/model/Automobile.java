package model;
import java.io.Serializable;
import java.util.ArrayList;

import model.OptionSet.Option;
/**
 * @version  1.0
 * @author YuZheng
 * @Date 9/14/2015
 * 
 * This class is a test file, which would create a Automotive class,
 * is implements the Serializable interface. Provide many functions to deal witt 
 * the option set and option. ALL CRUD functions are provided.
 */ 

public class Automobile implements Serializable {
	private static final long serialVersionUID = -2647584167364070782L;
	private String make;
	private String name;
	private String model;
	private float baseprice;
	private ArrayList<OptionSet> opset;
	private ArrayList<Option> choice;
	
	// Construction
	
	
	public Automobile() {}

	public Automobile(String make, String model, float baseprice) {
		this.make = make;
		this.name = make + " " + model;
		this.model = model;
		this.baseprice = baseprice;
		this.opset = new ArrayList<OptionSet>();
		this.choice = new ArrayList<Option>();
	}

	// all getter and setter generated by IDE
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getMake() {
		return make;
	}


	public void setMake(String make) {
		this.make = make;
	}


	public ArrayList<Option> getChoice() {
		return choice;
	}


	public void setChoice(ArrayList<Option> choice) {
		this.choice = choice;
	}


	public void setOpset(ArrayList<OptionSet> opset) {
		this.opset = opset;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public float getBaseprice() {
		return baseprice;
	}
	
	public void setBaseprice(float baseprice) {
		this.baseprice = baseprice;
	}
	
	/** 
	 * Additional getters and setters
	 */
	// find or get option set
	public OptionSet getOpset (int index) {
		if (index > opset.size() || index < 0 || opset.get(index) == null)
			return null;
		else
			return opset.get(index);
	}
	public OptionSet getOpset (String name) {
		for (int i = 0; i < opset.size(); i++) {
			if (opset.get(i).getName().equals(name))
				return opset.get(i);
		}
		return null;
	}
	
	// set option set
	public void setOpset (OptionSet opset, int index) {
		this.opset.add(opset);
	}
	public void setOpset (String name) {
		opset.add(new OptionSet(name));
	}
	
	// find option set by name
	public OptionSet findOpset (String name) {
		for (int i = 0; i < opset.size(); i++) {
			if (opset.get(i) != null) {
				if (opset.get(i).getName().equals(name)) 
					return opset.get(i);
			}
		}
		return null;
	}
	
	// Update Option Set
	public void updateOptionSet (String oldname, Option opt) {
		for (int i = 0; i < opset.size(); i++) {
			if (opset.get(i).getName().equals(oldname)) {
				opset.get(i).setOption(opt);
			}
		}
	}
	// Update Option set name only
	public void updateOptionSetNmae (int index, String name) {
		if (index < 0 || index > opset.size())
			return;
		opset.get(index).setName(name);
	}
	public void updateOptionSetNmae (String oldname, String name) {
		if (findOpset(oldname) != null)
			findOpset(oldname).setName(name);
	}
	
	
	// Delete the option set using its index
	public void deleteOptionSet (int index) {
		if (index < 0 || index > opset.size())
			return;
		if (opset.get(index) != null)
			opset.remove(index);
	}
	
	// delete the option set using its name
	public void deleteOptionSet (String name) {
		for (int i = 0; i < opset.size(); i++) {
			if (opset.get(i).getName().equals(name)) {
				opset.remove(i);
			}
		}
	}
	
	// get option
	public Option findOption (int index, String opname) {
		if (index >= 0 && index < opset.size()) {
			return opset.get(index).getOption(opname);
		}
		return null;
	}
	
	// find option by option name
	public Option findOption (String opsetname, String opname) {
		for (int i = 0; i < opset.size(); i++) {
			if (opset.get(i).getName().equals(opsetname)) {
				return opset.get(i).getOption(opname);
			}
		}
		return null;
	}
	
	// set option
	public void setOption (int index, String name, float price) {
		if (getOpset(index) != null) {
			getOpset(index).setOption(name, price);
		}
	}
	
	public void setOption(String setName, String name, float price) {
		if (findOpset(setName) != null) {
			findOpset(setName).setOption(name, price);
		}
	}
	
	public void setOption(int opsetindex, int opindex, String name, float price) {
		if(opsetindex >= 0 && opsetindex < opset.size()) {
			opset.get(opindex).setOption(name, price);
		}
	}
	
	// delete option using the index
	public void deleteOption (int index, String opname) {
		if (index == 0 && index < opset.size()) {
			if (opset.get(index) != null)
				opset.get(index).deleteOption(opname);
		}
	}
	// delete option using the option set name
	public void deleteOption (String opsetname, String opname) {
		for (int i = 0; i < opset.size(); i++) {
			if (opset.get(i).getName().equals(opsetname)) {
				opset.get(i).deleteOption(opname);
			}
		}
	}
	
	// Update option name only by using the index
	public void updateOptionName (int index, String oldname, String name) {
		if (index >= 0 && index < opset.size()) {
			opset.get(index).updateOptionName(oldname, name);
		}
	}
	// Update option name only by using the opiton set name
	public void updateOptionName (String opsetname, String oldname, String name) {
		if (findOpset(opsetname) != null)
			findOpset(opsetname).updateOptionName(oldname, name);
	}
	
	// Update option price only
	public void updateOptionPrice (int index, String name, float price) {
		if (index >= 0 && index < opset.size()) {
			opset.get(index).updateOptionprice(name, price);
		}
	}
	// update the option price 
	public void updateOptionPrice (String opsetname, String name, float price) {
		if (findOpset(opsetname) != null)
			findOpset(opsetname).updateOptionprice(name, price);
	}
	
	
	// Print Information
	public void printBasicInfomation () {
		System.out.println("The model name is: " + getName());
		System.out.println("The basic price is: " + getBaseprice());
		System.out.println();
	}
	
	// print the detail information about the option
	public void printoption() {
		for (int i = 0; i < opset.size(); i++) {
			if (opset.get(i) != null) {
				System.out.println(opset.get(i).getName());
				System.out.println("--------------------");
				opset.get(i).printoption();
				System.out.println();
			}
		}
	}
	
	// deal with choice
	public String getOptionChoice(String opsetName) {
		return getOpset(opsetName).getChoiceName();
	}
	public float getOptionChoicePrice(String opsetName) {
		return getOpset(opsetName).getChoicePrice();
	} 
	public void setOptionChoice(String opsetname, String opname) {
		OptionSet opset = getOpset(opsetname);
		choice.add(opset.getOption(opname));
		opset.setChoice(opname);
	}
	// computing the total price
	public float getTotalPrice() {
		float sum = baseprice;
		for (Option op : choice) {
			sum += op.getPrice();
		}
		return sum;
	}
	// pint total price
	public void printTotalPrice() {
		System.out.println("The total price is: " + getTotalPrice());
	}
	
	// pint choice
	public void printChoice() {
		System.out.println("Here are choices the user selected:");
		for (Option op : choice) {
			System.out.println(op.getName() + "\t"
								+ "Price: " + op.getPrice());
		}
	}
	
}
