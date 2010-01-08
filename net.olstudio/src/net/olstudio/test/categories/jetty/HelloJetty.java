package net.olstudio.test.categories.jetty;

//import org.mortbay.jetty.Connector;
import org.mortbay.http.SocketListener;
import org.mortbay.jetty.Server;
//import org.mortbay.jetty.nio.SelectChannelConnector;
//import org.mortbay.jetty.webapp.WebAppContext;
//import org.mortbay.thread.BoundedThreadPool;

// Oliver 于 2008-7-21 下午02:07:54 作出注释:
// jetty-6.1.6rc0.jar
// jetty-util-6.1.6rc0.jar
// ~=====================================================================
// core-3.1.1.jar
// jsp-2.1.jar
// jsp-api-2.1.jar
public class HelloJetty {
	public static void main(String[] args) throws Exception {

		// ~=====================================================================
		// Jetty Server 6.1 写法
		/*Server server = new Server();
		BoundedThreadPool threadPool = new BoundedThreadPool();
		threadPool.setMaxThreads(100);
		server.setThreadPool(threadPool);
		Connector connector = new SelectChannelConnector();
		connector.setPort(8080);
		server.setConnectors(new Connector[] { connector });
		// 要在項目的跟目錄創建webapp目錄,注意不是在類的根路徑下,不用和class在一起
		WebAppContext context = new WebAppContext("webapp", "/webapp");
		server.addHandler(context);
		server.setStopAtShutdown(true);
		server.setSendServerVersion(true);

		server.start();
		server.join();*/
		// ~=====================================================================
		// Jetty Server 5.1(只支持Jsp2.0) 写法
//		Server server = new Server(); // 创建一个新的HttpServer  
//		SocketListener listener = new SocketListener();// 创建一个新监听器  
//		listener.setPort(3003); // 设置监听端口为8080  
//		server.addListener(listener ); // 将监听类注册到server中  
//		server.addWebApplication("/webapp","webapp"); // 将这个web应用注册到这个Server中  
//		server.start(); // 最后启动这个server  
	}
}