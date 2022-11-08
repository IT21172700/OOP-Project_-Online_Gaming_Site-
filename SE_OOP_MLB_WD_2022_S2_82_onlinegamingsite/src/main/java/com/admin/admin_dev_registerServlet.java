package com.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/devregister")
public class admin_dev_registerServlet extends HttpServlet {


	private final static String query = "insert into developer (devName, devEmail, devContactNo, devCategory, devPassword) values(?,?,?,?,?)";
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {	
		//get PrintWriter
		PrintWriter pw = res.getWriter();
		
		//set content type
		res.setContentType("text/html");
		
		//link bootstrap
		pw.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
		
		//get the parameter values that developer enter the registration form
		String Name = req.getParameter("devName");
		String Email = req.getParameter("devEmail");
		String Contact_No = req.getParameter("devContactNo");
		String Category = req.getParameter("devCategory");
		String Password = req.getParameter("devPassword");
		
		//load the JDBC driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
				e.printStackTrace();
		}
		//generate the connection
		try(Connection con = DriverManager.getConnection("jdbc:mysql:///onlinegamingsite","root","IT21158186")){
			PreparedStatement ps = con.prepareStatement(query);
				
			//set the values to parameters
				ps.setString(1, Name);
				ps.setString(2, Email);
				ps.setString(3, Contact_No);
				ps.setString(4, Category);
				ps.setString(5, Password);
				
				//execute the query
				int count = ps.executeUpdate();
				pw.println("<div class='card' style='margin:auto;width:300px;margin-top:100px'>");
				
				if(count==1) {
					pw.println("<h2 class='bg-danger text-light text-center'>Developer Account Created Successfully!</h2>");
				}else {
					
					pw.println("<h2 class='bg-danger text-light text-center'>Unsuccessful! Error Occured..</h2>");
				}
				
		}catch(SQLException se) {
			pw.println("<h2 class='bg-danger text-dark text-center'>"+se.getMessage()+"</h2>");
			
			se.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		pw.println("<a href='admin_dev_registerform.html'><button class= 'btn btn-outline-success'>Home</button></a>");
		pw.println("</div>");
		//close the stream
		pw.close();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}
	
	
}
