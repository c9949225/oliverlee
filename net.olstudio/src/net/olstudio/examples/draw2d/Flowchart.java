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
    Shell shell = new Shell(); // Shell ����һ������
    shell.setSize(200, 300);
    shell.open();
    shell.setText("Flowchart");
    /*LightweightSystem��Draw2d��ճ�ϼ���
    ������Ա�ṩSWT composite����Ҫ���Ƶ�figure��εĸ���Ȼ��LightweightSystem��Ĭ��ֵ����ʣ��Ĺ��̡�
    LightweightSystem����Canvas��������һ��EventDispatcher��һ��UpdateManager��
    LightweightSystem������һ���¼����������ܹ����������¼���
    �ڽ�Canvas���ݸ�LightweightSystem(ͨ��ͨ��LightweightSystem�Ĺ��캯��)ʱ��
    LightweightSystem�ͽ�������Ϊ������ע�ᵽCanvas����Canvas��������LightweightSystem����Ȥ���¼���
    ��Ϊ��������LightweightSystem�ж���ķ����ᱻ���á�
    ��LightweightSystem�ж���ĸ��������������У�Ҫ��������SWT���¼�ת����draw2d�¼���ͨ��EventDispatcher��draw2d�¼����䵽��ǰ��ѡ�е�ͼ��Ԫ��Figure��
    ÿ��ͼ��Ԫ��Figure��������Ϊ������������*/
    LightweightSystem lws = new LightweightSystem(shell);
    
    
    ChartFigure flowchart = new ChartFigure();// ֧������ĵײ�����
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
    // ����һ��IFigure����ʹͼ��֧����ק
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
// FreeformLayeredPaneʹ�ô��������ⷽ������
class ChartFigure extends FreeformLayeredPane {
  public ChartFigure() {
    setLayoutManager(new FreeformLayout());
    setBorder(new MarginBorder(5));
    setBackgroundColor(ColorConstants.white);
    setOpaque(true);
  }
}

// MouseMotionListener.Stubʵ��MouseListener,֧������϶�
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
    // 1,2��ֵ����draw2d�������Ķ����Լ��������
    //  int x = r.x + place.x * r.width / 2;
    inAnchor.place = new Point(1, 0);// �����ڵ�ͼ���Ǹ�����,0�����,1���е�,2���ұ�
    targetAnchors.put("in_term", inAnchor);
    
    outAnchor = new FixedAnchor(this);
    // int y = r.y + place.y * r.height / 2;
    outAnchor.place = new Point(1, 2);// ��ô1,2 �ͱ�ʾ ��x����е�,y��ĵײ�
    sourceAnchors.put("out_term", outAnchor);
  }
  
  // �Լ�������һ�� Բ�Ǿ���
  public void paintFigure(Graphics g) {
    Rectangle r = bounds; // ���α߽�
//    x - the x coordinate
//    y - the y coordinate
//    w - the width
//    h - the height
//    offset - ��ʼ�ĽǶ�,180�������Ǹ���Բ,90�����˽Ƕ�,90���ǻ�������ͻ��,270���ǻ�������ͻ��,180��������ͻ��,360��������ͻ��
//    length - Բ�Ķ���,90��1/4Բ,180�ǰ�Բ,360��ȫԲ
    g.drawArc(r.x + r.width / 8, r.y, r.width / 4, r.height - 1, 90, 180);// ��߅�ĈA
    g.drawLine(r.x + r.width / 4, r.y, r.x + 3 * r.width / 4, r.y);// �ϱߵ���
    g.drawLine(r.x + r.width / 4, r.y + r.height - 1,r.x + 3 * r.width / 4, r.y + r.height - 1); // �±ߵ���
    g.drawArc(r.x + 5 * r.width / 8, r.y, r.width / 4, r.height - 1, 270,180); // ��߅�ĈA
    g.drawText(message, r.x + 3 * r.width / 8, r.y + r.height / 8);
  }
}

// ����һ���̶�������ê��
class FixedAnchor extends AbstractConnectionAnchor {
  Point place;

  public FixedAnchor(IFigure owner) {
    super(owner);
  }
  
  // ���ݸ��ŵľ��εĴ�С��place�ĵ�ֵ,������ê���λ��
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

  Hashtable sourceAnchors = new Hashtable();// ����ͷ���յ�ê��

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