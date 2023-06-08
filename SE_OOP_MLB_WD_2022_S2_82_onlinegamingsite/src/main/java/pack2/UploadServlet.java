package pack2;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/showglist")
public class UploadServlet extends HttpServlet {

private final static String query = "select id, name, details, type, devName, filepath, gameimage  from ugame";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {	
		//get PrintWriter
		PrintWriter pw = res.getWriter();
		
		//set content type
		res.setContentType("text/html");
		
		//link bootstrap
		pw.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
		
		pw.println("<button type=\"button\" class=\"btn btn-primary btn-block\"margin-top:50px\">Games Details</button>");
		
		//load the JDBC driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
				e.printStackTrace();
		}
		//generate the connection
		try(Connection con = GameDBconnect.getConnection()){
			PreparedStatement ps = con.prepareStatement(query);

			//resultSet
			ResultSet rs = ps. executeQuery();
			pw.println("<div style='margin:auto;width:1200px;margin-top:100px;'>");
			pw.println("<table class='table table-hover table-striped'>");
			pw.println("<tr>");
			
			pw.println("<th>ID</th>");
			pw.println("<th>Name</th>");
			pw.println("<th>Details</th>");
			pw.println("<th>Category</th>");
			pw.println("<th>Developer</th>");
			pw.println("<th>File</th>");
			pw.println("<th>Image</th>");
			pw.println("<th>Update</th>");
			pw.println("<th>Delete</th>");
			pw.println("</tr>");
			while(rs.next()) {
				pw.println("<tr>");
				pw.println("<td>"+rs.getInt(1)+"</td>");
				pw.println("<td>"+rs.getString(2)+"</td>");
				pw.println("<td>"+rs.getString(3)+"</td>");
				pw.println("<td>"+rs.getString(4)+"</td>");
				pw.println("<td>"+rs.getString(5)+"</td>");
				pw.println("<td>"+rs.getString(6)+"</td>");
				pw.println("<td>"+rs.getString(7)+"</td>");
				pw.println("<td><a href='editurl?id="+rs.getInt(1)+"'>Update</a></td>");
				pw.println("<td><a href='deleteurl?id="+rs.getInt(1)+"'>Delete</a></td>");
				pw.println("</tr>");
				
			}
			pw.println("</table>");
			
		}catch(SQLException se) {
			pw.println("<h2 class='bg-danger text-dark text-center'>"+se.getMessage()+"</h2>");
			
			se.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		pw.println("<a href='developerdashboard.jsp'><button class= 'btn btn-dark'>Dashboard</button></a>");
		pw.println("<a href='gameuploadform.jsp'><button class= 'btn btn-dark'>Upload a New Game</button></a>");
		pw.println("</div>");
		
		//close the stream
		pw.close();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}
}
