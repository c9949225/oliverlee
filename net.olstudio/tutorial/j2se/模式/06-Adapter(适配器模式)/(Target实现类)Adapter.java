// Adapterʵ��Target�ӿ�
public class Adapter implements Target {
	
	// AdapterҪ����Adaptee������
	private Adaptee pt;

	public Adapter() {
		pt = new Adaptee();
	}
	
	// ��Target�ӿڵķ����е���Adaptee�ķ���
	public long get2Power(long exp) {
		return pt.getPower(2, exp);
	}
}