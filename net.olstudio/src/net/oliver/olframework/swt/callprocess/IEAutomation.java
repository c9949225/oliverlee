package net.oliver.olframework.swt.callprocess;

import org.eclipse.swt.ole.win32.Variant;

/**
 * 
 * 更多方法参考MSDN“InternetExplorer Object”文档
 * 
 * @author 杨中科
 */

public class IEAutomation

{
	static final String progId = "InternetExplorer.Application";

	private CComObject ieComObject = new CComObject();

	public IEAutomation() {
		ieComObject.CreateComObjectProgID(progId);
	}

	public void setProperty(String name, int value) {
		ieComObject.setProperty(name, new Variant[] { new Variant(value) });
	}

	public int getPropertyAsInt(String name) {
		Variant value = ieComObject.getProperty(name, new Variant[0]);
		return value.getInt();
	}

	public void setProperty(String name, boolean value) {
		ieComObject.setProperty(name, new Variant[] { new Variant(value) });
	}

	public boolean getPropertyAsBool(String name) {
		Variant value = ieComObject.getProperty(name, new Variant[0]);
		return value.getBoolean();
	}

	public void setProperty(String name, String value) {
		ieComObject.setProperty(name, new Variant[] { new Variant(value) });
	}

	public String getPropertyAsString(String name) {
		Variant value = ieComObject.getProperty(name, new Variant[0]);
		return value.getString();
	}

	public void setVisible(boolean value) {
		setProperty("Visible", value);
	}

	public boolean isVisible() {
		return getPropertyAsBool("Visible");
	}

	public void setMenuBar(boolean value) {
		setProperty("MenuBar", value);
	}

	public boolean isMenuBar() {
		return getPropertyAsBool("MenuBar");
	}

	public int getHWND() {
		return getPropertyAsInt("HWND");
	}

	public void navigate(String url) {
		ieComObject
				.CallFunction("Navigate", new Variant[] { new Variant(url) });
	}

	public void quit() {
		ieComObject.CallFunction("Quit");
	}

}
