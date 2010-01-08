package net.olstudio.test.categories.selenium;

import junit.framework.TestCase;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

// Oliver �� 2008-7-21 ����02:16:41 ����ע��:
// ��Ҫ�������� java -jar selenium-server.jar ���� selenium server
public class HelloSelenium extends TestCase {
	private Selenium selenium;

	public void setUp() throws Exception {
		String url = "http://www.javaeye.com";
		
		// 1, Selenium Server����ʱ���ڵ�������,�ڱ�������������localhost
		
		// 2, 4444��SeleniumServerĬ�ϵĶ˿�  
		
		// 3, "*firefox"ָ����ʹ��firefox���� e.g. "*firefox", "*iexplore" or "c:\\program files\\internet explorer\\iexplore.exe"
		
		// 4, url��Ҫ���Ե���վ����ʼUrl
		selenium = new DefaultSelenium("localhost", 4444, "*iexplore", url);
		selenium.start();
	}

	protected void tearDown() throws Exception {
		selenium.stop();
	}

	public void testGoogleTestSearch() throws Throwable {
		// ��ҳ��
		selenium.open("/search/");
		// ����
		selenium.type("query", "selenium");
		// ���"��̳"
		selenium.click("link=��̳");
		// ���ó�ʱ
		selenium.waitForPageToLoad("5000");
		// Ҫ��֤���ص�ҳ��ı����ָ������ͬ
		assertEquals("JavaEye��̳Ƶ����ҳ", selenium.getTitle());
	}
}