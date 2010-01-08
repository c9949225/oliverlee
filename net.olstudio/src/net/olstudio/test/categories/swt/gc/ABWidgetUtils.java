/**
 * Copyright 1993-2009 Agree Tech.
 * All rights reserved.
 */
package net.olstudio.test.categories.swt.gc;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Region;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

/**
 * @author PuYun &lt;pu.yun@agree.com.cn&gt;
 * @version $Id$
 * 
 */
public class ABWidgetUtils
{
    public static class InheritImageTracker implements Listener
    {
        private Listener paintAndMessureListener;

        public InheritImageTracker(Control control,
                Listener paintAndMessureListener)
        {
            this.paintAndMessureListener = paintAndMessureListener;
            control.addListener(SWT.Paint, this);
            control.addListener(SWT.Resize, this);
            control.addListener(SWT.Dispose, this);
        }

        public void handleEvent(Event event)
        {
            Control control = (Control) event.widget;
            switch (event.type)
            {
                case SWT.Dispose:
                    control.removeListener(SWT.Paint, this);
                    control.removeListener(SWT.Dispose, this);
                    control.removeListener(SWT.Resize, this);
                    Image image = (Image) control.getData(DATA_INHERIT_CACHE);
                    if (image != null)
                    {
                        image.dispose();
                    }
                    break;
                case SWT.Paint:
                case SWT.Resize:
                    update(control);
                    break;
                default:
                    break;
            }
        }

        public void update(Control control)
        {
            // 0.1 check
            Point size = control.getSize();
            if (size.x == 0 || size.y == 0)
            {
                return;
            }
            if (!checkInheritInfoChanged(control))
            {
                return;
            }
            updateInheritImage(control, true);
        }
    }

    private static final String DATA_INHERIT_CACHE = InheritImageTracker.class
            .getName()
            + ".inheritCache";

    private static String DATA_INHERIT_INFO = ABWidgetUtils.class.getName()
            + ".inheritInfo";

    public static String DATA_INHERIT_TRACKER = ABWidgetUtils.class.getName()
            + ".inheritTracker";

    /**
     * 该时间用于获取绘制背景的参数，放在event.data中返回。
     */
    public static final int EVENTTYPE_MesureChanged = 1025;

    private static String INFO_ABSOLUTE_LOCATION = "absoluteLocation";

    private static String INFO_CONTROL = "control";

    private static String INFO_IMAGE = "image";

    private static String INFO_LOCATION = "location";

    private static String INFO_PARAMETER = "parameter";

    private static String INFO_SIZE = "size";

    /**
     * @param control
     *        Control
     * @param paintAndMesureListener
     *        处理事件类型包括{@link #EVENTTYPE_MesureChanged}和 @ SWT#Paint}
     */
    public static void bindInheritBackgroundImage(Control control,
            Listener paintAndMesureListener)
    {
        unbindInheritBackgroundImage(control);
        InheritImageTracker tracker = new InheritImageTracker(control,
                paintAndMesureListener);
        control.setData(DATA_INHERIT_TRACKER, tracker);
    }

    public static int blend(int v1, int v2, int ratio)
    {
        return (ratio * v1 + (100 - ratio) * v2) / 100;
    }

    public static RGB blend(RGB c1, RGB c2, int ratio)
    {
        int r = blend(c1.red, c2.red, ratio);
        int g = blend(c1.green, c2.green, ratio);
        int b = blend(c1.blue, c2.blue, ratio);
        return new RGB(r, g, b);
    }

