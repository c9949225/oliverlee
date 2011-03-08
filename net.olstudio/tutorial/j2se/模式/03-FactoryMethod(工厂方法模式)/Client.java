/*
 * 当一个类===不知道====它所必须创建对象的类或一个类希望由子类来指定它所创建的对象时，
 * 可以使用工厂方法。
 */
public class Client {
	public static void main(String[] args) {
		// 创建Nokia工厂
		MobileFactory mbf = new NokiaFactory();
		// 生产Nokia手机
		Mobile mb = mbf.produceMobile();
		mb.call();
		// 创建Motorola工厂
		mbf = new MotorolaFactory();
		// 生成Motorola手机
		mb = mbf.produceMobile();
		mb.call();
	}
}