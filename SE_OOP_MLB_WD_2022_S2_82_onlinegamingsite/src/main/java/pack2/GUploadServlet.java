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
@WebServlet("/gupload")
public class GUploadServlet extends HttpServlet {


	private final static String query = "insert into ugame (name, details, type, devName, filepath, gameimage) values(?,?,?,?,?,?)";
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {	
		//get PrintWriter
		PrintWriter pw = res.getWriter();
		
		//set content type
		res.setContentType("text/html");
		
		//link bootstrap
		pw.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
		
		//get the parameter values that developer enter the registration form
		String Name = req.getParameter("name");
		String Details = req.getParameter("details");
		String Category = req.getParameter("type");
		String Developer = req.getParameter("devName");
		String File = req.getParameter("filepath"); 		
		String Image = req.getParameter("gameimage"); 		
		System.out.println(Name);
		
		
	

		//load the JDBC driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
				e.printStackTrace();
		}
		//generate the connection
		try(Connection con = GameDBconnect.getConnection()){
			PreparedStatement ps = con.prepareStatement(query);
				
			//set the values to parameters
				ps.setString(1, Name);
				ps.setString(2, Details);
				ps.setString(3, Category);
				ps.setString(4, Developer);
				ps.setString(5, File);
				ps.setString(6, Image);
				
				//execute the query
				int count = ps.executeUpdate();
				pw.println("<div class='card' style='margin:auto;width:300px;margin-top:100px'>");
				
				if(count==1) {
					pw.println("<h2 class='bg-danger text-light text-center'>New Game Uploaded Successfully!</h2>");
				}else {
					
					pw.println("<h2 class='bg-danger text-light text-center'>Upload Unsuccessful!.. Error Occured..</h2>");
				}
				
		}catch(SQLException se) {
			pw.println("<h2 class='bg-danger text-dark text-center'>"+se.getMessage()+"</h2>");
			
			se.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		pw.println("<a href='developerdashboard.jsp'><button class= 'btn btn-outline-success'>Home</button></a>");
		pw.println("<a href='showglist'><button class= 'btn btn-outline-success'>Back to My Game List</button></a>");
		pw.println("</div>");
		//close the stream
		pw.close();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}
	
	
}
