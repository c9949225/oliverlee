package net.olstudio.test.categories.selenium;

import junit.framework.TestCase;

import org.openqa.selenium.server.SeleniumServer;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

// ������Ҫ
// net.olstudio.test.categories.jetty.HelloJetty
// ����������
// Ȼ�����������������jetty�������������
public class SeleniumSelfRun extends TestCase{

	private Selenium selenium;
	//  selenium-server.jar
	//	�屾����????
	//	�����еİ���һ��......
	//	....��Ȼselenium�̻���һ��jetty������.....
	//	�ҵ�....5.1.x
	//	���ҵ����jetty6.1��ɾȥ.... 
	//  ~=====================================================================
	//  ����ǰ��д������jetty�������Ĵ������������,��Ϊ��jetty6.1��д��,Ҫ�ĳ�5.1��д��
	private SeleniumServer ss;

	// �����Ϊ�Լ�����SeleniumServer,����������������
	public void setUp() throws Exception {
		ss = new SeleniumServer(SeleniumServer.DEFAULT_PORT, true, false);
		ss.start();
		String url = "http://www.javaeye.com";
		selenium = new DefaultSelenium("localhost",SeleniumServer.DEFAULT_PORT, "*iexplore", url);
		selenium.start();
	}

	protected void tearDown() throws Exception {
		selenium.stop();
		ss.stop();
	}

	public void testGoogleTestSearch() throws Throwable {
		selenium.open("/search/");
		selenium.type("query", "selenium");
		selenium.click("link=��̳");
		selenium.waitForPageToLoad("5000");
		assertEquals("JavaEye��̳Ƶ����ҳ", selenium.getTitle());
	}
}
