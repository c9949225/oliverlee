package net.olstudio.test.categories.selenium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import junit.framework.TestCase;

import org.mortbay.http.SocketListener;
import org.mortbay.jetty.Server;
import org.openqa.selenium.server.SeleniumServer;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

public class HelloWebSelenium extends TestCase {

	Server server;
	SeleniumServer ss;
	Selenium selenium;

	public static void main(String[] args) {
		junit.textui.TestRunner.run(HelloWebSelenium.class);
	}

	protected void setUp() throws Exception {
		
		super.setUp();
		// 新建Selenium服务器
		ss = new SeleniumServer();
		// 新建Jetty服务器
		server = new Server();
		SocketListener listener = new SocketListener();
		listener.setPort(3003);
//		server.addListener(listener);
//		server.addWebApplication("/webapp", "webapp");

		server.start();
		// Jetty服务器开始运行
		ss.start();
		// Selenium服务器开始运行

		// 将Jetty服务器的访问地址传给Selenium服务器
		selenium = new DefaultSelenium("localhost",
				//"*firefox"
				SeleniumServer.DEFAULT_PORT,"*iexplore",
				"http://localhost:3003/webapp/");
		selenium.start();
	}

	protected void tearDown() throws Exception {
		server.stop();
		ss.stop();
		super.tearDown();
	}

	public void testJettyServer() throws IOException, URISyntaxException {
		URL url = new URI("http://localhost:3003/webapp/test.jsp").toURL();
		InputStream in = url.openStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String str = br.readLine();
		System.out.println(str);
		assertEquals("jsp", str);
	}

	public void testJettyServerbyWebClient() throws MalformedURLException {
		// 访问test.jsp
		selenium.open("/webapp/test.jsp");
		// 点击test.链接
		selenium.click("link=test");
		selenium.waitForPageToLoad("3000");//自己本地的还是很快的... 
		// 保证得到服务返回的页面内容正确
		assertEquals("abc", selenium.getBodyText());
	}

}