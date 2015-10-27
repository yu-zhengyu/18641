package client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class CarModelOptionsIO {
	public static void main(String[] args) throws UnknownHostException {
		String localHost = "";
		Socket client = null;
		try {
			localHost = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Client Starting.......");
		try {
			client = new Socket(localHost, 2222);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DefaultSocketClient de = new DefaultSocketClient(client);
		System.out.println("Client has started, please input what you want to do...");
		de.run();
		
	}
}
