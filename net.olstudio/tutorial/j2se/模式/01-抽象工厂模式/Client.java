/*
 * С�᣺���󹤳�ģʽ�ر��ʺ���������һ�ֲ�Ʒ�ṹ��
 * 
 * ��Ʒ��Ϊ����ϵ�У���ÿ��ϵ���У���Ʒ�Ĳ��ֶ���Ҫͬ�ģ�
 * ��һ��ϵ����ĳ��λ�õĲ�Ʒ������һ��ϵ����һ����һ����Ӧ�Ĳ�Ʒ��
 * 
 * �����Ĳ�Ʒ�ṹ�Ǵ��ڵģ��⼸��ϵ����ͬһλ�õĲ�Ʒ�����ǻ���ģ���������Բ�ͬ�ͻ��Ľ��������ÿ���ͻ���ֻ����һ��
 * 
 * �ŵ㣺�������κ�ʱ����Ҫĳ�ֲ�Ʒ��ֻ���򹤳����󼴿ɡ������������޸ľͿ��Խ����²�Ʒ��
 * ȱ�㣺�������Ʒ�޸�ʱ����Ӧ�Ĺ�����ҲҪ����Ӧ���޸ġ�
 * 
 * �ڱ���������3�����幤��
 * 
 * AKetchen�� ţ�̺�����
 * BKetchen�� ����͵�
 * CKetchen�� ��Ͳ���
 * 
 */
public class Client {
	public void eat(KetchenFactory k) {
		System.out.println("A person eat " + k.getFood().getEatable()
				+ " with " + k.getTableWare().getTool() + "!");
	}

	public static void main(String[] args) {
		Client client = new Client();
		// ��ͬ�Ĺ���ʵ���߻�������ͬ������
		// A
		KetchenFactory kf = new AKetchen();
		client.eat(kf);
		// B
		kf = new BKetchen();
		client.eat(kf);
		// C
		kf = new CKetchen();
		client.eat(kf);
	}
}