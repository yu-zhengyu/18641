<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String modelinfo = (String) session.getAttribute("modelinfo");
	// get the model name and model basic price
	String[] info = modelinfo.split(":");
	String modelname = info[0];
	float totalprice = Float.parseFloat(info[1]);
	
	// get color
	String color = request.getParameter("color");
	String[] colorinfo = color.split(":");
	
	totalprice += Float.parseFloat(colorinfo[1]);
	
	// get transmission
	String transmission = request.getParameter("transmission");
	String[] transmissioninfo = transmission.split(":");
	
	totalprice += Float.parseFloat(transmissioninfo[1]);
	
	// get Breaks
	String breaks = request.getParameter("brakes");
	String[] breaksinfo = breaks.split(":");
	
	totalprice += Float.parseFloat(breaksinfo[1]);
	
	// get sia
	String sia = request.getParameter("sia");
	String[] siainfo = sia.split(":");
	
	totalprice += Float.parseFloat(siainfo[1]);
	
	// get moonroof
	String moonroof = request.getParameter("moonroof");
	String[] moonroofinfo = moonroof.split(":");
	
	totalprice += Float.parseFloat(moonroofinfo[1]);
	
	
	
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Car configuration</title>
</head>
<body>
		<h1> Here is what you selected </h1>
		<table border="2" cellpadding="10">
			<tr>
				<td><%=modelname%></td>
				<td>base price</td>
				<td><%=info[1] %></td>
			</tr>
			<tr>
				<td>Color</td>
				<td><%=colorinfo[0]%></td>
				<td><%=colorinfo[1]%></td>
			</tr>
			<tr>
				<td>Transmission</td>
				<td><%=transmissioninfo[0]%></td>
				<td><%=transmissioninfo[1]%></td>
			</tr>
			<tr>
				<td>ABS/Traction Control</td>
				<td><%=breaksinfo[0] %></td>
				<td><%=breaksinfo[1] %></td>
			</tr>
			<tr>
				<td>Side Impact Air Bags</td>
				<td><%=siainfo[0] %></td>
				<td><%=siainfo[1] %></td>
			</tr>
			<tr>
				<td>Power Moonroof</td>
				<td><%=moonroofinfo[0] %></td>
				<td><%=moonroofinfo[1] %></td>
			</tr>
			<tr>
                <td><b>Total Cost</b></td>
                <td></td>
                <td><b>$<%=totalprice %></b></td>
            </tr>
		</table>
</body>
</html>