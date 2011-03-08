/*
 * 小结：抽象工厂模式特别适合于这样的一种产品结构：
 * 
 * 产品分为几个系列，在每个系列中，产品的布局都是要同的，
 * 在一个系列中某个位置的产品，在另一个系列中一定有一个对应的产品。
 * 
 * 这样的产品结构是存在的，这几个系列中同一位置的产品可能是互斥的，它们是针对不同客户的解决方案，每个客户都只择其一。
 * 
 * 优点：消费者任何时候需要某种产品，只需向工厂请求即可。消费者无须修改就可以接纳新产品。
 * 缺点：当具体产品修改时，相应的工厂类也要做相应的修改。
 * 
 * 在本例子中有3个具体工厂
 * 
 * AKetchen， 牛奶和汤匙
 * BKetchen， 面包和刀
 * CKetchen， 肉和叉子
 * 
 */
public class Client {
	public void eat(KetchenFactory k) {
		System.out.println("A person eat " + k.getFood().getEatable()
				+ " with " + k.getTableWare().getTool() + "!");
	}

	public static void main(String[] args) {
		Client client = new Client();
		// 不同的工厂实现者会生产不同的事物
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