package employee_management;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(urlPatterns = "/home")
public class ViewDetailsHome extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		String eid= (String) session.getAttribute("eid");
		  
		
		Connection con=null;
		PreparedStatement pS=null;
		PrintWriter pw=resp.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management?user=root && password=root");
			pS=con.prepareStatement("select * from emp_details where emp_id =?");
			pS.setString(1, eid);
			ResultSet rS=pS.executeQuery();
			rS.next();
			
			String email=rS.getString(2);
			long phno=rS.getLong(3);
			String address=rS.getString(4);
			
			String ename=rS.getString(6);
			
			session.setAttribute("eid", eid);
			
			pw.println("<html>");
			
			pw.println("<head>\r\n"
					+ "    <meta charset=\"UTF-8\">\r\n"
					+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
					+ "    <title>Update</title>\r\n"
					+ "    <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n"
					+ "    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\r\n"
					+ "    <link href=\"https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap\" rel=\"stylesheet\">\r\n"
					+ "\r\n"
					+ "    <style>\r\n"
					+ "        body {\r\n"
					+ "            font-family: \"Montserrat\", sans-serif;\r\n"
					+ "            margin: 0;\r\n"
					+ "            padding: 0;\r\n"
					+ "            font-family: Montserrat, sans-serif;\r\n"
					+ "            background: linear-gradient(10deg, #EEEEEE, #508C9B);\r\n"
					+ "            box-sizing: border-box;\r\n"
					+ "            height: 100vh;\r\n"
					+ "            overflow: hidden;\r\n"
					+ "        }\r\n"
					+ "\r\n"
					+ "        #main {\r\n"
					+ "            position: absolute;\r\n"
					+ "            top: 20%;\r\n"
					+ "            left: 35%;\r\n"
					+ "            display: flex;\r\n"
					+ "            height: 40vh;\r\n"
					+ "            justify-content: center;\r\n"
					+ "            /* align-items: center; */\r\n"
					+ "            flex-direction: column;\r\n"
					+ "            background-color: white;\r\n"
					+ "            width: 30%;\r\n"
					+ "            padding: 20px 0px 20px 130px;\r\n"
					+ "            border-radius: 10px;\r\n"
					+ "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\r\n"
					+ "        }\r\n"
					+ "        #main h2{\r\n"
					+ "            /* position: absolute; */\r\n"
					+ "            color:#508C9B;\r\n"
					+ "        }\r\n"
					+ "\r\n"
					+ "        .info-row {\r\n"
					+ "            display: flex;\r\n"
					+ "            justify-content: flex-start;\r\n"
					+ "            width: 100%;\r\n"
					+ "            margin-bottom: 10px;\r\n"
					+ "        }\r\n"
					+ "\r\n"
					+ "        .info-row span {\r\n"
					+ "            width: 30%;\r\n"
					+ "            font-weight: bold;\r\n"
					+ "        }\r\n"
					+ "\r\n"
					+ "        .info-row p {\r\n"
					+ "            width: 70%;\r\n"
					+ "            margin: 0;\r\n"
					+ "        }\r\n"
					+ "\r\n"
					+ "        #btns {\r\n"
					+ "            margin-top: 40px;\r\n"
					+ "        }\r\n"
					+ "\r\n"
					+ "        #btns input[type='submit']{\r\n"
					+ "            margin: 0px 10px;\r\n"
					+ "            text-decoration: none;\r\n"
					+ "            padding: 10px 20px;\r\n"
					+ "            background-color: #508C9B;\r\n"
					+ "            color: white;\r\n"
					+ "            border-radius: 5px;\r\n"
					+ "            border: none;   \r\n"
					+ "        }\r\n"
					+ "        #btns input[type='submit']:hover{\r\n"
					+ "            background-color: #406d75;\r\n"
					+ "        }\r\n"
					+ "        #btns a {\r\n"
					+ "            margin: 10px 10px;\r\n"
					+ "            text-decoration: none;\r\n"
					+ "            padding: 10px 20px;\r\n"
					+ "            background-color: #508C9B;\r\n"
					+ "            color: white;\r\n"
					+ "            border-radius: 5px;\r\n"
					+ "        }\r\n"
					+ "\r\n"
					+ "        #btns a:hover {\r\n"
					+ "            background-color: #406d75;\r\n"
					+ "        }\r\n"
					+ "        #nt{\r\n"
					+ "            margin-left: 36%;\r\n"
					+ "            margin-top: 33%;\r\n"
					+ "            width: 550px;\r\n"
					+ "        }\r\n"
					+ "\r\n"
					+ "        #nt span{\r\n"
					+ "            /* color: red; */\r\n"
					+ "            text-decoration: underline;\r\n"
					+ "        }\r\n"
					+ "    </style>\r\n"
					+ "</head>\r\n"
					+ "<body>\r\n"
					+ "\r\n"
					+ "    <div id=\"main\">\r\n"
					+ "        <h2>Employee Details</h2>\r\n"
					+ "        <div class=\"info-row\">\r\n"
					+ "            <span>Employee ID:</span> \r\n"
					+ "            <p>"+eid+"</p>\r\n"
					+ "        </div>\r\n"
					+ "        <div class=\"info-row\">\r\n"
					+ "            <span>Emp Name:</span> \r\n"
					+ "            <p>"+ename+"</p>\r\n"
					+ "        </div>\r\n"
					+ "        <div class=\"info-row\">\r\n"
					+ "            <span>Email:</span> \r\n"
					+ "            <p>"+email+"</p>\r\n"
					+ "        </div>\r\n"
					+ "        <div class=\"info-row\">\r\n"
					+ "            <span>Phone No:</span> \r\n"
					+ "            <p>"+phno+"</p>\r\n"
					+ "        </div>\r\n"
					+ "        <div class=\"info-row\">\r\n"
					+ "            <span>Address:</span> \r\n"
					+ "            <p>"+address+"</p>\r\n"
					+ "        </div>\r\n"
					+ "\r\n"
					+ "        <div id=\"btns\">\r\n"
					+ "            <a href=\"update.html\">Update</a>\r\n"
					+ "            <a href=\"delete.html\">Delete</a>\r\n"
					+ "        </div>\r\n"
					+ "    </div>\r\n"
					+ "\r\n"
					+ "    <p id=\"nt\"> <span>Note:</span> To update your details please click on Update button and to delete your data click on Delete button </p>\r\n"
					+ "\r\n"
					+ "</body>");
			
			pw.println("</html>");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}

}
