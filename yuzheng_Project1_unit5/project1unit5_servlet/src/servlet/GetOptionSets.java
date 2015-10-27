package servlet;

/**
 * @version  1.0
 * @author YuZheng
 * @Date 10/8/2015
 * 
 * This class is a servlet class, it would print the list of option of 
 * specific model to web page 
 */ 

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Automobile;
import model.OptionSet;

/**
 * @version 2.0
 * @author YuZheng
 * @Date 10/1/2015
 * Servlet implementation class GetOptionSets
 */
@WebServlet("/GetOptionSets")
public class GetOptionSets extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Socket client = null;
	private String localHost = "";
	private ObjectInputStream objectInputStream = null;
	private ObjectOutputStream objectOutputStream = null;

	@Override
	public void destroy() {
		try {
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		super.destroy();
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		if (!openConnection())
			System.out.println("No avalible");
	}

	public boolean openConnection() {
		try {
			localHost = InetAddress.getLocalHost().getHostAddress();
			client = new Socket(localHost, 2222);
			objectInputStream = new ObjectInputStream(client.getInputStream());
			objectOutputStream = new ObjectOutputStream(
					client.getOutputStream());
			String serverinput = (String) objectInputStream.readObject();
			System.out.println("Server: " + serverinput);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetOptionSets() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 *      
	 *      print the list of option about the model user selected
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String serverinput = "";
		String userinput = "";
		try {
			// get the model name from GetModel page
			String modelName = request.getParameter("modelname");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			// tell the server we would start send the model name and
			// get the model instance
			objectOutputStream.writeObject("4");
			objectOutputStream.flush();

			// send the model name
			objectOutputStream.writeObject(modelName);
			objectOutputStream.flush();

			Automobile auto = null;
			auto = (Automobile) objectInputStream.readObject();

			if (auto == null) {
				out.println("There is no such car model");
			}
			
			serverinput = (String)objectInputStream.readObject();
			System.out.println("Server: " + serverinput);	// report the transfer is successful

			// use session to send the model name and model basic price to
			// GetResult.jsp page
			HttpSession session = request.getSession();

			// if there is no session, wait
			while (session == null) {
			}

			session.setAttribute("modelinfo",
					modelName + ":" + auto.getBaseprice());
			
			//get the color option set information
			@SuppressWarnings("unused")
			String colorhtmlString = "<select name=color>"
					+ " <optgroup label=\"color opset\">\n";
			
			// set the color to the list
			String[] colorop = auto.getOptionName("Color");
			for(int i = 0; i < colorop.length; i++) {
				colorhtmlString += "<option value=\"" 
						+ colorop[i] + ":" + 
						auto.getOptionPrice("Color", colorop[i]) + 
						"\">" + colorop[i] + "</option>";
			}
			
			colorhtmlString += "</optgroup>";
			
			//get Transmission
			String TransmissionString = "<select name=transmission>"
					+ " <optgroup label=\"transmission opset\">\n";
			
			String[] transmissionop = auto.getOptionName("Transmission");
			for(int i = 0; i < transmissionop.length; i++) {
				TransmissionString += "<option value=\"" 
						+ transmissionop[i] + ":" + 
						auto.getOptionPrice("Transmission", transmissionop[i]) + 
						"\">" + transmissionop[i] + "</option>";
			}
			TransmissionString += "</optgroup>";
			
			// get Brakes/Traction Control
			String brakesString = "<select name=brakes>"
					+ " <optgroup label=\"brakes opset\">\n";
			String[] breaksop = auto.getOptionName("Brakes/Traction Control");
			
			for(int i = 0; i < breaksop.length; i++) {
				brakesString += "<option value=\"" 
						+ breaksop[i] + ":" + 
						auto.getOptionPrice("Brakes/Traction Control", breaksop[i]) + 
						"\">" + breaksop[i] + "</option>";
			}
			brakesString += "</optgroup>";
			
			// get Side Impact Airbags
			String siaString = "<select name=sia>"
					+ " <optgroup label=\"sia opset\">\n";
			String[] siaop = auto.getOptionName("Side Impact Airbags");
			
			for(int i = 0; i < siaop.length; i++) {
				siaString += "<option value=\"" 
						+ siaop[i] + ":" + 
						auto.getOptionPrice("Side Impact Airbags", siaop[i]) + 
						"\">" + siaop[i] + "</option>";
			}
			siaString += "</optgroup>";
			
			// get moonroof
			String moonroofString = "<select name=moonroof>"
					+ " <optgroup label=\"moonroof opset\">\n";
			
			String[] moonroofop = auto.getOptionName("Power Moonroof");
			
			for(int i = 0; i < moonroofop.length; i++) {
				moonroofString += "<option value=\"" 
						+ moonroofop[i] + ":" + 
						auto.getOptionPrice("Power Moonroof", moonroofop[i]) + 
						"\">" + moonroofop[i] + "</option>";
			}
			moonroofString += "</optgroup>";
			
			// print the html 
			String title = "Car Configuration Information";
			
			out.println(ServletUtilities.headWithTitle(title) + 
					"<body>\n" + "<h1 align=\"center\">" + 
					title + "</h1>");
			out.println("<form action=\"GetResult.jsp\" method=\"get\">");
			out.println("<table border=1 align=\"center\">");
			
			// print the table
			out.println("<tr><td>" + "Make&Model" + "<td>" + modelName);
			out.println("<tr><td>" + "Color" + "<td>" + colorhtmlString);
			out.println("<tr><td>" + "Transmission" + "<td>" + TransmissionString);
			out.println("<tr><td>" + "Brakes/Traction Control" + "<td>" + brakesString);
			out.println("<tr><td>" + "Side Impact Air Bags" + "<td>" + siaString);
			out.println("<tr><td>" + "Power Moonroof" + "<td>" + moonroofString);
			
			//end
			out.println("<tr><td> <td> <input type=\"submit\" value=\"Done\">");
			out.println("</table>");
			out.println("</form ></body></html>");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
