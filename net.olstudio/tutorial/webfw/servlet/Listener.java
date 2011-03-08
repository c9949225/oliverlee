package com.tapestry4;

/*
 * Listener是Servlet的监听器，它可以监听客户端的请求、服务端的操作等。
 * 通过监听器，可以自动激发一些操作，比如监听在线的用户的数量。
 * 当增加一个HttpSession时，就激发sessionCreated
 * (HttpSessionEvent se)方法，这样就可以给在线人数加1。
 * 
 * 用的监听接口有以下几个：
 * 
 * ServletContextAttributeListener监听对ServletContext属性的操作，比如增加、删除、修改属性。
 * 
 * ServletContextListener监听ServletContext。
 * 当创建ServletContext时，激发contextInitialized(ServletContextEvent sce)方法；
 * 当销毁ServletContext时，激发contextDestroyed(ServletContextEvent sce)方法。
 * 
 * HttpSessionListener监听HttpSession的操作。
 * 当创建一个 Session时，激发session Created(HttpSessionEvent se)方法；
 * 当销毁一个Session时，激发sessionDestroyed (HttpSessionEvent se)方法。
 * 
 * HttpSessionAttributeListener监听HttpSession中的属性的操作。
 * 当在Session增加一个属性时，激发 attributeAdded(HttpSessionBindingEvent se) 方法；
 * 当在Session删除一个属性时，激发attributeRemoved(HttpSessionBindingEvent se)方法；当在Session属性被重新设置时，激发attributeReplaced(HttpSessionBindingEvent se) 方法。
 * 
 */

// ==================== Program Discription =====================
// 程序名称：示例14-9 : EncodingFilter .java
// 程序目的：学习使用监听器
// ==============================================================
import javax.servlet.http.*;
import javax.servlet.*;

public class OnLineCountListener implements HttpSessionListener,
		ServletContextListener, ServletContextAttributeListener {
	private int count;
	// ServletContext Servlet上下文环境
	private ServletContext context = null;

	public OnLineCountListener() {
		count = 0;
		// setContext();
	}

	// 创建一个session时激发
	public void sessionCreated(HttpSessionEvent se) {
		count++;
		setContext(se);

	}

	// 当一个session失效时激发
	public void sessionDestroyed(HttpSessionEvent se) {
		count--;
		setContext(se);
	}

	// 设置context的属性，它将激发attributeReplaced或attributeAdded方法
	public void setContext(HttpSessionEvent se) {
		se.getSession().getServletContext().setAttribute("onLine",
				new Integer(count));
	}

	// 增加一个新的属性时激发
	public void attributeAdded(ServletContextAttributeEvent event) {

		log("attributeAdded('" + event.getName() + "', '" + event.getValue()
				+ "')");

	}

	// 删除一个新的属性时激发
	public void attributeRemoved(ServletContextAttributeEvent event) {

		log("attributeRemoved('" + event.getName() + "', '" + event.getValue()
				+ "')");

	}

	// 属性被替代时激发
	public void attributeReplaced(ServletContextAttributeEvent event) {

		log("attributeReplaced('" + event.getName() + "', '" + event.getValue()
				+ "')");
	}

	// context删除时激发
	public void contextDestroyed(ServletContextEvent event) {

		log("contextDestroyed()");
		this.context = null;

	}

	// context初始化时激发
	public void contextInitialized(ServletContextEvent event) {

		this.context = event.getServletContext();
		log("contextInitialized()");

	}

	private void log(String message) {
		System.out.println("ContextListener: " + message);
	}
}
//
//了使监听器生效，需要在web.xml里进行配置，如下所示：
//
//<listener>
//        <listener-class>OnLineCountListener</listener-class>
//    </listener>

//测试程序：
//
//<%@ page contentType="text/html;charset=gb2312" %>
//
//目前在线人数：
//
//<font color=red><%=getServletContext().getAttribute("onLine")%></font><br>
//
//退出会话：
//
//<form action="exit.jsp" method=post>
//<input type=submit value="exit">
//</form>
//
//getServletContext().getAttribute("onLine")获得了count的具体值。客户端调用
//
//<%session.invalidate() ;%>
//
//    使Session失效，这样监听器就会使count减1。 
