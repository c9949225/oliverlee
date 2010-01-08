package net.olstudio.test.categories.swt.custom.tab;

 import org.eclipse.swt.SWT;   
 import org.eclipse.swt.custom.CTabFolder;   
 import org.eclipse.swt.custom.CTabFolder2Adapter;   
 import org.eclipse.swt.custom.CTabFolderEvent;   
 import org.eclipse.swt.custom.CTabItem;   
 import org.eclipse.swt.graphics.Color;   
 import org.eclipse.swt.graphics.Image;   
 import org.eclipse.swt.layout.GridData;   
 import org.eclipse.swt.layout.GridLayout;   
 import org.eclipse.swt.widgets.Display;   
 import org.eclipse.swt.widgets.Shell;   
 import org.eclipse.swt.widgets.Text;   
   
 public class CTabFolderSample   
 {   
     public static void main(String[] args)   
         {   
    	 	 // 0. 打开新窗口
             final Display display = Display.getDefault();   
             final Shell shell = new Shell();   
             shell.setSize(296, 255);   
             shell.setText("CTabFolder 练习");   
             shell.setLayout(new GridLayout());   
             shell.open();   
   
             final CTabFolder tabFolder = new CTabFolder(shell, SWT.NONE|SWT.CLOSE|SWT.BORDER); 
             
             // 监听对 tab面板大小改变的监听，来进行实际的处理
             tabFolder.addCTabFolder2Listener(new CTabFolder2Adapter() {   
            	 
                 public void minimize(CTabFolderEvent event) {   
                         tabFolder.setMinimized(true);   
                         tabFolder.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));   
                         shell.layout(true);//刷新布局   
                 }
                 
                 public void maximize(CTabFolderEvent event) {   
                         tabFolder.setMaximized(true);   
                         tabFolder.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));   
                         shell.layout(true);   
                 }
                 
                 public void restore(CTabFolderEvent event) {   
                         tabFolder.setMinimized(false);   
                         tabFolder.setMaximized(false);   
                         tabFolder.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));   
                         shell.layout(true);   
                 }   
             });   
             
             //tabFolder.setBounds(0, 0, 283, 211);   
             tabFolder.setTabHeight(20);   
             tabFolder.marginHeight = 5;   
             tabFolder.marginWidth = 5;   
             tabFolder.setMaximizeVisible(true);   
             tabFolder.setMinimizeVisible(true);   
             tabFolder.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));   
             //下面两个是设置固定的背景色和前景色   
             //tabFolder.setBackground(display.getSystemColor(SWT.COLOR_BLUE));   
             //tabFolder.setForeground(display.getSystemColor(SWT.COLOR_WHITE));   
             //下面是设置渐变色   
             Color[] color=new Color[4];   
             color[0]=display.getSystemColor(SWT.COLOR_DARK_BLUE);   
             color[1]=display.getSystemColor(SWT.COLOR_BLUE);   
             color[2]=display.getSystemColor(SWT.COLOR_DARK_GRAY);   
             color[3]=display.getSystemColor(SWT.COLOR_WHITE);   
             int[] intArray=new int[]{25,45,100};   
             tabFolder.setSelectionBackground(color, intArray);   
             //这是设置了背景颜色，但是如果同时设置了背景图片的话以背景图片优先   
             tabFolder.setSimple(false);//设置圆角   
             tabFolder.setUnselectedCloseVisible(true);   
             
             // 往tabFolder里添加4个标签页
             for (int i = 1; i < 4; i++) {   
                 CTabItem item = new CTabItem(tabFolder, SWT.None|SWT.MULTI|SWT.V_SCROLL);   
                 item.setText("选项卡" + i);   
                 Text t = new Text(tabFolder, SWT.None|SWT.MULTI|SWT.V_SCROLL|SWT.H_SCROLL|SWT.WRAP);   
                 t.setText("这是选项卡可以控制的文字" + i+"\n\n世界第一等\n\n一路顺风");   
                 item.setControl(t);   
   
             }   
//             Image image=new Image(display,"C:\\Documents and Settings\\Administrator\\桌面\\label.jpg");   
//             shell.setImage(image);     
             shell.setSize(300, 200);   
             shell.layout();   
             while (!shell.isDisposed()) {   
                 if (!display.readAndDispatch())   
                     display.sleep();   
             }   
         }   
 }
