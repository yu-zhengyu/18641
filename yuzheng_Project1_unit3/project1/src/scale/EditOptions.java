package scale;

import model.Automobile;

/**
 * @version 1.0
 * @author YuZheng
 * @Date 9/25/2015
 * 
 * This class is used to deal with synchronize issue. I only provide two
 * multithreading function since this time my goal is just learn how to do
 * the multithreading
 * 
 */

public class EditOptions extends Thread {
	private Automobile editauto;
	private String optionSet;
	private String option;
	private float optionNewprice;
	private int method;
	private String optionNewname;
	private int threaID;
	private int count = 1;

	public EditOptions() {
	}

	public EditOptions(Automobile editauto, String optionSet, String option,
			float optionNewprice, int method, String optionNewname, int threaID) {
		this.editauto = editauto;
		this.optionSet = optionSet;
		this.option = option;
		this.optionNewprice = optionNewprice;
		this.method = method;
		this.optionNewname = optionNewname;
		this.threaID = threaID;
	}
	
	/**
	 * update new option name construction
	 * @param editauto
	 * @param optionSet
	 * @param option
	 * @param method
	 * @param optionNewname
	 * @param threaID
	 */
	public EditOptions(Automobile editauto, String optionSet, String option,
			int method, String optionNewname, int threaID) {
		this.editauto = editauto;
		this.optionSet = optionSet;
		this.option = option;
		this.method = method;
		this.optionNewname = optionNewname;
		this.threaID = threaID;
	}

	/**
	 * update new option price construction
	 * @param editauto
	 * @param optionSet
	 * @param option
	 * @param optionNewprice
	 * @param method
	 * @param threaID
	 */
	public EditOptions(Automobile editauto, String optionSet, String option,
			float optionNewprice, int method, int threaID) {
		this.editauto = editauto;
		this.optionSet = optionSet;
		this.option = option;
		this.optionNewprice = optionNewprice;
		this.method = method;
		this.threaID = threaID;
	}
	
	public Automobile getEditAuto() {
		return editauto;
	}

	public void setEditAuto(Automobile editauto) {
		this.editauto = editauto;
	}

	public String getOptionSet() {
		return optionSet;
	}

	public void setOptionSet(String optionSet) {
		this.optionSet = optionSet;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public float getOptionNewprice() {
		return optionNewprice;
	}

	public void setOptionNewprice(float optionNewprice) {
		this.optionNewprice = optionNewprice;
	}

	public int getMethod() {
		return method;
	}

	public void setMethod(int method) {
		this.method = method;
	}

	public String getOptionNewname() {
		return optionNewname;
	}

	public void setOptionNewname(String optionNewname) {
		this.optionNewname = optionNewname;
	}

	public int getThreaID() {
		return threaID;
	}

	public void setThreaID(int threaID) {
		this.threaID = threaID;
	}

	@Override
	public void run() {
		switch (method) {
		// update the option name
		case 1:
			threadUpdateOptionName();
			break;
		// update the option  price
		case 2:
			threadUpdateOptionPrice();
			break;
		default:
			break;
		}

	}
	
	/**
	 * update the option name within multithread
	 */
	public void threadUpdateOptionName() {
		//synchronized (editauto) {
			randomWait();
			editauto.updateOptionName(optionSet, option, optionNewname);
			System.out.println("ThreadID: " + threaID + "\t"
					+ "optionSet: " + optionSet + "\t"
					+ "option: " + option + "\t"
					+ "option new name: " + optionNewname);
		//}
	}
	
	/**
	 * Update the option price within multithreading
	 */
	public void threadUpdateOptionPrice() {
		//synchronized (editauto) {
			randomWait();
			editauto.updateOptionPrice(optionSet, option, optionNewprice);
			System.out.println("ThreadID: " + threaID + "\t"
					+ "optionSet: " + optionSet + "\t"
					+ "option: " + option + "\t"
					+ "option new price: " + optionNewprice);
		//}
	}

	public void randomWait() {
		try {
			Thread.currentThread();
			Thread.sleep((long) (1000 * count++));
		} catch (InterruptedException e) {
			System.out.println("Interrupted!");
		}
	}
}
