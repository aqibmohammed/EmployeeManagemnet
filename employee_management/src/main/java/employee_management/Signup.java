package employee_management;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = "/signup")
public class Signup extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String eid= req.getParameter("eid");
		String email=req.getParameter("email");
		long phno= Long.parseLong(req.getParameter("phno"));
		String address=req.getParameter("address");
		String pwd=req.getParameter("pwd");
		String ename=req.getParameter("ename");
		
		PrintWriter pw=resp.getWriter();
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management?user=root && password=root");
			PreparedStatement pS=con.prepareStatement("insert into emp_details values(?,?,?,?,?,?)");
			pS.setString(1, eid);
			pS.setString(2, email);
			pS.setLong(3, phno);
			pS.setString(4, address);
			pS.setString(5, pwd);
			pS.setString(6, ename);
			
			pS.executeUpdate();
			
		
				RequestDispatcher rd=req.getRequestDispatcher("signup.html");
				pw.println("<html><body>");
				pw.println("<h4 style='font-size: 10px; position:absolute; left:650px; top:490px; z-index:1;color:#201E43'> Registration Successful Please login to continue</h4>");
				pw.println("</html></body>");
				rd.include(req, resp);
//				resp.sendRedirect("https://www.youtbe.com");
			
		
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			pw.println("<html><body>");
			pw.println("<h4 style='font-size: 10px; position:absolute; left:650px; top:490px; z-index:1;color:red'> *This employee is already registered </h4>");
			pw.println("</html></body>");
			RequestDispatcher rd=req.getRequestDispatcher("signup.html");
			
			rd.include(req, resp);
		}
	}

}
