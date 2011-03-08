package com.tapestry4;

/*
 * Listener��Servlet�ļ������������Լ����ͻ��˵����󡢷���˵Ĳ����ȡ�
 * ͨ���������������Զ�����һЩ����������������ߵ��û���������
 * ������һ��HttpSessionʱ���ͼ���sessionCreated
 * (HttpSessionEvent se)�����������Ϳ��Ը�����������1��
 * 
 * �õļ����ӿ������¼�����
 * 
 * ServletContextAttributeListener������ServletContext���ԵĲ������������ӡ�ɾ�����޸����ԡ�
 * 
 * ServletContextListener����ServletContext��
 * ������ServletContextʱ������contextInitialized(ServletContextEvent sce)������
 * ������ServletContextʱ������contextDestroyed(ServletContextEvent sce)������
 * 
 * HttpSessionListener����HttpSession�Ĳ�����
 * ������һ�� Sessionʱ������session Created(HttpSessionEvent se)������
 * ������һ��Sessionʱ������sessionDestroyed (HttpSessionEvent se)������
 * 
 * HttpSessionAttributeListener����HttpSession�е����ԵĲ�����
 * ����Session����һ������ʱ������ attributeAdded(HttpSessionBindingEvent se) ������
 * ����Sessionɾ��һ������ʱ������attributeRemoved(HttpSessionBindingEvent se)����������Session���Ա���������ʱ������attributeReplaced(HttpSessionBindingEvent se) ������
 * 
 */

// ==================== Program Discription =====================
// �������ƣ�ʾ��14-9 : EncodingFilter .java
// ����Ŀ�ģ�ѧϰʹ�ü�����
// ==============================================================
import javax.servlet.http.*;
import javax.servlet.*;

public class OnLineCountListener implements HttpSessionListener,
		ServletContextListener, ServletContextAttributeListener {
	private int count;
	// ServletContext Servlet�����Ļ���
	private ServletContext context = null;

	public OnLineCountListener() {
		count = 0;
		// setContext();
	}

	// ����һ��sessionʱ����
	public void sessionCreated(HttpSessionEvent se) {
		count++;
		setContext(se);

	}

	// ��һ��sessionʧЧʱ����
	public void sessionDestroyed(HttpSessionEvent se) {
		count--;
		setContext(se);
	}

	// ����context�����ԣ���������attributeReplaced��attributeAdded����
	public void setContext(HttpSessionEvent se) {
		se.getSession().getServletContext().setAttribute("onLine",
				new Integer(count));
	}

	// ����һ���µ�����ʱ����
	public void attributeAdded(ServletContextAttributeEvent event) {

		log("attributeAdded('" + event.getName() + "', '" + event.getValue()
				+ "')");

	}

	// ɾ��һ���µ�����ʱ����
	public void attributeRemoved(ServletContextAttributeEvent event) {

		log("attributeRemoved('" + event.getName() + "', '" + event.getValue()
				+ "')");

	}

	// ���Ա����ʱ����
	public void attributeReplaced(ServletContextAttributeEvent event) {

		log("attributeReplaced('" + event.getName() + "', '" + event.getValue()
				+ "')");
	}

	// contextɾ��ʱ����
	public void contextDestroyed(ServletContextEvent event) {

		log("contextDestroyed()");
		this.context = null;

	}

	// context��ʼ��ʱ����
	public void contextInitialized(ServletContextEvent event) {

		this.context = event.getServletContext();
		log("contextInitialized()");

	}

	private void log(String message) {
		System.out.println("ContextListener: " + message);
	}
}
//
//��ʹ��������Ч����Ҫ��web.xml��������ã�������ʾ��
//
//<listener>
//        <listener-class>OnLineCountListener</listener-class>
//    </listener>

//���Գ���
//
//<%@ page contentType="text/html;charset=gb2312" %>
//
//Ŀǰ����������
//
//<font color=red><%=getServletContext().getAttribute("onLine")%></font><br>
//
//�˳��Ự��
//
//<form action="exit.jsp" method=post>
//<input type=submit value="exit">
//</form>
//
//getServletContext().getAttribute("onLine")�����count�ľ���ֵ���ͻ��˵���
//
//<%session.invalidate() ;%>
//
//    ʹSessionʧЧ�������������ͻ�ʹcount��1�� 
