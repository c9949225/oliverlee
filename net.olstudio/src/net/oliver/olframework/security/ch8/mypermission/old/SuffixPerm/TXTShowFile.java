import java.io.*;

public class TXTShowFile{
     public static void main(String args[]) throws IOException{
        BufferedReader in;
        String s;
        in = new BufferedReader(new FileReader(args[0]));
        while ((s = in.readLine( )) != null) {
		System.out.println("read "+s);
        }
     }
}
