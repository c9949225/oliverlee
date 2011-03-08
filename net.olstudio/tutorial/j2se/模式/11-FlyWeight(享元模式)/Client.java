import javax.swing.*;
import java.awt.*;

/**
 * 
 * <DL>
 * <DT><B>Title * . </B></DT>
 * <p>
 * <DD>������ΪFlyweightģʽ�ĺ��ľ��ǰѴ���"����"�Ķ���"�ռ�"
 * ��һ��ʹ�ü򵥹���ģʽ���й���
 * �������ڴ�����С������ϵͳ���ڴ�������ġ�</DD>
 * </DL>
 * <p>
 * 
 * <DL>
 * <DT><B>Usage </B></DT>
 * <p>
 * <DD>Usage Details.</DD>
 * </DL>
 * <p>
 * 
 * @author Oliver Lee &li.fu@agree.com.cn&gt;
 * @copyright Agree Tech Co.
 * @version 1.00 2008-11-27����04:34:30
 */
public class Client extends JFrame {
	
	private MyImageFactory factory;
	
	private String[] names;

	public Client() {
		super("Flyweight sample");
		factory = new MyImageFactory();
		setSize(220, 300);
		setVisible(true);
		repaint();
		names = new String[] { "pig", "dog", "cat" };
	}

	public void resize() {
		repaint();
	}

	public void paint(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		String name;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				int number = (int) (Math.random() * 3 % 3);
				MyImage myImage = factory.getMyImage(number);
				number = (int) (Math.random() * 3 % 3);
				name = names[number];
				myImage.draw(g, 10 + i * 40, 45 + j * 45, name, this);
			}
		}
	}

	public static void main(String[] args) {
		new Client();
	}
}