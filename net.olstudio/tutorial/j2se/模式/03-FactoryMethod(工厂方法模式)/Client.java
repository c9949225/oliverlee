/*
 * ��һ����===��֪��====�������봴����������һ����ϣ����������ָ�����������Ķ���ʱ��
 * ����ʹ�ù���������
 */
public class Client {
	public static void main(String[] args) {
		// ����Nokia����
		MobileFactory mbf = new NokiaFactory();
		// ����Nokia�ֻ�
		Mobile mb = mbf.produceMobile();
		mb.call();
		// ����Motorola����
		mbf = new MotorolaFactory();
		// ����Motorola�ֻ�
		mb = mbf.produceMobile();
		mb.call();
	}
}