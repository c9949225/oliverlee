package net.olstudio.examples.draw2d;

import java.util.Enumeration;
import java.util.Hashtable;

import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayeredPane;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.ManhattanConnectionRouter;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Flowchart {
  public static void main(String args[]) {
    Shell shell = new Shell(); // Shell 代表一个窗口
    shell.setSize(200, 300);
    shell.open();
    shell.setText("Flowchart");
    /*LightweightSystem是Draw2d的粘合剂。
    开发人员提供SWT composite和想要绘制的figure层次的根，然后LightweightSystem用默认值启动剩余的过程。
    LightweightSystem依赖Canvas，并含有一个EventDispatcher和一个UpdateManager。
    LightweightSystem本身是一个事件监听器，能够监听多种事件。
    在将Canvas传递给LightweightSystem(通常通过LightweightSystem的构造函数)时，
    LightweightSystem就将自身作为监听器注册到Canvas，当Canvas产生各种LightweightSystem感兴趣的事件后，
    作为监听器，LightweightSystem中定义的方法会被调用。
    在LightweightSystem中定义的各个监听器方法中，要将来自于SWT的事件转换成draw2d事件并通过EventDispatcher将draw2d事件分配到当前被选中的图形元素Figure。
    每个图形元素Figure都可以作为监听器容器。*/
    LightweightSystem lws = new LightweightSystem(shell);
    
    
    ChartFigure flowchart = new ChartFigure();// 支持延伸的底层容器
    lws.setContents(flowchart);

    TerminatorFigure start = new TerminatorFigure();
    start.setName("Start");
    start.setBounds(new Rectangle(40, 20, 80, 20));
    
    DecisionFigure dec = new DecisionFigure();
    dec.setName("Should I?");
    dec.setBounds(new Rectangle(30, 60, 100, 60));
    
    ProcessFigure proc = new ProcessFigure();
    proc.setName("Do it!");
    proc.setBounds(new Rectangle(40, 140, 80, 40));
    
    TerminatorFigure stop = new TerminatorFigure();
    stop.setName("End");
    stop.setBounds(new Rectangle(40, 200, 80, 20));

    PathFigure path1 = new PathFigure();
    path1.setSourceAnchor(start.outAnchor);
    path1.setTargetAnchor(dec.inAnchor);
    PathFigure path2 = new PathFigure();
    path2.setSourceAnchor(dec.yesAnchor);
    path2.setTargetAnchor(proc.inAnchor);
    PathFigure path3 = new PathFigure();
    path3.setSourceAnchor(dec.noAnchor);
    path3.setTargetAnchor(stop.inAnchor);
    PathFigure path4 = new PathFigure();
    path4.setSourceAnchor(proc.outAnchor);
    path4.setTargetAnchor(stop.inAnchor);

    flowchart.add(start);
    flowchart.add(dec);
    flowchart.add(proc);
    flowchart.add(stop);
    flowchart.add(path1);
    flowchart.add(path2);
    flowchart.add(path3);
    flowchart.add(path4);
    // 传入一个IFigure即可使图形支持拖拽
    new Dnd(start);
    new Dnd(proc);
    new Dnd(dec);
    new Dnd(stop);

    Display display = Display.getDefault();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch())
        display.sleep();
    }
  }
}
// FreeformLayeredPane使得窗口向任意方向延伸
class ChartFigure extends FreeformLayeredPane {
  public ChartFigure() {
    setLayoutManager(new FreeformLayout());
    setBorder(new MarginBorder(5));
    setBackgroundColor(ColorConstants.white);
    setOpaque(true);
  }
}

// MouseMotionListener.Stub实现MouseListener,支持鼠标拖动
class Dnd extends MouseMotionListener.Stub implements MouseListener {
  public Dnd(IFigure figure) {
    figure.addMouseMotionListener(this);
    figure.addMouseListener(this);
  }

  Point start;

  public void mouseReleased(MouseEvent e) {
  }

  public void mouseClicked(MouseEvent e) {
  }

  public void mouseDoubleClicked(MouseEvent e) {
  }

  public void mousePressed(MouseEvent e) {
    start = e.getLocation();
  }

