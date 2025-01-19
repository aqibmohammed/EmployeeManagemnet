package employee_management;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/delete")
public class Delete extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	HttpSession session=req.getSession();
	String eid=(String)session.getAttribute("eid");
	
	Connection con = null;
	PreparedStatement pS = null;
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management?user=root && password=root");
		pS=con.prepareStatement("delete from emp_details where emp_id=?");
		pS.setString(1, eid);
		pS.executeUpdate();
		
		resp.sendRedirect("index.html");
		
	} catch (Exception e) {
		e.printStackTrace();
		
	}
	}
	

}
