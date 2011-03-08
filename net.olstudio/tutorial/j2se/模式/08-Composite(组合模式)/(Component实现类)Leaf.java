
public class Leaf implements Component {
	
	private int size;
	
	private String name;

	public Leaf(String aName, int aSize) {
		size = aSize;
		name = aName;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return "Leaf";
	}

	public int getSize() {
		return size;
	}

	public int getChildNum() {
		return 1;
	}

	public void add(Component c) {
		System.err.println("Not supported method!");
	}

	public void remove(Component c) {
		System.err.println("Not supported method!");
	}
}