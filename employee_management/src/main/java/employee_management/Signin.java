package employee_management;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(urlPatterns = "/signin")
public class Signin extends HttpServlet{

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String eid=req.getParameter("eid");
		String pwd=req.getParameter("pwd");
		PrintWriter pw=resp.getWriter();
		System.out.println(eid);
		
	
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management?user=root && password=root");
			PreparedStatement pS=con.prepareStatement("select * from emp_details where emp_id =? and password=?");
			pS.setString(1, eid);
			pS.setString(2,pwd);
			ResultSet rS= pS.executeQuery();
			
			HttpSession session=req.getSession();
			RequestDispatcher rd=null;
			if(rS.next()) {
				rd=req.getRequestDispatcher("home.html");
				session.setAttribute("eid", eid);
					rd.forward(req, resp);
			}else {
				 rd=req.getRequestDispatcher("signin.html");
				pw.println("<html><body>");
				pw.println("<h1 style='font-size: 10px; position:absolute; left:650px; top:365px;z-index:1;color:red'>Please Check Your EmpID & Password and try again</h1>");
				pw.println("</html></body>");
				rd.include(req, resp);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
}
	
}
