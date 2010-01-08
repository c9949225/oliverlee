package net.olstudio.test.categories.swt.custom.oliver;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

public class MovableMenu extends  Canvas implements Listener {

	public MovableMenu(Composite parent) {
		super(parent, SWT.FLAT);
		
		// 0. Ìí¼Ó¼àÌýÆ÷
		addListener(SWT.Paint, this);
	}

	public void handleEvent(Event event) {
		
		switch (event.type)
        {
            case SWT.Paint:
                paintControl(event);
                break;
            default:
                break;
        }
	}

	private void paintControl(Event event)
	{

	}
}
