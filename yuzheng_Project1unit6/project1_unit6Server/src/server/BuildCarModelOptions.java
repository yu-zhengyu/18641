/**
 * @version 2.0
 * @author YuZheng
 * @Date 10/1/2015
 * 
 *  This class has below function:
 *  1. accept properties object from client socket over an object stream and create an AutoMotile
 *  2. add that created Automobile to the LinkedHashMap
 *  3. AutoServer interface should be implemented in BuildAuto and BuildCarModelOptions classes
 */

package server;

import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Properties;

import adapter.BuildAuto;

public class BuildCarModelOptions implements AutoServer{

	private static BuildAuto auto = new BuildAuto();
	
	
	@Override
	public void buildAutoByProperty(Properties carProperties) {
		auto.buildAutoByProperty(carProperties);
	}
	
	@Override
	public void sendObject(ObjectOutputStream obOutputStream, String modelName) {
		auto.sendObject(obOutputStream, modelName);
	}

	@Override
	public ArrayList<String> getModelList() {
		// TODO Auto-generated method stub
		return auto.getModelList();
	}

	@Override
	public void buildAutoByfilename(String filename) {
		// TODO Auto-generated method stub
		auto.buildAuto(filename, "txt");
	}
	
	

}
