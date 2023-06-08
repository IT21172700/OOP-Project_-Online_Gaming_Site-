package pack2;

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
public class EditListServlet extends HttpServlet {

	private final static String query = "select id,name,details from ugame where id=?;";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {	
		//get PrintWriter
		PrintWriter pw = res.getWriter();
		
		//set content type
		res.setContentType("text/html");
		
		//get values
		int ID = Integer.parseInt(req.getParameter("id"));
		String Name = req.getParameter("name");
		String Details = req.getParameter("details");
	
		
		//link bootstrap
		pw.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
		
		//load the JDBC driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
				e.printStackTrace();
		}
		//generate the connection
		try(Connection con = GameDBconnect.getConnection()){
			PreparedStatement ps = con.prepareStatement(query);
			
			//set value
			ps.setInt(1,ID);
			
			//resultSet
			ResultSet rs = ps. executeQuery();
			rs.next();
			pw.println("<div style='margin:auto;width:800px;margin-top:100px;'>");
			pw.println("<form action='edit?id="+ID+"' method='post' >");
			pw.println("<table class='table table-hover table-striped'>");
			pw.println("<tr>");
			pw.println("<td>Name</td>");
			pw.println("<td><input type='text' name='name' value='"+rs.getString(2)+"';></td>");
			pw.println("</tr>");		
			pw.println("<tr>");
			pw.println("<td>Details</td>");
			pw.println("<td><input type='text' name='details' value='"+rs.getString(3)+"';></td>");
			pw.println("</tr>");		
			pw.println("<tr>");
					
			pw.println("<tr>");
			pw.println("<td><button type='submit' class= 'btn btn-outline-success'>Edit</button></td>");
			pw.println("<td><a href='showglist'><button type='reset'class= 'btn btn-danger'>Cancel</button></td>");
			pw.println("</tr>");		
			pw.println("</table>");
			pw.println("</form>");
			
		}catch(SQLException se) {
			pw.println("<h2 class='bg-danger text-light text-center'>"+se.getMessage()+"</h2>");
			
			se.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		pw.println("<a href='developerdashboard.jsp'><button class= 'btn btn-dark'>Dashboard</button></a>");
		pw.println("</div>");
		
		//close the stream
		pw.close();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}
}
