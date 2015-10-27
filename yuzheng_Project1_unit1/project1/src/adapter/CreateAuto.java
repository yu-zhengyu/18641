package adapter;

/**
 * @version  1.0
 * @author YuZheng
 * @Date 9/20/2015
 * 
 * This class is a interface call CreateAuto, which can be implemented by other class,
 * it focus on crate a new instance by file name and print all information about the 
 * car.
 */ 

public interface CreateAuto {
	
	/**
	 * Given a text file name a method called BuildAuto can be written to build an  
	 * instance of Automobile. This method does not have to return the Auto instance.
	 * @param filename
	 */
	public void buildAuto(String filename);
	
	/**
	 * This function searches and prints the properties of a given Automodel.
	 * @param Modelname
	 */
	public void printAuto(String Modelname);
}