    private static boolean checkInheritInfoChanged(Control control)
    {
        Map info = new HashMap(6);
        // 0. check
        InheritImageTracker tracker = (InheritImageTracker) control
                .getData(DATA_INHERIT_TRACKER);
        if (tracker == null)
        {
            return false;
        }
        // 1. inherit imange and control
        Control inheritControl = null;
        Image inheritImage = null;
        for (Control walker = control.getParent(); walker != null;)
        {
            inheritControl = findBackgroundControl(walker);
            if (inheritControl == null)
            {
                break;
            }
            if (checkInheritInfoChanged(inheritControl))
            {
                inheritImage = updateInheritImage(inheritControl, false);
            } else
            {
                // 首先尝试缓存，因为父亲可能没有更新实际背景
                inheritImage = (Image) inheritControl
                        .getData(DATA_INHERIT_CACHE);
                if (inheritImage == null)
                {
                    inheritImage = inheritControl.getBackgroundImage();
                }
            }
            if (inheritImage != null)
            {
                break;
            }
            if (inheritControl instanceof Shell)
            {
                walker = null;
            } else
            {
                walker = inheritControl.getParent();
            }
            inheritControl = null;
            inheritImage = null;
        }
        // 2. inherit location
        Point inheritLocation = null;
        if (inheritControl != null)
        {
            inheritLocation = inheritControl.toDisplay(0, 0);
            inheritLocation = control.toControl(inheritLocation);
        }
        // 3. inherit bounds
        Point absoluteLocation = control.toDisplay(0, 0);
        Point size = control.getSize();
        // 4. widget relevant data
        Event evt = new Event();
        evt.type = EVENTTYPE_MesureChanged;
        evt.widget = control;
        if (tracker.paintAndMessureListener != null)
        {
            tracker.paintAndMessureListener.handleEvent(evt);
        }
        Object parameter = evt.data == null ? "" : evt.data;
        // $. 整理
        if (inheritImage != null && evt.detail != 255)
        {
            info.put(INFO_CONTROL, inheritControl);
            info.put(INFO_IMAGE, new Integer(inheritImage.handle));
            info.put(INFO_LOCATION, inheritLocation);
            info.put(INFO_ABSOLUTE_LOCATION, absoluteLocation);
        }
        info.put(INFO_SIZE, size);
        info.put(INFO_PARAMETER, parameter);
        // $. 检查
        Map oldInfo = (Map) control.getData(DATA_INHERIT_INFO);
        if (oldInfo != null && oldInfo.equals(info))
        {
            return false;
        }
        control.setData(DATA_INHERIT_INFO, info);
        return true;
    }

    public static void decorateScrolledComposite(
            ScrolledComposite scrolledComposite)
    {
        scrolledComposite.setExpandHorizontal(true);
        scrolledComposite.setExpandVertical(true);
        scrolledComposite.getHorizontalBar().setIncrement(50);
        scrolledComposite.getVerticalBar().setIncrement(50);
        scrolledComposite.addListener(SWT.Resize, new Listener()
        {
            public void handleEvent(Event event)
            {
                ScrolledComposite sc = (ScrolledComposite) event.widget;
                Rectangle r = sc.getClientArea();
                sc.getHorizontalBar().setPageIncrement(r.width);
                sc.getVerticalBar().setPageIncrement(r.height);
            }
        });
        scrolledComposite.addListener(SWT.MouseWheel, new Listener()
        {
            public void handleEvent(Event event)
            {
                ScrolledComposite sc = (ScrolledComposite) event.widget;
                if (sc.getVerticalBar().isVisible())
                {
                    return;
                }
                for (Composite walker = sc.getParent(); walker != null
                        && walker != walker.getShell(); walker = walker
                        .getParent())
                {
                    if (walker instanceof ScrolledComposite)
                    {
                        ScrolledComposite scroll = (ScrolledComposite) walker;
                        if (scroll.getVerticalBar().isVisible())
                        {
                            int selection = scroll.getVerticalBar()
                                    .getSelection();
                            int delta = scroll.getVerticalBar().getIncrement();
                            int min = scroll.getVerticalBar().getMinimum();
                            int max = scroll.getVerticalBar().getMaximum();
                            selection -= delta * (event.count / 3);
                            selection = Math.min(max, selection);
                            selection = Math.max(min, selection);
                            if (selection != scroll.getVerticalBar()
                                    .getSelection())
                            {
                                scroll.getVerticalBar().setSelection(selection);
                                scroll.getVerticalBar().notifyListeners(
                                        SWT.Selection, null);
                            }
                            event.type = SWT.NONE;
                            return;
                        }
                    }
                }
            }
        });
    }

