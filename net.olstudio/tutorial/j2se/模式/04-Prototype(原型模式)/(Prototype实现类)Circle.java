public class Circle implements Prototype, Command {
	// 得到一个本实例的副本
	public Object clone() {
		Object clone = null;
		try {
			clone = super.clone();
		} catch (CloneNotSupportedException e) {
			System.err.println("Colone not support!");
		}
		return clone;
	}

	public String getName() {
		return "circle";
	}

	public void draw() {
		System.out.println("Draw a circle");
	}
}
