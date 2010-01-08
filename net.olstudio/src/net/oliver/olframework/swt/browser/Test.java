package net.oliver.olframework.swt.browser;

import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.ole.win32.OLE;
import org.eclipse.swt.ole.win32.OleAutomation;
import org.eclipse.swt.ole.win32.OleControlSite;
import org.eclipse.swt.ole.win32.OleFrame;
import org.eclipse.swt.ole.win32.Variant;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Test {

    public static void main(String[] args)
    {

        //a. Create Shell
        Display display = new Display();
        Shell shell = new Shell( display );
        shell.setSize( 610, 400 );

        //b. Create Browser (the old way)
        OleFrame frame = new OleFrame( shell, SWT.NONE );
        OleControlSite site = new OleControlSite( frame, SWT.NONE, "Shell.Explorer" );
        site.doVerb( OLE.OLEIVERB_INPLACEACTIVATE );
        OleAutomation browser = new OleAutomation( site );


        //c. Disable "link navigation" and go to google
        int[] nameIds = browser.getIDsOfNames(new String[] { "RegisterAsDropTarget"} ); //is this really necessary (I wonder)?
        browser.setProperty(nameIds[0], new Variant(false) ); //true or false --doesn't work

        nameIds = browser.getIDsOfNames( new String[] { "Navigate", "URL"} );
        browser.invoke( nameIds[0], new Variant[] { new Variant("http://www.google.com") },
        new int[] { nameIds[1] } );

        //d. Listen to drop events ???
        DropTarget target = new DropTarget(frame, DND.DROP_COPY ); // <--help here!!!
        target.setTransfer( new Transfer[] { FileTransfer.getInstance() } );
        target.addDropListener( new DropTargetListener()
        {
                public void dragEnter(DropTargetEvent event) { System.out.println("drag enter"); }
                public void dragLeave(DropTargetEvent event) { System.out.println("drag leave");}
                public void dragOver(DropTargetEvent event) { System.out.println("drag over");}
                public void dragOperationChanged(DropTargetEvent event) { System.out.println("drag changed");}
                public void dropAccept(DropTargetEvent event) { System.out.println("drag accept");}
                public void drop(DropTargetEvent event)
                {
                        String[] files = (String[]) event.data; //expects FileTransfer data
                        for (int p=0;p <files.length; p++)
                        System.out.println( files[p] );
                }/*drop*/
        });


        //e. Show window
        frame.setBounds( 0,0, 600, 400 );
        shell.open();

        while (!shell.isDisposed())
        {
                if (!display.readAndDispatch())
                        display.sleep();
        } /*while*/

        browser.dispose();
        display.dispose();

    }/*main*/
   

}/*test*/ 