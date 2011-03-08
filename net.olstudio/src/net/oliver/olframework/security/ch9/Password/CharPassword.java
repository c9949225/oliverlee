import java.io.*;
import java.util.*;
public class CharPassword {
   public static void main(String argv[]) throws IOException {
          CharPassword cp=new CharPassword();
          String prompt="Enter Password:";
          //�����̣߳�������ʾ����ˢ�¼��ʱ��
          MaskingThread mask= new MaskingThread(prompt,1);
          //�����̣߳��ں�̨����ˢ��
          mask.start();
          //��ʾ�û�����
          System.out.print(prompt);
          //��ȡ��������
          char [] passwd=cp.readPassword(System.in);
	    //��ֹ�߳�
          mask.stopMasking();     
          //ʹ�ÿ���
          System.out.print("The Passowrd inputed is: ");
          for(int i=0;i<passwd.length;i++){
              System.out.print(passwd[i]);
         }
//�������
          for(int i=0;i<passwd.length;i++){
              passwd[i]=' ';
         }
     }

private char[] readPassword(InputStream in) throws IOException {
	
char[] lineBuffer;
	char[] buf;
int i;
buf = lineBuffer = new char[128];
		int room = buf.length;
int offset = 0;
	int c;

loop:    while (true) {
 	    switch (c = in.read()) {
 	    case -1:
 	    case '\n':
				break loop;
 	    case '\r':
 			int c2 = in.read();
 			if ((c2 != '\n') && (c2 != -1)) {
 		    		if (!(in instanceof PushbackInputStream)) {
 					in = new PushbackInputStream(in);
 		    		}
 		    		((PushbackInputStream)in).unread(c2);
 			} else
 		    break loop;

 	     default:
 			if (--room < 0) {
 		    		buf = new char[offset + 128];
 		    room = buf.length - offset - 1;
 			    System.arraycopy(lineBuffer, 0, buf, 0, offset);
 			    Arrays.fill(lineBuffer, ' ');
 		    		lineBuffer = buf;
 			}
 		buf[offset++] = (char) c;
 			break;
 	    }
 	}

 	if (offset == 0) {
 	    return null;
 	}

 	char[] ret = new char[offset];
 	System.arraycopy(buf, 0, ret, 0, offset);
 	Arrays.fill(buf, ' ');

 	return ret;
   }
}