    public static void fillGradientLowerRoundRectangle(GC gc, int x, int y,
            int width, int height, int arcWidth, int arcHeight, boolean vertical)
    {
        int clipWidth = arcWidth / 2 - 1;
        int clipHeight = arcHeight / 2 - 1;
        // 1 clipping
        Rectangle oldClipping = gc.getClipping();
        Region r = new Region();
        gc.getClipping(r);
        // 1.3 left bottom
        r.subtract(new int[] { x, y + height, x, y + height - 1 - clipHeight,
                x + clipWidth, y + height });
        // 1.4 right bottom
        r.subtract(new int[] { x + width, y + height, x + width - clipWidth,
                y + height - 1, x + width, y + height - clipHeight - 1 });
        gc.setClipping(r);
        // 2 fill
        gc.fillGradientRectangle(x, y, width, height, vertical);
        // 3 reset
        r.dispose();
        gc.setClipping(oldClipping);
    }

    public static void fillGradientRoundRectangle(GC gc, int x, int y,
            int width, int height, int arcWidth, int arcHeight, boolean vertical)
    {
        int clipWidth = arcWidth / 2 - 1;
        int clipHeight = arcHeight / 2 - 1;
        // 1 clipping
        Rectangle oldClipping = gc.getClipping();
        Region r = new Region();
        gc.getClipping(r);
        // 1.1 left top
//        r.subtract(new int[] { x, y, x + clipWidth, y,((x+clipWidth)/3)*2,(y+clipHeight)/3,(x+clipWidth)/3,((y+clipHeight)/3)*2, x, y + clipHeight });
        r.subtract(new int[] { x, y, x + clipWidth, y,((x+clipWidth)/3)*2,(y+clipHeight)/3});
        r.subtract(new int[] { x, y,((x+clipWidth)/3)*2,(y+clipHeight)/3,(x+clipWidth)/3,((y+clipHeight)/3)*2});
        r.subtract(new int[] { x, y,(x+clipWidth)/3,((y+clipHeight)/3)*2, x, y + clipHeight});
        // 1.2 right top
        r.subtract(new int[] { x + width - clipHeight, y, x + width, y,
                x + width, y + clipHeight });
        // 1.3 left bottom
        r.subtract(new int[] { x, y + height, x, y + height - 1 - clipHeight,
                x + clipWidth, y + height });
        // 1.4 right bottom
        r.subtract(new int[] { x + width, y + height, x + width - clipWidth,
                y + height - 1, x + width, y + height - clipHeight - 1 });
        gc.setClipping(r);
        // 2 fill
        gc.fillGradientRectangle(x, y, width, height, vertical);
        // 3 reset
        r.dispose();
        gc.setClipping(oldClipping);

    }

    public static void fillGradientUpperRoundRectangle(GC gc, int x, int y,
            int width, int height, int arcWidth, int arcHeight, boolean vertical)
    {
        int clipWidth = arcWidth / 2 - 1;
        int clipHeight = arcHeight / 2 - 1;
        // 1 clipping
        Rectangle oldClipping = gc.getClipping();
        Region r = new Region();
        gc.getClipping(r);
        // 1.1 left top
        r.subtract(new int[] { x, y, x + clipWidth, y, x, y + clipHeight });
        // 1.2 right top
        r.subtract(new int[] { x + width - clipHeight, y, x + width, y,
                x + width, y + clipHeight });
        gc.setClipping(r);
        // 2 fill
        gc.fillGradientRectangle(x, y, width, height, vertical);
        // 3 reset
        r.dispose();
        gc.setClipping(oldClipping);
    };

