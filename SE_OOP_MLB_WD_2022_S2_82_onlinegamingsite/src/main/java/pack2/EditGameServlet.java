package pack2;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
@WebServlet("/edit")
public class EditGameServlet extends HttpServlet {
	private final static String query = "update ugame set name=?,details=? where id=?;";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//get PrintWriter
		PrintWriter pw = res.getWriter();
		
		//set content type
		res.setContentType("text/html");
		
		//link the bootstrap
		pw.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
		
		//get the values
		int ID = Integer.parseInt(req.getParameter("id"));
		String Name = req.getParameter("name");
		String Details = req.getParameter("details");
		
		
		
		//load the JDBC driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//generate the connection
		try(Connection con = GameDBconnect.getConnection();
				PreparedStatement ps = con.prepareStatement(query);){
			
			//set the values
			ps.setString(1, Name);
			ps.setString(2, Details);
			
			
			ps.setInt(3, ID);
			
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
		pw.println("<a href='developerdashboard.jsp'><button class='btn btn-outline-success'>Dashboard</button></a>");
		pw.println("&nbsp; &nbsp;");
		pw.println("<a href='showglist'><button class='btn btn-outline-success'>Show Game Details</button></a>");
		pw.println("</div>");
		
		//close the stram
		pw.close();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}
}
