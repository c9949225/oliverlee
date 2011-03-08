/*
 * �ܽ᣺
 * 
 * ����ģʽʹ��Ӧ�ó���������ʱ����ֻ����һ��ʵ������һЩ���Ӧ�ó����У�
 * ������ֻ��Ҫ��һ���������Ҫʹ�õ���ģʽ
 * 
 */
public class Singleton {
	
	// Ҫ˽�еľ�̬����
	private static Singleton instance;
	
	private Singleton() {
		generator = new Random();
	}

	public void setSeed(int seed) {
		generator.setSeed(seed);
	}

	public int nextInt() {
		return generator.nextInt();
	}

	public static synchronized Singleton getInstance() {
		if (instance == null) {
			instance = new Singleton();
		}
		return instance;
	}

	private Random generator;
}
