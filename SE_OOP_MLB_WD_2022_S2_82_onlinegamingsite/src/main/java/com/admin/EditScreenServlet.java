package com.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
@WebServlet("/editurl")
public class EditScreenServlet extends HttpServlet {

	private final static String query = "select devId,devName,devEmail,devContactNo,devCategory,devPassword from developer where devId=?;";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {	
		//get PrintWriter
		PrintWriter pw = res.getWriter();
		
		//set content type
		res.setContentType("text/html");
		
		//get values
		int devId = Integer.parseInt(req.getParameter("devId"));
		String devName = req.getParameter("devName");
		String devEmail = req.getParameter("devEmail");
		String devContactNo = req.getParameter("devContactNo");
		String devCategory = req.getParameter("devCategory");
		String devPassword = req.getParameter("devPassword");		
		
		//link bootstrap
		pw.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
		
		//load the JDBC driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
				e.printStackTrace();
		}
		//generate the connection
		try(Connection con = DriverManager.getConnection("jdbc:mysql:///onlinegamingsite","root","IT21158186")){
			PreparedStatement ps = con.prepareStatement(query);
			
			//set value
			ps.setInt(1,devId);
			
			//resultSet
			ResultSet rs = ps. executeQuery();
			rs.next();
			pw.println("<div style='margin:auto;width:800px;margin-top:100px;'>");
			pw.println("<form action='edit?devId="+devId+"' method='post' >");
			pw.println("<table class='table table-hover table-striped'>");
			pw.println("<tr>");
			pw.println("<td>Name</td>");
			pw.println("<td><input type='text' name='devName' value='"+rs.getString(2)+"';></td>");
			pw.println("</tr>");		
			pw.println("<tr>");
			pw.println("<td>Email</td>");
			pw.println("<td><input type='email' name='devEmail' value='"+rs.getString(3)+"';></td>");
			pw.println("</tr>");		
			pw.println("<tr>");
			pw.println("<td>Contact No</td>");
			pw.println("<td><input type='text' name='devContactNo' value='"+rs.getString(4)+"';></td>");
			pw.println("</tr>");		
			pw.println("<tr>");
			pw.println("<td>Category</td>");
			pw.println("<td><input type='text' name='devCategory' value='"+rs.getString(5)+"';></td>");
			pw.println("</tr>");		
			pw.println("<tr>");
			pw.println("<td>Password</td>");
			pw.println("<td><input type='text' name='devPassword' value='"+rs.getString(6)+"';></td>");
			pw.println("</tr>");		
			pw.println("<tr>");
			pw.println("<td><button type='submit' class= 'btn btn-outline-success'>Edit</button></td>");
			pw.println("<td><button type='reset'class= 'btn btn-danger'>Cancel</button></td>");
			pw.println("</tr>");		
			pw.println("</table>");
			pw.println("</form>");
			
		}catch(SQLException se) {
			pw.println("<h2 class='bg-danger text-light text-center'>"+se.getMessage()+"</h2>");
			
			se.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		pw.println("<a href='admin_dev_registerform.html'><button class= 'btn btn-dark'>Home</button></a>");
		pw.println("</div>");
		
		//close the stream
		pw.close();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}
}
