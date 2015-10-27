package servlet;

/**
 * @version  1.0
 * @author YuZheng
 * @Date 10/8/2015
 * 
 * This class is a servlet class, it would print the list of model to web page 
 */ 

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.OutputBuffer;

/**
 * @version 2.0
 * @author YuZheng
 * @Date 10/1/2015
 * Servlet implementation class GetModel
 */
@WebServlet("/GetModel")
public class GetModel extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Socket client = null;
	private String localHost = "";
	private ObjectInputStream objectInputStream = null;
	private ObjectOutputStream objectOutputStream = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetModel() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public void destroy() {
		// TODO Auto-generated method stub
    	try {
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.destroy();
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		if(!openConnection())
			System.out.println("No avalible");
	}
	
	public boolean openConnection() {
		try {
			localHost = InetAddress.getLocalHost().getHostAddress();
			client = new Socket(localHost, 2222);
			objectInputStream = new ObjectInputStream(client.getInputStream());
			objectOutputStream = new ObjectOutputStream(client.getOutputStream());
			String serverinput = (String) objectInputStream.readObject();
			System.out.println("Server: " + serverinput);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * 		print the list of model which client sent before
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String serverinput = "";
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		try {
			objectOutputStream.writeObject("3");
			objectOutputStream.flush();
			
			
			// get the massage from server
			serverinput = (String)objectInputStream.readObject();
			System.out.println("Server: " + serverinput);
			
			// get the model list name from server
			ArrayList<String> autoMobileList = null;
			autoMobileList = (ArrayList<String>)objectInputStream.readObject(); // got the list name
			serverinput = (String)objectInputStream.readObject();
			System.out.println("Server: " + serverinput);	// report the transfer is successful
						
			String title = "Model List";
			
			out.println(ServletUtilities.headWithTitle(title) + "<body>\n" 
					+ "<h1 align=\"center\">" + title + "<h1>\n");
			
			if(autoMobileList.size() != 0) {
				out.println("<form align=\"center\" action=\"GetOptionSets\" method=\"post\">");
				// print the all model name
				out.println("<select name = \"modelname\">");
				for(int i = 0; i < autoMobileList.size(); i++) {
					System.out.println(autoMobileList.get(i));
					out.println("<option value =\"" + autoMobileList.get(i)
							+ "\">" + autoMobileList.get(i) + "</option>");
				}
				out.println("</select>");
				out.println("<input type=\"submit\" value=\"Submit\">");
			}
			else {
				out.println("Threre is no model in the system!");
			}
			
			out.println("</form></body></html>");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
