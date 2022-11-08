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
@WebServlet("/edit")
public class EditRecordServlet extends HttpServlet {
	private final static String query = "update developer set devName=?,devEmail=?,devContactNo=?,devCategory=?,devPassword=? where devId=?;";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//get PrintWriter
		PrintWriter pw = res.getWriter();
		
		//set content type
		res.setContentType("text/html");
		
		//link the bootstrap
		pw.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
		
		//get the values
		int devId = Integer.parseInt(req.getParameter("devId"));
		String devName = req.getParameter("devName");
		String devEmail = req.getParameter("devEmail");
		String devContactNo = req.getParameter("devContactNo");
		String devCategory = req.getParameter("devCategory");
		String devPassword = req.getParameter("devPassword");
		
		//load the JDBC driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//generate the connection
		try(Connection con = DriverManager.getConnection("jdbc:mysql:///onlinegamingsite","root","IT21158186");
				PreparedStatement ps = con.prepareStatement(query);){
			
			//set the values
			ps.setString(1, devName);
			ps.setString(2, devEmail);
			ps.setString(3, devContactNo);
			ps.setString(4, devCategory);
			ps.setString(5,devPassword);
			ps.setInt(6, devId);
			
			//execute the query
			int count = ps.executeUpdate();
			pw.println("<div class='card' style='margin:auto;width:300px;margin-top:100px'>");
			if(count==1) {
				pw.println("<h2 class='bg-danger text-light text-center'>Record Edited Successfully</h2>");
			}else {
				pw.println("<h2 class='bg-danger text-light text-center'>Record Not Edited</h2>");
			}
		}catch(SQLException se) {
			pw.println("<h2 class='bg-danger text-light text-center'>"+se.getMessage()+"</h2>");
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		pw.println("<a href='admin_dev_registerform.html'><button class='btn btn-outline-success'>Home</button></a>");
		pw.println("&nbsp; &nbsp;");
		pw.println("<a href='showdevlist'><button class='btn btn-outline-success'>Show Developer Details</button></a>");
		pw.println("</div>");
		
		//close the stram
		pw.close();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}
}
