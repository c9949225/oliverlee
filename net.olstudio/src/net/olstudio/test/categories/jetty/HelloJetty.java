package net.olstudio.test.categories.jetty;

//import org.mortbay.jetty.Connector;
import org.mortbay.http.SocketListener;
import org.mortbay.jetty.Server;
//import org.mortbay.jetty.nio.SelectChannelConnector;
//import org.mortbay.jetty.webapp.WebAppContext;
//import org.mortbay.thread.BoundedThreadPool;

// Oliver �� 2008-7-21 ����02:07:54 ����ע��:
// jetty-6.1.6rc0.jar
// jetty-util-6.1.6rc0.jar
// ~=====================================================================
// core-3.1.1.jar
// jsp-2.1.jar
// jsp-api-2.1.jar
public class HelloJetty {
	public static void main(String[] args) throws Exception {

		// ~=====================================================================
		// Jetty Server 6.1 д��
		/*Server server = new Server();
		BoundedThreadPool threadPool = new BoundedThreadPool();
		threadPool.setMaxThreads(100);
		server.setThreadPool(threadPool);
		Connector connector = new SelectChannelConnector();
		connector.setPort(8080);
		server.setConnectors(new Connector[] { connector });
		// Ҫ���Ŀ�ĸ�Ŀ䛄���webappĿ�,ע�ⲻ����ĸ�·����,���ú�class��һ��
		WebAppContext context = new WebAppContext("webapp", "/webapp");
		server.addHandler(context);
		server.setStopAtShutdown(true);
		server.setSendServerVersion(true);

		server.start();
		server.join();*/
		// ~=====================================================================
		// Jetty Server 5.1(ֻ֧��Jsp2.0) д��
//		Server server = new Server(); // ����һ���µ�HttpServer  
//		SocketListener listener = new SocketListener();// ����һ���¼�����  
//		listener.setPort(3003); // ���ü����˿�Ϊ8080  
//		server.addListener(listener ); // ��������ע�ᵽserver��  
//		server.addWebApplication("/webapp","webapp"); // �����webӦ��ע�ᵽ���Server��  
//		server.start(); // ����������server  
	}
}