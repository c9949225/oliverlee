
// װ�����;������ʵ����ͬ�Ľӿ�
public class Decorator implements Component {
	
	private Component component;// ���б����εĶ���ʵ�����������ݴӸ�ʵ��ȡ��

	public Decorator(Component c) {
		component = c;
	}

	public void setName(String aName) {
		component.setName(aName);
	}

	public String getName() {
		return "Name :" + component.getName();
	}
}