import java.net.*;
import java.io.*;
import javax.net.ssl.*;
public class MyHTTPSServerProxy {
    public static void main(String args[ ]) {
        try {
SSLServerSocketFactory ssf= (SSLServerSocketFactory) 
SSLServerSocketFactory.getDefault( );
            ServerSocket ss=ssf.createServerSocket(443);
            System.out.println("Web Server OK ");
            while (true) {
                Socket s=ss.accept( );
                Process p=new Process(s);
                Thread t=new Thread(p);
                t.start( );
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
class Process implements  Runnable{
    Socket s;
    public Process (Socket s1) {
        s=s1;
    }
    public void run( ) {
        try {
            PrintStream out = new PrintStream(s.getOutputStream( ));
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(s.getInputStream( )));
            String info=null;
            String request=null;
            String refer=null;

            while(( info=in.readLine())!=null){
                if(info.startsWith("GET")){
                    //获取浏览器发来的get信息
                    request=info;
                }
                if(info.startsWith("Referer:")){
                    //获取浏览器发来的refer信息
                    refer=info;
                }

                System.out.println("now got "+info);
                if(info.equals("")) break;
            }

            System.out.println("now go");
            System.out.println("now gotreq "+request);
            if(request!=null){

                out.println("HTTP/1.0 200 OK");
                out.println("MIME_version:1.0");
                out.println("Content_Type:text/html");
                try{
                    // 浏览器请求形如  GET /http://www.my.com/ HTTP/1.1
                    // sp1, sp2为第一次和第二次出现空格的位置，
                    // filename从浏览器请求中提取出文件路径和名称 如 t/1.html
                    int sp1=request.indexOf(' ');
                    int sp2=request.indexOf(' ',sp1+1);
     String filename=request.substring(sp1+2,sp2);
     if(refer!=null){
          sp1=refer.indexOf(' ');
         refer=refer.substring(sp1+1,refer.length());
         if(!refer.endsWith("/")){
             refer=refer+"/";
         }
                        System.out.println("now processed ref "+refer);
         filename=refer+filename;


     }

//解析用户请求中网页的地址
System.out.println("now connecting "+filename);
URL con=new URL(filename);
//和相应站点建立输入流
                    InputStream gotoin=con.openStream( ); 
                    int n=gotoin.available( );   //获取网页的大小
                    byte buf[ ]=new byte[1024];
                    //按照Web服务器工作方式向用户浏览器发送信息
                    out.println("HTTP/1.0 200 OK");
                    out.println("MIME_version:1.0");
                    out.println("Content_Type:text/html");
                    out.println("Content_Length:"+n);
                    out.println("");
                    while ((n=gotoin.read(buf))>=0){  // 读取网页内容
                        out.write(buf,0,n);   //将网页内容发送给用户浏览器
                    }
                } catch(Exception e){
                    System.out.println(e);
                }

                out.close( );
                s.close( );
                in.close( );
            } // end if
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