  public void mouseDragged(MouseEvent e) {
    Point p = e.getLocation();
    Dimension d = p.getDifference(start);
    start = p;
    Figure f = ((Figure) e.getSource());
    f.setBounds(f.getBounds().getTranslated(d.width, d.height));
  }
}

class TerminatorFigure extends ActivityFigure {
  FixedAnchor inAnchor, outAnchor;

  public TerminatorFigure() {
	  
    inAnchor = new FixedAnchor(this);
    // 1,2的值不是draw2d给出来的而是自己算出来的
    //  int x = r.x + place.x * r.width / 2;
    inAnchor.place = new Point(1, 0);// 整个节点图形是个矩形,0是左边,1是中点,2是右边
    targetAnchors.put("in_term", inAnchor);
    
    outAnchor = new FixedAnchor(this);
    // int y = r.y + place.y * r.height / 2;
    outAnchor.place = new Point(1, 2);// 那么1,2 就表示 在x轴的中点,y轴的底部
    sourceAnchors.put("out_term", outAnchor);
  }
  
  // 自己画出来一个 圆角矩形
  public void paintFigure(Graphics g) {
    Rectangle r = bounds; // 矩形边界
//    x - the x coordinate
//    y - the y coordinate
//    w - the width
//    h - the height
//    offset - 开始的角度,180决定了是个半圆,90决定了角度,90就是弧度向左突起,270就是弧度向右突起,180就是向下突起,360就是向上突起
//    length - 圆的多少,90是1/4圆,180是半圆,360是全圆
    g.drawArc(r.x + r.width / 8, r.y, r.width / 4, r.height - 1, 90, 180);// 左邊的圓
    g.drawLine(r.x + r.width / 4, r.y, r.x + 3 * r.width / 4, r.y);// 上边的线
    g.drawLine(r.x + r.width / 4, r.y + r.height - 1,r.x + 3 * r.width / 4, r.y + r.height - 1); // 下边的线
    g.drawArc(r.x + 5 * r.width / 8, r.y, r.width / 4, r.height - 1, 270,180); // 右邊的圓
    g.drawText(message, r.x + 3 * r.width / 8, r.y + r.height / 8);
  }
}

// 代表一个固定的链接锚点
class FixedAnchor extends AbstractConnectionAnchor {
  Point place;

  public FixedAnchor(IFigure owner) {
    super(owner);
  }
  
  // 根据附着的矩形的大小和place的点值,来计算锚点的位置
  public Point getLocation(Point loc) {
    Rectangle r = getOwner().getBounds();
    int x = r.x + place.x * r.width / 2;
    int y = r.y + place.y * r.height / 2;
    Point p = new PrecisionPoint(x, y);
    getOwner().translateToAbsolute(p);
    return p;
  }
}

class DecisionFigure extends ActivityFigure {
  FixedAnchor inAnchor, yesAnchor, noAnchor;

  public DecisionFigure() {
    inAnchor = new FixedAnchor(this);
    inAnchor.place = new Point(1, 0);
    targetAnchors.put("in_dec", inAnchor);
    noAnchor = new FixedAnchor(this);
    noAnchor.place = new Point(2, 1);
    sourceAnchors.put("no", noAnchor);
    yesAnchor = new FixedAnchor(this);
    yesAnchor.place = new Point(1, 2);
    sourceAnchors.put("yes", yesAnchor);
  }

  public void paintFigure(Graphics g) {
    Rectangle r = bounds;
    PointList pl = new PointList(4);
    pl.addPoint(r.x + r.width / 2, r.y);
    pl.addPoint(r.x, r.y + r.height / 2);
    pl.addPoint(r.x + r.width / 2, r.y + r.height - 1);
    pl.addPoint(r.x + r.width, r.y + r.height / 2);
    g.drawPolygon(pl);
    g.drawText(message, r.x + r.width / 4 + 5, r.y + 3 * r.height / 8);
    g.drawText("N", r.x + 7 * r.width / 8, r.y + 3 * r.height / 8);
    g.drawText("Y", r.x + r.width / 2 - 2, r.y + 3 * r.height / 4);
  }
}

class PathFigure extends PolylineConnection {
  public PathFigure() {
    setTargetDecoration(new PolylineDecoration());
    setConnectionRouter(new ManhattanConnectionRouter());
  }
}

