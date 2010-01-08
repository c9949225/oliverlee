package net.oliver.olframework.util.filesystem.registry;

import java.util.prefs.Preferences;

public class Registery {

	String[] keys = { "version", "initial", "creator" };
	String[] values = { "1.3", "ini.mp3", "caokai1818@sina.com" };

	// 把相应的值储存到变量中去
	public void writeValue() {

		// 在注册表的HKEY_LOCAL_MACHINE\Software\JavaSoft\prefs下写入注册表值.
		Preferences pre = Preferences.systemRoot().node("/javaplayer");
		for (int i = 0; i < keys.length; i++) {
			// pre.put(keys,values);
			pre.put(keys[i], values[i]);
		}
		// 上面也就规定了version=1.3,initial=ini.mp3,creator=caokai18182sina.com
	}

	public static void main(String[] args) {
		Registery reg = new Registery();
		reg.writeValue();
	}

}
