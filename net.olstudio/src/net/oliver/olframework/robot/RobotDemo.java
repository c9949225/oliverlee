package net.oliver.olframework.robot;

/*
 从jdk1.3开始，提供了一个Robot类，在java.awt.*包下面。
 这个类在jdk中描述如下：

 此类用于为测试自动化、自运行演示程序和其他需要控制鼠标和键盘的应用程序生成本机系统输入事件。Robot 的主要目的是便于 Java 平台实现自动测试。 

 使用该类生成输入事件与将事件发送到 AWT 事件队列或 AWT 组件的区别在于：事件是在平台的本机输入队列中生成的。例如，Robot.mouseMove 将实际移动鼠标光标，而不是只生成鼠标移动事件。 

 注意，某些平台需要特定权限或扩展来访问低级输入控件。如果当前平台配置不允许使用输入控件，那么试图构造 Robot 对象时将抛出 AWTException。例如，如果 X 服务器不支持（或没有启用）XTEST 2.2 标准扩展，则 X-Window 系统会抛出异常。 

 出于自测试之外的目的而使用 Robot 的应用程序应妥善处理这些错误条件。 


 这个类除了模拟鼠标键盘操作以外，还可以用来截取屏幕，只演示一下怎么模拟鼠标键盘操作，具体api参考javadoc。这个演示完成了弹出QQ和移动窗口的功能。代码如下：
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
	/** 可以弹出QQ */
	public void keyBoardDemo() {
		robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_Z);
		robot.keyRelease(KeyEvent.VK_Z);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_ALT);
	}

	/** */
	/** 前提是有个最大化的窗口，功能是移动到标题栏，然后拖拽到600,600的位置 */
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
