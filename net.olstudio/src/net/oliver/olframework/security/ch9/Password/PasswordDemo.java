import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PasswordDemo {
    //口令和文本输入域
    JPasswordField passwordField = new JPasswordField(12);
    JTextField textField = new JTextField(12);
public static void main(String[] argv) {
         PasswordDemo pd=new PasswordDemo();
         pd.go();
    }
    void go() {
        //设置口令回显字符为X
        passwordField.setEchoChar('X');
        //创建各个标签、按钮
        final JFrame f = new JFrame("PasswordDemo");
        JLabel label1 = new JLabel("Input name: ");
        JLabel label2 = new JLabel("Input password: ");
        JButton button1 = new JButton("OK");
        JButton button2 = new JButton("Cancel");
        //鼠标单击“OK”按钮则执行该段代码
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //获取输入的名字
                String name = textField.getText();
                //获取输入的口令值
                char[] password = passwordField.getPassword();
                System.out.println("Input name "+name);
              System.out.println("Input Password "+
new String(password));
            }
         });
        //使用网格布局安排各个按钮和输入域
        JPanel contentPane = new JPanel(new GridLayout(3,2));
        contentPane.add(label1);
        contentPane.add(textField);
        contentPane.add(label2);
        contentPane.add(passwordField);
        contentPane.add(button1);
        contentPane.add(button2);
        f.setContentPane(contentPane);
        //窗口事件，用于关闭窗口
      f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { System.exit(0); }
        });
        f.pack();
        f.setVisible(true);
    }
}
