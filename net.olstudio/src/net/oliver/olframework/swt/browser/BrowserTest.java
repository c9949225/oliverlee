package net.oliver.olframework.swt.browser;

import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;
//import org.eclipse.core.runtime.jobs.*;
//import org.eclipse.core.runtime.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.browser.*;
import org.eclipse.swt.layout.*;

public class BrowserTest {
  public static void main(String[] args) {
      Display display = new Display();
      Shell shell = new Shell(display);
      shell.setText("Main Window");
      shell.setLayout(new FillLayout());
      Browser browser = new Browser(shell, SWT.NONE);
      initialize(display, browser);
      shell.open();
      browser.setUrl("http://www.baidu.com");
      while (!shell.isDisposed()) {
        if (!display.readAndDispatch())
          display.sleep();
      }
      display.dispose();
  }

  static void initialize(final Display display, Browser browser) {
      browser.addListener(SWT.MouseDown, new Listener(){
        public void handleEvent (Event event){
          System.out.println("event.time:" + event.time);
        }
      });
      browser.addOpenWindowListener(new OpenWindowListener() {
        public void open(WindowEvent event) {
          // Certain platforms can provide a default full browser.
          // simply return in that case if the application prefers
          // the default full browser to the embedded one set below.
          //if (!event.required) return;

          // Embed the new window
          Shell shell = new Shell(display);
          shell.setText("New Window");
          shell.setLayout(new FillLayout());
          Browser browser = new Browser(shell, SWT.NONE);
          initialize(display, browser);
          event.browser = browser;
        }
      });
      browser.addVisibilityWindowListener(new VisibilityWindowListener() {
        public void hide(WindowEvent event) {
          Browser browser = (Browser)event.widget;
          Shell shell = browser.getShell();
          shell.setVisible(false);
        }
        public void show(WindowEvent event) {
          Browser browser = (Browser)event.widget;
          Shell shell = browser.getShell();
          if (event.location != null) shell.setLocation(event.location);
          if (event.size != null) {
              Point size = event.size;
              shell.setSize(shell.computeSize(size.x, size.y));
          }
          if (event.addressBar || event.menuBar || event.statusBar || event.toolBar) {
              // Create widgets for the address bar, menu bar, status bar and/or tool bar
              // leave enough space in the Shell to accomodate a Browser of the size
              // given by event.size
          }
          shell.open();
        }
      });
      browser.addCloseWindowListener(new CloseWindowListener() {
        public void close(WindowEvent event) {
          Browser browser = (Browser)event.widget;
          Shell shell = browser.getShell();
          shell.close();
        }
      });
  }

}