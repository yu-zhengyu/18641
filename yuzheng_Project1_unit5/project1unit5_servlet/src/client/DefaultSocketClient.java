package client;

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

import model.Automobile;

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
		this.iPort = s.getPort();
	}// constructor

	public void run() {
		if (openConnection()) {
			handleSession();
			closeSession();
		}
	}// run

	public boolean openConnection() {
		try {
			objectInputStream = new ObjectInputStream(sock.getInputStream());
			objectOutputStream = new ObjectOutputStream(sock.getOutputStream());
		} catch (Exception e) {
			if (DEBUG)
				System.err.println("Unable to obtain stream to/from " + iPort);
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public void handleSession() {
		String userinput = "";
		String serverinput = "";
		// wait for user input option
		reader = new BufferedReader(new InputStreamReader(System.in));
		if (DEBUG)
			System.out.println("Handing in " + iPort);

		// telling the user connection is successful
		try {
			serverinput = (String) objectInputStream.readObject();
			System.out.println("Server: " + serverinput);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (true) {
			System.out.println("Here is the option you can choose: ");
			System.out.println("1. Upload");
			System.out.println("2. Configure");
			System.out.println("0. Quit");

			try {
				userinput = reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				continue;
			}

			
			try {
				// if user input 0, tell server quit
				if (userinput.equals("0")) {
					objectOutputStream.writeObject(userinput);
					break;
				}
				else if(userinput.equals("1") || userinput.equals("2")){
					
				}
				else {
					System.out.println("Please input 0, 1 or 2.");
					continue;
				}
				// if user input 1 or 2, do the detail
				// 1. we would upload a property file or txt file
				// 2. configure a car.
				objectOutputStream.writeObject(userinput);
				
				while(true) {
					if(userinput.equals("1")) {
						// server would tell you input a file name
						serverinput = (String)objectInputStream.readObject();
						System.out.println("Server: " + serverinput);
						userinput = reader.readLine();	// input a file name, include file type
						String filename = userinput;	// record the file name
						objectOutputStream.writeObject(userinput);	// send the file name to server
						serverinput = (String) objectInputStream.readObject();	// got massage from server
						System.out.println("Server: " + serverinput);	// server successfully got the file name
						
						String[] type= filename.split("\\.");
						// if the file type is properties
						if(type[1].equals("Properties")) {
							Properties p = new Properties();
							FileInputStream fileInputStream = new FileInputStream(filename);
							p.load(fileInputStream);	// load the properties file
							fileInputStream.close();
							objectOutputStream.writeObject(p);	// send the properties file
							serverinput = (String) objectInputStream.readObject();	// got the server message
							System.out.println("Server: " + serverinput);	// server got the properties
							
						}
						else { // file is .txt file
							try {
								FileInputStream fileInputStream = 
										new FileInputStream(new File(filename));
								BufferedOutputStream bufferedOutputStream = 
										new BufferedOutputStream(sock.getOutputStream());
								byte[] buf = new byte[1024];
								int length = 0;
								
								// upload the txt file to server
								while((length = fileInputStream.read(buf, 0, 1024)) != -1) {
									bufferedOutputStream.write(buf, 0, length);
									bufferedOutputStream.flush();
								}
								fileInputStream.close();
								serverinput = (String) objectInputStream.readObject();
								System.out.println("Server: " + serverinput);
								break;
							}catch (IOException e) {
								e.printStackTrace();
							}
							
						}
						
					}
					// 2. configuration
					else if(userinput.equals("2")) {
						serverinput = (String)objectInputStream.readObject();
						System.out.println("Server: " + serverinput); // server got the message
						@SuppressWarnings("unused")
						ArrayList<String> autoMobileList = null;
						autoMobileList = (ArrayList<String>)objectInputStream.readObject(); // got the list name
						serverinput = (String)objectInputStream.readObject();
						System.out.println("Server: " + serverinput);	// report the transfer is successful
						
						if(autoMobileList.size() == 0) {
							System.out.println("There is no car in the list");
							break;
						}
						
						// print all model we can choose later
						System.out.println("Here is list about our car model");
						System.out.println("----------------------------------");
						for (int i = 0; i < autoMobileList.size(); i++) {
							System.out.println(i + ". " + autoMobileList.get(i));
						}
						
						while(true) {
							System.out.println("Please input the model you want to configure(only number): ");
							userinput = reader.readLine();
							// if the input is not a number, repeat
							if(!userinput.matches("[0-9]+") ||
									Integer.parseInt(userinput) < 0 ||
									Integer.parseInt(userinput) > autoMobileList.size() - 1) 
								continue;
							else {
								break;
							}
						}
						int modelindex = Integer.parseInt(userinput);
						String modelName = autoMobileList.get(modelindex);
						System.out.println("You choose the model: " + modelName);
						
						// send the model to server
						objectOutputStream.writeObject(modelName);
						
						// get the AutoMobile we want
						Automobile currentAutoMoble = (Automobile)objectInputStream.readObject();
						serverinput = (String)objectInputStream.readObject();
						System.out.println("Server: " + serverinput);
						
						System.out.println("Now, choosing the option you like, please input the number:");
						configureAutobyChoice(currentAutoMoble);	// set the option choice
						
						System.out.println("Configuration was done, here is the information:");
						currentAutoMoble.printBasicInfomation();
						currentAutoMoble.printChoice();
						currentAutoMoble.printTotalPrice();
						System.out.println("--------------------------");
					}
					
					break;
				}
				

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
			writer = null;
			reader = null;
			sock.close();
			objectOutputStream.close();
			objectInputStream.close();
		} catch (IOException e) {
			if (DEBUG)
				System.err.println("Error closing socket to " + strHost);
		}
	}

	public void setHost(String strHost) {
		this.strHost = strHost;
	}

	public void setPort(int iPort) {
		this.iPort = iPort;
	}
	
	public void configureAutobyChoice(Automobile auto) {
		BufferedReader userin = new BufferedReader(new InputStreamReader(System.in)); // user input
		String choice = null;
		for(int i = 0; i < auto.getAllOpset().size(); i++) {
			auto.printoption(i);
			System.out.println("Choose one of above, input number: ");
			// get the choice from user
			try {
				choice = userin.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			while(!choice.matches("[0-9]+") || 
					Integer.parseInt(choice) < 0 || 
					Integer.parseInt(choice) > auto.getOptionSize(i)) {
				
				System.out.println("Please input a legal number...");
				try {
					choice = userin.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			int option = Integer.parseInt(choice);
			System.out.println("OK, we got it");
			auto.setOptionChoice(auto.getOptionSetName(i), 
					auto.getOptionName(i, option));
			System.out.println();
			
		}
	}

}// class DefaultSocketClient
