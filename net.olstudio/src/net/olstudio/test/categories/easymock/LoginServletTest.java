package net.olstudio.test.categories.easymock;

import javax.servlet.http.HttpServletRequest;

import junit.framework.TestCase;

import org.easymock.MockControl;

public class LoginServletTest extends TestCase {

	public void testLoginFailed() throws Exception {
		MockControl mc = MockControl.createControl(HttpServletRequest.class);
		
		HttpServletRequest request = (HttpServletRequest) mc.getMock();

		// set Mock Object behavior:  
		// ~=====================================================================
		request.getParameter("username");
		mc.setReturnValue("admin", 1);
		request.getParameter("password");
		mc.setReturnValue("1234", 1);
		
		// ok, all behaviors are set!  
		// ~=====================================================================
		mc.replay();
		
		// now start test:  
		// ~=====================================================================
		LoginServlet servlet = new LoginServlet();
		try {
			servlet.doPost(request, null);
			fail("Not caught exception!");
		} catch (RuntimeException re) {
			assertEquals("Login failed.", re.getMessage());
		}
		
		// verify:  
		// ~=====================================================================
		mc.verify();
	}
}