public class Rectangle implements Prototype, Command {
	
	// 得到一个本实例的副本
	public Object clone() {
		Object clone = null;
		try {
			clone = super.clone();
		} catch (CloneNotSupportedException e) {
			System.err.println("Clone not supported!");
		}
		return clone;
	}

	public String getName() {
		return "Rectangle";
	}

	public void draw() {
		System.out.println("Draw a rectangle");
	}
}