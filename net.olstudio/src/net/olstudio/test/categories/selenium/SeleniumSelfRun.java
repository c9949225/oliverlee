package net.olstudio.test.categories.selenium;

import junit.framework.TestCase;

import org.openqa.selenium.server.SeleniumServer;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

// 本类需要
// net.olstudio.test.categories.jetty.HelloJetty
// 先启动起来
// 然后本类测试启动起来的jetty服务器里的内容
public class SeleniumSelfRun extends TestCase{

	private Selenium selenium;
	//  selenium-server.jar
	//	板本错误????
	//	把所有的包拆开一看......
	//	....竟然selenium固化了一套jetty在里面.....
	//	我倒....5.1.x
	//	把我导入的jetty6.1包删去.... 
	//  ~=====================================================================
	//  这样前面写的启动jetty服务器的代码就有问题了,因为是jetty6.1的写法,要改成5.1的写法
	private SeleniumServer ss;

	// 这里改为自己启动SeleniumServer,而不是在外面启动
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
		selenium.click("link=论坛");
		selenium.waitForPageToLoad("5000");
		assertEquals("JavaEye论坛频道首页", selenium.getTitle());
	}
}
