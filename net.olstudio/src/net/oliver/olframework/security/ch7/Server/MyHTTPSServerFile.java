import java.net.*;
import java.io.*;
import javax.net.ssl.*;
public class MyHTTPSServerFile {
    public static void main(String args[ ]) {
        int i=0;
        try {
            SSLServerSocketFactory ssf= (SSLServerSocketFactory) SSLServerSocketFactory.getDefault( );
            ServerSocket ss=ssf.createServerSocket(443);
            System.out.println("Web Server OK ");

            while(true){
                Socket s=ss.accept( );  //等待请求
                PrintStream out = new PrintStream(s.getOutputStream( ));
                BufferedReader in = new BufferedReader(
                                        new InputStreamReader(s.getInputStream( )));
                String info=null;
                String request=null;
                while(( info=in.readLine())!=null){
                    if(info.indexOf("GET")!=-1){
                        //获取浏览器发来的get信息
                        request=info;
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
                        // 浏览器请求形如  GET /t/1.html HTTP/1.1
                        // sp1, sp2为第一次和第二次出现空格的位置，
                        // filename从浏览器请求中提取出文件路径和名称 如 t/1.html
                        int sp1=request.indexOf(' ');
                        int sp2=request.indexOf(' ',sp1+1);
                        String filename=request.substring(sp1+2,sp2);
                        // 若浏览器请求中无文件名，则加上默认文件名index.html
                        if(filename.equals("") || filename.endsWith("/")){
                            filename+="index.html";
                        }
                        System.out.println("Sending "+filename);
                        // 向浏览器发送文件
                        File fi=new File(filename);
                        InputStream fs=new FileInputStream(fi);
                        int n=fs.available();
                        byte buf[]=new byte[1024];
                        out.println("Content_Length:"+n);
                        out.println("");
                        while ((n=fs.read(buf))>=0){
                            out.write(buf,0,n);
                        }
                    } catch(Exception e){
                        System.out.println(e);
                    }

                    out.close( );
                    s.close( );
                    in.close( );
                } // end if
            }  // end while
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
