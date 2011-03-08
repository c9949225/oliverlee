public class Circle implements Prototype, Command {
	// �õ�һ����ʵ���ĸ���
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
