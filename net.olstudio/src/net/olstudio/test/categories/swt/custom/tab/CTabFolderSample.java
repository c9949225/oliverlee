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
    	 	 // 0. ���´���
             final Display display = Display.getDefault();   
             final Shell shell = new Shell();   
             shell.setSize(296, 255);   
             shell.setText("CTabFolder ��ϰ");   
             shell.setLayout(new GridLayout());   
             shell.open();   
   
             final CTabFolder tabFolder = new CTabFolder(shell, SWT.NONE|SWT.CLOSE|SWT.BORDER); 
             
             // ������ tab����С�ı�ļ�����������ʵ�ʵĴ���
             tabFolder.addCTabFolder2Listener(new CTabFolder2Adapter() {   
            	 
                 public void minimize(CTabFolderEvent event) {   
                         tabFolder.setMinimized(true);   
                         tabFolder.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));   
                         shell.layout(true);//ˢ�²���   
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
             //�������������ù̶��ı���ɫ��ǰ��ɫ   
             //tabFolder.setBackground(display.getSystemColor(SWT.COLOR_BLUE));   
             //tabFolder.setForeground(display.getSystemColor(SWT.COLOR_WHITE));   
             //���������ý���ɫ   
             Color[] color=new Color[4];   
             color[0]=display.getSystemColor(SWT.COLOR_DARK_BLUE);   
             color[1]=display.getSystemColor(SWT.COLOR_BLUE);   
             color[2]=display.getSystemColor(SWT.COLOR_DARK_GRAY);   
             color[3]=display.getSystemColor(SWT.COLOR_WHITE);   
             int[] intArray=new int[]{25,45,100};   
             tabFolder.setSelectionBackground(color, intArray);   
             //���������˱�����ɫ���������ͬʱ�����˱���ͼƬ�Ļ��Ա���ͼƬ����   
             tabFolder.setSimple(false);//����Բ��   
             tabFolder.setUnselectedCloseVisible(true);   
             
             // ��tabFolder�����4����ǩҳ
             for (int i = 1; i < 4; i++) {   
                 CTabItem item = new CTabItem(tabFolder, SWT.None|SWT.MULTI|SWT.V_SCROLL);   
                 item.setText("ѡ�" + i);   
                 Text t = new Text(tabFolder, SWT.None|SWT.MULTI|SWT.V_SCROLL|SWT.H_SCROLL|SWT.WRAP);   
                 t.setText("����ѡ����Կ��Ƶ�����" + i+"\n\n�����һ��\n\nһ·˳��");   
                 item.setControl(t);   
   
             }   
//             Image image=new Image(display,"C:\\Documents and Settings\\Administrator\\����\\label.jpg");   
//             shell.setImage(image);     
             shell.setSize(300, 200);   
             shell.layout();   
             while (!shell.isDisposed()) {   
                 if (!display.readAndDispatch())   
                     display.sleep();   
             }   
         }   
 }