class ProcessFigure extends ActivityFigure {
  FixedAnchor inAnchor, outAnchor;

  public ProcessFigure() {
    inAnchor = new FixedAnchor(this);
    inAnchor.place = new Point(1, 0);
    targetAnchors.put("in_proc", inAnchor);
    outAnchor = new FixedAnchor(this);
    outAnchor.place = new Point(1, 2);
    sourceAnchors.put("out_proc", outAnchor);
  }
  
  @Override
  public void paintFigure(Graphics g) {
    Rectangle r = bounds;
    g.drawText(message, r.x + r.width / 4, r.y + r.height / 4);
    g.drawRectangle(r.x, r.y, r.width - 1, r.height - 1);
  }
}

abstract class ActivityFigure extends Figure {
  Rectangle r = new Rectangle();

  Hashtable targetAnchors = new Hashtable();

  Hashtable sourceAnchors = new Hashtable();// 带箭头的终点锚点

  String message = new String();

  public void setName(String msg) {
    message = msg;
    repaint();
  }

  public ConnectionAnchor ConnectionAnchorAt(Point p) {
    ConnectionAnchor closest = null;
    long min = Long.MAX_VALUE;
    Hashtable conn = getSourceConnectionAnchors();
    conn.putAll(getTargetConnectionAnchors());
    Enumeration e = conn.elements();
    while (e.hasMoreElements()) {
      ConnectionAnchor c = (ConnectionAnchor) e.nextElement();
      Point p2 = c.getLocation(null);
      long d = p.getDistance2(p2);
      if (d < min) {
        min = d;
        closest = c;
      }
    }
    return closest;
  }

  public ConnectionAnchor getSourceConnectionAnchor(String name) {
    return (ConnectionAnchor) sourceAnchors.get(name);
  }

  public ConnectionAnchor getTargetConnectionAnchor(String name) {
    return (ConnectionAnchor) targetAnchors.get(name);
  }

  public String getSourceAnchorName(ConnectionAnchor c) {
    Enumeration e = sourceAnchors.keys();
    String name;
    while (e.hasMoreElements()) {
      name = (String) e.nextElement();
      if (sourceAnchors.get(name).equals(c))
        return name;
    }
    return null;
  }

  public String getTargetAnchorName(ConnectionAnchor c) {
    Enumeration e = targetAnchors.keys();
    String name;
    while (e.hasMoreElements()) {
      name = (String) e.nextElement();
      if (targetAnchors.get(name).equals(c))
        return name;
    }
    return null;
  }

  public ConnectionAnchor getSourceConnectionAnchorAt(Point p) {
    ConnectionAnchor closest = null;
    long min = Long.MAX_VALUE;
    Enumeration e = getSourceConnectionAnchors().elements();
    while (e.hasMoreElements()) {
      ConnectionAnchor c = (ConnectionAnchor) e.nextElement();
      Point p2 = c.getLocation(null);
      long d = p.getDistance2(p2);
      if (d < min) {
        min = d;
        closest = c;
      }
    }
    return closest;
  }

  public Hashtable getSourceConnectionAnchors() {
    return sourceAnchors;
  }

  public ConnectionAnchor getTargetConnectionAnchorAt(Point p) {
    ConnectionAnchor closest = null;
    long min = Long.MAX_VALUE;
    Enumeration e = getTargetConnectionAnchors().elements();
    while (e.hasMoreElements()) {
      ConnectionAnchor c = (ConnectionAnchor) e.nextElement();
      Point p2 = c.getLocation(null);
      long d = p.getDistance2(p2);
      if (d < min) {
        min = d;
        closest = c;
      }
    }
    return closest;
  }

  public Hashtable getTargetConnectionAnchors() {
    return targetAnchors;
  }
}

class FigureFactory {
  public static IFigure createTerminatorFigure() {
    return new TerminatorFigure();
  }

  public static IFigure createDecisionFigure() {
    return new DecisionFigure();
  }

  public static IFigure createProcessFigure() {
    return new ProcessFigure();
  }

  public static PathFigure createPathFigure() {
    return new PathFigure();
  }

  public static ChartFigure createChartFigure() {
    return new ChartFigure();
  }
}