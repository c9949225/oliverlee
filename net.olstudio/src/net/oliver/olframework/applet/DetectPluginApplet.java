package net.oliver.olframework.applet;

import java.awt.*;

public class DetectPluginApplet extends java.applet.Applet {
	public void init() {
		add(new Label("DetectPluginApplet"));
	}
	
	// �÷�����ҳ����Ľű�����
	// ȥ����binĿ¼�µ���html
	public String getJavaVersion() {
		return System.getProperty("java.version");
	}
}