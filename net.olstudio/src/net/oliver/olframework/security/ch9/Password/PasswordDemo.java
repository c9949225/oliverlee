import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PasswordDemo {
    //������ı�������
    JPasswordField passwordField = new JPasswordField(12);
    JTextField textField = new JTextField(12);
public static void main(String[] argv) {
         PasswordDemo pd=new PasswordDemo();
         pd.go();
    }
    void go() {
        //���ÿ�������ַ�ΪX
        passwordField.setEchoChar('X');
        //����������ǩ����ť
        final JFrame f = new JFrame("PasswordDemo");
        JLabel label1 = new JLabel("Input name: ");
        JLabel label2 = new JLabel("Input password: ");
        JButton button1 = new JButton("OK");
        JButton button2 = new JButton("Cancel");
        //��굥����OK����ť��ִ�иöδ���
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //��ȡ���������
                String name = textField.getText();
                //��ȡ����Ŀ���ֵ
                char[] password = passwordField.getPassword();
                System.out.println("Input name "+name);
              System.out.println("Input Password "+
new String(password));
            }
         });
        //ʹ�����񲼾ְ��Ÿ�����ť��������
        JPanel contentPane = new JPanel(new GridLayout(3,2));
        contentPane.add(label1);
        contentPane.add(textField);
        contentPane.add(label2);
        contentPane.add(passwordField);
        contentPane.add(button1);
        contentPane.add(button2);
        f.setContentPane(contentPane);
        //�����¼������ڹرմ���
      f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { System.exit(0); }
        });
        f.pack();
        f.setVisible(true);
    }
}
