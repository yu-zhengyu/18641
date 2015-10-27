/**
 * @version 2.0
 * @author YuZheng
 * @Date 10/1/2015
 * 
 * This is a interface class, it would implemented by BuildAuto and BuildCarModelOptions
 */

package server;

import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Properties;

public interface AutoServer {
	
	/**
	 * Build a AutoMobile instance by using a properties file.
	 * @param carProperties
	 */
	public void buildAutoByProperty(Properties carProperties);
	
	/**
	 * Build a AutoMobile instance by using a txt file
	 * @param filename
	 */
	public void buildAutoByfilename(String filename);

	/**
	 * Provide a list of available models to the client.
	 * @return
	 */
	public ArrayList<String> getModelList();
	
	/**
	 * Send the object (using Serialization) to the client
	 * 
	 */
	public void sendObject(ObjectOutputStream obOutputStream, String modelName);
}
