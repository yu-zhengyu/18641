package server;

/**
 * @version 1.0
 * @author YuZheng
 * @Date 10/1/2015
 * These code is borrow from professor's ppt, it implement all action which running in a server.
 */

import java.net.*;
import java.util.ArrayList;
import java.util.Properties;
import java.io.*;

public class DefaultSocketClient extends Thread implements
		SocketClientInterface, SocketClientConstants {

	private BufferedReader reader;
	private BufferedWriter writer;
	private Socket sock;
	private String strHost;
	private int iPort;
	private ObjectInputStream objectInputStream = null;
	private ObjectOutputStream objectOutputStream = null;

	public DefaultSocketClient(Socket s) {
		this.sock = s;
		this.iPort = iDAYTIME_PORT;
	}// constructor

	public void run() {
		if (openConnection()) {
			handleSession();
			closeSession();
		}
	}// run

	public boolean openConnection() {
		try {
			objectOutputStream = new ObjectOutputStream(sock.getOutputStream());
			objectInputStream = new ObjectInputStream(sock.getInputStream());
		} catch (Exception e) {
			if (DEBUG)
				System.err.println("Unable to obtain stream to/from " + iPort);
			return false;
		}
		return true;
	}

	public void handleSession() {
		String strInput = "";
		String strOutput = "";
		if (DEBUG)
			System.out.println("Handing in " + iPort);

		// telling the user connection is successful
		strOutput = "Have connected to " + iPort;
		try {
			objectOutputStream.writeObject(strOutput);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		BuildCarModelOptions buildCar = new BuildCarModelOptions();
		while (true) {
			try {
				strInput = (String) objectInputStream.readObject();

			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				break;
			}

			int comment = Integer.parseInt(strInput);

			if (comment == 0) {
				System.out.println("One user has gone~~");
				break;
			}

			switch (comment) {
			case 1:
				// upload the property file.
				// send get the quest reply
				try {
					objectOutputStream
							.writeObject("Got the upload order, please input the file name: ");
					String filename = (String) objectInputStream.readObject();
					objectOutputStream.writeObject("System has got the file "
							+ filename);
					// define which type of the file
					String[] type = filename.split("\\.");
					
					if (type[1].equals("Properties")) {
						Properties p = null;
						p = (Properties) objectInputStream.readObject();

						// tell client the action is successful
						objectOutputStream
								.writeObject("The car is built successfully!!!");

						// run an instance of BuildCarModelOption class to build
						// Automobile using the Properties file.
						buildCar.buildAutoByProperty(p);
					}
					// if the input file is txt file
					else {
						FileOutputStream fileOutputStream = 
								new FileOutputStream(new File(filename));
						
						BufferedInputStream bufferedInputStream = 
								new BufferedInputStream(sock.getInputStream());
						byte[] buf = new byte[10240];
						int length = 0;
						while ((length = bufferedInputStream.read(buf, 0, 10240)) > 0) {
							fileOutputStream.write(buf, 0, length);
							fileOutputStream.flush();
							break;
						}
						fileOutputStream.close();
						
						// build the automobile using the txt file
						buildCar.buildAutoByfilename(filename);
						
						// delete the file in server end
						File file = new File(filename);
						file.delete();
						objectOutputStream
						.writeObject("The car is built successfully!!!");
					}

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					break;
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					break;
				}
				break;
			case 2:

				// 2. configuration
				try {
					objectOutputStream.writeObject("Get the configuration oder!!");
					ArrayList<String> autoMobilelist = buildCar.getModelList();
					objectOutputStream.writeObject(autoMobilelist);	// send the list to client
					objectOutputStream.writeObject("Sent the AutoList, please read it");
					
					// send the AutoMobile
					String modelName = null;
					modelName = (String)objectInputStream.readObject();
					buildCar.sendObject(objectOutputStream, modelName);
					
					// tell the client, transfer is done
					objectOutputStream.writeObject("Transfer AutoMobile done!");
					
					
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// Provide a list of available models to the client.
				break;
				
			case 3:
				try {
					objectOutputStream.writeObject("Get the configuration oder!!");
					ArrayList<String> autoMobilelist = buildCar.getModelList();
					objectOutputStream.writeObject(autoMobilelist);
					objectOutputStream.writeObject("Sent the AutoList, please read it");
				} catch (IOException e) {
					// TODO: handle exception
					e.printStackTrace();
					break;
				}
				break;
				
			case 4:
				// get the model name from web page
				try{
					// send the AutoMobile
					String modelName = (String)objectInputStream.readObject();
					buildCar.sendObject(objectOutputStream, modelName);
					// tell the client, transfer is done
					objectOutputStream.writeObject("Transfer AutoMobile done!");
					
				} catch (IOException | ClassNotFoundException e) {
					// TODO: handle exception
					break;
				}
				break;
				
			default:
				System.out
						.println("Please input 1 or 2, 0 is quit the system.");
				break;
			}

		}

	}

	public void sendOutput(String strOutput) {
		try {
			writer.write(strOutput, 0, strOutput.length());
		} catch (IOException e) {
			if (DEBUG)
				System.out.println("Error writing to " + strHost);
		}
	}

	public void handleInput(String strInput) {
		System.out.println(strInput);
	}

	public void closeSession() {
		try {
			objectInputStream.close();
			objectInputStream.close();
			writer = null;
			reader = null;
			sock.close();
		} catch (IOException e) {
			if (DEBUG)
				System.err.println("Error closing socket to " + strHost);
		}
		System.out.println("END");
	}

	public void setHost(String strHost) {
		this.strHost = strHost;
	}

	public void setPort(int iPort) {
		this.iPort = iPort;
	}

}// class DefaultSocketClient
