// Adapter实现Target接口
public class Adapter implements Target {
	
	// Adapter要持有Adaptee的引用
	private Adaptee pt;

	public Adapter() {
		pt = new Adaptee();
	}
	
	// 在Target接口的方法中调用Adaptee的方法
	public long get2Power(long exp) {
		return pt.getPower(2, exp);
	}
}