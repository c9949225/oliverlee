import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JFrame;
import java.awt.Container;
public class Client{
  public static void main(String[] args){
    JTabbedPane tabbedPane = new JTabbedPane();
    for (int i = 0;i < imageNames.length; i++) {
      JLabel label = new JLabel(new ImageProxy(imageNames[i]));
      tabbedPane.add(imageNames[i], label);
    }
    JFrame frame = new JFrame();
    Container contentPane = frame.getContentPane();
    contentPane.add(tabbedPane);
 
    frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.show();
 
  }
 
  private static final String[] imageNames = {
    "gogo16.gif" ,
    "gogo17.gif" ,
    "gogo18.gif" ,
    "gogo19.gif" ,
    "gogo20.gif" ,
    "gogo21.gif" ,
    "gogo22.gif" ,
    "gogo23.gif" ,
    "gogo24.gif" ,
    "gogo25.gif" ,
    "gogo26.gif" ,
    "gogo27.gif" ,
    "gogo28.gif" ,
    "gogo29.gif" ,
    "gogo30.gif" ,
    "another.gif"
  };
  private static final int FRAME_WIDTH = 500;
  private static final int FRAME_HEIGHT = 300;
}