    public static Control findBackgroundControl(Control control)
    {
        for (Control walker = control; walker != null;)
        {
            if (walker.getData(DATA_INHERIT_TRACKER) != null)
            {
                return walker;
            }
            Image image = getBackgroundImage(walker);
            if (image != null)
            {
                return walker;
            }
            if (walker instanceof Shell)
            {
                return null;
            }
            walker = walker.getParent();
        }
        try
        {
            Method m = Control.class.getDeclaredMethod("findBackgroundControl",
                    new Class[0]);
            m.setAccessible(true);
            return (Control) m.invoke(control, new Object[0]);
        } catch (Throwable e)
        {
            return null;
        }
    }

    public static Image getBackgroundImage(Control control)
    {
        // 跟踪控件
        InheritImageTracker tracker = (InheritImageTracker) control
                .getData(DATA_INHERIT_TRACKER);
        if (tracker != null)
        {
            if (checkInheritInfoChanged(control))
            {
                updateInheritImage(control, true);
            }
            return (Image) control.getData(DATA_INHERIT_CACHE);
        }

        // 一般控件
        try
        {
            Field f = Control.class.getDeclaredField("backgroundImage");
            f.setAccessible(true);
            return (Image) f.get(control);
        } catch (Throwable e)
        {
            return null;
        }
    }

    public static void unbindInheritBackgroundImage(Control control)
    {
        InheritImageTracker tracker = (InheritImageTracker) control
                .getData(DATA_INHERIT_TRACKER);
        if (tracker != null)
        {
            Event evt = new Event();
            evt.widget = control;
            evt.type = SWT.Dispose;
            tracker.handleEvent(evt);
        }
    }

    private static Image updateInheritImage(Control control, boolean set)
    {
        // 0.1 prepare
        InheritImageTracker tracker = (InheritImageTracker) control
                .getData(DATA_INHERIT_TRACKER);
        Map info = (Map) control.getData(DATA_INHERIT_INFO);
        Point inheritLocation = (Point) info.get(INFO_LOCATION);
        Point size = (Point) info.get(INFO_SIZE);
        if (size.x == 0 || size.y == 0)
        {
            return null;
        }
        Control inheritControl = (Control) info.get(INFO_CONTROL);
        Image inheritImage = null;
        if (inheritControl != null)
        {
            inheritImage = (Image) inheritControl.getData(DATA_INHERIT_CACHE);
            if (inheritImage == null)
            {
                inheritImage = inheritControl.getBackgroundImage();
            }
            if (inheritImage.handle != ((Integer) info.get(INFO_IMAGE)))
            {
                inheritImage = null;
            }
        }
        // 1. draw
        Image image = new Image(control.getDisplay(), size.x, size.y);
        GC gc = new GC(image);
        if (inheritImage != null)
        {
            for (int x = inheritLocation.x; x < size.x; x += inheritImage
                    .getBounds().width)
            {
                for (int y = inheritLocation.y; y < size.y; y += inheritImage
                        .getBounds().height)
                {
                    gc.drawImage(inheritImage, x, y);
                }
            }
        }
        Event evt = new Event();
        evt.display = control.getDisplay();
        evt.gc = gc;
        evt.widget = control;
        evt.type = SWT.Paint;
        if (tracker.paintAndMessureListener != null)
        {
            tracker.paintAndMessureListener.handleEvent(evt);
        }
        gc.dispose();
        Image oldImage = (Image) control.getData(DATA_INHERIT_CACHE);
        control.setData(DATA_INHERIT_CACHE, image);
        if (oldImage != null)
        {
            oldImage.dispose();
        }
        // $. set image
        if (set || oldImage == null)
        {
            control.setBackgroundImage(image);
        }
        return image;
    }

    private ABWidgetUtils()
    {

    }
}
