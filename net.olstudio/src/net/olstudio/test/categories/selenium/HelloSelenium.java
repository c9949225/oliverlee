package net.olstudio.test.categories.selenium;

import junit.framework.TestCase;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

// Oliver 于 2008-7-21 下午02:16:41 作出注释:
// 需要首先运行 java -jar selenium-server.jar 启动 selenium server
public class HelloSelenium extends TestCase {
	private Selenium selenium;

	public void setUp() throws Exception {
		String url = "http://www.javaeye.com";
		
		// 1, Selenium Server运行时所在的主机名,在本机运行所以是localhost
		
		// 2, 4444是SeleniumServer默认的端口  
		
		// 3, "*firefox"指明是使用firefox测试 e.g. "*firefox", "*iexplore" or "c:\\program files\\internet explorer\\iexplore.exe"
		
		// 4, url是要测试的网站的起始Url
		selenium = new DefaultSelenium("localhost", 4444, "*iexplore", url);
		selenium.start();
	}

	protected void tearDown() throws Exception {
		selenium.stop();
	}

	public void testGoogleTestSearch() throws Throwable {
		// 打开页面
		selenium.open("/search/");
		// 输入
		selenium.type("query", "selenium");
		// 点击"论坛"
		selenium.click("link=论坛");
		// 设置超时
		selenium.waitForPageToLoad("5000");
		// 要保证返回的页面的标题和指定的相同
		assertEquals("JavaEye论坛频道首页", selenium.getTitle());
	}
}