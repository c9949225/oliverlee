package net.oliver.olframework.swt.capturewindow;

import org.sf.feeling.swt.win32.extension.shell.Windows;

public class Test {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Windows.enumWindows();
//		int[] ary = Windows.enumChildrens();
//		Windows.hideTitleBar(984622);984622
//		System.out.println(Windows.getParent(790128)); // �ݹ飿
//		Windows.enumChildrens(984622);
		System.out.println(isParentWinodow(984622,133956));
//		enumWindows();
	}
	
	
	public static boolean isParentWinodow(int parent,int child)
	{
		 return parent == findTopParent(child);
	}
	
	public static int findTopParent(int handle)
	{
		if(Windows.getParent(handle) == 0)
			return handle;
		
		return findTopParent(Windows.getParent(handle));
	}
}
