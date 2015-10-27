package adapter;

/**
 * @version  1.0
 * @author YuZheng
 * @Date 9/20/2015
 * 
 * This class is a interface call UpdateAuto, which can be implemented by other class,
 * focus on update the option set name and option price
 */ 

public interface UpdateAuto {
	
	/**
	 * This function searches the Model for a given OptionSet and sets the 
	 * name of OptionSet to newName.
	 * @param Modelname
	 * @param OptionSetname
	 * @param newName
	 */
	public void updateOptionSetName(String Modelname, String OptionSetname, 
			String newName);
	
	/**
	 * This function searches the Model for a given OptionSet 
	 * and Option name, and sets the price to newPrice.
	 * @param Modelname
	 * @param Optionname
	 * @param Option
	 * @param newprice
	 */
	public void updateOptionPrice(String Modelname, String Optionname, 
			String Option, float newprice);

}
