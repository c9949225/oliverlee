package net.oliver.olframework.util.filesystem.registry;

import java.util.prefs.Preferences;

public class Registery {

	String[] keys = { "version", "initial", "creator" };
	String[] values = { "1.3", "ini.mp3", "caokai1818@sina.com" };

	// ����Ӧ��ֵ���浽������ȥ
	public void writeValue() {

		// ��ע����HKEY_LOCAL_MACHINE\Software\JavaSoft\prefs��д��ע���ֵ.
		Preferences pre = Preferences.systemRoot().node("/javaplayer");
		for (int i = 0; i < keys.length; i++) {
			// pre.put(keys,values);
			pre.put(keys[i], values[i]);
		}
		// ����Ҳ�͹涨��version=1.3,initial=ini.mp3,creator=caokai18182sina.com
	}

	public static void main(String[] args) {
		Registery reg = new Registery();
		reg.writeValue();
	}

}
