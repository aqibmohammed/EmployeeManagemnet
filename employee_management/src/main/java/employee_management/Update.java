package employee_management;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet (urlPatterns = "/update")
public class Update extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		String eid=(String) session.getAttribute("eid");
//		 if (eid == null || eid.isEmpty()) {
//	            System.out.println("EID is null or empty");
//	            resp.sendRedirect("error.html"); // Redirect to an error page
//	            return;
//	        }
		String ename=req.getParameter("ename");
		String email=req.getParameter("email");
		long phno= Long.parseLong(req.getParameter("phno"));
		String address=req.getParameter("address");
		String pwd=req.getParameter("pwd");
		
		Connection con =null;
		PreparedStatement pS=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management?user=root && password=root");
			 pS=con.prepareStatement("update emp_details set email=?,phone_no=?,address=?,password=?,ename=? where emp_id=?");
			 pS.setString(1, email);
			 pS.setLong(2, phno);
			 pS.setString(3, address);
			 pS.setString(4, pwd);
			 pS.setString(5, ename);
			 pS.setString(6, eid);
			 pS.executeUpdate();
			 
			 System.out.println(eid);
			 System.out.println(ename);
//			 session.setAttribute("eid", eid);
//			 RequestDispatcher rd=req.getRequestDispatcher("/viewdetails.html/");
				resp.sendRedirect("home.html");
//			 rd.include(req, resp);
				

		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
