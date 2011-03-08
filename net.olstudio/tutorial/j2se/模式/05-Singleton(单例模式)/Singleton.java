/*
 * 总结：
 * 
 * 单例模式使得应用程序在运行时保持只能有一个实例，在一些大的应用程序中，
 * 主程序只需要有一个，因此需要使用单例模式
 * 
 */
public class Singleton {
	
	// 要私有的静态变量
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
