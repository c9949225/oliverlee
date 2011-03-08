/*
 * 结论：
 * 
 * Bridge模式有效的使抽象类中一个具体方法的实现可以独立变化，
 * 与工厂方法不同的是，其具体的方法实现可以被共享。
 * 
 */

public class Client {
	public static void main(String[] args) {
		
		// 继承抽象类,持有ImageImp的实现类对象
		// bmp持有不同的对象就有不同的行为
		BMPImage bmp = new BMPImage(); 
		
		// ImageImp的实现类
		ImageImp winimp = new WinImp();
		
		// 1, 传入一种类型
		bmp.setImageImp(winimp);
		bmp.method("Paint ==>");
		
		ImageImp uniximp = new UnixImp();
		
		// 2, 传入另外一种类型
		bmp.setImageImp(uniximp);
		bmp.method("Paint ==>");
	}
}