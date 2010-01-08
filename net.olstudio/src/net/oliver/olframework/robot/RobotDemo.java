package net.oliver.olframework.robot;

/*
 ��jdk1.3��ʼ���ṩ��һ��Robot�࣬��java.awt.*�����档
 �������jdk���������£�

 ��������Ϊ�����Զ�������������ʾ�����������Ҫ�������ͼ��̵�Ӧ�ó������ɱ���ϵͳ�����¼���Robot ����ҪĿ���Ǳ��� Java ƽ̨ʵ���Զ����ԡ� 

 ʹ�ø������������¼��뽫�¼����͵� AWT �¼����л� AWT ������������ڣ��¼�����ƽ̨�ı���������������ɵġ����磬Robot.mouseMove ��ʵ���ƶ�����꣬������ֻ��������ƶ��¼��� 

 ע�⣬ĳЩƽ̨��Ҫ�ض�Ȩ�޻���չ�����ʵͼ�����ؼ��������ǰƽ̨���ò�����ʹ������ؼ�����ô��ͼ���� Robot ����ʱ���׳� AWTException�����磬��� X ��������֧�֣���û�����ã�XTEST 2.2 ��׼��չ���� X-Window ϵͳ���׳��쳣�� 

 �����Բ���֮���Ŀ�Ķ�ʹ�� Robot ��Ӧ�ó���Ӧ���ƴ�����Щ���������� 


 ��������ģ�������̲������⣬������������ȡ��Ļ��ֻ��ʾһ����ôģ�������̲���������api�ο�javadoc�������ʾ����˵���QQ���ƶ����ڵĹ��ܡ��������£�
 */
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

/** */
/**
 * @author bean
 * 
 */
public class RobotDemo {

	private Robot robot = null;

	public RobotDemo() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	/** */
	/** ���Ե���QQ */
	public void keyBoardDemo() {
		robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_Z);
		robot.keyRelease(KeyEvent.VK_Z);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_ALT);
	}

	/** */
	/** ǰ�����и���󻯵Ĵ��ڣ��������ƶ�����������Ȼ����ק��600,600��λ�� */
	public void mouseDemo() {
		robot.mouseMove(80, 10);
		robot.mousePress(KeyEvent.BUTTON1_MASK);
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		robot.mouseMove(600, 600);
		robot.mouseRelease(KeyEvent.BUTTON1_MASK);
	}

	/** */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RobotDemo demo = new RobotDemo();
		demo.keyBoardDemo();
		demo.mouseDemo();
	}

}
