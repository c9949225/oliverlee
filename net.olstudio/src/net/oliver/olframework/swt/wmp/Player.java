package net.oliver.olframework.swt.wmp;

 import org.eclipse.swt.SWT;  
 import org.eclipse.swt.layout.FillLayout;  
 import org.eclipse.swt.widgets.Display;  
 import org.eclipse.swt.widgets.Shell;  
   
 public class Player  
 {  
   
     protected Shell shell;  
   
     /** 
      * Launch the application 
      * @param args 
      */  
     public static void main(String[] args)  
     {  
         try  
         {  
             Player window = new Player();  
             window.open();  
         }  
         catch (Exception e)  
         {  
             e.printStackTrace();  
         }  
     }  
   
     /** 
      * Open the window 
      */  
     public void open()  
     {  
         final Display display = Display.getDefault();  
         createContents();  
         shell.open();  
         shell.layout();  
         while (!shell.isDisposed())  
         {  
             if (!display.readAndDispatch())  
                 display.sleep();  
         }  
     }  
   
     /** 
      * Create contents of the window 
      */  
     protected void createContents()  
     {  
         shell = new Shell();  
         shell.setLayout(new FillLayout());  
         shell.setSize(500, 375);  
         shell.setText("SWT Application");  
   
         final WMP composite = new WMP(shell, SWT.NONE);  
         composite.play("New Stories (Highway Blues).wma");  
         
         //  
     }  
 }
