package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @version 1.0
 * @author YuZheng
 * @Date 10/1/2015
 * 
 *       This is the main function that can start the server.
 * 
 */

public class Server {

	public static void main(String[] args) {
		int port = 2222;
		ServerSocket serverSocket = null;
		System.out.println("The server is starting....");
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Listening on the port: " + port);
		System.out.println("Server has started, waiting for client....");

		DefaultSocketClient defale;
		try {
			while (true) {
				Socket client = serverSocket.accept();
				defale = new DefaultSocketClient(client);
				defale.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println("END");
	}

}
