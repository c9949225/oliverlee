public class Rectangle implements Prototype, Command {
	
	// �õ�һ����ʵ���ĸ���
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