/*
 * 
 * �ܽ᣺ԭ��ģʽ�����ɸ��Ӷ���ȽϿ��ѵĻ����бȽ����ã�
 * ͨ����¡���ж�����ʵ�ִ����µĶ��󣬽�ʡ��ʱ��Ϳռ䡣
 * 
 */
public class Client {
	public static void main(String[] args) {
		Toolbar tb = new Toolbar();
		Command c = (Command) tb.getClone("circle");
		c.draw();
		c = (Command) tb.getClone("rectangle");
		c.draw();
	}
}