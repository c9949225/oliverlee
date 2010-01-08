package net.oliver.olframework.swt.capturewindow;

import java.io.IOException;

import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.TCHAR;

@SuppressWarnings("restriction")
public class TestHandler {

	public TestHandler() {

	}

	private static void act() {

//		Runtim.getRuntime().exec("cmd   /c   start   d:\\a.xls")
		
		int embedHandle = 0;
		int embedHandle1 = 0;
		
		try {
//			Runtime.getRuntime().exec("cmd.exe cd D:\\ccra\\Package\\Support").waitFor();
			Runtime.getRuntime().exec("D:\\test.bat");
			
//			Runtime.getRuntime().exec("D:\\ccra\\Package\\Support\\ccra.exe");
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
//		catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		TCHAR ccra = new TCHAR(0, "ThunderRT6FormDC", true);
		embedHandle = OS.FindWindow(ccra,null);
		
//		embedHandle = OS
//				.FindWindow(
//						null,
//						new TCHAR(
//								0,
//								"CTI AGENT",
//								true));


		embedHandle1 = OS.FindWindow(null, new TCHAR(0, "CTI", true));
		
		// 去除窗口边框 ==================================================================
		int oldStyle = OS.GetWindowLong(embedHandle, OS.GWL_STYLE);
		OS.SetWindowLong(embedHandle, OS.GWL_STYLE, oldStyle & ~OS.WS_BORDER);
		// ==============================================================================
		
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		OS.SetMenu(embedHandle, 0); // 去除菜单栏
		OS.SetParent(embedHandle, embedHandle1); // 子窗口 父窗口
		
		OS.SetWindowPos(embedHandle, OS.HWND_TOP, 0, 0, 406, 256, OS.SWP_NOZORDER);
		// OS.SendMessage(embedHandle, OS.WM_CLOSE, 0, 0);
		System.out.println(embedHandle);
	}

	public static void main(String[] args) {
//		act();
		// /MIN 最小化窗口
		   String commandLine = "cmd.exe /c start /WAIT /B D:\\runCCRA.bat";  
		   Runtime runTime = Runtime.getRuntime();  
		   Process process;
		try {
			process = runTime.exec(commandLine);
//			 int code = process.waitFor(); 
//			 System.out.println(code);
		} catch (IOException e) {
			e.printStackTrace();
		} 
//		catch (InterruptedException e) {
//			e.printStackTrace();
//		}  
		  
	}

}
