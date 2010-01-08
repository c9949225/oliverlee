package net.olstudio.test.categories.swt.custom.msndialog;

import java.util.Vector;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;

/**
 * A SWT ComboBox component with custom style
 * 
 * @author liuyitong
 * 
 */
public class ComboSelector extends Composite implements SelectionListener {
	
	private final Color ENABLED_LINE_COLOR = new Color(Display.getCurrent(),
			170, 183, 199);

	private final Color DISABLED_LINE_COLOR = new Color(Display.getCurrent(),
			208, 215, 229);

	private final Color ENABLED_BG = new Color(Display.getCurrent(), 254, 254,
			254);

	private final Color DISABLED_BG = new Color(Display.getCurrent(), 238, 241,
			249);

	private final Image COMBO_ICON = new Image(Display.getDefault(),
			"combo.png");

	private Text inputText;

	private Menu selectorMenu;

	private Object selectedItem;

	private Vector<Object> dataSet = new Vector<Object>();

	/**
	 * construct a instance
	 * 
	 * @param parent
	 */
	public ComboSelector(Composite parent) {
		super(parent, SWT.FLAT);
		inputText = new Text(this, SWT.FLAT);
		selectorMenu = new Menu(this);
		setMenu(selectorMenu);// 设置控件的弹出菜单
		
		// 按下回车后生效
		inputText.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				commit();
			}
		});
		
		// Control本身自己的方法，当控件需要被绘制的时候，该方法被调用
		// 只要运行肯定会被调用
		addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				GC gc = e.gc;
				// 设定控件边框颜色
				gc.setForeground(isEnabled() ? ENABLED_LINE_COLOR : DISABLED_LINE_COLOR);
				// 在该控件的大小基础上画矩形
				gc.drawRectangle(0, 0, getSize().x - 1, getSize().y - 1);
				// 在指定坐标位置画输入框内右侧的小三角,x,y坐标
				// x: 从最右边往左5像素
				// y: 垂直高度的最中间
				gc.drawImage(COMBO_ICON, getSize().x - COMBO_ICON.getBounds().width - 5,
						(getSize().y - COMBO_ICON.getBounds().height) / 2);
			}
		});
		
		// 控件移动或者变换大小监听器
		addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				// 设置输入框的位置及大小
				// 永远是相对于父容器的1,1位置
				inputText.setBounds(1, 1, getSize().x
						- COMBO_ICON.getBounds().width - 15, getSize().y - 2);
			}
		});
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				// 如果鼠标在箭头位置点击，则显示出菜单列表
				if (e.x > getBounds().width - COMBO_ICON.getBounds().width - 15
						&& e.x < getBounds().width && e.y > 0
						&& e.y < getBounds().height) {
					// 设置弹出菜单位置
					// 控件位置向右3像素
					// 控件位置向下23像素
					selectorMenu.setLocation(getScreenLocation().x + 3,getScreenLocation().y + getSize().y + 30);
					// 显示弹出菜单
					selectorMenu.setVisible(true);
				}
			}
		});
		setEnabled(true);
	}

	/**
	 * load values
	 * 
	 * @param objects
	 *            a array of value.you must overridden each of element's
	 *            toString() method to display
	 * 
	 */
	public void loadMenuItems(Object[] objects) {
		dataSet.clear();
		MenuItem[] items = selectorMenu.getItems();
		for (MenuItem item : items) {
			item.removeSelectionListener(this);
			item.dispose();
		}
		for (int i = 0; i < objects.length; i++) {
			dataSet.add(objects[i]);
			MenuItem item = new MenuItem(selectorMenu, SWT.PUSH);
			item.setText(objects[i].toString());
			item.setData(objects[i]);
			item.addSelectionListener(this);
		}
	}
	/**
	 * enable or disable the component
	 */
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		setBackground(enabled ? ENABLED_BG : DISABLED_BG);
		inputText.setEnabled(enabled);
		redraw();
	}

	/**
	 * set if or not the textfield can be editable
	 * 
	 * @param editable
	 */
	public void setEditable(boolean editable) {
		inputText.setEditable(editable);
	}

	/**
	 * select the object of index count and set the text of select object to
	 * text field
	 * 
	 * @param index
	 *            line number
	 */
	// 选择某个列表项
	public void select(int index) {
		MenuItem[] items = selectorMenu.getItems();
		if (index < 0 || index >= items.length) {
			throw new ArrayIndexOutOfBoundsException(
					"the index value must between " + 0 + "and "
							+ (items.length - 1));
		}
		selectedItem = items[index].getData();
		inputText.setText(items[index].getText());
	}

	/**
	 * get the current select object
	 * 
	 * @return current select object,if none of object is selected or there is
	 *         no data in the combox,return null
	 */
	public Object getSelectedItem() {
		return selectedItem;
	}

	/**
	 * todo your custom behaver by overridden this method in sub class when
	 * click Enter key at text field
	 * 
	 */
	protected void commit() {
	};

	/**
	 * todo your custom behaver when select one item
	 * 
	 * @param object
	 *            a item which you select
	 */
	protected void selected(Object object) {
	};


	/**
	 * add a item
	 * 
	 * @param object
	 *            the item you added.you must overridden the toString() method
	 *            of object to display
	 */
	public void addItem(Object object) {
		dataSet.addElement(object);
		loadMenuItems(dataSet.toArray());
	}

	/**
	 * remove a item
	 * 
	 * @param object
	 *            the item you removed
	 */
	public void delItem(Object object) {
		dataSet.remove(object);
		loadMenuItems(dataSet.toArray());
	}

	/**
	 * get the text in text field
	 * 
	 * @return the text in text field
	 */
	public String getText() {
		return inputText.getText();
	}

	/**
	 * set the text in text field
	 * 
	 * @param text
	 *            the text which you want to set in the text field
	 */
	public void setText(String text) {
		inputText.setText(text);
	}

	/**
	 * Sets the maximum number of characters which you may input in the text
	 * field
	 * 
	 * @param limit
	 *            new text limit
	 */
	public void setTextLimit(int limit) {
		inputText.setTextLimit(limit);
	}
	
	// 得到当前控件相对于整个屏幕的位置
	private Point getScreenLocation() {
		Control control = this;
		// 0. 得到本控件大小
		int width = control.getLocation().x;
		int height = control.getLocation().y;
		// 1. 合并父容器所有的坐标偏移
		while (control.getParent() != null) {
			control = control.getParent();
			width += control.getLocation().x;
			height += control.getLocation().y;
		}
		return new Point(width, height);
	}

	@Override
	public void dispose() {
		ENABLED_LINE_COLOR.dispose();
		DISABLED_LINE_COLOR.dispose();
		ENABLED_BG.dispose();
		DISABLED_BG.dispose();
		COMBO_ICON.dispose();

		super.dispose();
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public final void widgetDefaultSelected(SelectionEvent e) {

	}

	public final void widgetSelected(SelectionEvent e) {
		MenuItem item = (MenuItem) e.getSource();
		selectedItem = item.getData();
		String text = item.getData().toString();
		inputText.setText(text);
		inputText.setSelection(0, text.length());
		selected(item.getData());
	}
}
