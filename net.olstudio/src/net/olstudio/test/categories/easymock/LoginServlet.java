package net.olstudio.test.categories.easymock;

/**  
 * LoginServlet.java  
 * Author: Liao Xue Feng, www.crackj2ee.com  
 */
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String passWord = request.getParameter("password");
		
		if ("admin".equals(username) && "123456".equals(passWord)) {
			ServletContext context = getServletContext();
			
			RequestDispatcher dispatcher = context
					.getNamedDispatcher("dispatcher");
			
			dispatcher.forward(request, response);
		} else {
			throw new RuntimeException("Login failed.");
		}
	}
}