package net.oliver.olframework.security.ch8.mypermission.check;

import java.io.*;
import java.security.*;
import java.awt.*;

public class SecMCheckPerm {
	public static void main(String args[]) {
		try {
			SecurityManager sm = System.getSecurityManager();
			if (sm != null) {
				FilePermission fp = new FilePermission("c:\\autoexec.bat",
						"read");
				sm.checkPermission(fp);
			}
			System.out.println("Has FilePermission to read c:\\autoexec.bat");

		} catch (AccessControlException e) {
			System.out.println(e);
		}

		try {
			SecurityManager sm = System.getSecurityManager();
			if (sm != null) {
				AWTPermission ap = new AWTPermission("accessClipboard");
				sm.checkPermission(ap);
			}
			System.out.println("Has AWTPermission to access AWT Clipboard");

		} catch (AccessControlException e) {
			System.out.println(e);
		}
	}
}
