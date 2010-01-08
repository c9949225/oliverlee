package net.oliver.olframework.applet;

import java.awt.*;

public class DetectPluginApplet extends java.applet.Applet {
	public void init() {
		add(new Label("DetectPluginApplet"));
	}
	
	// 该方法被页面类的脚本调用
	// 去工程bin目录下调用html
	public String getJavaVersion() {
		return System.getProperty("java.version");
	}
}