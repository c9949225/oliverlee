import java.io.*;
import java.awt.*;
import java.applet.*;
public class AppletShowFile extends Applet{
	String content="The content of file:";
        TextArea ta=new TextArea(10,80);

	public void init ( ) {
             String s;
             BufferedReader in;
             try{
                  in = new BufferedReader(new FileReader("c:\\autoexec.bat"));
                  while ((s = in.readLine( )) != null) {
                       content+=s+"\n";
                  }
             }
             catch(Exception e){
                  System.err.println(e);
             }
             add(ta);
        }
	public void paint(Graphics g){
             ta.setText(content);
	}
}
