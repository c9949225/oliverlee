/*
 * 结论：
 * 
 * 生成器模式使得客户可以更加"精细的控制一个对象的构建"，
 * 
 * 每个具体的builder包含了创建和装配一个特定产品的所有代码，
 * 
 * 这些代码只用写一次，便可以被不同的Director使用，从而可以装配出不同的对象。
 * 
 * 用不同方式实现同样的接口来生成不同的对象
 */
public class Client {
	public static void description(Animal animal) {
		String desc = "This animal is called " + animal.getName();
		desc += "\n Its meat is " + animal.getMeat();
		desc += "\n And it has " + animal.getLegs() + " legs.";
		System.out.println(desc);
	}

	public static void main(String[] args) {
		String aName = "chicken";
		Director d = new Director();
		Animal animal = d.getAnimal(aName);
		description(animal);
		aName = "pig";
		animal = d.getAnimal(aName);
		description(animal);
	}
}