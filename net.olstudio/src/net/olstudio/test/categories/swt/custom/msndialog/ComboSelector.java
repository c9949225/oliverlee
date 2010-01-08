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
		setMenu(selectorMenu);// ���ÿؼ��ĵ����˵�
		
		// ���»س�����Ч
		inputText.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				commit();
			}
		});
		
		// Control�����Լ��ķ��������ؼ���Ҫ�����Ƶ�ʱ�򣬸÷���������
		// ֻҪ���п϶��ᱻ����
		addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				GC gc = e.gc;
				// �趨�ؼ��߿���ɫ
				gc.setForeground(isEnabled() ? ENABLED_LINE_COLOR : DISABLED_LINE_COLOR);
				// �ڸÿؼ��Ĵ�С�����ϻ�����
				gc.drawRectangle(0, 0, getSize().x - 1, getSize().y - 1);
				// ��ָ������λ�û���������Ҳ��С����,x,y����
				// x: �����ұ�����5����
				// y: ��ֱ�߶ȵ����м�
				gc.drawImage(COMBO_ICON, getSize().x - COMBO_ICON.getBounds().width - 5,
						(getSize().y - COMBO_ICON.getBounds().height) / 2);
			}
		});
		
		// �ؼ��ƶ����߱任��С������
		addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				// ����������λ�ü���С
				// ��Զ������ڸ�������1,1λ��
				inputText.setBounds(1, 1, getSize().x
						- COMBO_ICON.getBounds().width - 15, getSize().y - 2);
			}
		});
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				// �������ڼ�ͷλ�õ��������ʾ���˵��б�
				if (e.x > getBounds().width - COMBO_ICON.getBounds().width - 15
						&& e.x < getBounds().width && e.y > 0
						&& e.y < getBounds().height) {
					// ���õ����˵�λ��
					// �ؼ�λ������3����
					// �ؼ�λ������23����
					selectorMenu.setLocation(getScreenLocation().x + 3,getScreenLocation().y + getSize().y + 30);
					// ��ʾ�����˵�
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
	// ѡ��ĳ���б���
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
	
	// �õ���ǰ�ؼ������������Ļ��λ��
	private Point getScreenLocation() {
		Control control = this;
		// 0. �õ����ؼ���С
		int width = control.getLocation().x;
		int height = control.getLocation().y;
		// 1. �ϲ����������е�����ƫ��
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
